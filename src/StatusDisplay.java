public class StatusDisplay {
    private double myScore=0;
    private int myLevel=1;

    private int myLives=3;

    public StatusDisplay(double score, int level, int lives){
        myScore=score;
        myLevel=level;
        myLives=lives;

    }
    public void updateScore(double score){
        myScore+=score;
    }
    public void updateLevel(){
        myLevel+=1;
    }
    public void updateLives(){
        myLives-=1;
        System.out.print(myLives);
    }
    public double getScore(){
        return myScore;
    }
    public double getLevel(){
        return myLevel;
    }
    public double getLives(){
        return myLives;
    }

}
