package core.world

import com.badlogic.gdx.physics.box2d.Filter
import kotlin.experimental.or

/** Represents color groups. */
enum class Group(override val filter: Filter) : FilterContainer {
    BLACK(Filter().let { inp -> inp.categoryBits = 0b0000000000000001; inp }),
    WHITE(Filter().let { inp -> inp.categoryBits = 0b0000000000000010; inp });

    init {
        // This goes through each group and makes it so that they collide with all other groups but not themselves.
        for (value in values()) {
            value.filter.maskBits = values().toMutableSet().minus(value).map { acc -> acc.filter.categoryBits }.reduce { acc, sh -> acc or sh }
        }
    }
}

private interface FilterContainer {
    val filter: Filter
}
