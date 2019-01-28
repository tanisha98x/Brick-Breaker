import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Rectangle;

public class Paddle {
    public final String PADDLE_IMAGE = "paddle.jpeg";
    public ImageView myPaddle;
    public static final int PADDLE_SPEED = 5;

    public void createPaddle(){
        var image = new Image(this.getClass().getClassLoader().getResourceAsStream(PADDLE_IMAGE));
        myPaddle = new ImageView(image);

    }
    public void paddleRules(ImageView paddle){
        if(paddle.getX()>= display.setScene){

        }
    }
    public void handleKeyInput (KeyCode code) {
        if (code == KeyCode.RIGHT) {
            myPaddle.setX(myPaddle.getX() + PADDLE_SPEED);
        }
        else if (code == KeyCode.LEFT) {
            myPaddle.setX(myPaddle.getX() - PADDLE_SPEED);
        }
        else if (code == KeyCode.UP) {
            myPaddle.setY(myPaddle.getY() - PADDLE_SPEED);
        }
        else if (code == KeyCode.DOWN) {
            myPaddle.setY(myPaddle.getY() + PADDLE_SPEED);
        }
    }

}
