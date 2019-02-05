
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

import java.awt.*;
import java.util.Random;

public class Bouncer  {
    public static final String BOUNCER_IMAGE = "ball2.png";
    public static int BOUNCER_SPEED = 15;
    private double x_Direction;
    private double y_Direction;

    private double myX;
    private double myY;
    public double myVelocityX = 100;
    public double myVelocityY = 100;
    public int myState;
    private ImageView myImageView;
    private Random myRandom = new Random();

    public Bouncer(double xPos, double yPos, int ballState) {
        var image = new Image(this.getClass().getClassLoader().getResourceAsStream(BOUNCER_IMAGE));
        myImageView =  new ImageView(image);
        myImageView.setX(xPos);
        myImageView.setY(yPos);
        myState = ballState;
    }

    public double getXVel() {
        return myVelocityX;
    }

    public double getYVel() {
        return myVelocityY;
    }

    public void updateVelocity(){ //ball moves a little faster everytime it hits the paddle--Power Up
        myVelocityX+=5;
        myVelocityY+=5;
    }


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
                    Rules.myScore += 1;
                    Bricks.myBrickNumber -=1;
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
    public void looseALife(){
        if(this.getView().getX() >= display.SCREEN_SIZE || this.getView().getY()>= display.SCREEN_SIZE){
            Rules.myLives -= 1;
            this.myState = 1;
        }
    }

}