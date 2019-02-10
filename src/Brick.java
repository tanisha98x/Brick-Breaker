import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Brick {
    private ImageView myImageView;
    public int myHitsLeft;
    public boolean myPowerUp;
    public boolean myInvisibility;
    private int myX;
    private int myY;

    public Brick(int brickType, int y, int x) {
        var image =  new Image(this.getClass().getClassLoader().getResourceAsStream("brick" + brickType + ".gif"));
        myImageView = new ImageView(image);
        myInvisibility = false;
        if(brickType <= 4){
            myPowerUp = false;
            myHitsLeft = brickType;
        }
        if(brickType >4 ){
            myPowerUp = true;
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

   public Node changeBrickType(Brick myBrick, int HitsLeft){
        myBrick = new Brick(HitsLeft, myBrick.myY, myBrick.myX );
        return myBrick.getNode();
    }


}
