/*
 * Mor Siman Tov
 * ID: 208682484
 */

package level;

import background.FinalFourBackground;
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
 * DirectHitLevel class, describes the third level of the game. Includes three balls and seven rows of blocks.
 */

public class FinalFourLevel implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public List<Velocity> initialBallVelocities() {

        // Create a new list of velocities
        List<Velocity> list = new ArrayList<>();
        final int speed = 8;

        // Add to the list the velocities of the three balls, each in a different angle
        list.add(Velocity.fromAngleAndSpeed(180, speed));
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
        return "Final Four";
    }

    @Override
    public Sprite getBackground() {
        return new FinalFourBackground(new Rectangle(new Point(0, 0), 800, 600),
                new Color(59, 147, 255));
    }

    @Override
    public List<Block> blocks() {
        double x = 25;
        double y = 100;
        final int blockWidth = 50;
        final int blockHeight = 25;
        final int numBlocksRows = 7;
        final int numBlocks = 15;

        // r will set the colors of the blocks
        int r = 0;

        // Create a list of blocks
        List<Block> blocksList = new LinkedList<>();

        // Set the colors of the blocks
        Color[] colors = this.setColors();
        for (int i = 0; i < numBlocksRows; i++) {
            for (int j = 0; j < numBlocks; j++) {

                // Create a new block and add it to the list
                Block block = new Block(new Rectangle(new Point(x + (j * blockWidth), y), blockWidth, blockHeight),
                        colors[r]);
                blocksList.add(block);
            }
            y += blockHeight;
            r++;
        }
        return blocksList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 105;
    }

    /**
     * Create a an array of colors for the blocks of the level.
     *
     * @return an array of colors
     */
    public Color[] setColors() {
        return new Color[] {Color.gray, Color.red, Color.yellow, Color.green, Color.white,
                new Color(255, 161, 231), new Color(138, 253, 255)};
    }
}