package core.world

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.physics.box2d.Filter
import kotlin.experimental.or

/** Represents color groups. */
enum class Group(override val filter: Filter, override val color: Color) : Grp {
    BLACK(Filter().let { inp -> inp.categoryBits = 0b0000000000000001; inp }, Color.BLACK),
    WHITE(Filter().let { inp -> inp.categoryBits = 0b0000000000000010; inp }, Color.WHITE);

    companion object {
        init {
            // This goes through each group and makes it so that they collide with all other groups but not themselves.
            for (value in values()) {
                value.filter.maskBits = sumOfCategoryBits(values().toMutableSet().minus(value))
            }
        }

        private fun sumOfCategoryBits(groups: Collection<Grp>): Short {
            return groups.map { it.filter.categoryBits }.reduce { acc, sh -> acc or sh }
        }

        private fun sumOfMaskBits(groups: Collection<Grp>): Short {
            return groups.map { it.filter.maskBits }.reduce { acc, sh -> acc or sh }
        }
    }
}

interface Grp {
    val filter: Filter
    val color: Color
}
