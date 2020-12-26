package core.world

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.Filter
import core.graphics.Draw
import core.utils.Box2dUtils

/** Collides with all groups. */
class SolidBlock(override val name: String) : Block {

    override fun collision(group: Group, tile: Tile): Body {
        return Box2dUtils.staticBox(tile.pos, 1f, 1f, Filter().apply { categoryBits = 0b111111111111111 })
    }

    override fun render(tile: Tile) {
        val center = centerPos(tile)
        Draw.color(Color.BLACK)
        Draw.rectFilled(center.x, center.y, 1f, 1f)
    }

    override fun update(tile: Tile) {

    }
}
