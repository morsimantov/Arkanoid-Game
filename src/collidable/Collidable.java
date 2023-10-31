/*
 * Mor Siman Tov
 * ID: 208682484
 */

package collidable;

import game.Velocity;
import geometry.Point;
import geometry.Rectangle;
import sprite.Ball;

/**
 * @author Mor Siman Tov
 * Collidable interface, includes all the obstacles that another object can collide with. Collidables have a shape,
 * and can be notified when the collision occurs with a suitable velocity.
 */

public interface Collidable {

    /**
     * Return the "collision shape" of the object.
     *
     * @return rectangle
     */
    Rectangle getCollisionRectangle();

    /**
     * Return the new velocity expected after the hit of the object that we collided with it.
     *
     * @param collisionPoint the point of collision
     * @param currentVelocity the current velocity
     * @param hitter the Ball that's doing the hitting
     * @return the new velocity
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}