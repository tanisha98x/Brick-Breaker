import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


//author: Tanisha Nalavadi, Melissa Leal

/**
 * Purpose: This class creates a paddle object that holds attributes such as the dimensions and contains methods
 * specific to the working of the paddle including updating the width and paddle rules
 */
public class Paddle {
    private ImageView myImageView;
    private int PADDLE_SPEED = 800;
    private int displaySize;
    private double baseWidth;

    /**
     * Purpose: Constructor that creates a paddle object containing attributes like display size and basewidth
     * @param PADDLE_IMAGE
     * @param startXPos
     * @param startYPos
     * @param sizeOfDisplayWidth
     */
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

    /**
     * Purpose: To change the paddle image of the paddle, hasn't been used but can be called
     */
    public void changePaddleImage(String PADDLE_IMAGE) {
        var image = new Image(this.getClass().getClassLoader().getResourceAsStream(PADDLE_IMAGE));
        myImageView.setImage(image);
    }


    /**
     * Purpose: To reduce the width of the paddle as the level increases
     * @param level
     * @param paddle
     */
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

    /**
     * Purpose: Ensures the paddle appears back on the screen once it's X coordinate exceeds the display width
     */
    public void paddleRules() {
        if (myImageView.getX() >= displaySize) {
            myImageView.setX(myImageView.getFitWidth());
        }
        if (myImageView.getX() <= 0) {
            myImageView.setX(displaySize - myImageView.getFitWidth());
        }
    }

    /**
     * Purpose: Access the Image View in the paddle object
     * @return ImageView
     */
    public ImageView getView() {
        return myImageView;
    }
}


