
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.text.*;
import java.io.File;
//author: Tanisha Nalavadi, Melissa Leal
public class display extends Application {
    public static final String TITLE = "BRICK BREAKER !!!";
    public static final String SPLASH_SCREEN1 = "splashscreen1.gif";
    public static final String SPLASH_SCREEN2 = "splashscreen2.gif";
    public static final String PADDLE_IMAGE = "paddle.gif";
    public static final int SCREEN_SIZE_WIDTH = 840;
    public static final int SCREEN_SIZE_HEIGHT = 800;
    public static final int FRAMES_PER_SECOND = 60;
    public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    public static final Paint BACKGROUND = Color.LIGHTBLUE;
    private Paddle myPaddle;
    private Bouncer myBouncer;
    private Rules myRules = new Rules();
    public static Group myInitialRoot;
    public static Group myGameRoot;
    private static int PADDLE_SPEED = 15;
    private HighScore myHigh= new HighScore();

    private StatusDisplay Status = new StatusDisplay();
    //private TesterModes Modes = new TesterModes();
    private Stage myStage;
    private Brick[][] myBrickArray;
    private File myHighScoreFile= new File("Resources/HighScoreTracker");
    private Timeline myAnimation;







    /**
     * Initialize what will be displayed and how it will be updated.
     */
    @Override

    public void start (Stage stage) {
        myStage = stage;
        setStartingStage(myStage, "splash-1");
        myAnimation= new Timeline();

    }


    private void setStartingStage(Stage stage, String type) {
        myInitialRoot = new Group();
        Scene scene;
        if(type.equals("splash-1")){
            var splash = new SplashScreen(SPLASH_SCREEN1,0,0);
            myInitialRoot.getChildren().add(splash.getView());
            scene = new Scene(myInitialRoot, SCREEN_SIZE_WIDTH, SCREEN_SIZE_HEIGHT, BACKGROUND);
            scene.setOnMouseClicked(e->setStartingStage(stage, "splash-2"));
            stage.setScene(scene);

        }
        else if(type.equals("splash-2")){
            var splash = new SplashScreen(SPLASH_SCREEN2,0,0);
            myInitialRoot.getChildren().add(splash.getView());
            scene = new Scene(myInitialRoot, SCREEN_SIZE_WIDTH, SCREEN_SIZE_HEIGHT, BACKGROUND);
            scene.setOnMouseClicked(e->startGame());
            stage.setScene(scene);
        }
        stage.show();

    }


    private void startGame(){
        myHigh.myHighScore=myHigh.calculateHighScore(myHighScoreFile);
        var myScene = makeLevel(1);
        myScene.fillProperty().setValue(Color.LIGHTBLUE);
        myStage.setScene(myScene);

        var frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> {
            try {
                step(1, SECOND_DELAY);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        myAnimation.setCycleCount(Timeline.INDEFINITE);
        myAnimation.getKeyFrames().add(frame);
        myAnimation.play();
    }

    private Scene makeLevel(int level) {
        myGameRoot = addGameObjects();
        myGameRoot.getChildren().add(Status.displayBar());
        try {
            myBrickArray = BrickManager.createBrickArray(level);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Brick[] row : myBrickArray){
            for (Brick myBrick : row){
                if(myBrick != null){
                myGameRoot.getChildren().add(myBrick.getNode());
                }

            }
        }
        var scene = new Scene(myGameRoot, SCREEN_SIZE_WIDTH, SCREEN_SIZE_HEIGHT); //figure if this is correct
        scene.fillProperty().setValue(Color.LIGHTBLUE);
        scene.setOnKeyPressed(e -> {
            try {
                handleKeyInput(e.getCode());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        return scene;
    }

    private Group addGameObjects(){
        Group root = new Group();
        myPaddle = new Paddle(PADDLE_IMAGE, SCREEN_SIZE_WIDTH/2-30, SCREEN_SIZE_HEIGHT-13, SCREEN_SIZE_WIDTH);
        myBouncer = new Bouncer(SCREEN_SIZE_WIDTH/2-10,SCREEN_SIZE_HEIGHT-45, 1);
        root.getChildren().add(myPaddle.getView());
        root.getChildren().add(myBouncer.getView());
        return root;
    }


    public void makeWinLoseScreen(boolean win) {
        myHigh.addScore(myHighScoreFile, Rules.myScore);
        myHigh.myHighScore=myHigh.calculateHighScore(myHighScoreFile);
        if(win){
            myGameRoot.getChildren().clear();
            myGameRoot.getChildren().add(setUp(new Text("Congratulations, you won :') !! "+myHigh.returnHighScoreDisplay(Rules.myScore, HighScore.myHighScore))));
            Restart();
        }
        else{
            myGameRoot.getChildren().clear();
            myGameRoot.getChildren().add(setUp(new Text ("So sorry you lost :'( "+ myHigh.returnHighScoreDisplay(Rules.myScore, HighScore.myHighScore))));
            Restart();
        }
    }



//    private Text w = new Text("Congratulations, you won :') !! "+myHigh.returnHighScoreDisplay(Rules.myScore, HighScore.myHighScore) );
//    private Text l = new Text("So sorry you lost :'( "+ myHigh.returnHighScoreDisplay(Rules.myScore, HighScore.myHighScore));
//    private Text toDisplayW = setUp(w);
//    private Text toDisplayl = setUp(l);
//
    private Text setUp(Text t) {
        t.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        t.setY(30);
        t.setX(30);
        return t;
    }

    private void Restart(){
        myAnimation.stop();
        myAnimation.getKeyFrames().clear();
        myRules.resetRules();
        myGameRoot.getScene().setOnKeyPressed(e->{
            if (e.getCode()== KeyCode.SPACE){
                startGame();
            }
        });


    }

    public void changeLevel(int level){
        Scene scene;
        if(level > 4){
            makeWinLoseScreen(true);
        }
        else{
            scene = makeLevel(level);
            myStage.setScene(scene);
            myStage.show();
            myPaddle.updateWidth(level, myPaddle);

        }
    }

    private void step (int mode, double elapsedTime) throws Exception {
        if(mode == 1) {
            myBouncer.looseALife();
            Status.displayBar();
            // update attributes
            if (myBouncer.myState == 1) {
                myBouncer.getView().setX(myPaddle.getView().getX() + (myPaddle.getView().getFitWidth())/ 2-6);
                myBouncer.getView().setY(myPaddle.getView().getY() - 25);
            } else if (myBouncer.myState == 2) {
                myBouncer.move(elapsedTime);
            }
            if (myRules.checkForWin()) {
                Rules.myLevel+=1;
                changeLevel(Rules.myLevel);
            }
            if (myRules.checkForLoss()) {
                makeWinLoseScreen(false);
            }
            myPaddle.paddleRules();
            myBouncer.bounce(SCREEN_SIZE_WIDTH, myPaddle, myBrickArray);
        }
        if(mode == 2){
            //Modes.stepMode2(elapsedTime);

        }
        if(mode == 3){
            //Modes.stepMode3(elapsedTime);

        }
        if(mode == 4){
           // Modes.stepMode4(elapsedTime);
        }
    }

    public static Boolean intersect(Bouncer ball, Paddle paddle){
        if (ball.getView().intersects(paddle.getView().getBoundsInParent())){
            return true;
        }
        return false;
    }

    public static void collision(Bouncer ball, Brick brick){
        if (Math.abs(brick.getImage().getX()-ball.getView().getX())<40 ||Math.abs(brick.getImage().getX()+brick.getImage().getFitWidth()-ball.getView().getX())<40 ){
            ball.myVelocityX*=-1;
        }
        if (Math.abs(brick.getImage().getY()-ball.getView().getY())<40 ||Math.abs(brick.getImage().getY()+brick.getImage().getFitHeight()-ball.getView().getY())<40 ){
            ball.myVelocityY*=-1;
        }

    }

    public static Boolean hitBrick(Bouncer ball, Brick mybrick){
        if (ball.getView().intersects(mybrick.getNode().getBoundsInParent())){
            return true;
        }
        return false;
    }

    public void handleKeyInput (KeyCode code) throws Exception { //combine key methods
        if (code==KeyCode.E){
            Rules.myLives=0;
        }
        if (code==KeyCode.X){
            Rules.myScore+=50;
        }
        if(code == KeyCode.SPACE) {
           if(myBouncer.myState == 1){
               myBouncer.myState = 2;
           }
        }
        if(code == KeyCode.PERIOD){
            step(2, SECOND_DELAY);
        }
        if(code == KeyCode.COMMA){
            step(3, SECOND_DELAY);
        }
        if(code == KeyCode.BACK_SLASH){
            step(4, SECOND_DELAY);
        }
        if(code == KeyCode.R){
            myBouncer.myState = 1;
        }
        if (code==KeyCode.L){
            Rules.myLives+=1;
        }
        if (code==KeyCode.K){
            myPaddle.getView().setFitWidth(SCREEN_SIZE_WIDTH-100);
        }

        if (code == KeyCode.RIGHT) {
            myPaddle.getView().setX(myPaddle.getView().getX() + PADDLE_SPEED);
        }
        else if (code == KeyCode.LEFT) {
            myPaddle.getView().setX(myPaddle.getView().getX() - PADDLE_SPEED);
        }
        else if (code == KeyCode.UP) {
            myPaddle.getView().setY(SCREEN_SIZE_HEIGHT - 13);
        }
        else if (code == KeyCode.DOWN) {
            myPaddle.getView().setY(SCREEN_SIZE_HEIGHT -13);
        }
        if(code == KeyCode.DIGIT1){
            myRules.myLevel=1;
            changeLevel(1);
        }
        if(code == KeyCode.DIGIT2){
            myRules.myLevel=2;
            changeLevel(2);
        }
        if(code == KeyCode.DIGIT3){
            myRules.myLevel=3;
            changeLevel(3);
        }
        if(code == KeyCode.DIGIT4){
            myRules.myLevel=4;
            changeLevel(4);
        }
    }

    public static void main (String[] args) {
        launch(args);
    }
}
