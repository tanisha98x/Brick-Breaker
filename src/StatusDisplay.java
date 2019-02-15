import javafx.scene.text.Text;
//author: Tanisha Nalavadi, Melissa Leal

/**
 * Purpose: This class manages the display of the status bar that holds the number of lives left, score, level and high score
 */
public class StatusDisplay {
    private Text statusBar = new Text();

    /**
     * Purpose: Creates a Display bar that contains the score, high score, levels and lives left. If the score exceeds the existing
     * high score, the score becomes the high score
     * @param score
     * @param lives
     * @param level
     * @param highScore
     * @return Text display bar
     */
    public Text displayBar(int score, int lives, int level, int highScore){
        if (highScore<score){
            statusBar.setText("Score: "+ (score)+ "  Lives: "+ (lives)+ "  Level: "+ level + "  High Score: "+ score);
        }
        else{
            statusBar.setText("Score: "+ (score)+ "  Lives: "+ (lives)+ "  Level: "+ level+ "  High Score: "+ highScore);}
        statusBar.setX(550);
        statusBar.setY(50);

        return statusBar;
    }
}
