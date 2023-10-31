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
 * WideEasyBackground class, creates the background for the game level "Wide Easy".
 */

public class WideEasyBackground extends GameBackground {

    /**
     * Construct a background, given a rectangle and a color.
     *
     * @param rectangle the rectangle, the shape of the window
     * @param color the color of the window
     */
    public WideEasyBackground(Rectangle rectangle, java.awt.Color color) {
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

        // Create a rainbow by coloring circles in different sizes and colors
        d.setColor(new Color(206, 4, 4));
        d.fillCircle(400, 620, 320);
        d.setColor(new Color(248, 189, 44));
        d.fillCircle(400, 620, 290);
        d.setColor(new Color(243, 240, 75));
        d.fillCircle(400, 620, 250);
        d.setColor(new Color(48, 210, 51));
        d.fillCircle(400, 620, 210);
        d.setColor(new Color(27, 36, 108));
        d.fillCircle(400, 620, 170);
        d.setColor(new Color(13, 19, 73));
        d.fillCircle(400, 620, 130);
        d.setColor(new Color(101, 42, 88));
        d.fillCircle(400, 620, 90);
        d.setColor(Color.white);
        d.fillCircle(400, 620, 50);

        // Create two clouds
        d.setColor(new Color(215, 215, 217));
        d.fillCircle(75, 200, 25);
        d.fillCircle(100, 215, 25);
        d.setColor(new Color(193, 193, 193));
        d.fillCircle(115, 185, 30);
        d.setColor(new Color(187, 187, 187));
        d.fillCircle(155, 190, 35);
        d.fillCircle(135, 220, 25);

        d.setColor(new Color(215, 215, 217));
        d.fillCircle(600, 275, 25);
        d.fillCircle(625, 305, 25);
        d.setColor(new Color(193, 193, 193));
        d.fillCircle(640, 265, 30);
        d.setColor(new Color(187, 187, 187));
        d.fillCircle(680, 265, 35);
        d.fillCircle(660, 295, 25);

        // Create 4 birds
        d.setColor(Color.black);
        d.drawLine(600, 100, 615, 80);
        d.drawLine(600, 100, 585, 80);
        d.drawLine(615, 80, 620, 80);
        d.drawLine(585, 80, 580, 80);

        d.drawLine(200, 200, 215, 180);
        d.drawLine(200, 200, 185, 180);
        d.drawLine(215, 180, 220, 180);
        d.drawLine(185, 180, 180, 180);

        d.drawLine(250, 150, 265, 130);
        d.drawLine(250, 150, 235, 130);
        d.drawLine(265, 130, 270, 130);
        d.drawLine(235, 130, 230, 130);

        d.drawLine(660, 245, 675, 225);
        d.drawLine(660, 245, 645, 225);
        d.drawLine(675, 225, 680, 225);
        d.drawLine(645, 225, 640, 225);
    }

    @Override
    public void addToGame(GameLevel g) {
        super.addToGame(g);
    }
}