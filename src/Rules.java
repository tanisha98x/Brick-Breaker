//author: Tanisha Nalavadi, Melissa Leal\

/**
 * Purpose: This class delimits the rules of the game and enables the game to function in order to loose and to win,
 * loose lives and keep a score
 * Dependencies: This class creates instances of other classes and calls on other public methods from other classes in this package
 */

public class Rules {
    private int myLives;
    private int myScore;
    private int myLevel;
    private BrickManager myBrickManager;

    /**
     * Purpose: Contructor that creates a rule class object that holds the lives, score and level
     * @param lives
     * @param score
     * @param level
     * @param brickManager
     */
    public Rules(int lives, int score, int level, BrickManager brickManager){
         myLives =lives;
         myScore = score;
         myLevel = level;
         myBrickManager = brickManager;
    }

    /**
     * Purpose: Checks whether the player has lost the game (ie lives=0)
     * @param lives
     * @return boolen true if the player has lost the game
     */
    public boolean checkForLoss(int lives){
        boolean status;
        if(lives == 0){
            status = true;
        }
        else{
            status = false;
        }
        return status;
    }

    /**
     * Purpose: Checks whether the player has won the game, this happens when the player has destroyed all the bricks on the screen for a given level
     * @return true if the player has won
     */
    public boolean checkForWin(){
        boolean status;
        if(myBrickManager.getMyBrickNumber() == 0){
            status = true;
        }
        else{
            status = false;
        }
        return status;
    }

    /**
     * Purpose: Returns the score
     * @return int score
     */
    public int getMyScore(){
        return myScore;
    }

    /**
     * Purpose: Updates the score every time the player destroys a brick
     */
    public void updateScore(int num){ myScore+=num;
    }

    /**
     * Purpose: Returns the lives
     * @return int lives
     */
    public int getMyLives(){
        return myLives;
    }

    /**
     * Purpose: Updates the lives
     */
    public void updateLife(int life) {
        myLives+=life;
    }

    /**
     * Purpose: Returns the levels
     * @return int levels
     */
    public int getMyLevel(){
        return myLevel;
    }

    /**
     * Purpose: Updates the levels every time a player destroys all the bricks on the screen
     */
    public void updateLevel() {
        myLevel+=1;
    }

    /**
     * Purpose: Changes the level to a given level
     */
    public void setMyLevel(int level){
        myLevel = level;
    }

    /**
     * Purpose: Resets the lives, score and level back to the starting value
     */

    public void resetRules(){
        myScore=0;
        myLives=3;
        myLevel=1;
    }


}