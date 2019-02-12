import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import javafx.scene.Group;
//author: Tanisha Nalavadi, Melissa Leal
public class BrickManager {

    private static int myBrickNumber;

    public static Brick [][] createBrickArray(int level) throws Exception{

        //read first two ints which will instantiate brickNumberAcross, brickNumberDown
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Scanner data = new Scanner(loader.getResourceAsStream(String.format("Level%dConfiguration",level)));
        int brickNumberDown = data.nextInt();
        int brickNumberAcross = data.nextInt();
        Brick[][]brickArray = new Brick[brickNumberDown][brickNumberAcross];
        for (int i = 0; i < brickNumberDown; i++) {
            for (int k = 0; k < brickNumberAcross; k++) {
                int read = data.nextInt();
                if (read != 0) {
                    brickArray[i][k] = new Brick(read, i, k);
                    myBrickNumber+=1;
                }
                else {
                    brickArray[i][k] = null;
                }
            }

        }
        return brickArray;
    }
    public Group removeBrick(Brick myBrick, Group myroot){
        if(myBrick.myInvisibility) {
            myroot.getChildren().remove(myBrick.getNode());
        }
        return myroot;
    }

    public Group addBrick(Brick myBrick, Group myroot){
        myroot.getChildren().add(myBrick.getNode());
        return myroot;
    }
    public int getMyBrickNumber(){
        return myBrickNumber;
    }
    public static void updateBrickNumber(int x){
        myBrickNumber+=x;
    }

}




