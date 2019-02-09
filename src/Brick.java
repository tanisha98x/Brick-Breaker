import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Brick {
    private ImageView myImageView;
    private double myHitsLeft;
    private boolean myPowerUp;
    public static int myBrickNumber;
    public boolean myInvisibility;

    public Brick(int brickType, int y, int x) {
        var image =  new Image(this.getClass().getClassLoader().getResourceAsStream("brick" + brickType + ".gif"));
        myImageView = new ImageView(image);
        if(brickType <= 4){
            myPowerUp = false;
            myHitsLeft = brickType;
        }
        if(brickType >=4 ){
            myPowerUp = true;
            myHitsLeft = 1;
        }

        myImageView.setX(100*x);
        myImageView.setY(25*y);

    }

    public Node getNode(){
        return myImageView;
    }


}
