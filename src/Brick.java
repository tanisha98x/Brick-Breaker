import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
//author: Tanisha Nalavadi, Melissa Leal
public class Brick {

    private ImageView myImageView;
    private int myHitsLeft;
    private boolean myInvisibility;
    private int myX;
    private int myY;
    private int myBrickType;

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
    public Node getNode(){
        return myImageView;
    }
    public ImageView getImage(){
        return myImageView;
    }
    public int getMyHitsLeft(){
        return myHitsLeft;
    }
    public int getMyBrickType(){
        return myBrickType;
    }
    public boolean getInvisibility(){
        return myInvisibility;
    }
    public void updateMyHits(int hits){
        myHitsLeft+= hits;
    }
    public void updateMyInvisbiltiy(boolean invisible){
        myInvisibility = invisible;
    }
   public Brick changeBrickType(Brick myBrick, int HitsLeft) {
       return new Brick(HitsLeft, myBrick.myY, myBrick.myX);
   }

}
