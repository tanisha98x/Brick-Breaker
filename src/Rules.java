//author: Tanisha Nalavadi, Melissa Leal\

/**
 * This class delimits the rules of the game and enables the game to function in order to loose and to win, loose lives and gain a score
 */
public class Rules {
    public static int myLives=3;
    public static int myScore = 0;
    public static int myLevel = 1;


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
        if(BrickManager.getMyBrickNumber() == 0){
            status = true;
            myLevel +=1;
        }
        else{
            status = false;
        }
        return status;
    }
    public int getScore(){
        return myScore;

    }

    public  void resetRules(){
        myScore=0;
        myLives=3;
        myLevel=1;
    }
}
