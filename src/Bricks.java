import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.awt.*;

public class Bricks {

    private ImageView myImageView;
    private double myX;
    private double myY;
    private double myHitsLeft;
    private boolean myPowerUp;
    public Rectangle myBrick;

public Bricks(double xpos, double ypos, double width, double height, boolean powerUp, double hitsLeft){//do we need a constructor???
    Rectangle brick= new Rectangle(xpos, ypos, width, height);
    myBrick=brick;
    myHitsLeft=hitsLeft;
    myPowerUp=powerUp;
    brick.setStroke(javafx.scene.paint.Color.BLACK);
    if (powerUp){
        brick.setFill(Color.RED);
    }
    else if (myHitsLeft==1){
        brick.setFill(Color.WHITE);
    }
    else if (myHitsLeft==2){
        brick.setFill(Color.GREY);
    }
    else if (myHitsLeft==3){
        brick.setFill(Color.BLACK);
    }

}


public static Bricks [][] createBrickArray(int brickNumberAcross, int brickNumberDown,double brickAreaWidth, double widthGap,double brickAreaHeight, double heightGap){
    Bricks [][] brickArray= new Bricks [brickNumberAcross][brickNumberDown];
    double startingXpos=calculateStartingXPos(brickAreaWidth);
    double startingYpos=calculateStartingYPos(brickAreaHeight);
    double brickWidth= calculateBrickWidth( brickAreaWidth,  widthGap,  brickNumberAcross);
    double brickHeight= calculateBrickHeight( brickAreaHeight,  heightGap,  brickNumberDown);

    for (int i=0; i<brickNumberAcross; i++){
        for (int k=0; k<brickNumberDown; k++){
            if (k==0){startingYpos=calculateStartingYPos(brickAreaHeight);}
            brickArray[i][k]=new Bricks(startingXpos, startingYpos, brickWidth, brickHeight, false, 1);
            startingYpos+=heightGap+brickHeight;

        }
        startingXpos+=widthGap+brickWidth;
    }
    return brickArray;
}

public static double calculateBrickWidth(double brickAreaWidth, double widthGap, int brickNumberAcross){
    return ((brickAreaWidth-(widthGap*(brickNumberAcross-2)))/brickNumberAcross);
}

public static double calculateBrickHeight(double brickAreaHeight, double heightGap, int brickNumberDown){
    return ((brickAreaHeight-(heightGap*(brickNumberDown-2)))/brickNumberDown);
    }

public static double calculateStartingXPos(double brickAreaWidth){//need to work on alignment but looks ok for now
    return (display.SCREEN_SIZE-brickAreaWidth)/2.5;
    }


    public  static double calculateStartingYPos(double brickAreaHeight){
        return (display.SCREEN_SIZE-brickAreaHeight)/3;
    }









}
