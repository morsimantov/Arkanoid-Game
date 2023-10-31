/*
 * Mor Siman Tov
 * ID: 208682484
 */

package game;

import listener.HitListener;

/**
 * @author Mor Siman Tov
 * HitNotifier interface, includes all the objects that send notifications when they are being hit.
 */

public interface HitNotifier {

    /**
     * Add hl as a listener to hit events.
     *
     * @param hl the hit listener
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl the hit listener
     */
    void removeHitListener(HitListener hl);
}