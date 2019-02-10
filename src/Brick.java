import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Brick {
    private ImageView myImageView;
    public double myHitsLeft;
    public boolean myPowerUp;
    public boolean myInvisibility;

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

    }

    public Node getNode(){
        return myImageView;
    }

   public Brick changeBrickType(Brick myBrick, double HitsLeft){
        myBrick

    }


}
