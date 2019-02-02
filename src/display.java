
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;


public class display extends Application {
    private static final String TITLE = "BRICK BREAKER !!!";
    private static final String SPLASH_SCREEN1 = "splashscreen1.gif";
    private static final String SPLASH_SCREEN2 = "splashscreen2.gif";
    private static final int SIZE = 400;
    private static final int FRAMES_PER_SECOND = 60;
    private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    private static final Paint BACKGROUND = Color.LAVENDERBLUSH;
    private Scene myScene;
    private SplashScreen myScreen1;
    private SplashScreen myScreen2;
    private Paddle myPaddle;
    private Bouncer myBouncer;
    private Group myRoot;
    private boolean instructionsShown= true;
    private boolean gameStarted= true;



    private Scene setupGame (int width, int height, Paint background,  String SPLASH_SCREEN1, String SPLASH_SCREEN2) {
        // create one top level collection to organize the things in the scene
        myRoot = new Group();

        // create a place to see the shapes
        var scene = new Scene(myRoot, width, height, background);
        myScreen1= new SplashScreen(SPLASH_SCREEN1,0, 0);
        myScreen2= new SplashScreen(SPLASH_SCREEN2,width, height);
        //myRoot.getChildren().add(myScreen1.getView());
        //myRoot.getChildren().remove(myScreen1.getView());

        //myRoot.getChildren().add(myScreen2.getView());
        //myRoot.getChildren().remove(myScreen2.getView());

        // make some shapes and set their properties
        // order added to the group is the order in which they are drawn
        myPaddle = new Paddle(this);
        myPaddle.getView().setX(width/2);
        myPaddle.getView().setY(height-10);
        myBouncer = new Bouncer(myPaddle.getView().getX(), myPaddle.getView().getY());
        myRoot.getChildren().add(myPaddle.getView());

        myRoot.getChildren().add(myBouncer.getView());
        //paddleObject.paddleRules(myPaddle);
        // respond to input
        scene.setOnKeyPressed(e -> myPaddle.handleKeyInput(e.getCode()));
        return scene;
    }
    /**
     * Initialize what will be displayed and how it will be updated.
     */
    @Override
    public void start (Stage stage) {
        // attach scene to the stage and display it
        myScene = setupGame(SIZE, SIZE, BACKGROUND, SPLASH_SCREEN1, SPLASH_SCREEN2);
        stage.setScene(myScene);
        stage.setTitle(TITLE);
        stage.show();
        // attach "game loop" to timeline to play it
        var frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
        var animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }

    public void makeScene(Stage stage){
        myScene = setupGame(SIZE, SIZE, BACKGROUND, SPLASH_SCREEN1, SPLASH_SCREEN2);
        stage.setScene(myScene);
        stage.setTitle(TITLE);
        stage.show();
        // attach "game loop" to timeline to play it
       var frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
        var animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }

    private void step (double elapsedTime) {
        // update attributes
        myPaddle.paddleRules();
        myBouncer.move(elapsedTime);
        myBouncer.bounce(myScene.getWidth(),myScene.getHeight());
    }

    public Boolean shownInstructions(){
        return false;
    }

    public Boolean startedGame(){
        return false;
    }

    public int getSize(){
        return SIZE;
    }
    public static void main (String[] args) {
        launch(args);
    }
}
