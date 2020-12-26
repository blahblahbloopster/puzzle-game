package core.world

import com.badlogic.gdx.physics.box2d.Body

/**
 * Represents an obstacle.
 */

interface Obstacle {
    // The actual collision
    val collision: Body
    // The "negative," what the other player collides with
    val negative: Body

    fun render()
}
