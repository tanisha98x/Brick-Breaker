import javafx.scene.text.Text;
//author: Tanisha Nalavadi, Melissa Leal
public class StatusDisplay {
    private Rules myRules= new Rules();

    private Text statusBar = new Text();

    public Text displayBar(){
        if (HighScore.myHighScore<myRules.myScore){
            statusBar.setText("Score: "+ (myRules.myScore)+ "  Lives: "+ (myRules.myLives)+ "  Level: "+ myRules.myLevel+ "  High Score: "+ myRules.myScore);
        }
        else{
        statusBar.setText("Score: "+ (myRules.myScore)+ "  Lives: "+ (myRules.myLives)+ "  Level: "+ myRules.myLevel+ "  High Score: "+ HighScore.myHighScore);}
        statusBar.setX(550);
        statusBar.setY(50);

        return statusBar;
    }



}
