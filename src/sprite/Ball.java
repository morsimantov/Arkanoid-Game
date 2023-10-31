/*
 * Mor Siman Tov
 * ID: 208682484
 */

package sprite;

import biuoop.DrawSurface;
import collidable.CollisionInfo;
import game.GameLevel;
import game.GameEnvironment;
import game.Velocity;
import geometry.Line;
import geometry.Point;

import java.awt.Color;

/**
 * @author Mor Siman Tov
 * Ball class, creats a ball given different parameters that can bounce.
 */

public class Ball implements Sprite {
    private int r;
    private Point center;
    private java.awt.Color color;
    private Velocity v;
    private GameEnvironment environment;

    /*
    Defining the bounds of the window, in case we need the ball to move without going outside the frames of the window
    animation.
    */
    private int upperBound;
    private int leftBound;
    private int rightBound;
    private int lowerBound;

    // Default values for dx and dy positions
    private static final int DX = 5;
    private static final int DY = 5;

    /**
     * Construct a ball given a center point, a radius and a color.
     *
     * @param center a center point
     * @param r the radius
     * @param color a color
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.v = new Velocity(DX, DY);
    }

    /**
     * Construct a ball given a center point, a radius, a color and the environment game of the ball.
     *
     * @param center a center point
     * @param r the radius
     * @param color a color
     * @param velocity the velocity of the ball
     */
    public Ball(Point center, int r, java.awt.Color color, Velocity velocity) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.v = velocity;
    }

    /**
     * Construct a ball given two coordinates x and y, a radius and a color.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @param r the radius
     * @param color a color
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
        this.v = new Velocity(DX, DY);
    }

    /**
     * Construct a ball given two coordinates x and y, a radius, a color and the game environment of the ball.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @param r the radius
     * @param color a color
     * @param environment the game environment of the ball
     */
    public Ball(int x, int y, int r, java.awt.Color color, GameEnvironment environment) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
        this.v = new Velocity(DX, DY);
        this.environment = environment;
    }

    /**
     * Construct a black ball given a center point and a radius.
     *
     * @param center a center point
     * @param r the radius
     */
    public Ball(Point center, int r) {
        this.center = center;
        this.r = r;
        this.color = java.awt.Color.BLACK;
    }

    /**
     * Return the x value of the circle's center.
     *
     * @return x coordinate of the center
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Return the y value of the circle's center.
     *
     * @return y coordinate of the center
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Return the radius of the circle.
     *
     * @return radius of circle
     */
    public int getSize() {
        return this.r;
    }

    /**
     * Return the center of the ball.
     *
     * @return the center point of the ball
     */
    public Point getCenter() {
        return this.center;
    }

    /**
     * Return the color of the circle.
     *
     * @return color of circle
     */
    public java.awt.Color getColor() {
        return this.color;
    }


    @Override
    public void drawOn(DrawSurface d) {

        // Set the color of the ball
        d.setColor(this.color);

        // Draw the ball
        d.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.r);

        // Set the color of the ball's frame
        d.setColor(Color.black);

        // Draw the balls's frame
        d.drawCircle((int) this.center.getX(), (int) this.center.getY(), this.r);
    }

    @Override
    public void timePassed() {

        // Move the ball by one step
        moveOneStep();
    }

    /**
     * Set the velocity of the ball.
     *
     * @param velocity the velocity
     */
    public void setVelocity(Velocity velocity) {
        this.v = velocity;
    }

    /**
     * Set the velocity of the ball by dx and dy positions.
     *
     * @param dx the first position
     * @param dy the second position
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }

    /**
     * Set the game environment of the ball.
     *
     * @param gameEnvironment the game environment
     */
    public void setGameEnvironment(GameEnvironment gameEnvironment) {
        this.environment = gameEnvironment;
    }

    /**
     * Set the bounds of the window of the moving ball.
     *
     * @param upper the upper bound
     * @param lower the lower bound
     * @param left the left bound
     * @param right the right bound
     */
    public void setWindowBounds(int upper, int left, int lower, int right) {
        this.upperBound = upper;
        this.lowerBound = lower;
        this.leftBound = left;
        this.rightBound = right;
    }

    /**
     * Return the velocity of the ball.
     *
     * @return the velocity of the ball
     */
    public Velocity getVelocity() {
        return this.v;
    }

    /**
     * Move the ball by changing the coordinates of the ball's center, according to the size of the animation window.
     *
     */
    public void moveOnBounds() {

        /*
        If the x coordinate of the ball's center is outside the bounds of the window, change the velocity accordingly.
         */
        if (this.getSize() + this.center.getX() >= this.lowerBound && this.v.getDx() > 0
                || this.center.getX() - this.getSize() <= this.upperBound && this.v.getDx() < 0) {
            setVelocity(-(this.v.getDx()), this.v.getDy());
        }

        /*
        If the y coordinate of the ball's center is outside the bounds of the window, change the velocity accordingly.
         */
        if (this.getSize() + this.center.getY() >= this.rightBound && this.v.getDy() > 0
                || this.center.getY() - this.getSize() <= this.leftBound && this.v.getDy() < 0) {
            setVelocity(this.v.getDx(), -(this.v.getDy()));
        }
        this.center = this.getVelocity().applyToPoint(this.center);
    }

    /**
     * Move the ball by changing the coordinates of the ball's center, such that its movement will be guided by the
     * obstacles in the game environment.
     *
     */
    public void moveOneStep() {

        // Compute the ball trajectory - how the ball is gonna move without obstacles
        Line trajectory = new Line(this.center.getX(), this.center.getY(), this.center.getX() + this.v.getDx(),
                this.center.getY() + this.v.getDy());

        // Check for a collision point between the ball and an obstacle
        CollisionInfo closestCollisionInfo = this.environment.getClosestCollision(trajectory);

        /*
        Check (using the game environment) if moving on the trajectory will hit anything. If so, ove the ball slightly
        before the hit point, by changing its velocity.
         */
        if (closestCollisionInfo != null) {
            this.center = new Point(closestCollisionInfo.collisionPoint().getX() - this.v.getDx(),
                    closestCollisionInfo.collisionPoint().getY() - this.v.getDy());
            this.v = closestCollisionInfo.collisionObject().hit(this, closestCollisionInfo.collisionPoint(), this.v);
        }

        // If there isn't a collision point, move the ball to the end of the trajectory
        this.center = this.getVelocity().applyToPoint(this.center);
    }

    /**
     * Add the ball to the sprite collection of the game.
     *
     * @param g the game
     */
    public void addToGame(GameLevel g) {
        if (g != null) {
            g.addSprite(this);
        }
    }

    /**
     * Remove the ball from the sprite collection of the game.
     *
     * @param g the game
     */
    public void removeFromGame(GameLevel g) {
        if (g != null) {
            g.removeSprite(this);
        }
    }
}

