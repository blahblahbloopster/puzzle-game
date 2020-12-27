package core.world

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Body

/** Describes a type of block that can go in a tile. */
abstract class Block {
    abstract val name: String
    var id: Int = lastId++
    init {
        Blocks.blockMap.put(id, this)
    }

    companion object {
        var lastId = 0
    }

    /** Gets the collision body. */
    abstract fun collision(tile: Tile): Body

    /** Renders the block. */
    abstract fun render(tile: Tile)

    /** Updates physics and everything else. */
    abstract fun update(tile: Tile)

    fun centerPos(tile: Tile): Vector2 {
        return tile.pos.cpy().add(0.5f, 0.5f)
    }
}
