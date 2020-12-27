package core.world

import com.badlogic.gdx.graphics.Color

object Blocks {
    val wall: Block = SolidBlock("wall")
    val blackGroupOnly = GroupSpecificBlock("blackGroupOnly", setOf(Group.BLACK), Color.GOLD)
}
