import javafx.scene.text.Text;
//author: Tanisha Nalavadi, Melissa Leal

/**
 * Manages the display of the status bar
 */
public class StatusDisplay {
    private Rules myRules= new Rules();
    private Text statusBar = new Text();
    private HighScore myHighScore = new HighScore();

    public Text displayBar(){
        if (myHighScore.getMyHighScore()<myRules.getMyScore()){
            statusBar.setText("Score: "+ (myRules.getMyScore())+ "  Lives: "+ (myRules.getMyLives())+ "  Level: "+ myRules.getMyLevel()+ "  High Score: "+ myRules.getMyScore());
        }
        else{
            statusBar.setText("Score: "+ (myRules.getMyScore())+ "  Lives: "+ (myRules.getMyLives())+ "  Level: "+ myRules.getMyLevel()+ "  High Score: "+ myHighScore.getMyHighScore());}
        statusBar.setX(550);
        statusBar.setY(50);

        return statusBar;
    }
}
