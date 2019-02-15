import java.io.*;
//author: Tanisha Nalavadi, Melissa Leal

/**
 * Purpose: This class creates a HighScore text file to keep track of all the scores of all times the game has been played and maintains
 * the highestScore
 * Dependencies: This class creates instances of other classes and calls on other public methods from other classes in this package
 */
public class HighScore {

    private int myHighScore;
    private BrickManager brickManager = new BrickManager();
    private Rules myRules = new Rules(3, 0, 1, brickManager);

    /**
     * Purpose: Parses over the files that stores the scores of every game and returns the highest score that is then displayed on the status bar
     * @param file that holds the high scores
     * @return the highest value in the file
     */
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

    /**
     * Purpose: Sets the display for high score at the end of the game and declares if the user has reached a new high score
     * @param points
     * @param highScore
     * @return text that is added to the game end text display
     */
    public String returnHighScoreDisplay(int points, int highScore){

        System.out.print("high "+ myHighScore);

        if (points == highScore) {
            return ("You have a high score of "+ highScore);
        } else {
            return ("The all time high score was " + highScore);
        }
    }

    /**
     * Purpose: Adds the current score to the score maintainer file
     * @param file
     * @param highscore
     */
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

    /**
     * Purpose: Getter function for high score private instance variable
     * @return int high score
     */
    public int getMyHighScore(){
        return myHighScore;
    }

    /**
     * Purpose: Updates the high score
     * @param x
     */
    public void updateHighScore(int x){
        myHighScore=x;
    }
}
