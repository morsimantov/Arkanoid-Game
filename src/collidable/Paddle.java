/*
 * Mor Siman Tov
 * ID: 208682484
 */

package collidable;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.GameLevel;
import game.Velocity;
import geometry.Point;
import geometry.Rectangle;
import sprite.Ball;
import sprite.Sprite;

import java.awt.Color;

/**
 * @author Mor Siman Tov
 * Paddle class, a controller that can be moved by the keyboard
 */

public class Paddle implements Sprite, Collidable {
    private KeyboardSensor keyboard;
    private Rectangle rectangle;
    private java.awt.Color color;
    private int speed;

    // x and y values for the upper left point of the paddle
    private static final int X_UPPER_LEFT = 400;
    private static final int Y_UPPER_LEFT = 565;

    // The height of the paddle
    private static final int HEIGHT_PADDLE = 18;

    /**
     * Construct a paddle given a keyboard sensor.
     *
     * @param keyboard the keyboard sensor
     * @param paddleWidth the width of the paddle
     * @param paddleSpeed the speed of the paddle
     */
    public Paddle(KeyboardSensor keyboard, int paddleWidth, int paddleSpeed) {
        this.keyboard = keyboard;
        this.rectangle = new Rectangle(new Point(X_UPPER_LEFT - (double) paddleWidth / 2, Y_UPPER_LEFT), paddleWidth,
                HEIGHT_PADDLE);
        this.speed = paddleSpeed;
        this.color = Color.yellow;
    }

    /**
     * Move the paddle left.
     *
     */
    public void moveLeft() {

        // The left border of the game window
        final double leftBorder = 25;

        // If the paddle reached the left border of the screen
        if (this.rectangle.getUpperLeft().getX() <= leftBorder) {
            return;
        }

        // Create a new upper left point for the paddle
        Point newPoint = new Point(this.rectangle.getUpperLeft().getX() - this.speed,
                this.rectangle.getUpperLeft().getY());

        // Move the paddle left by changing its starting point
        this.rectangle = new Rectangle(newPoint, this.rectangle.getWidth(), this.rectangle.getHeight());
    }

    /**
     * Move the paddle right.
     *
     */
    public void moveRight() {

        // The right border of the game window
        final double rightBorder = 775 - this.rectangle.getWidth();

        // If the paddle reached the right border of the screen
        if (this.rectangle.getUpperLeft().getX() >= rightBorder) {
            return;
        }

        // Create a new upper left point for the paddle
        Point newPoint = new Point(this.rectangle.getUpperLeft().getX() + this.speed,
                this.rectangle.getUpperLeft().getY());

        // Move the paddle right by changing its starting point
        this.rectangle = new Rectangle(newPoint, this.rectangle.getWidth(), this.rectangle.getHeight());
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        final int divisionParts = 5;

        // Numbers of each region of the paddle
        final int region2 = 2;
        final int region3 = 3;
        final int region4 = 4;
        final int region5 = 5;

        // The angles according to which the ball will bounce from the paddle
        final int angleRegion1 = 300;
        final int angleRegion2 = 330;
        final int angleRegion4 = 30;
        final int angleRegion5 = 60;

        // Define the speed of the new velocity
        double newSpeed = Math.sqrt((currentVelocity.getDx() * currentVelocity.getDx()) + (currentVelocity.getDy()
                * currentVelocity.getDy()));

        /*
        Divide the paddle into 5 regions (from left to right), each one in the size of x. The new velocity after the
        ball hits the paddle will be determined depending on where it hits the paddle.
         */
        double x = this.rectangle.getWidth() / divisionParts;

        // If the object hits the left corner of the paddle
        if (collisionPoint.getX() == this.rectangle.getUpperLeft().getX()
                && collisionPoint.getY() != this.rectangle.getUpperLeft().getY()) {
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }

        // If the ball hits region 1 of the paddle
        if (collisionPoint.getX() <= this.rectangle.getUpperLeft().getX() + x) {
            return Velocity.fromAngleAndSpeed(angleRegion1, newSpeed);
        }

        // If the ball hits region 2 of the paddle
        if (collisionPoint.getX() <= this.rectangle.getUpperLeft().getX() + region2 * x) {
            return Velocity.fromAngleAndSpeed(angleRegion2, newSpeed);
        }

        // If the ball hits region 3 of the paddle
        if (collisionPoint.getX() <= this.rectangle.getUpperLeft().getX() + region3 * x) {
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }

        // If the ball hits region 4 of the paddle
        if (collisionPoint.getX() <= this.rectangle.getUpperLeft().getX() + region4 * x) {
            return Velocity.fromAngleAndSpeed(angleRegion4, newSpeed);
        }

        // If the object hits the right corner of the paddle
        if (collisionPoint.getX() == this.rectangle.getUpperLeft().getX() + region5 * x
                && collisionPoint.getY() != this.rectangle.getUpperLeft().getY()) {
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }

        // If the ball hits region 4 of the paddle
        if (collisionPoint.getX() <= this.rectangle.getUpperLeft().getX() + region5 * x) {
            return Velocity.fromAngleAndSpeed(angleRegion5, newSpeed);
        }

        // If non of the conditions above is met, return the current velocity
        return currentVelocity;
    }

    @Override
    public void drawOn(DrawSurface d) {

        // Set the color of the paddle
        d.setColor(this.color);

        // Draw the paddle
        d.fillRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());

        // Set the color of the paddle's frame
        d.setColor(Color.black);

        // Draw the paddle's frame
        d.drawRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
    }

    @Override
    public void timePassed() {

        // If the "left" key is pressed, move the paddle left
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }

        // If the "right" key is pressed, move the paddle right
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * Add this paddle to the game.
     *
     * @param g the game
     */
    public void addToGame(GameLevel g) {
        if (g != null) {
            g.addSprite(this);
            g.addCollidable(this);
        }
    }
}