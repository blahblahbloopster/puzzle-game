package core.world

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Body

/** Describes a type of block that can go in a tile. */
interface Block {
    val name: String

    /** Gets the collision body for a given [Group] */
    fun collision(group: Group, tile: Tile): Body

    /** Renders the block. */
    fun render(tile: Tile)

    /** Updates physics and everything else. */
    fun update(tile: Tile)

    fun centerPos(tile: Tile): Vector2 {
        return tile.pos.cpy().add(0.5f, 0.5f)
    }
}
