package core.world

import com.badlogic.gdx.math.Vector2
import ktx.box2d.createWorld

/**
 * A class that holds all the obstacles in the world.
 */
class World(width: Int, height: Int) {
    val box2dWorld = createWorld(Vector2(0f, 0f))
    /** A 2d array of tiles. */
    val tiles: Array<Array<Tile>> = Array(height) { y -> Array(width) { x -> Tile(x, y) } }
}
