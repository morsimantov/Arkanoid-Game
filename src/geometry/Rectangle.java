/*
 * Mor Siman Tov
 * ID: 208682484
 */

package geometry;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Mor Siman Tov
 * Rectangle class, creates a rectangle given a point, width and height.
 */

public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;

    // The number of edges\corners of the rectangle
    private static final int RECTANGLE_EDGES = 4;

    /**
     * Create a new rectangle with location point, width and height.
     *
     * @param upperLeft location point of the upper-left of the rectangle
     * @param width the width of the rectangle
     * @param height the height of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Return a (possibly empty) List of intersection points of this rectangle with the specified line.
     *
     * @param line location point of the upper-left of the rectangle
     * @return list of points
     */
    public java.util.List<Point> intersectionPoints(Line line) {

        // Create an array of the rectangle's edges
        Line[] edgesArrayRec = edgesArrayRec();

        // Create a list of intersection points of this line and the rectangle
        List<Point> intersectionPoints = new LinkedList<>();
        Point intersectionPoint;
        for (int i = 0; i < RECTANGLE_EDGES; i++) {

            // If one of the edges of the rectangle intersects with the line, add the intersection point to the list
            if (edgesArrayRec[i].isIntersecting(line)) {
                intersectionPoint = edgesArrayRec[i].intersectionWith(line);
                if (!intersectionPoint.isPointExist(intersectionPoints)) {
                    intersectionPoints.add(intersectionPoint);
                }
            }
        }

        // Return the list of intersection points
        return intersectionPoints;
    }

    /**
     * Create an array of the rectangle's edges.
     *
     * @return an array of this rectangle's edges
     */
    public Line[] edgesArrayRec() {
        return new Line[] {new Line(this.upperLeft.getX(), this.upperLeft.getY(), this.upperLeft.getX()
                + this.width, this.upperLeft.getY()), new Line(this.upperLeft.getX(), this.upperLeft.getY(),
                this.upperLeft.getX(), this.upperLeft.getY() + this.height), new Line(this.upperLeft.getX(),
                this.upperLeft.getY() + this.height, this.upperLeft.getX() + this.width,
                this.upperLeft.getY() + this.height), new Line(this.upperLeft.getX() + this.width,
                this.upperLeft.getY(), this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height)};
    }

    /**
     * Create an array of points that are the corners of this rectangle.
     *
     * @return an array of points, corners of the rectangle
     */
    public Point[] cornersArrayRec() {
        return new Point[] {this.upperLeft, new Point(this.upperLeft.getX(), this.upperLeft.getY() + height),
                new Point(this.upperLeft.getX() + width, this.upperLeft.getY() + height),
                new Point(this.upperLeft.getX() + width, this.upperLeft.getY())};
    }

    /**
     * Return the width of the rectangle.
     *
     * @return width of this rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Return the height of the rectangle.
     *
     * @return height of this rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Return the upper-left point of the rectangle.
     *
     * @return upper-left point of this rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }
}