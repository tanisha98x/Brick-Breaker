
    import javafx.scene.Group;
    import javafx.scene.image.Image;
    import javafx.scene.image.ImageView;
    import javafx.scene.shape.Rectangle;

    import java.awt.*;
    import java.util.Random;

    //author: Tanisha Nalavadi, Melissa Leal

    public class Bouncer  {
        private static final String BOUNCER_IMAGE = "ball2.png";
        public double myVelocityX = 120;
        public double myVelocityY = 120;
        private ImageView myImageView;
        public int myState;

        public Bouncer(double xPos, double yPos, int ballState) {
            var image = new Image(this.getClass().getClassLoader().getResourceAsStream(BOUNCER_IMAGE));
            myImageView =  new ImageView(image);
            myImageView.setX(xPos);
            myImageView.setY(yPos);
            myState=ballState;
        }

        public void updateVelocity(){
            myVelocityX+=5;
            myVelocityY+=5;
        }


        public void move(double elapsedTime){
            myImageView.setY(myImageView.getY() + myVelocityY * elapsedTime);
            myImageView.setX(myImageView.getX() + myVelocityX * elapsedTime);
        }


        public void bounce(double screenWidth, Paddle paddle, Brick [][] myBrickArray) {
            if (display.intersect(this, paddle)) {
                myVelocityY *= -1;
                updateVelocity();
            }

            if (myImageView.getX() <= 0 || myImageView.getX() + myImageView.getImage().getWidth() >= screenWidth) {
                myVelocityX *= -1;
            }
            if (myImageView.getY() <= 0) {
                myVelocityY *= -1;
            }

            for (int i=0; i<6; i++) {

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
                            else{
                                Brick myBrick= myBrickArray[i][k];
                                BrickManager.removeBrick(myBrickArray[i][k], display.myGameRoot);
                                BrickManager.addBrick(myBrick.changeBrickType(myBrick, myBrick.myHitsLeft), display.myGameRoot);
                                myBrickArray[i][k]=myBrick.changeBrickType(myBrick, myBrick.myHitsLeft);
                            }
                            Rules.myScore += 1;

                        }


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

        public int getState(){
            return myState;
        }

        public double getXVelocity(){
            return myVelocityX;
        }

        public double getYVelocity(){
            return myVelocityY;
        }

    }