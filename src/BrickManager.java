import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BrickManager {

    public static Brick [][] createBrickArray(int level) throws Exception{

        //read first two ints which will instantiate brickNumberAcross, brickNumberDown
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStreamReader data = new InputStreamReader(loader.getResourceAsStream(String.format("Level%dConfiguration",level)));
        BufferedReader input = new BufferedReader(data);
        String inputLine;

        int brickNumberDown = Integer.parseInt(input.readLine());
        int brickNumberAcross = Integer.parseInt(input.readLine());
        Brick[][]brickArray = new Brick[brickNumberDown][brickNumberAcross];
        for (int i = 0; i < brickNumberDown; i++) {
            for (int k = 0; k < brickNumberAcross; k++) {
                int read = Integer.parseInt(input.readLine());
                if (read != 0) {
                    brickArray[i][k] = new Brick(read, i, k);
                }
            }

        }
        return brickArray;
    }


}
