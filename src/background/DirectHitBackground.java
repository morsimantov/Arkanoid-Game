/*
 * Mor Siman Tov
 * ID: 208682484
 */

package background;

import biuoop.DrawSurface;
import game.GameLevel;
import geometry.Rectangle;

import java.awt.Color;


/**
 * @author Mor Siman Tov
 * DirectHitBackground class, creates the background for the game level "Direct Hit".
 */

public class DirectHitBackground extends GameBackground {

    /**
     * Construct a background, given a rectangle and a color.
     *
     * @param rectangle the rectangle, the shape of the window
     * @param color the color of the window
     */
    public DirectHitBackground(Rectangle rectangle, java.awt.Color color) {
        super(rectangle, color);
    }

    @Override
    public void drawOn(DrawSurface d) {

        // Set the color of the background
        d.setColor(this.getColor());

        // Draw the window
        d.fillRectangle((int) this.getRectangle().getUpperLeft().getX(),
                (int) this.getRectangle().getUpperLeft().getY(), (int) this.getRectangle().getWidth(),
                (int) this.getRectangle().getHeight());

        // Create 3 blue circles
        d.setColor(new Color(0, 40, 173));
        d.drawCircle(400, 200, 60);
        d.drawCircle(400, 200, 100);
        d.drawCircle(400, 200, 140);

        // Crate two vertical lines
        d.drawLine(400, 370, 400, 25);
        d.drawLine(228, 200, 572, 200);
    }

    @Override
    public void addToGame(GameLevel g) {
        super.addToGame(g);
    }
}