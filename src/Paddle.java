import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;


public class Paddle extends ImageView {
    public final String PADDLE_IMAGE = "paddle.gif";
    public static int PADDLE_SPEED = 15;

    /**
     * Creates a paddle object
     */
    public Paddle(){
        super();
        var image = new Image(this.getClass().getClassLoader().getResourceAsStream(PADDLE_IMAGE));
        this.setImage(image);
        this.setFitHeight(image.getHeight());
        this.setFitWidth(image.getWidth());
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
        if(this.getX() >= display.SIZE) {
            this.setX(this.getFitWidth());
        }
        if(this.getX()<= 0 ){
            this.setX(display.SIZE-this.getFitWidth());
        }
    }

    public void handleKeyInput (KeyCode code) {
        if (code == KeyCode.RIGHT) {
            this.setX(this.getX() + PADDLE_SPEED);
        }
        else if (code == KeyCode.LEFT) {
            this.setX(this.getX() - PADDLE_SPEED);
        }
        else if (code == KeyCode.UP) {
            this.setY(display.SIZE);
        }
        else if (code == KeyCode.DOWN) {
            this.setY(display.SIZE);
        }
    }

}
