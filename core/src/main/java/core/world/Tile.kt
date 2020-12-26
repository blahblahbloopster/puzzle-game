package core.world

import com.badlogic.gdx.physics.box2d.Body
import core.geometry.Rotation
import core.utils.Box2dUtils

class Tile(val x: Int, val y: Int) {
    /** The tile's current block. */
    var block: Block? = null
    /** The tile's rotation, as specified in [core.geometry.Rotation] */
    var rotation: Byte = Rotation.RIGHT

    fun render() {
        block?.render()
    }

    /** Gets the tile's collision body for a given group. */
    fun collision(group: Group): Body {
        return block?.collision(group) ?: Box2dUtils.nullCollision
    }

    fun update() {
        block?.update(this)
    }
}
