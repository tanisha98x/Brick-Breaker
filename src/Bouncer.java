
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

import java.awt.*;
import java.util.Random;

//author: Tanisha Nalavadi, Melissa Leal

public class Bouncer  {
    public static final String BOUNCER_IMAGE = "ball2.png";
    public static int BOUNCER_SPEED = 100;
    private double x_Direction;
    private double y_Direction;
    private double myX;
    private double myY;
    public double myVelocityX = 200;
    public double myVelocityY = 200;
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

    public void updateVelocity(){
        myVelocityX+=5;
        myVelocityY+=5;
    }

//    public void bouncer(String BOUNCER_IMAGE){
//        var image = new Image(this.getClass().getClassLoader().getResourceAsStream(BOUNCER_IMAGE));
//        myImageView =  new ImageView(image);
//        myImageView.setX(myRandom.nextInt());
//        myX = myImageView.getX();
//        myImageView.setX(myRandom.nextInt());
//        myY = myImageView.getX();
//    }

    public void move(double elapsedTime){
        myImageView.setY(myImageView.getY() + myVelocityY * elapsedTime);
        myImageView.setX(myImageView.getX() + myVelocityX * elapsedTime);
    }

    public void bounce(double screenWidth, Paddle paddle, Brick [][] myBrickArray) {
        if (display.intersect(this, paddle)) {//edit so Y changes when top and x when side
            myVelocityY *= -1;
            updateVelocity();
        }

        if (myImageView.getX() <= 0 || myImageView.getX() + myImageView.getImage().getWidth() >= screenWidth) {//add width and height
            myVelocityX *= -1;
        }
        if (myImageView.getY() <= 0) {
            myVelocityY *= -1;
        }

        for (int i=0; i<6; i++) {//remove from brick array!!

            for (int k=0; k<12; k++) {

                if (myBrickArray[i][k] != null) {

                    if (display.hitBrick(this, myBrickArray[i][k]) && !myBrickArray[i][k].myInvisibility) {
                        display.collision(this, myBrickArray[i][k]);
                        if (myBrickArray[i][k].myBrickType == 5) {
                            Rules.myScore += 5;
                        }
                        if (myBrickArray[i][k].myBrickType == 6) {
                            Rules.myLives += 1;
                        }
                        if (myBrickArray[i][k].myBrickType == 7) {
                            Rules.myLevel += 1;
                        }

                        myBrickArray[i][k].myHitsLeft -=1;

                        if(myBrickArray[i][k].myHitsLeft ==0) {
                            myBrickArray[i][k].myInvisibility = true;
                            BrickManager.removeBrick(myBrickArray[i][k], display.myGameRoot);
                            BrickManager.myBrickNumber -=1;
                        }
                        else{//changed this!!

                            Brick myBrick= myBrickArray[i][k];
                            BrickManager.removeBrick(myBrickArray[i][k], display.myGameRoot);
                            BrickManager.addBrick(myBrick.changeBrickType(myBrick, myBrick.myHitsLeft), display.myGameRoot);
                            myBrickArray[i][k]=myBrick.changeBrickType(myBrick, myBrick.myHitsLeft);
                        }
                        Rules.myScore += 1;

                        //myVelocityY *= -1;

                    }

//
//                   if (myImageView.getX()-myBrick.getImage().getX()<=30){
//                            myVelocityX*= -1;
//                        }
//                        if (this.getView().getY()==myBrick.getImage().getY() || this.getView().getY()==myBrick.getImage().getY()+myBrick.getImage().getFitHeight()){
//                            myVelocityY*= -1;
//                        }


                    }

                }
            }
        }


    public ImageView getView(){
        return myImageView;
    }
    public void looseALife(){
        if(this.getView().getY()>= display.SCREEN_SIZE_HEIGHT){
            Rules.myLives -= 1;
            this.myState = 1;
        }
    }

}