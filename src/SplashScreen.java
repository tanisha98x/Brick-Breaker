import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
//author: Tanisha Nalavadi, Melissa Leal
public class SplashScreen {
    public static final String SPLASH_IMAGE1 = "splashscreen1.gif";
    public static final String SPLASH_IMAGE2= "spalshscreen2.gif";
    private ImageView myImageView;
    private double myX;
    private double myY;

    public SplashScreen(String screenFile, int width, int height) {
        var image = new Image(this.getClass().getClassLoader().getResourceAsStream(screenFile));
        myImageView =  new ImageView(image);
        myImageView.setX(width);
        myImageView.setY(height);
        myX = myImageView.getX();
        myY = myImageView.getX();
        myImageView.setFitHeight(display.SCREEN_SIZE_HEIGHT);
        myImageView.setFitWidth(display.SCREEN_SIZE_WIDTH);
    }

    public String getSplashScreen1(){
        return SPLASH_IMAGE1;}

    public String getSplashScreen12(){
        return SPLASH_IMAGE2;}

    public ImageView getView(){
        return myImageView;
    }

}
