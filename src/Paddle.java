import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;


public class Paddle extends ImageView {
    private int PADDLE_SPEED = 800;
    private int displaySize;
    /**
     * Creates a paddle object
     */
    public Paddle(String PADDLE_IMAGE, int startXPos, int startYPos, int sizeOfDisplay){ //yposition just be the height minus height of paddle
        super();
        var image = new Image(this.getClass().getClassLoader().getResourceAsStream(PADDLE_IMAGE));
        this.setImage(image);
        this.setFitHeight(image.getHeight());
        this.setFitWidth(image.getWidth());
        this.setX(startXPos);
        this.setY(startYPos);
        this.displaySize = sizeOfDisplay;

    }

    public void changePaddleSpeed(int speedIncrease){
        PADDLE_SPEED  = PADDLE_SPEED + speedIncrease;
    }

    public void changePaddleImage(String PADDLE_IMAGE){
        var image = new Image(this.getClass().getClassLoader().getResourceAsStream(PADDLE_IMAGE));
        this.setImage(image);
    }


    public void paddleRules(){
        if(this.getX() >= displaySize) {
            this.setX(this.getFitWidth());
        }
        if(this.getX()<= 0 ){
            this.setX(displaySize-this.getFitWidth());
        }
    }


}
