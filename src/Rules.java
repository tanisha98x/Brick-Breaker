//author: Tanisha Nalavadi, Melissa Leal\

/**
 * This class delimits the rules of the game and enables the game to function in order to loose and to win, loose lives and gain a score
 */
public class Rules {
    private int myLives;
    private int myScore;
    private int myLevel;
    BrickManager myBrickManager;

    public Rules(int lives, int score, int level, BrickManager brickManager){
         myLives =lives;
         myScore = score;
         myLevel = level;
         myBrickManager = brickManager;
    }

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

    public int getMyScore(){
        return myScore;
    }

    public void updateScore(int num){ myScore+=num;
    }

    public int getMyLives(){
        return myLives;
    }

    public void updateLife(int life) {
        myLives+=life;
    }

    public int getMyLevel(){
        return myLevel;
    }

    public void updateLevel() {
        myLevel+=1;
    }

    public void setMyLevel(int level){
        myLevel = level;
    }

    public void resetRules(){
        myScore=0;
        myLives=3;
        myLevel=1;
    }


}