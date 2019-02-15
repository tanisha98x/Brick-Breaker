Breakout Game
====

This project implements the game of Breakout.

### Timeline

Start Date: 31st January

Finish Date: 11th February

Hours Spent: 37.5

### Resources Used
Keeping track of the high score: https://stackoverflow.com/questions/26443957/save-game-scores-to-file-and-determine-the-high-score


### Running the Program

Main class: Display

Data files needed: Ball, Paddle, Brick images, level configuration files, test files, High score tracker file

Key/Mouse inputs: 
1. Click= Starts the game at the beginning
2. SPACE= Changes the state of the bouncer, Restarts the game after it has ended
3. .,/= Testing
4. RIGHT/LEFT= Moves the paddle left and right

Cheat keys:
1. X= Adds a +50 bonus score
2. R= Brings back the bouncer to the paddle
3. L= Adds an extra life
4. K= Extends the width of the paddle
5. 1/2/3/4= Changes the level

Known Bugs: If the speed of the bouncer is increased, the bounce function malfunctions as the per pixel movement increases and the intersection is thus missed, After running a single test no way to restart the game

Extra credit: 
1. Every time the ball hits the paddle, the speed increases.
2. Added an extra level
3. Different colored bricks require a different number of hits to die, every time the bouncer hits a brick it changes color to a lower level brick until it reaches the lowest level (blue) and then disappears 
4. Method present to change the paddle image
5. When the paddle reaches the end of the screen and you keep moving it, it will emerge from the other side of the screen


### Notes
Power Ups:
1. Brick type 5 adds a bonus score
2. Brick type 6 adds an extra life
3. Brick type 7 ups the level

Level Up Features:
1. Number of bricks increase
2. The brick types change- more bricks that require more hits to die
3. The paddle size decreases
