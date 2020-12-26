package core.world

import com.badlogic.gdx.math.Vector2
import ktx.box2d.createWorld

/**
 * A class that holds all the obstacles in the world.
 */
class World {
    val box2dWorld = createWorld(Vector2(0f, 0f))
}
