/*
 * Mor Siman Tov
 * ID: 208682484
 */

package sprite;

import biuoop.DrawSurface;
import game.Counter;
import geometry.Rectangle;

import java.awt.Color;

/**
 * @author Mor Siman Tov
 * ScoreIndicator class, which is in charge of displaying the current score.
 */

public class ScoreIndicator implements Sprite {
    private Counter scoresCounter;
    private java.awt.Color color;
    private Rectangle rectangle;
    private String levelName;

    /**
     * Construct a ScoreIndicator given a counter, a rectangle and a color.
     *
     * @param scoresCounter the scores counter
     * @param rectangle the block that will display the score
     * @param color a color
     * @param levelName the name of the current level
     */
    public ScoreIndicator(Counter scoresCounter, Rectangle rectangle, java.awt.Color color, String levelName) {
        this.scoresCounter = scoresCounter;
        this.rectangle = rectangle;
        this.color = color;
        this.levelName = levelName;
    }

    @Override
    public void drawOn(DrawSurface d) {
        // Set the color of the block
        d.setColor(this.color);

        // Draw the block
        d.fillRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());

        // Set the color of the text
        d.setColor(Color.black);

        // Write the current score of the game and the level's name at the top of the block
        d.drawText(380, 15, "Score: " + this.scoresCounter.getValue(), 15);
        d.drawText(535, 15, "Level Name: " + this.levelName, 15);
    }

    @Override
    public void timePassed() {
    }
}