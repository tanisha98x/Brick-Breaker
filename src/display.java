
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;


public class display extends Application {
    public static final String TITLE = "BRICK BREAKER !!!";
    public static final int SIZE = 400;
    public static final int FRAMES_PER_SECOND = 60;
    public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    public static final Paint BACKGROUND = Color.AZURE;
    private Scene myScene;
    private Paddle paddle = new Paddle();
    private ImageView myPaddle = paddle.createPaddle();


    private Scene setupGame (int width, int height, Paint background) {
        // create one top level collection to organize the things in the scene
        var root = new Group();
        // create a place to see the shapes
        var scene = new Scene(root, width, height, background);
        // make some shapes and set their properties
        // order added to the group is the order in which they are drawn
        root.getChildren().add(myPaddle);
        // respond to input
        scene.setOnKeyPressed(e -> paddle.handleKeyInput(e.getCode()));
        return scene;
    }
    /**
     * Initialize what will be displayed and how it will be updated.
     */
    @Override
    public void start (Stage stage) {
        // attach scene to the stage and display it
        myScene = setupGame(SIZE, SIZE, BACKGROUND);
        stage.setScene(myScene);
        stage.setTitle(TITLE);
        stage.show();
        // attach "game loop" to timeline to play it
        //var frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
        var animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        //animation.getKeyFrames().add(frame);
        animation.play();
    }

    public void makeScene(Stage stage){
        myScene = setupGame(SIZE, SIZE, BACKGROUND);
        stage.setScene(myScene);
        stage.setTitle(TITLE);
        stage.show();
        // attach "game loop" to timeline to play it
       // var frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
        var animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
       // animation.getKeyFrames().add(frame);
        animation.play();
    }

    //private void step (double elapsedTime) {
        // update attributes
      //  myPaddle.setRotate(myPaddle.getRotate() - 1);
   // }

    public static void main (String[] args) {
        launch(args);
    }
}
