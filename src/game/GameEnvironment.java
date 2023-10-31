/*
 * Mor Siman Tov
 * ID: 208682484
 */

package game;

import collidable.Collidable;
import collidable.CollisionInfo;
import geometry.Line;
import geometry.Point;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Mor Siman Tov
 * GameEnvironment class, that holds the collection of objects a Ball can collide with
 */

public class GameEnvironment {

    // A list of collidables objects
    private List<Collidable> collidableList = new LinkedList<>();

    /**
     * Add the given collidable to the environment.
     *
     * @param c the collidable
     */
    public void addCollidable(Collidable c) {
        this.collidableList.add(c);
    }

    /**
     * Remove the given collidable from the environment of the game.
     *
     * @param c the collidable
     */
    public void removeCollidable(Collidable c) {
        this.collidableList.remove(c);
    }

    /**
     * Given an objects that is moving from line.start() to line.end(), if it collides with one of the collidables,
     * return the information about the closest collision that is going to occur. Otherwise, return null.
     *
     * @param trajectory the line the object is moving along its length.
     * @return the information about the closest collision point
     */
    public CollisionInfo getClosestCollision(Line trajectory) {

        // The minimal distance between the object and the collidable
        double minDistance = -1;
        Point closestPoint;
        CollisionInfo collisionInfo = null;

        List<Collidable> collidables = new LinkedList<Collidable>(this.collidableList);

        // Go through list of collidables and check the closest intersection point of each collidable with the line
        for (int i = 0; i < collidables.size(); i++) {
            closestPoint
                    = trajectory.closestIntersectionToStartOfLine(collidables.get(i).getCollisionRectangle());

            // If there is an intersection point between one of the collidables and the line
            if (closestPoint != null) {

                /*
                If the intersection point is closer to the start of the line than the previous intersection points
                found, save it and update the minimal distance.
                 */
                if (minDistance == -1 || minDistance > trajectory.start().distance(closestPoint)) {
                    minDistance = trajectory.start().distance(closestPoint);
                    collisionInfo = new CollisionInfo(closestPoint, collidables.get(i));
                }
            }
        }

        // Return the collision info of the closest point found
        return collisionInfo;
    }
}