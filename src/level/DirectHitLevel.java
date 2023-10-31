/*
 * Mor Siman Tov
 * ID: 208682484
 */

package level;

import background.DirectHitBackground;
import collidable.Block;
import game.Velocity;
import geometry.Point;
import geometry.Rectangle;
import sprite.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mor Siman Tov
 * DirectHitLevel class, describes the first level of the game. Includes a single block and a single ball that
 * is designated to hit the block in the first hit.
 */

public class DirectHitLevel implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        final int angle = 180;
        final int speed = 5;

        // Create a new list of velocities
        List<Velocity> list = new ArrayList<>();

        // Add the ball's velocity to the list
        list.add(Velocity.fromAngleAndSpeed(angle, speed));
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
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {

        // The sizes of the window
        final int windowWidth = 800;
        final int windowHeight = 600;

        // Create a new background in the sizes of the window
        return new DirectHitBackground(new Rectangle(new Point(0, 0), windowWidth, windowHeight),
                Color.black);
    }

    @Override
    public List<Block> blocks() {
        final double x = 385;
        final double y = 185;
        final double blockWidth = 30;
        final double blockHeight = 30;

        // Create a list of blocks
        List<Block> blocksList = new ArrayList<>();

        // Create one block and add it to the list
        Block block = new Block(new Rectangle(new Point(x, y), blockWidth, blockHeight), Color.red);
        blocksList.add(block);
        return blocksList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}