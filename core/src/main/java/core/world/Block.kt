package core.world

import com.badlogic.gdx.physics.box2d.Body

/** Describes a type of block that can go in a tile. */
abstract class Block(val name: String) {

    /** Gets the collision body for a given [Group] */
    abstract fun collision(group: Group): Body

    /** Renders the block. */
    abstract fun render()

    /** Updates physics and everything else. */
    abstract fun update(tile: Tile)
}
