import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
//author: Tanisha Nalavadi, Melissa Leal

/**
 * Purpose: This class controls the creation of the two splash screens created before the game starts
 */
public class SplashScreen {
    public static final String SPLASH_IMAGE1 = "splashscreen1.gif";
    public static final String SPLASH_IMAGE2= "spalshscreen2.gif";
    private ImageView myImageView;
    private double myX;
    private double myY;

    /**
     * Purpose: Constructor that creates a SplashScreen object
     * @param screenFile
     * @param width
     * @param height
     */
    public SplashScreen(String screenFile, int width, int height) {
        var image = new Image(this.getClass().getClassLoader().getResourceAsStream(screenFile));
        myImageView = new ImageView(image);
        myImageView.setX(width);
        myImageView.setY(height);
        myX = myImageView.getX();
        myY = myImageView.getX();
        myImageView.setFitHeight(display.SCREEN_SIZE_HEIGHT);
        myImageView.setFitWidth(display.SCREEN_SIZE_WIDTH);
    }

    /**
     * Purpose: Returns an Image View for a given SplashScreen object
     * @return ImageView
     */
    public ImageView getView(){
        return myImageView;
    }

}
