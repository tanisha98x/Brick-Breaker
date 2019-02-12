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
 * This is the main class of the program, it controls the display, the scene of each level and handles key input
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
    private Rules myRules = new Rules();
    private Group myInitialRoot;
    private HighScore myHigh= new HighScore();
    private BrickManager myBrickManager = new BrickManager();
    private StatusDisplay Status = new StatusDisplay();
    //private TesterModes Modes = new TesterModes();
    private Stage myStage;
    private Brick[][] myBrickArray;
    private File myHighScoreFile= new File("Resources/HighScoreTracker");
    private Timeline myAnimation;


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
        myGameRoot.getChildren().add(Status.displayBar(myRules.getMyScore(), myRules.getMyLives(), myRules.getMyLevel()));
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
        myHigh.addScore(myHighScoreFile, myRules.getMyScore());
        myHigh.updateHighScore(myHigh.calculateHighScore(myHighScoreFile));
        if(win){
            myGameRoot.getChildren().clear();
            myGameRoot.getChildren().add(setUp(new Text("Congratulations, you won :') !! "+myHigh.returnHighScoreDisplay(myRules.getMyScore(), myHigh.getMyHighScore())+". Click the space bar to restart the game.")));
            Restart();
        }
        else{
            myGameRoot.getChildren().clear();
            myGameRoot.getChildren().add(setUp(new Text ("So sorry you lost :'( "+ myHigh.returnHighScoreDisplay(myRules.getMyScore(), myHigh.getMyHighScore())+". Click the space bar to restart the game.")));
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
            Status.displayBar(myRules.getMyScore(), myRules.getMyLives(), myRules.getMyLevel());
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
    public Group getMyGameRoot(){
        return myGameRoot;
    }

    public void handleKeyInput (KeyCode code) throws Exception { //combine key methods

        if (code==KeyCode.X){
            myRules.updateScore(50);
        }
        if(code == KeyCode.SPACE) {
           if(myBouncer.getState() == 1){
               myBouncer.setState(2);
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
    public static void main (String[] args) {
        launch(args);
    }
}
