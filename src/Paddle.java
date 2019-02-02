import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;


public class Paddle {
    private final String PADDLE_IMAGE = "paddle.gif";
    private static int PADDLE_SPEED = 15;
    private display myDisplay;
    private ImageView myImageView;

    /**
     * Creates a paddle object
     */
    public Paddle(display display){
        super();
        myDisplay = display;
        var image = new Image(this.getClass().getClassLoader().getResourceAsStream(PADDLE_IMAGE));
        myImageView =  new ImageView(image);
        myImageView.setImage(image);
        myImageView.setFitHeight(image.getHeight());
        myImageView.setFitWidth(image.getWidth());

    }

//   public ImageView createPaddle(){
//        var image = new Image(this.getClass().getClassLoader().getResourceAsStream(PADDLE_IMAGE));
//        myPaddle = new ImageView(image);
//        myPaddle.setFitHeight(image.getHeight());
//        myPaddle.setFitWidth(image.getWidth());
//        myPaddle.setX(display.SIZE/2 - myPaddle.getFitWidth());
//        myPaddle.setY(display.SIZE);
//        return myPaddle;
//   }

    public void paddleRules(){
        if(myImageView.getX() >= myDisplay.getSize()) {
            myImageView.setX(myImageView.getFitWidth());
        }
        if(myImageView.getX()<= 0 ){
            myImageView.setX(myDisplay.getSize()-myImageView.getFitWidth());
        }
    }

    public void handleKeyInput (KeyCode code) {//combine key methods
        if (code == KeyCode.RIGHT) {
            myImageView.setX(myImageView.getX() + PADDLE_SPEED);
        }
        else if (code == KeyCode.LEFT) {
            myImageView.setX(myImageView.getX() - PADDLE_SPEED);
        }
        else if (code == KeyCode.UP) {
            myImageView.setY(myDisplay.getSize());
        }
        else if (code == KeyCode.DOWN) {
            myImageView.setY(myDisplay.getSize());
        }


    }
    public ImageView getView(){
        return myImageView;
    }

}
