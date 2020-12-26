package core.world

import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.Filter
import core.graphics.Colors
import core.graphics.Draw
import core.utils.Box2dUtils

/** Collides with all groups. */
class SolidBlock(override val name: String) : Block {

    override fun collision(tile: Tile): Body {
        return Box2dUtils.staticBox(centerPos(tile), 1f, 1f, Filter().apply { categoryBits = 0b111111111111111; maskBits = 0b111111111111111 })
    }

    override fun render(tile: Tile) {
        Draw.color(Colors.WALL)
        Draw.rectFilled(tile.pos.x, tile.pos.y, 1f, 1f)
    }

    override fun update(tile: Tile) {

    }
}
