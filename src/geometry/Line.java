/*
 * Mor Siman Tov
 * ID: 208682484
 */

package geometry;

import java.util.List;

/**
 * @author Mor Siman Tov
 * Line class, recieves two points and creates a line from the start point to the end point.
 */

public class Line {

    // The starting point of the line
    private Point start;

    // The ending point of the line
    private Point end;

    /**
     * Construct a line given start and end points.
     *
     * @param start the first point
     * @param end the second point
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Construct a line given four coordinates.
     *
     * @param x1 the first coordinate
     * @param y1 the second coordinate
     * @param x2 the third coordinate
     * @param y2 the fourth coordinate
     */
    public Line(double x1, double y1, double x2, double y2) {
        this(new Point(x1, y1), new Point(x2, y2));
    }

    /**
     * Return the length of the line.
     *
     * @return the line's length
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * Return the middle point of the line.
     *
     * @return middle point
     */
    public Point middle() {
        double x = (Math.max(this.end.getX(), this.start.getX()) + Math.min(this.end.getX(), this.start.getX())) / 2;
        double y = (Math.max(this.end.getY(), this.start.getY()) + Math.min(this.end.getY(), this.start.getY())) / 2;
        return new Point(x, y);
    }

    /**
     * Return the start point of the line.
     *
     * @return start point of line
     */
    public Point start() {
        return this.start;
    }

    /**
     * Return the end point of the line.
     *
     * @return end point of line
     */
    public Point end() {
        return this.end;
    }

    /**
     * Check whether this line intersects with the other lines.
     *
     * @param other the other line
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        Point intersection = this.intersectionWith(other);
        if (intersection == null) {
            return false;
        }
        return true;
    }

    /**
     * If this line intersects with the other line return the intersection point, using line equations' calculations.
     *
     * @param other the other line
     * @return the intersection point if the lines intersect, and null otherwise.
     */
    public Point intersectionWith(Line other) {
        double m1, m2, c1, c2, x, y;

        // If the two line are equal points
        if (this.start.equals(other.start) && this.end.equals(other.end) && this.start.equals(this.end)) {
            return this.start;
        }

        // If the two lines are a single point but not equal
        if (this.start.equals(this.end) && other.start.equals(other.end) && !this.start.equals(other.start)) {
            return null;
        }

        /*
        If the two lines have an equal point. If they converge return null, otherwise return the equal point.
         */
        if (this.start.equals(other.start)) {
            if (areLinesConverge(this.start, this.end, other.end)) {
                return null;
            }
            return this.start;
        }
        if (this.end.equals(other.end)) {
            if (areLinesConverge(this.end, this.start, other.start)) {
                return null;
            }
            return this.end;
        }
        if (this.start.equals(other.end)) {
            if (areLinesConverge(this.start, this.end, other.start)) {
                return null;
            }
            return this.start;
        }
        if (this.end.equals(other.start)) {
            if (areLinesConverge(this.end, this.start, other.end)) {
                return null;
            }
            return this.end;
        }

        /*
        If this line is a single point, check if it is on the other line by substituting the point into the other
        line equation.
         */
        if (this.start.equals(this.end) && other.start.getX() != other.end.getX() && other.start.getY()
                != other.end.getY()) {
            if (isOnLine(this, other)) {
                return this.start;
            }
            return null;
        }

         /*
        If the other line is a single point, check if it is on this line by substituting the point into this
        line equation.
         */
        if (other.start.equals(other.end) && this.start.getX() != this.end.getX() && this.start.getY()
                != this.end.getY()) {
            if (isOnLine(other, this)) {
                return other.start;
            }
            return null;
        }

        // If this line is parallel to the x axis
        if (this.start.getX() == this.end.getX() && !this.start.equals(this.end)) {

            // If the other line is a single point, check if it is on this line
            if (other.start.equals(other.end)) {
                x = other.start.getX();
                y = other.start.getY();
                if (isPointInRange(other, x, y)) {
                    return other.start;
                }
                return null;
            }

            // Calculate the potential intersection point using line equations
            x = this.start.getX();
            m2 = mValue(other);
            c2 = cValue(other.start, m2);
            y = lineEquation(m2, x, c2);

        // If the other line is parallel to the x axis
        } else if (other.start.getX() == other.end.getX() && !other.start.equals(other.end)) {

            // If this line is a single point, check if it is on the other line
            if (this.start.equals(this.end)) {
                x = this.start.getX();
                y = this.start.getY();
                if (isPointInRange(other, x, y)) {
                    return this.start;
                }
                return null;
            }

            // Calculate the potential intersection point using line equations
            x = other.start.getX();
            m1 = mValue(this);
            c1 = cValue(this.start, m1);
            y = lineEquation(m1, x, c1);

        /*
         In case the lines don't meet any of the conditions above, calculate their intersection point by comparing
         their line equations.
         */
        } else {
            m1 = mValue(this);
            m2 = mValue(other);
            if (m1 == 0) {
                y = this.start.getY();
                c1 = 0;
                c2 = cValue(other.start, m2);
                x = (y - c2) / m2;
            } else if (m2 == 0) {
                y = other.start.getY();
                c1 = cValue(this.start, m1);
                c2 = 0;
                x = (y - c1) / m1;
            } else {
                c1 = cValue(this.start, m1);
                c2 = cValue(other.start, m2);
                x = (c2 - c1) / (m1 - m2);
                y = lineEquation(m1, x, c1);
            }
            if (m1 == m2 && c1 == c2) {
                return null;
            }
        }

        // If the intersection point is on both lines, return it
        if (isPointInRange(other, x, y)) {
            return (new Point(x, y));
        }

        // If the intersection point is not on one of the lines, return null
        return null;
    }

    /**
     * Calculate the gradient m of the straight line by 2 points.
     *
     * @param line a line
     * @return the gradient m of the straight line
     */
    private static double mValue(Line line) {
        return (line.start.getY() - line.end.getY()) / (line.start.getX() - line.end.getX());
    }

    /**
     * Calculate the c value of a line equation by substituting a point in the equation.
     *
     * @param point a point
     * @param m the gradient m of the line
     * @return the c value of the line equation
     */
    private static double cValue(Point point, double m) {
        return point.getY() - (point.getX() * m);
    }

    /**
     * Calculate the y value of a point by substitution of an x value in the line equation.
     *
     * @param m the gradient m of the line equation
     * @param x the x value
     * @param c the c value of the line equation
     * @return the y value
     */
    private static double lineEquation(double m, double x, double c) {
        return m * x + c;
    }

    /**
     * Check whether line1 is a single point (x, y) that is on line2.
     *
     * @param line1 the first line
     * @param line2 the second line
     * @return true if line1 is a single point that is on line2, false otherwise
     */
    private boolean isOnLine(Line line1, Line line2) {
        double x = line1.start.getX();
        double y = line1.start.getY();
        double m1 = mValue(line2);
        double c1 = cValue(line2.start, m1);
        return lineEquation(m1, x, c1) == y && isPointInRange(line1, x, y);
    }

    /**
     * Check whether a point (x, y) is both on this line and on the other line.
     *
     * @param other the other line
     * @param x the x value of the point
     * @param y the y value of the point
     * @return true if the point on both lines, false otherwise
     */
    public boolean isPointInRange(Line other, double x, double y) {
        return x >= Math.min(this.start.getX(), this.end.getX()) && x <= Math.max(this.start.getX(), this.end.getX())
                && y >= Math.min(this.start.getY(), this.end.getY())
                && y <= Math.max(this.start.getY(), this.end.getY())
                && x >= Math.min(other.start.getX(), other.end.getX())
                && x <= Math.max(other.start.getX(), other.end.getX())
                && y >= Math.min(other.start.getY(), other.end.getY())
                && y <= Math.max(other.start.getY(), other.end.getY());
    }

    /**
     * Check whether two lines that have one equal point converge.
     *
     * @param equalPoint the equal point of the two lines
     * @param point1 the point of the first line
     * @param point2 the point of the second line
     * @return true if the lines converge, false otherwise
     */
    private boolean areLinesConverge(Point equalPoint, Point point1, Point point2) {
        double m1 = (point1.getY() - equalPoint.getY()) / (point1.getX() - equalPoint.getX());
        double m2 = (point2.getY() - equalPoint.getY()) / (point2.getX() - equalPoint.getX());
        return !(m1 != m2 || point1.getX() != point2.getX()
                && (Math.max(equalPoint.getX(), point1.getX()) == Math.min(equalPoint.getX(), point2.getX())
                || Math.max(equalPoint.getX(), point2.getX()) == Math.min(equalPoint.getX(), point1.getX()))
                || point1.getY() != point2.getY()
                && (Math.max(equalPoint.getY(), point1.getY()) == Math.min(equalPoint.getY(), point2.getY())
                || Math.max(equalPoint.getY(), point2.getY()) == Math.min(equalPoint.getY(), point1.getY())));
    }

    /**
     * Check whether this line equals to the other lines.
     *
     * @param other the other line
     * @return return true is the lines are equal, false otherwise
     */
    public boolean equals(Line other) {

        // If the two lines have equal points, return true
        return this.start.equals(other.start) && this.end.equals(other.end) || this.start.equals(other.end)
                && this.end.equals(other.start);
    }

    /**
     * If the line intersects with the rectangle, return the closest intersection point to the start of the line.
     * Otherwise, return null.
     *
     * @param rect the rectangle
     * @return if this line intersects with the rectangle return the closest intersection point, null otherwise
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {

        // Create a list of intersection points of the rectangle with this line
        List<Point> list = rect.intersectionPoints(this);

        // If there aren't any intersection points return null
        if (list.isEmpty()) {
            return null;
        }
        double minDistance = this.start.distance(list.get(0));
        Point closestPoint = list.get(0);

        // Find the closest intersection point to the start of the line
        for (int i = 0; i < list.size(); i++) {
            if (this.start.distance(list.get(i)) < minDistance) {
                minDistance = this.start.distance(list.get(i));
                closestPoint = list.get(i);
            }
        }

        // Return the closest intersection point
        return closestPoint;
    }
}