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

/**
 * Purpose: This is the main class of the program that controls the display, creates the scene at every level, adds and removes
 * objects from the the group as and when needed and handles key inputs
 * Dependencies: This class creates instances of other classes and calls on other public methods from other classes in this package
 */
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
    private static final int PADDLE_SPEED = 15;
    private static Group myGameRoot;
    private Paddle myPaddle;
    private Bouncer myBouncer;
    private BrickManager myBrickManager = new BrickManager();
    private Rules myRules = new Rules(3, 0, 1, myBrickManager);
    private Group myInitialRoot;
    private HighScore myHigh= new HighScore();
    private StatusDisplay Status = new StatusDisplay();
    private Stage myStage;
    private Brick[][] myBrickArray;
    private File myHighScoreFile= new File("Resources/HighScoreTracker");
    private Timeline myAnimation;

    /**
     * Purpose: Starting the game by calling the stage set up methods
     * @param stage
     */
    @Override
    public void start (Stage stage) {
        myStage = stage;
        stage.setTitle(TITLE);
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
        myHigh.updateHighScore(myHigh.calculateHighScore(myHighScoreFile));
        var myScene = makeLevel(myRules.getMyLevel());
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
        myGameRoot.getChildren().add(Status.displayBar(myBouncer.getMyScore(), myBouncer.getMyLives(), myRules.getMyLevel(), myHigh.getMyHighScore()));
        try {
            myBrickArray = myBrickManager.createBrickArray(level);
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
        var scene = new Scene(myGameRoot, SCREEN_SIZE_WIDTH, SCREEN_SIZE_HEIGHT);
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

    private void makeWinLoseScreen(boolean win) {
        myHigh.addScore(myHighScoreFile, myBouncer.getMyScore());
        myHigh.updateHighScore(myHigh.calculateHighScore(myHighScoreFile));
        if(win){
            myGameRoot.getChildren().clear();
            myGameRoot.getChildren().add(setUp(new Text("Congratulations, you won :') !! "+myHigh.returnHighScoreDisplay(myBouncer.getMyScore(), myHigh.getMyHighScore())+". Click the space bar to restart the game.")));
            Restart();
        }
        else{
            myGameRoot.getChildren().clear();
            myGameRoot.getChildren().add(setUp(new Text ("So sorry you lost :'( "+ myHigh.returnHighScoreDisplay(myBouncer.getMyScore(), myHigh.getMyHighScore())+". Click the space bar to restart the game.")));
            Restart();
        }
    }

    private Text setUp(Text t) {
        t.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
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

    private void changeLevel(int level){
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
            Status.displayBar(myBouncer.getMyScore(), myBouncer.getMyLives(), myRules.getMyLevel(), myHigh.getMyHighScore());
            // update attributes
            if (myBouncer.getState() == 1) {
                myBouncer.getView().setX(myPaddle.getView().getX() + (myPaddle.getView().getFitWidth())/ 2-6);
                myBouncer.getView().setY(myPaddle.getView().getY() - 25);
            } else if (myBouncer.getState() == 2) {
                myBouncer.move(elapsedTime);
            }
            if (myRules.checkForWin()) {
                myRules.updateLevel();
                changeLevel(myRules.getMyLevel());
            }
            if (myRules.checkForLoss(myBouncer.getMyLives())) {
                makeWinLoseScreen(false);
            }
            myPaddle.paddleRules();
            myBouncer.bounce(SCREEN_SIZE_WIDTH, myPaddle, myBrickArray);
        }

    }

    /**
     *Purpose: Returns the game root group that is called on by other classes
     * @return Group
     */
    public Group getMyGameRoot(){
        return myGameRoot;
    }

    private void handleKeyInput (KeyCode code) throws Exception { //combine key methods

        if (code==KeyCode.X){
            myRules.updateScore(50);
        }
        if(code == KeyCode.SPACE) {
           if(myBouncer.getState() == 1){
               myBouncer.setState(2);
           }
        }

        if(code == KeyCode.PERIOD ) {
            myAnimation.stop();
            if (myRules.getMyLevel() == 1) {
                testPaddleLength(myPaddle);
            }
        }

        if(code == KeyCode.COMMA ) {
            myAnimation.stop();
            if (myRules.getMyLevel() == 1) {
                testUpdateLevel(myRules);
            }
        }

        if(code == KeyCode.BACK_SLASH ) {
            myAnimation.stop();
            if (myRules.getMyLevel() == 1) {
                testUpdateScore(myRules);
            }
        }

        if(code == KeyCode.R){
            myBouncer.setState(1);
        }
        if (code==KeyCode.L){
            myRules.updateLife(1);
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
            myRules.setMyLevel(1);
            changeLevel(1);
        }
        if(code == KeyCode.DIGIT2){
            myRules.setMyLevel(2);
            changeLevel(myRules.getMyLevel());
        }
        if(code == KeyCode.DIGIT3){
            myRules.setMyLevel(3);
            changeLevel(3);
        }
        if(code == KeyCode.DIGIT4){
            myRules.setMyLevel(4);
            changeLevel(4);
        }

        }


    private void testPaddleLength(Paddle paddle){
        double testWidth1= paddle.getView().getFitWidth();
        paddle.updateWidth(4, paddle);
        double testWidth2=paddle.getView().getFitWidth();
        paddle.updateWidth(1, paddle);

        if (testWidth1>testWidth2){
            System.out.print("Passes the decrease paddle length as level up test");
        }
        else {
            System.out.println("Fails the decrease paddle length as level up test");
        }
    }

    private void testUpdateScore(Rules rule){
        double testScore1= rule.getMyScore();
        rule.updateScore(1);
        double testScore2=rule.getMyScore();
        if (testScore1<testScore2){
            System.out.print("Passes the update score test");
        }
        else {
            System.out.println("Fails the update score test");
        }
    }

    private void testUpdateLevel(Rules rule){
        double testLevel1= rule.getMyLevel();
        rule.updateLevel();
        double testLevel2=rule.getMyScore();
        if (testLevel1<testLevel2){
            System.out.print("Passes the update level test");
        }
        else {
            System.out.println("Fails the update level test");
        }
    }

    /**
     * Purpose: Main that runs the program
     * @param args
     */
    public static void main (String[] args) {
        launch(args);
    }
}
