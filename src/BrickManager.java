import java.util.Scanner;
import javafx.scene.Group;
//author: Tanisha Nalavadi, Melissa Leal

/**
 * Purpose: This class manages brick array creation to make the levels, remove bricks, addbricks, and manages the number of bricks
 * currently in the level to enable win/loose regulation. It also contains methods specific to the brick array such as updating the brick number
 */
public class BrickManager {

    private static int myBrickNumber;
    private static int brickNumberDown;
    private static int brickNumberAcross;

    /**
     * Purpose: Creates a brick array that stores the brick objects
     * @param level
     * @return
     * @throws Exception
     */
    public static Brick [][] createBrickArray(int level) throws Exception{

        //read first two ints which will instantiate brickNumberAcross, brickNumberDown
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Scanner data = new Scanner(loader.getResourceAsStream(String.format("Level%dConfiguration",level)));
        brickNumberDown = data.nextInt();
        brickNumberAcross = data.nextInt();
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

    /**
     * Purpose: Removes a brick from a given group; called when the bouncer destroys the brick
     * @param myBrick
     * @param myroot
     * @return an altered group
     */
    public Group removeBrick(Brick myBrick, Group myroot){
        if(myBrick.getInvisibility()) {
            myroot.getChildren().remove(myBrick.getNode());
        }
        return myroot;
    }

    /**
     * Purpose: Adds a brick to a given group
     * @param myBrick
     * @param myroot
     * @return an altered group
     */
    public Group addBrick(Brick myBrick, Group myroot){
        myroot.getChildren().add(myBrick.getNode());
        return myroot;
    }

    /**
     * Purpose: Returns the brick number (ie the number of bricks left)
     * @return int brick number
     */
    public static int getMyBrickNumber(){
        return myBrickNumber;
    }

    /**
     * Purpose: Updates the brick number of the brick count
     * @param x
     */
    public static void updateBrickNumber(int x){
        myBrickNumber+=x;
    }

    /**
     * Purpose: Returns the number of bricks down, as read in the configuration file
     * @return int
     */
    public int getBrickNumberDown(){
        return brickNumberDown;
    }

    /**
     * Purpose: Returns the number of bricks across, as read in the configuration file
     * @return int
     */
    public int getBrickNumberAcross(){
        return brickNumberAcross;
    }
}




