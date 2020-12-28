package core.world

import com.badlogic.gdx.math.Vector2
import com.esotericsoftware.kryo.io.Input
import com.esotericsoftware.kryo.io.Output
import core.Vars.kryo
import core.game.Player
import ktx.box2d.createWorld
import java.io.File
import java.util.zip.DeflaterOutputStream
import java.util.zip.InflaterInputStream

/**
 * A class that holds all the tiles in the world.
 */
class World(width: Int, height: Int) {
    /** The box2d physics world. */
    @Transient val box2dWorld = createWorld(Vector2.Zero)
    /** An array of tiles, see [Tiles]. */
    var tiles: Tiles = Tiles(width, height)
    /** The list of players. */
    val players = mutableListOf<Player>()

    /** Utility class, holds tiles.  Tiles can be retrieved with `tiles.get(x, y)` (java) or `tiles[x, y]` (kotlin) */
    class Tiles(val width: Int, val height: Int) : Collection<Tile> {
        override val size = width * height
        private val tiles = Array(width * height) { index -> Tile(index % width, index / width).apply { block = null } }

        operator fun get(x: Int, y: Int): Tile? {
            if (x < 0 || x > width || y < 0 || y > height) {
                return null
            }
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

        fun forEachXY(block: (x: Int, y: Int, tile: Tile) -> Unit) {
            forEachIndexed { index, tile -> block(index % width, index / width, tile) }
        }
    }
}


fun save(filename: String, world: World) {
    val file = File(filename)
    val output = Output(DeflaterOutputStream(file.outputStream()))
    kryo.writeObject(output, world)
    output.close()
}

fun load(filename: String): World {
    val file = File(filename)
    val input = Input(InflaterInputStream(file.inputStream()))
    val world = kryo.readObject(input, World::class.java)
    input.close()
    return world
}
