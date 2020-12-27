package core.world

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Body
import core.geometry.Rotation

class Tile(val x: Int, val y: Int) {
    /** The tile's current block. */
    var block: Block? = null
    /** The tile's rotation, as specified in [core.geometry.Rotation] */
    var rotation: Byte = Rotation.RIGHT
    val pos: Vector2 = Vector2(x.toFloat(), y.toFloat())

    fun render() {
        block?.render(this)
    }

    /** Gets the tile's collision body for a given group. */
    fun collision(): Body? {
        return block?.collision(this)
    }

    fun update() {
        block?.update(this)
    }

    fun set(tile: Tile?) {
        tile ?: return
        block = tile.block
        rotation = tile.rotation
    }
}
