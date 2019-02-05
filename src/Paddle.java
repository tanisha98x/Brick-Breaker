import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;


public class Paddle {
    private ImageView myImageView;
    private int PADDLE_SPEED = 800;
    private int displaySize;
    /**
     * Creates a paddle object
     */
    public Paddle(String PADDLE_IMAGE, int startXPos, int startYPos, int sizeOfDisplay){ //yposition just be the height minus height of paddle
        //super();
        var image = new Image(this.getClass().getClassLoader().getResourceAsStream(PADDLE_IMAGE));
        myImageView = new ImageView(image);
        myImageView.setFitHeight(image.getHeight());
        myImageView.setFitWidth(image.getWidth());
        myImageView.setX(startXPos);
        myImageView.setY(startYPos);
        displaySize = sizeOfDisplay;
    }

    public void changePaddleSpeed(int speedIncrease){
        PADDLE_SPEED  = PADDLE_SPEED + speedIncrease;
    }

    public void changePaddleImage(String PADDLE_IMAGE){
        var image = new Image(this.getClass().getClassLoader().getResourceAsStream(PADDLE_IMAGE));
        myImageView.setImage(image);
    }


    public void paddleRules(){
        if(myImageView.getX() >= displaySize) {
            myImageView.setX(myImageView.getFitWidth());
        }
        if(myImageView.getX()<= 0 ){
            myImageView.setX(displaySize-myImageView.getFitWidth());
        }
    }
    public ImageView getView(){
        return myImageView;
    }


}
