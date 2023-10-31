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
 * FinalFourBackground class, creates the background for the game level "Final Four".
 */

public class FinalFourBackground extends GameBackground {

    /**
     * Construct a background, given a rectangle and a color.
     *
     * @param rectangle the rectangle, the shape of the window
     * @param color the color of the window
     */
    public FinalFourBackground(Rectangle rectangle, java.awt.Color color) {
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
        int x1 = 85;
        int x2 = 65;

        // Draw rain drops
        d.setColor(Color.white);
        for (int i = 0; i < 10; i++) {
            d.drawLine(x1, 400, x2, 600);
            x1 += 10;
            x2 += 10;
        }

        // Draw a cloud
        d.setColor(new Color(215, 215, 217));
        d.fillCircle(85, 400, 25);
        d.fillCircle(110, 415, 25);
        d.setColor(new Color(193, 193, 193));
        d.fillCircle(125, 385, 30);
        d.setColor(new Color(187, 187, 187));
        d.fillCircle(165, 390, 35);
        d.fillCircle(145, 420, 25);

        // Draw rain drops
        d.setColor(Color.white);
        x1 = 605;
        x2 = 560;
        for (int i = 0; i < 10; i++) {
            d.drawLine(x1, 465, x2, 665);
            x1 += 10;
            x2 += 10;
        }

        // Draw a cloud
        d.setColor(new Color(215, 215, 217));
        d.fillCircle(600, 475, 25);
        d.fillCircle(625, 505, 25);
        d.setColor(new Color(193, 193, 193));
        d.fillCircle(640, 465, 30);
        d.setColor(new Color(187, 187, 187));
        d.fillCircle(680, 465, 35);
        d.fillCircle(660, 495, 25);
    }

    @Override
    public void addToGame(GameLevel g) {
        super.addToGame(g);
    }
}