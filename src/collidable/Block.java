/*
 * Mor Siman Tov
 * ID: 208682484
 */

package collidable;

import biuoop.DrawSurface;
import game.GameLevel;
import game.HitNotifier;
import game.Velocity;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import listener.HitListener;
import sprite.Ball;
import sprite.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Mor Siman Tov
 * Block class, includes all the obstacles on the screen.
 */

public class Block implements Collidable, Sprite, HitNotifier {
    private java.awt.Color color;
    private Rectangle rectangle;
    private List<HitListener> hitListeners = new LinkedList<>();

    // The number of edges\corners of the block (the rectangle)
    private static final int BLOCK_EDGES = 4;

    /**
     * Construct a block given a shape.
     *
     * @param rectangle the shape of the block
     */
    public Block(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    /**
     * Construct a block given a shape and a color.
     *
     * @param rectangle the shape of the block
     * @param color the color of the block
     */
    public Block(Rectangle rectangle, java.awt.Color color) {
        this.rectangle = rectangle;
        this.color = color;
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * Notify all listeners about a hit event when a ball hits a block.
     *
     * @param hitter the ball that's doing the hit
     */
    private void notifyHit(Ball hitter) {

        // Make a copy of the hitListeners before iterating over them
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);

        // Notify all listeners about a hit event
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();

        // An array of the rectangle's edges
        Line[] edgesArrayRec = rectangle.edgesArrayRec();

        // An array of the rectangle's corners
        Point[] cornersArrayRec = rectangle.cornersArrayRec();
        for (int i = 0; i < BLOCK_EDGES; i++) {

            // If the collision point is a corner of the block
            if (collisionPoint.equals(cornersArrayRec[i])) {
                dx = -currentVelocity.getDx();
                dy = -currentVelocity.getDy();
            }

            // If the collision point is on one of the edges of the block
            if (edgesArrayRec[i].isPointInRange(edgesArrayRec[i], collisionPoint.getX(), collisionPoint.getY())) {

                // If the collision point is on the top or the bottom of the block
                if (i == 0 || i == 2) {
                    dx = currentVelocity.getDx();
                    dy = -currentVelocity.getDy();
                }

                // If the collision point is on the left or the right side of the block
                if (i == 1 || i == 3) {
                    dx = -currentVelocity.getDx();
                    dy = currentVelocity.getDy();
                }
            }
        }
        this.notifyHit(hitter);
        return new Velocity(dx, dy);
    }

    @Override
    public void drawOn(DrawSurface d) {

        // Set the color of the block
        d.setColor(this.color);

        // Draw the block
        d.fillRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());

        // Set the color of the block's frame
        d.setColor(Color.black);

        // Draw the block's frame
        d.drawRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
    }

    @Override
    public void timePassed() {
    }

    /**
     * Add the block to the sprite collection and to the environment of the game.
     *
     * @param g the game
     */
    public void addToGame(GameLevel g) {
        if (g != null) {
            g.addSprite(this);
            g.addCollidable(this);
        }
    }

    /**
     * Remove the block to the sprite collection and to the environment of the game.
     *
     * @param gameLevel the game
     */
    public void removeFromGame(GameLevel gameLevel) {
        if (gameLevel != null) {
            gameLevel.removeSprite(this);
            gameLevel.removeCollidable(this);
        }
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
}