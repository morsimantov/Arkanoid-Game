/*
 * Mor Siman Tov
 * ID: 208682484
 */

package level;
import background.WideEasyBackground;
import collidable.Block;
import game.Velocity;
import geometry.Point;
import geometry.Rectangle;
import sprite.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Mor Siman Tov
 * DirectHitLevel class, describes the second level of the game. Includes a wide paddle and ten balls in different
 * angles.
 */

public class WideEasyLevel implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        int angle = 135;
        final int increment = 9;
        final int speed = 6;

        // Create a new list of velocities
        List<Velocity> list = new ArrayList<>();

        // Add the balls' velocities to the list, each ball with a different angle
        for (int i = 0; i < this.numberOfBalls(); i++) {
            list.add(Velocity.fromAngleAndSpeed(angle, speed));
            angle += increment;
            if (i == 4) {
                angle += increment;
            }
        }
        return list;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 600;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        // The sizes of the window
        final int windowWidth = 800;
        final int windowHeight = 600;

        return new WideEasyBackground(new Rectangle(new Point(0, 0), windowWidth, windowHeight),
                new Color(67, 88, 255));
    }

    @Override
    public List<Block> blocks() {
        final int blockWidth = 50;
        final int blockHeight = 25;
        double x = 25;
        double y = 250;

        // Set the colors of the blocks
        Color[] colors = this.setColors();

        // r will set the colors of the blocks
        int r = 0;

        // Create a list of blocks
        List<Block> blocksList = new LinkedList<>();
        for (int i = 0; i < 15; i++) {

            // Create a new block and add it to the list
            Block block = new Block(new Rectangle(new Point(x, y), blockWidth, blockHeight),
                    colors[r]);
            blocksList.add(block);
            x += blockWidth;
            r++;
        }
        return blocksList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 15;
    }

    /**
     * Create a an array of colors for the blocks of the level.
     *
     * @return an array of colors
     */
    public Color[] setColors() {
        return new Color[] {Color.red, Color.red, Color.orange, Color.orange, Color.yellow, Color.yellow, Color.green,
                Color.green, Color.green, Color.blue, Color.blue, new Color(255, 161, 231),
                new Color(255, 161, 231), new Color(138, 253, 255), new Color(138, 253, 255)};
    }
}