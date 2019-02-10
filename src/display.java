
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.text.*;

public class display extends Application {
    public static final String TITLE = "BRICK BREAKER !!!";
    public static final String SPLASH_SCREEN1 = "splashscreen1.gif";
    public static final String SPLASH_SCREEN2 = "splashscreen2.gif";
    public static final String PADDLE_IMAGE = "paddle.gif";
    public static final int SCREEN_SIZE_WIDTH = 840;
    public static final int SCREEN_SIZE_Height = 800;
    public static final int FRAMES_PER_SECOND = 60;
    public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    public static final Paint BACKGROUND = Color.PINK;
    private Paddle myPaddle;
    private Bouncer myBouncer;
    private Rules myRules = new Rules();
    public static Group myInitialRoot;
    public static Group myGameRoot;
    private static int PADDLE_SPEED = 15;
    private Text w = new Text("Congratulations, you won :') !!");
    private Text l = new Text("So sorry you lost :'( ");
    private Text toDisplayW = setUp(w);
    private Text toDisplayl = setUp(l);
    private StatusDisplay Status = new StatusDisplay();
    //private TesterModes Modes = new TesterModes();
    private Stage myStage;
    private Brick[][] myBrickArray;



    /**
     * Initialize what will be displayed and how it will be updated.
     */
    @Override

    public void start (Stage stage) {
        myStage = stage;
        // attach scene to the stage and display itv
        setStartingStage(myStage, "splash-1");

    }


    private void setStartingStage(Stage stage, String type) {
        myInitialRoot = new Group();
        Scene scene;
        if(type.equals("splash-1")){
            var splash = new SplashScreen(SPLASH_SCREEN1,0,0);
            myInitialRoot.getChildren().add(splash.getView());
            scene = new Scene(myInitialRoot, SCREEN_SIZE_WIDTH, SCREEN_SIZE_Height, BACKGROUND);
            scene.setOnMouseClicked(e->setStartingStage(stage, "splash-2"));
            stage.setScene(scene);

        }
        else if(type.equals("splash-2")){
            var splash = new SplashScreen(SPLASH_SCREEN2,0,0);
            myInitialRoot.getChildren().add(splash.getView());
            scene = new Scene(myInitialRoot, SCREEN_SIZE_WIDTH, SCREEN_SIZE_Height, BACKGROUND);
            scene.setOnMouseClicked(e->startGame());
            stage.setScene(scene);
        }
        stage.show();

    }


    private void startGame(){
        var myScene = makeLevel(1);
        myScene.fillProperty().setValue(Color.PINK);
        myStage.setScene(myScene);

        var frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> {
            try {
                step(1, SECOND_DELAY);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        var animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }

    private Scene makeLevel(int level) {
        myGameRoot = addGameObjects();
        myGameRoot.getChildren().add(Status.displayBar(Rules.myScore, Rules.myLives, level));
        myBrickArray = new Brick[0][];
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
        var scene = new Scene(myGameRoot, SCREEN_SIZE_WIDTH, SCREEN_SIZE_Height); //figure if this is correct
        scene.fillProperty().setValue(Color.PINK);
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
        myPaddle = new Paddle(PADDLE_IMAGE, SCREEN_SIZE_WIDTH/2-30, SCREEN_SIZE_Height-13, SCREEN_SIZE_WIDTH);
        myBouncer = new Bouncer(SCREEN_SIZE_WIDTH/2-10,SCREEN_SIZE_Height-45, 1);
        root.getChildren().add(myPaddle.getView());
        root.getChildren().add(myBouncer.getView());
        return root;
    }
    private Text setUp(Text t) {
        t.setY(SCREEN_SIZE_Height/2);
        t.setX(SCREEN_SIZE_WIDTH/2);
        return t;
    }

    public void makeWinLoseScreen(boolean win) {
        if(win){
            myGameRoot.getChildren().clear();
            myGameRoot.getChildren().add(toDisplayW);
        }
        else{
            myGameRoot.getChildren().clear();
            myGameRoot.getChildren().add(toDisplayl);
        }
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

        }
    }

    private void step (int mode, double elapsedTime) throws Exception {
        if(mode == 1) {
            myBouncer.looseALife();
            Status.displayBar(Rules.myScore, Rules.myLives, Rules.myLevel);
            // update attributes
            if (myBouncer.myState == 1) {
                myBouncer.getView().setX(myPaddle.getView().getX() + myPaddle.getView().getFitWidth() / 2);
                myBouncer.getView().setY(myPaddle.getView().getY() - 25);
            } else if (myBouncer.myState == 2) {
                myBouncer.move(elapsedTime);
            }
            if (myRules.checkForWin()) {
                makeLevel(Rules.myLevel+1);
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

    public static Boolean destroyBrick(Bouncer ball, Brick mybrick){
        if (ball.getView().intersects(mybrick.getNode().getBoundsInParent())){
            return true;
        }
        return false;
    }

    public void handleKeyInput (KeyCode code) throws Exception { //combine key methods
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
        if (code == KeyCode.RIGHT) {
            myPaddle.getView().setX(myPaddle.getView().getX() + PADDLE_SPEED);
        }
        else if (code == KeyCode.LEFT) {
            myPaddle.getView().setX(myPaddle.getView().getX() - PADDLE_SPEED);
        }
        else if (code == KeyCode.UP) {
            myPaddle.getView().setY(SCREEN_SIZE_Height - 13);
        }
        else if (code == KeyCode.DOWN) {
            myPaddle.getView().setY(SCREEN_SIZE_Height -13);
        }
        if(code == KeyCode.DIGIT1){
            changeLevel(1);
        }
        if(code == KeyCode.DIGIT2){
            changeLevel(2);
        }
        if(code == KeyCode.DIGIT3){
            changeLevel(3);
        }
        if(code == KeyCode.DIGIT4){
            changeLevel(4);
        }
    }

    public static void main (String[] args) {
        launch(args);
    }
}
