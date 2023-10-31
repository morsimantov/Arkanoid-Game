/*
 * Mor Siman Tov
 * ID: 208682484
 */

package background;

import biuoop.DrawSurface;
import geometry.Rectangle;
import sprite.Sprite;
import game.GameLevel;

/**
 * @author Mor Siman Tov
 * GameBackground class, creates the background for the game.
 */

public class GameBackground implements Sprite {
    private Rectangle rectangle;
    private java.awt.Color color;

    /**
     * Construct a game background, given a rectangle and a color.
     *
     * @param rectangle the rectangle, the shape of the window
     * @param color the color of the window
     */
    public GameBackground(Rectangle rectangle, java.awt.Color color) {
        this.rectangle  = rectangle;
        this.color = color;
    }

    @Override
    public void drawOn(DrawSurface d) {

        // Set the color of the background
        d.setColor(this.color);

        // Draw the window
        d.fillRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
    }

    @Override
    public void timePassed() {
    }

    /**
     * Add the game window to the sprite collection of the game.
     *
     * @param gameLevel the game
     */
    public void addToGame(GameLevel gameLevel) {
        if (gameLevel != null) {
            gameLevel.addSprite(this);
        }
    }

    /**
     * Return the shape of the window (a rectangle).
     *
     * @return the shape of the window
     */
    public Rectangle getRectangle() {
        return this.rectangle;
    }

    /**
     * Return the color of the window.
     *
     * @return the color of the window
     */
    public java.awt.Color getColor() {
        return this.color;
    }
}