/*
 * Mor Siman Tov
 * ID: 208682484
 */

package game;

import geometry.Point;

/**
 * @author Mor Siman Tov
 * Velocity class, specifies the change in position on the `x` and the `y` axes.
 */

public class Velocity {
    private double dx;
    private double dy;

    /**
     * Construct a velocity given dx and dy positions.
     *
     * @param dx the first position
     * @param dy the second position
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Construct a velocity by angle and speed.
     *
     * @param angle the angle of the ball
     * @param speed the speed of the ball
     * @return velocity set by angle and speed
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {

        // Create dx and dy positions by calculations of the angle and speed
        double dx = Math.sin(Math.toRadians(angle)) * speed;
        double dy = -(Math.cos(Math.toRadians(angle))) * speed;
        return new Velocity(dx, dy);
    }

    /**
     * Take a point with position (x,y) and return a new point with position (x+dx, y+dy).
     *
     * @param p the point
     * @return the new point after the change of positions dx and dy
     */
    public Point applyToPoint(Point p) {

        // If the point given is null, return null
        if (p == null) {
            return null;
        }
        return new Point(p.getX() + this.getDx(), p.getY() + this.getDy());
    }

    /**
     * Return the dx position of this velocity.
     *
     * @return dx of this velocity
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Return the dy position of this velocity.
     *
     * @return dy of this velocity
     */
    public double getDy() {
        return this.dy;
    }
}