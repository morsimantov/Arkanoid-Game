/*
 * Mor Siman Tov
 * ID: 208682484
 */

package level;

import background.Green3Background;
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
 * DirectHitLevel class, describes the third level of the game. Includes two balls and blocks rearanged in a decending
 * order.
 */

public class Green3Level implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        final int speed = 7;

        // Create a new list of velocities
        List<Velocity> list = new ArrayList<>();

        // Add two different velocities for the two balls
        list.add(Velocity.fromAngleAndSpeed(210, speed));
        list.add(Velocity.fromAngleAndSpeed(150, speed));
        return list;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 82;
    }

    @Override
    public String levelName() {
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {

        // The sizes of the window
        final int windowWidth = 800;
        final int windowHeight = 600;

        return new Green3Background(new Rectangle(new Point(0, 0), windowWidth, windowHeight),
                new Color(44, 168, 46));
    }

    @Override
    public List<Block> blocks() {
        double x = 225;
        double y = 150;
        final int blockWidth = 55;
        final int blockHeight = 25;
        final int numBlocksRows = 5;
        final int numBlocks = 10;

        // r will set the colors of the blocks
        int r = 0;

        // Create a list of blocks
        List<Block> blocksList = new LinkedList<>();

        // r will set the colors of the blocks
        Color[] colors = this.setColors();
        for (int i = numBlocks; i > numBlocksRows; i--) {
            for (int j = 0; j < i; j++) {

                // Create a new block and add it to the list
                Block block = new Block(new Rectangle(new Point(x + (j * blockWidth), y), blockWidth, blockHeight),
                        colors[r]);
                blocksList.add(block);
            }
            y += blockHeight;
            x += blockWidth;
            r++;
        }
        return blocksList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 40;
    }

    /**
     * Create a an array of colors for the blocks of the level.
     *
     * @return an array of colors
     */
    public Color[] setColors() {
        return new Color[] {Color.gray, Color.red, Color.yellow, Color.blue, Color.white};
    }
}