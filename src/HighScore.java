import java.io.*;
//author: Tanisha Nalavadi, Melissa Leal

/**
 * This class creates a HighScore text file to keep track of all the scores of all times the game has been played and maintains
 * the highestScore
 */
public class HighScore {

    private int myHighScore;
    private BrickManager brickManager = new BrickManager();
    private Rules myRules = new Rules(3, 0, 1, brickManager);


    public int calculateHighScore(File file){
        int highScore = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while (line != null)
            {
                try {
                    int score = Integer.parseInt(line.trim());
                    if (score > highScore)
                    {
                        highScore = score;
                    }
                } catch (NumberFormatException e1) {
                    System.err.println("ignoring invalid score: " + line);
                }
                line = reader.readLine();
            }
            reader.close();

        } catch (IOException ex) {
            System.err.println("ERROR reading scores from file");
        }
        myHighScore=highScore;
        return highScore;
    }

    public String returnHighScoreDisplay(int points, int highScore){

        System.out.print("high "+ myHighScore);

        if (points == highScore) {
            return ("You have a high score of "+ highScore);
        } else {
            return ("The all time high score was " + highScore);
        }
    }

    public static void addScore(File file, int highscore) {
        try {
            BufferedWriter output = new BufferedWriter(new FileWriter(file, true));
            output.newLine();
            output.append("" + highscore);
            output.close();

        } catch (IOException ex1) {
            System.out.printf("ERROR writing score to file: %s\n", ex1);
        }
    }

    public int getMyHighScore(){
        return myHighScore;
    }
    public void updateHighScore(int x){
        myHighScore=x;
    }
}
