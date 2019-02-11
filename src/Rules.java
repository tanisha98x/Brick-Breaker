//author: Tanisha Nalavadi, Melissa Leal
public class Rules {
    private static int myLives=3;
    private static int myScore = 0;
    private static int myLevel = 1;


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
       if(BrickManager.myBrickNumber == 0){
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

    public void updateScore(int num){
        myScore+=num;
    }

    public int getLives(){
        return myLives;
    }

    public static void updateLife(int life) {
        myLives+=life;
    }

    public int getMyLevel(){
        return myLevel;
    }

    public static void updateLevel(int lev) {
        myLevel+=lev;
    }

    public  void resetRules(){
        myScore=0;
        myLives=3;
        myLevel=1;
    }


}
