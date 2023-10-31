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
 * Green3Background class, creates the background for the game level "Green 3".
 */

public class Green3Background extends GameBackground {

    /**
     * Construct a background, given a rectangle and a color.
     *
     * @param rectangle the rectangle, the shape of the window
     * @param color the color of the window
     */
    public Green3Background(Rectangle rectangle, java.awt.Color color) {
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

        // Draw a rectangle in the shape of a building
        d.setColor(new Color(56, 50, 50));
        d.fillRectangle(70, 350, 125, 250);

        // Create the windows of the building
        d.setColor(new Color(153, 239, 239));
        int x = 60;
        int y = 355;
        int heightRec = 20;
        int widthRec = 10;
        for (int j = 0; j < 10; j++) {
            for (int i = 0; i < 8; i++) {
                x += widthRec + 5;
                d.fillRectangle(x, y, widthRec, heightRec);
            }
            x = 60;
            y += heightRec + 5;
        }

        // Draw an antenna on top of the building
        d.setColor(new Color(76, 68, 68));
        d.fillRectangle(120, 300, 25, 50);
        d.setColor(new Color(94, 85, 85));
        d.fillRectangle(128, 200, 10, 100);

        // Create a red light bulb on top of the antenna
        d.setColor(new Color(255, 93, 93));
        d.fillCircle(133, 200, 13);
        d.setColor(new Color(239, 33, 33));
        d.fillCircle(133, 200, 9);
        d.setColor(new Color(255, 188, 188));
        d.fillCircle(133, 200, 4);
    }

    @Override
    public void addToGame(GameLevel g) {
        super.addToGame(g);
    }
}