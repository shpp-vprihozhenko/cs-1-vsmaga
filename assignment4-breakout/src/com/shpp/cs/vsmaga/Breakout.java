package com.shpp.cs.vsmaga;

/* Breakout begins with ten rows of bricks, with each two rows a different color.
* The color order from the bottom up is cyan, green, yellow, orange and red.
* Using a single ball, the player must knock down as many bricks as possible
* by using the walls and/or the paddle below to ricochet the ball against
* the bricks and eliminate them. If the player's paddle misses the ball's rebound,
* he or she loses a turn. The player has three turns to try to clear screen of bricks.
* */

import acm.graphics.*;
import acm.util.RandomGenerator;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Breakout extends WindowProgram {
    /** Width and height of application window in pixels */
    public static final int APPLICATION_WIDTH = 400;
    public static final int APPLICATION_HEIGHT = 550;

    /** Dimensions of game board (usually the same) */
    private static final int WIDTH = APPLICATION_WIDTH;
    private static final int HEIGHT = APPLICATION_HEIGHT - 25; // -25 correction on the top menu in Windows

    /** Dimensions of the paddle */
    private static final int PADDLE_WIDTH = 60;
    private static final int PADDLE_HEIGHT = 10;

    /** Offset of the paddle up from the bottom */
    private static final int PADDLE_Y_OFFSET = 30;

    /** Number of bricks per row */
    private static final int NBRICKS_PER_ROW = 10;

    /** Number of rows of bricks */
    private static final int NBRICK_ROWS = 10;

    /** Separation between bricks */
    private static final int BRICK_SEP = 4;

    /** Width of a brick */
    private static final int BRICK_WIDTH =
            (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

    /** Height of a brick */
    private static final int BRICK_HEIGHT = 8;

    /** Radius of the ball in pixels */
    private static final int BALL_RADIUS = 10;

    /** Offset of the top brick row from the top */
    private static final int BRICK_Y_OFFSET = 70;

    /** Number of turns */
    private static final int NTURNS = 3;

    /* Start speed of the ball */
    private static final double SPEED_Y = 3.0;

    /* Delay for ball animation*/
    private static final double PAUSE_TIME = 15;

    /* Delay for blinking message*/
    private static final double BLINK_PAUSE = 500;

    /* Number of blinks*/
    private static final int BLINK_NUM = 2;

    /*Random generator*/
    RandomGenerator rgen = RandomGenerator.getInstance();

    private GRect paddle = null;
    private GOval ball = null;

    /* Flag for checking start of game*/
    private boolean gameIsStart = false;

    /*Flag for checking falling ball*/
    private boolean ballFell = false;

    /* Counter for downed bricks*/
    private int deletedBricksCounter = 0;


    /** Provides the main game logic:
     * User can move the paddle with mouse and beat the ball using the paddle
     * User have three attempts to knock down all bricks
     * Every new attempt not begins until the user clicks the mouse.
     * It displays the number of remaining attempts and the final result in blinking message
    * */
    public void run() {
        int turnCounter = 0; //attempt counter
        addMouseListeners();
        paddle = createPaddle(PADDLE_WIDTH, PADDLE_HEIGHT);
        ball = createBall(BALL_RADIUS);
        createBricks(); //create rows of bricks

        GLabel resultMessage = createMessage("You win!!!", Color.GREEN); //prepares a message of victory
        GLabel tryMessage = null;

        while (deletedBricksCounter < NBRICKS_PER_ROW * NBRICK_ROWS) { //play game until the player will not knock all the bricks
            if (turnCounter < NTURNS) { //check the remaining attempts
                if (turnCounter > 0) { // from the second attempt draw message with the remaining attempts
                    tryMessage = createMessage("You have " + (NTURNS - turnCounter) + " more attempt", Color.BLUE);
                    blinkMessage(tryMessage);
                }
                turnCounter++;
                paddleToStartPosition(paddle);
                ballToStartPosition(ball);
                ballFell = false; // resets flag of the fallen ball before a new attempt
                waitForClick();
                gameIsStart = true; //disable control of of the ball with the mouse
                runBall();


            }
            else { //after three lose attempts finishes the game and change message the resul message

                resultMessage = createMessage("You lose (((", Color.RED);
                break;

            }

        }

        blinkMessage(resultMessage);
        add(resultMessage); //leave the result message

    }

    /** This method blinks on the screen received label
    *
    * @param message GLabel that will be animated
    */
    private void blinkMessage(GLabel message) {
        for (int i = 0; i < BLINK_NUM; i++){
            add(message);
            pause(BLINK_PAUSE * 2);
            remove(message);
            pause(BLINK_PAUSE);
        }
    }

    /** This method create a message with result of game
    *
    * @param text Text with final message
    * @param color Color of the final text
    * @return GLabel with received text and color in the center of the window
    */
    private GLabel createMessage(String text, Color color) {
        GLabel message = new GLabel(text);
        message.setFont("Arial Black-25");
        message.setColor(color);
        double x = (WIDTH - message.getWidth()) / 2;
        double y = (HEIGHT - message.getDescent()) / 2;
        message.setLocation(x, y);
        return message;
    }

    /* This method draws a rows of the bricks with specified number and size
    * */
    private void createBricks() {
        double xStart = (WIDTH - ((NBRICKS_PER_ROW*BRICK_WIDTH) + ((NBRICKS_PER_ROW-1) *BRICK_SEP))) / 2 ; // starting coordinate x
        double yStart = BRICK_Y_OFFSET; // starting coordinate y
        double x = xStart; // coordinate x of every next brick
        double y = yStart; // coordinate y of every next brick
        Color color = null;

        for (int i=0; i < NBRICKS_PER_ROW; i++) {
            for (int j=0; j < NBRICK_ROWS; j++){
                x = xStart + ((BRICK_WIDTH + BRICK_SEP) * i);
                y = yStart + ((BRICK_HEIGHT + BRICK_SEP) * j);
                if (j < 2 ){
                    color = Color.RED;
                }
                else if (j >= 2 && j < 4){
                    color = Color.ORANGE;
                }
                else if (j >= 4 && j <6){
                    color = Color.YELLOW;
                }
                else if (j >=6 && j < 8){
                    color = Color.GREEN;
                }
                else {
                    color = Color.CYAN;
                }
                drawBrick(x, y, color);

            }


        }

    }

    /** This method draws a brick with received coordinates and color
    *
    * @param x Coordinate X of resulted brick
    * @param y Coordinate X of resulted brick
    * @param color Color of resulted brick
    */
    private void drawBrick(double x, double y, Color color) {
        GRect box = new GRect(x, y, BRICK_WIDTH, BRICK_HEIGHT);
        box.setFilled(true);
        box.setFillColor(color);
        add(box);
    }

    /** This method sets paddle in the start position
    *
    * @param paddle Paddle that will be located in the center of the window horizontally  on the line with offset
    * from down specified by constant PADDLE_Y_OFFSET
    */
    private void paddleToStartPosition(GRect paddle) {
        paddle.setLocation((WIDTH - PADDLE_WIDTH) / 2, HEIGHT - PADDLE_HEIGHT - PADDLE_Y_OFFSET);
    }

    /** This method sets ball in the start position
    *
    * @param ball Ball that will be located in the center of the window horizontally  on the line under the paddle
    */
    private void ballToStartPosition(GOval ball) {
        ball.setLocation((WIDTH / 2) - BALL_RADIUS, HEIGHT - PADDLE_Y_OFFSET - PADDLE_HEIGHT - (BALL_RADIUS * 2));
    }

    /** This method returns ball with received radius and blue color
    *
    * @param radius The radius of the ball
    * @return Blue GOval with received radius
    */
    private GOval createBall(int radius) {
        GOval ball = new GOval(radius * 2, radius * 2);
        ball.setFilled(true);
        ball.setFillColor(Color.BLUE);
        add(ball);
        return ball;
    }

    /** This method returns paddle with received size and specified color
    *
    * @param paddleWidth The width of the resulted rectangle
    * @param paddleHeight The height of the resulted rectangle
    * @return Gray rectangle with received  size
    */
    private GRect createPaddle(int paddleWidth, int paddleHeight) {
        GRect paddle = new GRect(paddleWidth, paddleHeight );
        paddle.setFilled(true);
        paddle.setFillColor(Color.GRAY);
        add(paddle);
        return paddle;
    }

    @Override
    /** Check for mouse moving
    */
    public void mouseMoved(MouseEvent e){
        movePaddle(e); //moves paddle with the mouse
        if (!gameIsStart){ //before the game starts move ball with mouse too.
            moveBall(e);
        }


    }

    /** This method moves ball with the mouse
     *
     * @param e Mouse event received from MouseListener
     * */
    private void moveBall(MouseEvent e) {
        double x = 0;
        double y = 0;
        if (e.getX() < PADDLE_WIDTH / 2){ //mouse near/over the left wall
            x = (PADDLE_WIDTH / 2) - BALL_RADIUS;
            y = HEIGHT - PADDLE_Y_OFFSET - PADDLE_HEIGHT - (BALL_RADIUS * 2);

        } else if (e.getX() > WIDTH - (PADDLE_WIDTH) / 2){ //mouse near/over the right wall
            x = WIDTH - (PADDLE_WIDTH / 2) - BALL_RADIUS;
            y = HEIGHT - PADDLE_Y_OFFSET - PADDLE_HEIGHT - (BALL_RADIUS * 2);

        } else {    //mouse at the normal distance from the wall
            x = e.getX() - BALL_RADIUS;
            y = HEIGHT - PADDLE_Y_OFFSET - PADDLE_HEIGHT - (BALL_RADIUS * 2);

        }
        ball.setLocation(x,y);
    }

    /** This method moves paddle with the mouse
     *
     * @param e Mouse event received from MouseListener
     * */
    private void movePaddle(MouseEvent e) {
        if (e.getX() < PADDLE_WIDTH / 2){ //mouse near/over the left wall
            paddle.setLocation(0,HEIGHT - PADDLE_HEIGHT - PADDLE_Y_OFFSET);
        } else if (e.getX() > WIDTH - (PADDLE_WIDTH) / 2){ //mouse near/over the right wall
            paddle.setLocation(WIDTH - PADDLE_WIDTH,HEIGHT - PADDLE_HEIGHT - PADDLE_Y_OFFSET);
        } else {    //mouse at the normal distance from the wall
            paddle.setLocation(e.getX() - (PADDLE_WIDTH / 2), HEIGHT - PADDLE_HEIGHT - PADDLE_Y_OFFSET );
        }
    }

    /* This method moves the ball, reflects it off the walls and paddle until the ball falls*/
    private void runBall() {

        double vx = 0;
        double vy = SPEED_Y;
        vx = rgen.nextDouble(1.0, 3.0); //randomly sets horizontal speed
        if (rgen.nextBoolean(0.5)) //with a 50 percent chance change the direction of movement of the ball
            vx = -vx;

        /* While ball not fells down and number of downed bricks less then total number of bricks
        * check collisions with objects and walls, moves the ball according to physics laws and knocks down bricks.
        * */
        while (!ballFell){
            if (deletedBricksCounter < NBRICKS_PER_ROW * NBRICK_ROWS) {
                vy = checkY(ball, vy);
                vx = checkX(ball, vx);

                vx = checkPaddle(vx);
                vx = checkCollidingX(ball, vx);
                vy = checkCollidingY(ball, vy);

                ball.move(vx, vy);
                pause(PAUSE_TIME);
            }
            else {
                break;
            }
        }


    }

     /** Check left and right end of the paddle for collisions with the ball
     * and change ball's direction depending on the direction of movement of the ball
     *
     * @param vx A shift of the ball on the X axis
     * @return Changed shift of the ball on the X axis
     */
    private double checkPaddle(double vx) {
        GPoint leftPaddle = new GPoint(paddle.getX() - 1, paddle.getY() + PADDLE_HEIGHT / 2);
        GPoint rightPaddle = new GPoint(paddle.getX() + PADDLE_WIDTH + 1, paddle.getY() + PADDLE_HEIGHT / 2);
        if (getElementAt(leftPaddle) != null){
           if (vx > 0 ){    //change the direction of the ball if it moves to a meeting the left end of the paddle
               vx = - vx;
           }
        }
        if (getElementAt(rightPaddle) != null){
            if (vx < 0 ){   //change the direction of the ball if it moves to a meeting the right end of the paddle
                vx = - vx;
            }
        }
        return vx;
    }

    /** Processes the horizontal collision with a possible object
     * and returns horizontal shift of the ball
     * Change the direction of the ball if it collides with any object and knock down collided bricks
     *
     * @param ball The ball for check
     * @param vx  A shift of the ball on the X axis
     * @return Changed shift of the ball on the X axis
     * */
    private double checkCollidingX(GOval ball, double vx) {
        GObject collider = getCollidingObjectX(ball);
        if (collider != null){
            if (ball.getY() + BALL_RADIUS < HEIGHT - PADDLE_Y_OFFSET) {
                vx = -vx; //change the direction of move
            }

            if (collider != paddle){ //removes the colliding brick
                remove(collider);
                deletedBricksCounter++;

            }

        }

        return vx;
    }

    /**
     * It check four points of the ball (left, right, southwest and southeast) for colliding with any objects
     * and return link to object in any point
     *
     * @param ball The ball for check
     * @return Link to the object in the points or NULL if there are no objects
     * */
    private GObject getCollidingObjectX(GOval ball) {
        GObject collider = null;

        collider = getElementAt(ball.getX() - 1 , ball.getY() + BALL_RADIUS); //check the left point of ball
        if (collider != null){
            return collider;
        }

        collider = getElementAt(ball.getX() + (BALL_RADIUS * 2) + 1,
                ball.getY() + BALL_RADIUS); //check the right point of ball
        if (collider != null){
            return collider;
        }


        collider = getElementAt(ball.getX() + BALL_RADIUS - (BALL_RADIUS / Math.sqrt(2)) - 1,
                ball.getY() + BALL_RADIUS + (BALL_RADIUS / Math.sqrt(2))); //check the southwest point of ball
        if (collider != null){
            return collider;
        }

        collider = getElementAt(ball.getX() + BALL_RADIUS +(BALL_RADIUS / Math.sqrt(2)) + 1,
                ball.getY() + BALL_RADIUS + (BALL_RADIUS / Math.sqrt(2))); //check the southeast point of ball
        if (collider != null){
            return collider;
        }


        return null;
    }

    /** Processes the vertical collision with a possible object
     * and returns vertical shift of the ball
     * Change the direction of the ball if it collides with any object and knock down collided bricks
     * Also speeds up the ball if it collides with the paddle
     *
     * @param ball The ball for check
     * @param vy  A shift of the ball on the Y axis
     * @return Changed shift of the ball on the Y axis
     * */
    private double checkCollidingY(GOval ball, double vy) {
        GObject collider = getCollidingObjectY(ball);
        if (collider != null){
            if (ball.getY() + BALL_RADIUS * 2 < HEIGHT - PADDLE_HEIGHT- PADDLE_Y_OFFSET + 1) {
                vy = -vy; //change the direction of move
            }
            if (collider != paddle){ //removes the colliding brick
                remove(collider);
                deletedBricksCounter++;

            }
            else {

                vy = vy - 0.1; //speedup the ball with every new colliding with the paddle
            }
        }

        return vy;
    }

    /**
     * It check six points of the ball (left, right, southwest, southeast, northwest and northeast)
     * for colliding with any objects and return link to object in any point
     *
     * @param ball The ball for check
     * @return Link to the object in the points or NULL if there are no objects
     * */
    private GObject getCollidingObjectY(GOval ball) {
        GObject collider = null;

        collider = getElementAt(ball.getX() + BALL_RADIUS, ball.getY() - 1); //check the top of ball
        if (collider != null){
            return collider;
        }

        collider = getElementAt(ball.getX() + BALL_RADIUS,
                ball.getY() + (BALL_RADIUS * 2) + 1); //check the down of ball
        if (collider != null){
            return collider;
        }

        collider = getElementAt(ball.getX() + BALL_RADIUS - (BALL_RADIUS / Math.sqrt(2)) - 1,
                ball.getY() + BALL_RADIUS - (BALL_RADIUS / Math.sqrt(2))); //check the northwest corner of ball
        if (collider != null){
            return collider;
        }

        collider = getElementAt(ball.getX() + BALL_RADIUS +(BALL_RADIUS / Math.sqrt(2)) + 1,
                ball.getY() + BALL_RADIUS - (BALL_RADIUS / Math.sqrt(2))); //check the northeast corner of ball
        if (collider != null){
            return collider;
        }


        collider = getElementAt(ball.getX(), ball.getY() + BALL_RADIUS * 2); //check the southwest corner of ball
        if (collider != null){
            return collider;
        }

        collider = getElementAt(ball.getX() + BALL_RADIUS * 2,
                ball.getY() + BALL_RADIUS * 2); //check the southeast corner of ball
        if (collider != null){
            return collider;
        }
        return null;
    }

    /** Rebound ball from the left and right wall
     * Change the direction of ball's movement if it collides with the right or the left wall
     *
     * @param ball The ball for check the collision with wall
     * @return Changed shift of the ball on the X axis
     * */
    private double checkX(GOval ball, double vx) {
        if (ball.getX() >= WIDTH - (BALL_RADIUS * 2))
            vx = -vx;
        else if (ball.getX() <=0){
            vx = -vx;
        }
        return vx;
    }

    /** Rebound ball from the top wall and set the flag if the ball fells
     * Change the direction of ball's movement if it collides with the top wall
     * or set flag ballFell and stops the attempt if ball fells down
     *
     * @param ball The ball for check the collision with wall
     * @return Changed shift of the ball on the Y axis
     * */
    private double checkY(GOval ball, double vy) {
        if (ball.getY()>= HEIGHT){
            ballFell = true;
            gameIsStart = false;
        } else if (ball.getY() <=0){
            vy = -vy;
        }
        return vy;
    }
}