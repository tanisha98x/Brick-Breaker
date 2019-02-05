import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class TesterModes{
    private Paddle myPaddle;
    public static final int SCREEN_SIZE = 800;
    private Bouncer myBouncer;
    private Scene myScene;
    private Rules myRules = new Rules();
    private static Bricks[][] myBrickArray= BrickManager.createBrickArray(4,7,SCREEN_SIZE*0.8, 20, SCREEN_SIZE*0.6, 10);
    public static Group myRoot;
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
        Status.displayBar(Rules.myScore, Rules.myLives, 1);
        if (myBouncer.myState == 1) {
            myBouncer.getView().setX(ballInfo.get(0));
            myBouncer.getView().setY(ballInfo.get(1));
        } else if (myBouncer.myState == 2) {
            myBouncer.move(elapsedTime);
            myBouncer.myVelocityX = ballInfo.get(2);
            myBouncer.myVelocityY = ballInfo.get(3);
        }
        myPaddle.paddleRules();
        myBouncer.bounce(myScene.getWidth(), myPaddle, myBrickArray);

    }
    public void stepMode3(double elapsedTime) throws Exception {
        ArrayList<Double> ballInfo = extractInfo(3);
        myBouncer.looseALife();
        Status.displayBar(Rules.myScore, Rules.myLives, 1);
        if (myBouncer.myState == 1) {
            myBouncer.getView().setX(ballInfo.get(0));
            myBouncer.getView().setY(ballInfo.get(1));
        } else if (myBouncer.myState == 2) {
            myBouncer.move(elapsedTime);
            myBouncer.myVelocityX = ballInfo.get(2);
            myBouncer.myVelocityY = ballInfo.get(3);
        }
        myPaddle.paddleRules();
        myBouncer.bounce(myScene.getWidth(), myPaddle, myBrickArray);

    }
    public void stepMode4(double elapsedTime) throws Exception{
        ArrayList<Double> ballInfo = extractInfo(4);
        myBouncer.looseALife();
        Status.displayBar(Rules.myScore, Rules.myLives, 1);
        if (myBouncer.myState == 1) {
            myBouncer.getView().setX(ballInfo.get(0));
            myBouncer.getView().setY(ballInfo.get(1));
        } else if (myBouncer.myState == 2) {
            myBouncer.move(elapsedTime);
            myBouncer.myVelocityX = ballInfo.get(2);
            myBouncer.myVelocityY = ballInfo.get(3);
        }
        myPaddle.paddleRules();
        myBouncer.bounce(myScene.getWidth(), myPaddle, myBrickArray);
    }

}
