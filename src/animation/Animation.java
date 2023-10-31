/*
 * Mor Siman Tov
 * ID: 208682484
 */

package animation;

import biuoop.DrawSurface;

/**
 * @author Mor Siman Tov
 * Animation interface, includes all the game-specific logic and stopping conditions of the animation.
 */

public interface Animation {

    /**
     * Preform all the actions that needs to be executed in one frame of the animation.
     *
     * @param d rhe draw surface
     */
    void doOneFrame(DrawSurface d);

    /**
     * Return whether the animation should stop.
     *
     * @return boolean value, true or false
     */
    boolean shouldStop();
}