
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
import jdk.dynalink.beans.StaticClass;


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
    private Scene myScene;
    private SplashScreen myScreen1;
    private SplashScreen myScreen2;
    private Paddle myPaddle;
    private Bouncer myBouncer;
    public static Group myRoot;
    private boolean instructionsShown= true;
    private boolean gameStarted= true;
    private int count=0;
    private static int PADDLE_SPEED = 15;
    //private static Rectangle myRectangle;
    private static Bricks[][] myBrickArray= Bricks.createBrickArray(4,7,SCREEN_SIZE*0.5, 20, SCREEN_SIZE*0.3, 10);

    public Group getRoot(){
        return myRoot;
    }



    private Scene setupGame (int width, int height, Paint background,  String SPLASH_SCREEN1, String SPLASH_SCREEN2) {//level1 :4 by 7
        // create one top level collection to organize the things in the scene
        myRoot = new Group();
//        myRectangle= new Rectangle(40,40,60,20);
//        myRectangle.setStroke(Color.BLACK);
//        myRectangle.setFill(Color.FLORALWHITE);

        // create a place to see the shapes
        var scene = new Scene(myRoot, width, height, background);

        myScreen1= new SplashScreen(SPLASH_SCREEN1,0, 0);

        myScreen2= new SplashScreen(SPLASH_SCREEN2,0, 0);
        myRoot.getChildren().add(myScreen1.getView());
        //myRoot.getChildren().remove(myScreen1.getView());

       // myRoot.getChildren().add(myScreen2.getView());
        //myRoot.getChildren().remove(myScreen2.getView());

        // make some shapes and set their properties
        // order added to the group is the order in which they are drawn
        myPaddle = new Paddle(PADDLE_IMAGE, width/2-30, height-12, SCREEN_SIZE); //30 and 12 are due to the height 12 and width 60, of the image
        myBouncer = new Bouncer(width/2-10,height-45, 1); //changed it to the correct starting location
        //myRoot.getChildren().add(myPaddle.getView());

        //myRoot.getChildren().add(myBouncer.getView());
        //paddleObject.paddleRules(myPaddle);
        // respond to input
        scene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
        return scene;
    }

    /**
     * Initialize what will be displayed and how it will be updated.
     */
    @Override
    public void start (Stage stage) {
        // attach scene to the stage and display it
        myScene = setupGame(SCREEN_SIZE, SCREEN_SIZE, BACKGROUND, SPLASH_SCREEN1, SPLASH_SCREEN2);
        stage.setScene(myScene);
        stage.setTitle(TITLE);
        stage.setResizable(false);
        stage.show();
        // attach "game loop" to timeline to play it
        var frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
        var animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }

    public void makeScene(Stage stage){
        myScene = setupGame(SCREEN_SIZE, SCREEN_SIZE, BACKGROUND, SPLASH_SCREEN1, SPLASH_SCREEN2);
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

    private void step (double elapsedTime) {//need to make this start only when space bar is entered

        // update attributes
        if(myBouncer.myState == 1){
            //myBouncer.moveFirst(0, 0, elapsedTime);
            myBouncer.getView().setX(myPaddle.getX()+ myPaddle.getFitWidth()/2);
            myBouncer.getView().setY(myPaddle.getY()- 25); //25 bc the heigth of the image has heigth 12 so it goes a bit above it
        }
        else if(myBouncer.myState == 2){
            myBouncer.move(elapsedTime);
        }
        myPaddle.paddleRules();
        myBouncer.bounce(myScene.getWidth(), myPaddle, myBrickArray);
    }

    public static Boolean intersect(Bouncer ball, Paddle paddle){
        if (ball.getView().intersects(paddle.getBoundsInParent())){
            return true;
        }
        return false;
    }

    public static Boolean destroyBrick(Bouncer ball, Rectangle brick){
        if (ball.getView().intersects(brick.getBoundsInParent())){
            return true;
        }
        return false;
    }



    public int getSize(){
        return SCREEN_SIZE;
    }

    public void handleKeyInput (KeyCode code) {//combine key methods
        if (code==KeyCode.SPACE && count==0){
            myRoot.getChildren().remove(myScreen1.getView());
            myRoot.getChildren().add(myScreen2.getView());
            count+=1;
        }
        else if (code==KeyCode.SPACE && count==1){
            myRoot.getChildren().remove(myScreen2.getView());
            myRoot.getChildren().add(myPaddle);
            myRoot.getChildren().add(myBouncer.getView());
            for (Bricks [] each: myBrickArray){
                for (Bricks object : each){
                    myRoot.getChildren().add(object.myBrick);
                }
            }
           // myRoot.getChildren().add(myRectangle);
            //myRoot.getChildren().add(new Bricks(0,0));
            count+=1;
        }
        else if(code == KeyCode.SPACE && count == 2) {  //my idea is that when we click space bar it will change its velocity to normal
           if(myBouncer.myState == 1){
               myBouncer.myState = 2;
           }
        }
        if (code==KeyCode.L){
            StatusDisplay.myLives+=1;
        }

        if (code == KeyCode.RIGHT) {
            myPaddle.setX(myPaddle.getX() + PADDLE_SPEED);
        }
        else if (code == KeyCode.LEFT) {
            myPaddle.setX(myPaddle.getX() - PADDLE_SPEED);
        }
        else if (code == KeyCode.UP) {
            myPaddle.setY(this.getSize());
        }
        else if (code == KeyCode.DOWN) {
            myPaddle.setY(this.getSize());
        }}

    public static void main (String[] args) {
        launch(args);
    }
}
