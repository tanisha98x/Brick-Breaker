
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
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;

public class display extends Application {
    public static final String TITLE = "BRICK BREAKER !!!";
    public static final String SPLASH_SCREEN1 = "splashscreen1.gif";
    public static final String SPLASH_SCREEN2 = "splashscreen2.gif";
    public static final String PADDLE_IMAGE = "paddle.gif";
    public static final int SCREEN_SIZE = 800;
    public static final int FRAMES_PER_SECOND = 60;
    public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    public static final Paint BACKGROUND = Color.PINK;
    private SplashScreen myScreen1;
    private SplashScreen myScreen2;
    private int count = 0;
    private Paddle myPaddle;
    private Bouncer myBouncer;
    private Rules myRules = new Rules();
    public static Group myRoot;
    private static int PADDLE_SPEED = 15;
    private Text w = new Text("Congratulations, you won :') !!");
    private Text l = new Text("So sorry you lost :'( ");
    private Text toDisplayW = setUp(w);
    private Text toDisplayl = setUp(l);
    private StatusDisplay Status = new StatusDisplay();
    private TesterModes Modes = new TesterModes();
    private Stage myStage;
    private Brick[][] myBrickArray;


    private Scene startScreen (int width, int height, Paint background,  String SPLASH_SCREEN1, String SPLASH_SCREEN2) {//level1 :4 by 7
        // create one top level collection to organize the things in the scene
        myRoot = new Group();
        // create a place to see the shapes
        var scene = new Scene(myRoot, width, height, background);

        myScreen1= new SplashScreen(SPLASH_SCREEN1,0, 0);

        myScreen2= new SplashScreen(SPLASH_SCREEN2,0, 0);
        myRoot.getChildren().add(myScreen1.getView());
        // respond to input
        scene.setOnKeyPressed(e -> {
            try { //had to surround almost all with try/catch because the mode file reading requires exception handling
                handleKeyInput(e.getCode());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        return scene;
    }

    /**
     * Initialize what will be displayed and how it will be updated.
     */
    @Override
    public void start (Stage stage) {
        myStage = stage;
        // attach scene to the stage and display itv
        Scene myScene = startScreen(SCREEN_SIZE, SCREEN_SIZE, BACKGROUND, SPLASH_SCREEN1, SPLASH_SCREEN2);
        myScene.setOnKeyPressed(e ->startGame());
        myStage.setScene(myScene);
        myStage.setTitle(TITLE);
        myStage.setResizable(false);
        myStage.show();
    }

    private void startGame(){
        var myScene = makeLevel(1);
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
        Group root = addGameObjects();
        root.getChildren().add(Status.displayBar(Rules.myScore, Rules.myLives, level));
        myBrickArray = new Brick[0][];
        try {
            myBrickArray = BrickManager.createBrickArray(level);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Brick[] row : myBrickArray){
            for (Brick myBrick : row){
                root.getChildren().add(myBrick.getNode());
            }
        }
        var scene = new Scene(root, SCREEN_SIZE, SCREEN_SIZE); //figure if this is correct

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
        myPaddle = new Paddle(PADDLE_IMAGE, SCREEN_SIZE/2-30, SCREEN_SIZE-13, SCREEN_SIZE);
        myBouncer = new Bouncer(SCREEN_SIZE/2-10,SCREEN_SIZE-45, 1);
        root.getChildren().add(myPaddle.getView());
        root.getChildren().add(myBouncer.getView());
        return root;
    }
    private Text setUp(Text t) {
        t.setY(SCREEN_SIZE/2);
        t.setX(SCREEN_SIZE/2);
        return t;
    }

    public void makeWinLoseScreen(boolean win) {
        if(win){
            myRoot.getChildren().clear();
            myRoot.getChildren().add(toDisplayW);
        }
        else{
            myRoot.getChildren().clear();
            myRoot.getChildren().add(toDisplayl);
        }
    }
    public void changeLevel(int level){
        Scene scene;
        if(level >= 4){
            makeWinLoseScreen(true);
        }
        else{
            makeLevel(level);

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
            myBouncer.bounce(SCREEN_SIZE, myPaddle, myBrickArray);
        }
        if(mode == 2){
            Modes.stepMode2(elapsedTime);

        }
        if(mode == 3){
            Modes.stepMode3(elapsedTime);

        }
        if(mode == 4){
            Modes.stepMode4(elapsedTime);
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
        if (code==KeyCode.SPACE && count==0){
            myRoot.getChildren().remove(myScreen1.getView());
            myRoot.getChildren().add(myScreen2.getView());
            count+=1;
        }
        else if (code==KeyCode.SPACE && count==1){

        }
        else if(code == KeyCode.SPACE && count == 2) {
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
            myPaddle.getView().setY(SCREEN_SIZE - 13);
        }
        else if (code == KeyCode.DOWN) {
            myPaddle.getView().setY(SCREEN_SIZE -13);
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
