//author: Tanisha Nalavadi, Melissa Leal
public class Rules {
    private static int myLives=3;
    private static int myScore = 0;
    private int myLevel = 1;
    private BrickManager myBrickManager = new BrickManager();


    public boolean checkForLoss(){
        boolean status;
        if(myLives == 0){
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
            myLevel +=1;
        }
        else{
            status = false;
        }
        return status;
    }

    public int getMyScore(){
        return myScore;
    }

    public void updateScore(int num){
        myScore+=num;
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

    public void updateLevel(int lev) {
        myLevel+=lev;
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