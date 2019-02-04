
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

import java.awt.*;
import java.util.Random;

public class Bouncer {
    public static final String BOUNCER_IMAGE = "ball2.png";
    public static int BOUNCER_SPEED = 15;
    private double x_Direction;
    private double y_Direction;

    private double myX;
    private double myY;
    public double myVelocityX = 200;
    public double myVelocityY = 200;
    private ImageView myImageView;
    private Random myRandom = new Random();

    public Bouncer(double velX, double vleY, double xPos, double yPos) { //added two parameters: velX and velY so that we set it up to 0 at first
        var image = new Image(this.getClass().getClassLoader().getResourceAsStream(BOUNCER_IMAGE));
        myImageView =  new ImageView(image);
        myImageView.setX(xPos);
        myImageView.setY(yPos);
        myX = myImageView.getX();
        myY = myImageView.getX();
        myVelocityY = vleY;
        myVelocityX = velX;
    }

    public double getXVel() {
        return myVelocityX;
    }

    public double getYVel() {
        return myVelocityY;
    }

    public void updateVelocity(){//ball moves a little faster everytime it hits the paddle
        myVelocityX-=5;
        myVelocityY-=5;

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
    public void moveFirst(double velX, double velY, double elapsedTime){ // this was a trial method so that I could call it but did not seem to work
        myImageView.setY(myImageView.getY() + velX * elapsedTime);
        myImageView.setX(myImageView.getX() + velY * elapsedTime);
    }
    public void move(double elapsedTime){
        moveFirst(myVelocityX, myVelocityY,elapsedTime);
    }

    public void bounce(double screenWidth, Paddle paddle, Bricks [][] myBrickArray){
        int row=-1;
        int col=-1;
        if (display.intersect(this,  paddle)){//edit so Y changes when top and x when side
            myVelocityY*=-1;
            updateVelocity();
        }
        for (Bricks [] each: myBrickArray){ //remove from brick array!!
            row+=1;
            for (Bricks object : each){



//                if (display.intersect(this,  paddle)){
//                    myVelocityY*=-1;
//                    updateVelocity();
//                }
                if (display.destroyBrick(this,  object.myBrick) && !object.myInvisibility){
                    display.myRoot.getChildren().remove(object.myBrick);
                    object.myInvisibility=true;
                    myVelocityY*=-1;

                    //myBrickArray.remove(object);
            }
        }
//        if (display.intersect(this,  paddle)){
//            myVelocityY*=-1;
//            updateVelocity();
//        }
//        if (display.destroyBrick(this,  brick)){
//            myVelocityY*=-1;
//            root.getChildren().remove(brick);


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