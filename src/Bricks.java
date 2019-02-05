import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.io.File;
import java.util.*;

import java.awt.*;

public class Bricks {

    private ImageView myImageView;
    private double myX;
    private double myY;
    private double myHitsLeft;
    private boolean myPowerUp;
    public Rectangle myBrick;
    public static int myBrickNumber;
    public File levelConfiguration= new File("testfile1.txt");
    //public int [][] hitsLeftArray= createHitsLeftArray(levelConfiguration, 4,7);
    public boolean myInvisibility;

public Bricks(double xpos, double ypos, double width, double height, boolean powerUp, double hitsLeft ){//do we need a constructor???
    Rectangle brick= new Rectangle(xpos, ypos, width, height);
    myInvisibility=false;
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




// private int [][] createHitsLeftArray(File levelConfiguration, int brickNumberAcross, int brickNumberDown){
//     int [][] hitsLeftArray= new int [brickNumberAcross][brickNumberDown];
//     int row=0;
//     try{
//         Scanner sc = new Scanner(levelConfiguration);
//         while (sc.hasNextLine()){
//             String [] filelinearray= sc.nextLine().split(" ");
//             for (int i=0; i<filelinearray.length; i++){
//                 hitsLeftArray[i][row]=Integer.parseInt(filelinearray[i].substring(0));
//             }
//             row+=1;
//         }
//         sc.close();
//     }
//     catch(Exception error){
//         throw new IllegalArgumentException("Filename Invalid");
//     }
//     for (int k=0; k<4; k++){
//         for (int t=0; t<7; t++){
//             System.out.print(hitsLeftArray[k][t]+" ");
//         }System.out.println();
//     }
//     return hitsLeftArray;
//
//
//
// }









}
