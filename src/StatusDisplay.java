import javafx.scene.text.Text;

public class StatusDisplay {

    private Text statusBar = new Text();

    public Text displayBar(int myScore, int myLives, int myLevel){
        statusBar.setText("Score: "+ Integer.toString(myScore)+ "  Lives: "+ Integer.toString(myLives)+ "  Level: "+ myLevel);
        statusBar.setX(600);
        statusBar.setY(50);
        return statusBar;
    }



}
