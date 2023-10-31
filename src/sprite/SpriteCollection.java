/*
 * Mor Siman Tov
 * ID: 208682484
 */

package sprite;

import biuoop.DrawSurface;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Mor Siman Tov
 * SpriteCollection class, that holds the collection of all the sprites of the game.
 */

public class SpriteCollection {
    private List<Sprite> spritesList = new LinkedList<>();

    /**
     * Adds the sprite to the collection of sprites.
     *
     * @param s the sprite
     */
    public void addSprite(Sprite s) {
        this.spritesList.add(s);
    }

    /**
     * Removes the sprite from the collection of sprites.
     *
     * @param s the sprite
     */
    public void removeSprite(Sprite s) {
        this.spritesList.remove(s);
    }
    /**
     * Call timePassed() on all sprites.
     *
     */
    public void notifyAllTimePassed() {
        List<Sprite> sprites = new LinkedList<Sprite>(this.spritesList);
        for (int i = 0; i < sprites.size(); i++) {
            sprites.get(i).timePassed();
        }
    }

    /**
     * Call drawOn(d) on all sprites.
     *
     * @param d the draw surface.
     */
    public void drawAllOn(DrawSurface d) {
        List<Sprite> sprites = new LinkedList<Sprite>(this.spritesList);
        for (int i = 0; i < sprites.size(); i++) {
            sprites.get(i).drawOn(d);
        }
    }
}