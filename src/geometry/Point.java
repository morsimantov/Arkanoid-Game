/*
 * Mor Siman Tov
 * ID: 208682484
 */

package geometry;

import java.util.List;

/**
 * @author Mor Siman Tov
 * Point class that recieves two cooredinates x and y and creates a point (x,y).
 */

public class Point {
    private double x;
    private double y;

    // Default values for the epsilon definition, which is num raised to the power
    private static final int NUM = 10;
    private static final int POWER = -15;

    /**
     * Construct a point given x and y coordinates.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Return the distance of this point to the other point.
     *
     * @param other the other point
     * @return the distance between the two points
     */
    public double distance(Point other) {
        return Math.sqrt(((this.x - other.x) * (this.x - other.x)) + ((this.y - other.y) * (this.y - other.y)));
    }

    /**
     * Check if this point is equal to the other point.
     *
     * @param other the other point
     * @return true if the points are equal, false otherwise
     */
    public boolean equals(Point other) {

        // If the other point is null, return false
        if (other == null) {
            return false;
        }
        double epsilon = Math.pow(NUM, POWER);

        // Check if the two points are equal approximately by epsilon
        return Math.abs(other.getX() - this.getX()) < epsilon && Math.abs(other.getY() - this.getY()) < epsilon;
    }

    /**
     * Return the x value of this point.
     *
     * @return x of this point
     */
    public double getX() {
        return this.x;
    }

    /**
     * Return the y value of this point.
     *
     * @return y of this point
     */
    public double getY() {
        return this.y;
    }

    /**
     * Check if this point exists in a given list of points.
     *
     * @param list list of points
     * @return true if the point exists in the list, false otherwise
     */
    public boolean isPointExist(List<Point> list) {

        // Go through the list of points
        for (int i = 0; i < list.size(); i++) {

            // If the point exists in the list
            if (list.get(i).equals(this)) {
                return true;
            }
        }
        return false;
    }
}