package core.world

import com.badlogic.gdx.physics.box2d.Body

/** Describes a type of block that can go in a tile. */
abstract class Block(val name: String) {

    abstract fun collision(group: Group): Body

    abstract fun render()
}
