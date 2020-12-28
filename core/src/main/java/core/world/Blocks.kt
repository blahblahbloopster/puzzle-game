package core.world

import com.badlogic.gdx.utils.IntMap

object Blocks {
    val blockMap = IntMap<Block>()

    val wall = SolidBlock("wall")
    val blackGroupOnly = GroupSpecificBlock("blackGroupOnly", setOf(Group.BLACK), Group.WHITE.color)
    val whiteGroupOnly = GroupSpecificBlock("whiteGroupOnly", setOf(Group.WHITE), Group.BLACK.color)
}
