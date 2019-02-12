import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

//author: Tanisha Nalavadi, Melissa Leal

/**
 * This class creates the bouncer object and specifies how it bounces and what occurs when it does
 */

public class Bouncer  {
    private static final String BOUNCER_IMAGE = "ball2.png";
    private double myVelocityX = 120;
    private double myVelocityY = 120;
    private ImageView myImageView;
    private int myState;
    private Rules myRules = new Rules();
    private BrickManager mybrickManager = new BrickManager();
    private display myDisplay = new display();

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
        if (intersect(this, paddle)) {
            myVelocityY *= -1;
            updateVelocity();
        }
        if (myImageView.getX() <= 0 || myImageView.getX() + myImageView.getImage().getWidth() >= screenWidth) {
            myVelocityX *= -1;
        }
        if (myImageView.getY() <= 0) {
            myVelocityY *= -1;
        }

        for (int i=0; i<mybrickManager.getBrickNumberDown(); i++) {

            for (int k=0; k<mybrickManager.getBrickNumberAcross(); k++) {

                if (myBrickArray[i][k] != null) {

                    if (hitBrick(this, myBrickArray[i][k]) && !myBrickArray[i][k].getInvisibility()) {
                        collision(this, myBrickArray[i][k]);
                        if (myBrickArray[i][k].getMyBrickType() == 5) { //powerUp
                            myRules.updateScore(5);
                        }
                        if (myBrickArray[i][k].getMyBrickType() == 6) {
                            myRules.updateScore(1);
                        }
                        if (myBrickArray[i][k].getMyBrickType() == 7) {
                            myRules.updateScore(1);
                        }
                        myBrickArray[i][k].updateMyHits(-1);
                        if(myBrickArray[i][k].getMyHitsLeft() ==0) {
                            myBrickArray[i][k].updateMyInvisbiltiy(true);
                            mybrickManager.removeBrick(myBrickArray[i][k], myDisplay.getMyGameRoot());
                            mybrickManager.updateBrickNumber(-1);
                        }
                        else{
                            Brick myBrick= myBrickArray[i][k];
                            mybrickManager.removeBrick(myBrickArray[i][k], myDisplay.getMyGameRoot());
                            mybrickManager.addBrick(myBrick.changeBrickType(myBrick, myBrick.getMyHitsLeft()), myDisplay.getMyGameRoot());
                            myBrickArray[i][k]=myBrick.changeBrickType(myBrick, myBrick.getMyHitsLeft());
                        }
                        //myRules.updateScore(1);
                    }
                }
            }
        }
    }
    public static Boolean intersect(Bouncer ball, Paddle paddle){
        if (ball.getView().intersects(paddle.getView().getBoundsInParent())){
            return true;
        }
        return false;
    }

    public static void collision(Bouncer ball, Brick brick){
        if (Math.abs(brick.getImage().getX()-ball.getView().getX())<40 ||Math.abs(brick.getImage().getX()+brick.getImage().getFitWidth()-ball.getView().getX())<40 ){
            ball.updateVelocityX(-1);
        }
        if (Math.abs(brick.getImage().getY()-ball.getView().getY())<40 ||Math.abs(brick.getImage().getY()+brick.getImage().getFitHeight()-ball.getView().getY())<40 ){
            ball.updateVelocityY(-1);
        }
    }

    public static Boolean hitBrick(Bouncer ball, Brick mybrick){
        if (ball.getView().intersects(mybrick.getNode().getBoundsInParent())){
            return true;
        }
        return false;
    }

    public ImageView getView(){
        return myImageView;
    }
    public void looseALife(){
        if(this.getView().getY()>= display.SCREEN_SIZE_HEIGHT){
            myRules.updateLife(-1);
            this.myState = 1;
        }
    }

    public int getState(){
        return myState;
    }

    public void setState(int state){
        myState = state;
    }
    public void updateVelocityX(double vel){
        myVelocityX *= vel;
    }
    public void updateVelocityY(double vel){
        myVelocityY *= vel;
    }
}




