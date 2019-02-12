import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


//author: Tanisha Nalavadi, Melissa Leal

/**
 * This class creates a paddle object and defines all methods for its proper functionality.
 */
public class Paddle {
    private ImageView myImageView;
    private int PADDLE_SPEED = 800;
    private int displaySize;
    private double baseWidth;

    /**
     * Creates a paddle object
     */
    public Paddle (){
        myImageView = new ImageView();
    }

    public Paddle(String PADDLE_IMAGE, int startXPos, int startYPos, int sizeOfDisplayWidth) {
        var image = new Image(this.getClass().getClassLoader().getResourceAsStream(PADDLE_IMAGE));
        myImageView = new ImageView(image);
        myImageView.setFitHeight(image.getHeight());
        baseWidth = image.getWidth();
        myImageView.setFitWidth(baseWidth + 40);
        myImageView.setX(startXPos);
        myImageView.setY(startYPos);
        displaySize = sizeOfDisplayWidth;
    }

    public void changePaddleImage(String PADDLE_IMAGE) {
        var image = new Image(this.getClass().getClassLoader().getResourceAsStream(PADDLE_IMAGE));
        myImageView.setImage(image);
    }

    public void updateWidth(int level, Paddle paddle) {
        if (level == 1) {
            paddle.getView().setFitWidth(baseWidth + 40);
        }
        if (level == 2) {
            paddle.getView().setFitWidth(baseWidth + 25);

        }
        if (level == 3) {
            paddle.getView().setFitWidth(baseWidth + 10);

        }
        if (level == 4) {
            paddle.getView().setFitWidth(baseWidth - 5);
        }
    }


    public void paddleRules() {
        if (myImageView.getX() >= displaySize) {
            myImageView.setX(myImageView.getFitWidth());
        }
        if (myImageView.getX() <= 0) {
            myImageView.setX(displaySize - myImageView.getFitWidth());
        }
    }

    public ImageView getView() {
        return myImageView;
    }
}


