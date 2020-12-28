package core.world

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.physics.box2d.Filter
import kotlin.experimental.or

/** Represents color groups. */
enum class Group(override val filter: Filter, override val color: Color) : Grp {
    BLACK(generateFilter(0b0000000000000001, 0b111111111111111.toShort()), Color.BLACK.cpy()),
    WHITE(generateFilter(0b0000000000000010, 0b111111111111111.toShort()), Color.WHITE.cpy());

    companion object {
        fun sumOfCategoryBits(groups: Collection<Grp>): Short {
            return groups.map { it.filter.categoryBits }.reduce { acc, sh -> acc or sh }
        }

        fun sumOfMaskBits(groups: Collection<Grp>): Short {
            return groups.map { it.filter.maskBits }.reduce { acc, sh -> acc or sh }
        }
    }
}

interface Grp {
    val filter: Filter
    val color: Color
}

fun generateFilter(category: Short, mask: Short): Filter {
    val filter = Filter()
    filter.categoryBits = category
    filter.maskBits = mask
    return filter
}
