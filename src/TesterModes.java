import javafx.scene.Group;
import javafx.scene.Scene;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
//author: Tanisha Nalavadi, Melissa Leal

/**
 * Purpose: This class is incomplete, but it manages the different tester modes, and controls extracting the data from
 * the different text files
 */
public class TesterModes{
    private Paddle myPaddle;
    public static final int SCREEN_SIZE_Width = 840;
    public static final int SCREEN_SIZE_Height = 800;
    private Bouncer myBouncer;
    private Scene myScene;
    private BrickManager brickManager = new BrickManager();
    private Brick[][] myBrickArray;


     {
        try {
            myBrickArray = brickManager.createBrickArray(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public  Group myRoot;
    private StatusDisplay Status = new StatusDisplay();

    public ArrayList<Double> extractInfo(int mode) throws Exception{
        ArrayList<Double> dataArray = new ArrayList<>();
        InputStreamReader data = new InputStreamReader(TesterModes.class.getResourceAsStream(String.format("mode%d",mode)));
        BufferedReader input = new BufferedReader(data);
        String inputLine;
        while ((inputLine = input.readLine()) != null)
            dataArray.add(Double.parseDouble(inputLine));
        input.close();
        return dataArray;
    }
    public void stepMode2(double elapsedTime) throws Exception {
        ArrayList<Double> ballInfo = extractInfo(2);
        myBouncer.looseALife();
       // Status.displayBar(Rules.myScore, Rules.myLives, 1);
        if (myBouncer.getState() == 1) {
            myBouncer.getView().setX(ballInfo.get(0));
            myBouncer.getView().setY(ballInfo.get(1));
        } else if (myBouncer.getState() == 2) {
            myBouncer.move(elapsedTime);
            //myBouncer.getXVelocity() = ballInfo.get(2);
           // myBouncer.getYVelocity() = ballInfo.get(3);
        }
        myPaddle.paddleRules();
        myBouncer.bounce(myScene.getWidth(), myPaddle, myBrickArray);

    }
    public void stepMode3(double elapsedTime) throws Exception {
        ArrayList<Double> ballInfo = extractInfo(3);
        myBouncer.looseALife();
      //  Status.displayBar(Rules.myScore, Rules.myLives, 1);
        if (myBouncer.getState() == 1) {
            myBouncer.getView().setX(ballInfo.get(0));
            myBouncer.getView().setY(ballInfo.get(1));
        } else if (myBouncer.getState() == 2) {
            myBouncer.move(elapsedTime);
           //myBouncer.getXVelocity() = ballInfo.get(2);
           // myBouncer.getYVelocity() = ballInfo.get(3);
        }
        myPaddle.paddleRules();
        myBouncer.bounce(myScene.getWidth(), myPaddle, myBrickArray);

    }
    public void stepMode4(double elapsedTime) throws Exception{
        ArrayList<Double> ballInfo = extractInfo(4);
        myBouncer.looseALife();
       // Status.displayBar(Rules.myScore, Rules.myLives, 1);
        if (myBouncer.getState() == 1) {
            myBouncer.getView().setX(ballInfo.get(0));
            myBouncer.getView().setY(ballInfo.get(1));
        } else if (myBouncer.getState() == 2) {
            myBouncer.move(elapsedTime);
           // myBouncer.getXVelocity() = ballInfo.get(2);
            //myBouncer.getYVelocity() = ballInfo.get(3);
        }
        myPaddle.paddleRules();
        myBouncer.bounce(myScene.getWidth(), myPaddle, myBrickArray);
    }

}
