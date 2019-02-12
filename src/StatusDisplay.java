import javafx.scene.text.Text;
//author: Tanisha Nalavadi, Melissa Leal

/**
 * Manages the display of the status bar
 */
public class StatusDisplay {
    private Text statusBar = new Text();
    private HighScore myHighScore = new HighScore();

    public Text displayBar(int score, int lives, int level){
        if (myHighScore.getMyHighScore()<score){
            statusBar.setText("Score: "+ (score)+ "  Lives: "+ (lives)+ "  Level: "+ level + "  High Score: "+ score);
        }
        else{
            statusBar.setText("Score: "+ (score)+ "  Lives: "+ (lives)+ "  Level: "+ level+ "  High Score: "+ myHighScore.getMyHighScore());}
        statusBar.setX(550);
        statusBar.setY(50);

        return statusBar;
    }
}
