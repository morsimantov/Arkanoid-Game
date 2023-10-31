/*
 * Mor Siman Tov
 * ID: 208682484
 */

package collidable;

import geometry.Point;

/**
 * @author Mor Siman Tov
 * CollisionInfo class, holds the information about the collision of an object with another collidable object.
 */

public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * Create the collision information on the collision point of the sprite and the collidable object.
     *
     * @param collisionPoint the collision point
     * @param collisionObject the collidable object
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * Return the collision point of the object, the point at which the collision occurs.
     *
     * @return collision point
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * Return the collidable object involved in the collision.
     *
     * @return collision object
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}