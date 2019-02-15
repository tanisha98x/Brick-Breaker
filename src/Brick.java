import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
//author: Tanisha Nalavadi, Melissa Leal
/**
 * Purpose: This class creates a brick object and manages all its attributes, invisibility, type(varying on the image) and hitsLeft.
 * It also contains methods specific to the brick such as updating the various attributes.
 */
public class Brick {

    private ImageView myImageView;
    private int myHitsLeft;
    private boolean myInvisibility;
    private int myX;
    private int myY;
    private int myBrickType;

    /**
     * Purpose: Constructor that creates a brick objects containing attributes like invisibility and hits left
     * @param brickType
     * @param y
     * @param x
     */
    public Brick(int brickType, int y, int x) {
        var image =  new Image(this.getClass().getClassLoader().getResourceAsStream("brick" + brickType + ".gif"));
        myImageView = new ImageView(image);
        myBrickType=brickType;
        myInvisibility = false;
        if(brickType <= 4){
            myHitsLeft = brickType;
        }
        if(brickType >4 ){
            myHitsLeft = 1;
        }
        myImageView.setX(70*x);
        myImageView.setY(55+(20*y));
        myX = x;
        myY = y;

    }

    /**
     * Purpose: Returns a Node of a given brick
     * @return Node
     */
    public Node getNode(){
        return myImageView;
    }

    /**
     * Purpose: Returns a Image View of a given brick
     * @return Image View
     */
    public ImageView getImage(){
        return myImageView;
    }

    /**
     * Purpose: Returns the number of hits left before a given brick is destroyed
     * @return int num
     */
    public int getMyHitsLeft(){
        return myHitsLeft;
    }

    /**
     * Purpose: Gives the brick type of a given brick, various brick types have various attributes
     * @return int
     */
    public int getMyBrickType(){
        return myBrickType;
    }

    /**
     * Purpose: Returns whether the brick is invisible or not; a given brick becomes invisible when the number of hits lft is zero
     * @return true if needs to be invisible
     */
    public boolean getInvisibility(){
        return myInvisibility;
    }

    /**
     * Purpose: To reduce the number of hits left everytime a given brick is hit by the bouncer
     * @param hits
     */
    public void updateMyHits(int hits){
        myHitsLeft+= hits;
    }

    /**
     * Purpose: Changes the invisibility of a given brick
     * @param invisible
     */
    public void updateMyInvisbiltiy(boolean invisible){
        myInvisibility = invisible;
    }

    /**
     * Purpose: Eveytime a brick is hit, the brick type changes and the brick on the screen changes color or becomes invisible
     * @param myBrick
     * @param HitsLeft
     * @return new Brick that is one level closer to being destroyed
     */
   public Brick changeBrickType(Brick myBrick, int HitsLeft) {
       return new Brick(HitsLeft, myBrick.myY, myBrick.myX);
   }
}
