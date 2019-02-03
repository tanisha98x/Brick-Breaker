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
    public Paddle(display display, double width, double height){
        super();
        myDisplay = display;
        var image = new Image(this.getClass().getClassLoader().getResourceAsStream(PADDLE_IMAGE));
        myImageView =  new ImageView(image);
        myImageView.setImage(image);
        myImageView.setFitHeight(image.getHeight());
        myImageView.setFitWidth(image.getWidth());
        myImageView.setX(width);
        myImageView.setY(height);


    }

//   public ImageView createPaddle(){
//        var image = new Image(this.getClass().getClassLoader().getResourceAsStream(PADDLE_IMAGE));
//        myPaddle = new ImageView(image);
//        myPaddle.setFitHeight(image.getHeight());
//        myPaddle.setFitWidth(image.getWidth());
//        myPaddle.setX(display.SCREEN_SIZE/2 - myPaddle.getFitWidth());
//        myPaddle.setY(display.SCREEN_SIZE);
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






    public ImageView getView(){
        return myImageView;
    }

}
