public class Rules {
    public static int myLives=3;
    public static int myScore = 0;


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
       if(Bricks.myBrickNumber == 0){
           status = true;
       }
       else{
           status = false;
       }
       return status;
    }
    public int getScore(){
        return myScore;

    }
}
