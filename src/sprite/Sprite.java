/*
 * Mor Siman Tov
 * ID: 208682484
 */

package sprite;

import biuoop.DrawSurface;

/**
 * @author Mor Siman Tov
 * Sprite interface, includes all the game objects that can be drawn to the screen. Sprites can be drawn on the screen,
 * and can be notified that time has passed.
 */
public interface Sprite {

    /**
     * Draws the sprite on the given DrawSurface.
     *
     * @param d the DrawSurface
     */
    void drawOn(DrawSurface d);

    /**
     * Notify the sprite that time has passed.
     *
     */
    void timePassed();
}