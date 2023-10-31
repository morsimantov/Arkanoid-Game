/*
 * Mor Siman Tov
 * ID: 208682484
 */

package listener;

import collidable.Block;
import sprite.Ball;

/**
 * @author Mor Siman Tov
 * HitListener interface, includes all the objects that need to be notified of hit events.
 */

public interface HitListener {

    /**
     * This method is called whenever the beingHit object is hit.
     *
     * @param beingHit the block that's being hit
     * @param hitter the ball that's doing the hit
     */
    void hitEvent(Block beingHit, Ball hitter);
}