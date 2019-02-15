import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
//author: Tanisha Nalavadi, Melissa Leal
/**
 * Purpose: This class creates a bouncer object that holds attributes such as the state and position,  and contains methods
 * specific to the working of the bouncer including intersection, collision and bounce.
 * Dependencies: This class creates instances of other classes and calls on other public methods from other classes in this package
 */

public class Bouncer  {
    private static final String BOUNCER_IMAGE = "ball2.png";
    private double myVelocityX = 120;
    private double myVelocityY = 120;
    private ImageView myImageView;
    private int myState;
    private BrickManager mybrickManager = new BrickManager();
    private Rules myRules = new Rules(3, 0,1, mybrickManager);
    private display myDisplay = new display();

    /**
     *Purpose: Constructor for ball object that holds attributes like state
     * @param xPos
     * @param yPos
     * @param ballState
     */

    public Bouncer(double xPos, double yPos, int ballState) {
        var image = new Image(this.getClass().getClassLoader().getResourceAsStream(BOUNCER_IMAGE));
        myImageView =  new ImageView(image);
        myImageView.setX(xPos);
        myImageView.setY(yPos);
        myState=ballState;
    }

    /**
     * Purpose: Updates the velocity of the ball, called every time it hits the paddle
     */
    public void updateVelocity(){
        myVelocityX+=5;
        myVelocityY+=5;
    }

    /**
     * Purpose: Changes the position of the ball after the 'elapsed time' so it appears to be moving
     * @param elapsedTime
     */
    public void move(double elapsedTime){
        myImageView.setY(myImageView.getY() + myVelocityY * elapsedTime);
        myImageView.setX(myImageView.getX() + myVelocityX * elapsedTime);
    }

    /**
     * Purpose: Causes the ball to bounce (that is change direction) every time it hits the paddle, screen edge or brick
     * @param screenWidth
     * @param paddle
     * @param myBrickArray
     */
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
                            myRules.updateScore(1);
                        }
                        if (myBrickArray[i][k].getMyBrickType() == 6) {
                            myRules.updateLevel();
                        }
                        if (myBrickArray[i][k].getMyBrickType() == 7) {
                            myRules.updateLife(1);
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
                        myRules.updateScore(1);
                    }
                }
            }
        }
    }

    /**
     * Purpose: Returns true if the ball intersects with the paddle
     * @param ball
     * @param paddle
     * @return
     */
    public Boolean intersect(Bouncer ball, Paddle paddle){
        if (ball.getView().intersects(paddle.getView().getBoundsInParent())){
            return true;
        }
        return false;
    }

    /**
     * Purpose: Creates a buffer around every brick and changes the X velocity if the ball hits the sides and Y velocity if the ball hits the top or the bottom of the brick
     * @param ball
     * @param brick
     */
    public void collision(Bouncer ball, Brick brick){
        if (Math.abs(brick.getImage().getX()-ball.getView().getX())<40 ||Math.abs(brick.getImage().getX()+brick.getImage().getFitWidth()-ball.getView().getX())<40 ){
            ball.updateVelocityX(-1);
        }
        if (Math.abs(brick.getImage().getY()-ball.getView().getY())<40 ||Math.abs(brick.getImage().getY()+brick.getImage().getFitHeight()-ball.getView().getY())<40 ){
            ball.updateVelocityY(-1);
        }
    }

    /**
     * Purpose: Returns true if the ball hits a brick
     * @param ball
     * @param mybrick
     * @return true if the ball hits the brick
     */
    public Boolean hitBrick(Bouncer ball, Brick mybrick){
        if (ball.getView().intersects(mybrick.getNode().getBoundsInParent())){
            return true;
        }
        return false;
    }

    /**
     * Purpose: Returns the image view of a bouncer object
     * @return
     */
    public ImageView getView(){
        return myImageView;
    }

    /**
     * Purpose: Updates the lives to lose a life
     */
    public void looseALife(){
        if(this.getView().getY()>= display.SCREEN_SIZE_HEIGHT){
            myRules.updateLife(-1);
            this.myState = 1;
        }
    }

    /**
     * Purpose: Returns the state of the ball
     * @return int
     */
    public int getState(){
        return myState;
    }

    /**
     * Purpose: Returns the score
     * @return int
     */
    public int getMyScore(){
        return myRules.getMyScore();
    }

    /**
     * Purpose: Returns the lives
     * @return int
     */
    public int getMyLives(){ return myRules.getMyLives(); }

    /**
     * Purpose: Changes the state of the ball
     * @param state
     */
    public void setState(int state){
        myState = state;
    }

    /**
     * Purpose: Updates the X velocity of the ball
     * @param vel
     */
    public void updateVelocityX(double vel){
        myVelocityX *= vel;
    }

    /**
     * Purpose: Updates the Y velocity of the ball
     * @param vel
     */
    public void updateVelocityY(double vel){
        myVelocityY *= vel;
    }
}




