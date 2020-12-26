package core.world

import com.badlogic.gdx.math.Vector2
import ktx.box2d.createWorld

/**
 * A class that holds all the tiles in the world.
 */
class World(width: Int, height: Int) {
    /** The box2d physics world. */
    val box2dWorld = createWorld(Vector2(0f, 0f))
    /** An array of tiles, see [Tiles]. */
    val tiles: Tiles = Tiles(width, height)

    /** Utility class, holds tiles.  You can get tiles out with `tiles.get(x, y)` (java) or `tiles[x, y]` (kotlin) */
    class Tiles(private val width: Int, private val height: Int) : Collection<Tile> {
        override val size = width * height
        private val tiles = Array(width * height) { index -> Tile(index % width, index / width).apply { block = Blocks.wall } }

        operator fun get(x: Int, y: Int): Tile {
            return tiles[y * width + x]
        }

        override fun contains(element: Tile): Boolean {
            return tiles.contains(element)
        }

        override fun containsAll(elements: Collection<Tile>): Boolean {
            return tiles.asList().containsAll(elements)
        }

        override fun isEmpty(): Boolean {
            return tiles.isEmpty()
        }

        override fun iterator(): Iterator<Tile> {
            return tiles.iterator()
        }
    }
}
