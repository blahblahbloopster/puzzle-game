package core.world

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.physics.box2d.Body
import core.Vars
import core.graphics.Draw
import core.utils.Box2dUtils

class GroupSpecificBlock(override val name: String, val allow: Set<Group>, val color: Color) : Block() {

    override fun collision(tile: Tile): Body {
        val mskBits: Short = Group.sumOfCategoryBits(Group.values().toList().minus(allow))
        return Box2dUtils.staticBox(centerPos(tile), 1f, 1f, generateFilter(Vars.WALL, mskBits))
    }

    override fun render(tile: Tile) {
        Draw.color(color)
        Draw.rectFilled(tile.pos.x, tile.pos.y, 1f, 1f)
    }

    override fun update(tile: Tile) {

    }
}
