
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Random;

public class Bouncer {
    public static final String BOUNCER_IMAGE = "ball2.png";
    public static int BOUNCER_SPEED = 15;
    private double x_Direction;
    private double y_Direction;

    private double myX;
    private double myY;
    private double myVelocityX = 100;
    private double myVelocityY = 100;
    private ImageView myImageView;
    private Random myRandom = new Random();

    public Bouncer(double xPos, double yPos) {
        var image = new Image(this.getClass().getClassLoader().getResourceAsStream(BOUNCER_IMAGE));
        myImageView =  new ImageView(image);
        myImageView.setX(xPos);
        myImageView.setY(yPos);
        myX = myImageView.getX();
        myY = myImageView.getX();
    }

    public double getXVel() {
        return myVelocityX;
    }

    public double getYVel() {
        return myVelocityY;
    }

    //
//
//
//
    public void bouncer(String BOUNCER_IMAGE){
        var image = new Image(this.getClass().getClassLoader().getResourceAsStream(BOUNCER_IMAGE));
        myImageView =  new ImageView(image);
        myImageView.setX(myRandom.nextInt());
        myX = myImageView.getX();
        myImageView.setX(myRandom.nextInt());
        myY = myImageView.getX();
    }
    public void move(double elapsedTime){
        myImageView.setY(myImageView.getY() + myVelocityY * elapsedTime);
        myImageView.setX(myImageView.getX() + myVelocityX * elapsedTime);
    }

    public void bounce(double screenWidth, double screenHeight, Paddle paddle){
        if (display.intersect(this,  paddle)){
            myVelocityY*=-1;
        }
        if(myImageView.getX()<=0||myImageView.getX()+myImageView.getImage().getWidth()>= screenWidth){//add width and height
            myVelocityX*=-1;
        }
        if(myImageView.getY()<=0){//||myImageView.getY()>= screenHeight
            myVelocityY*=-1;
        }
    }

    public ImageView getView(){
        return myImageView;
    }
//}
}