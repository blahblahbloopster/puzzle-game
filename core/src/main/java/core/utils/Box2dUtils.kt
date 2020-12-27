package core.utils

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.*
import core.Vars
import core.Vars.world
import ktx.box2d.body
import ktx.box2d.box
import ktx.box2d.circle
import ktx.box2d.filter

object Box2dUtils {

    /** Returns an immovable box body. */
    fun staticBox(position: Vector2, width: Float, height: Float, filter: Filter): Body {
        return world.box2dWorld.body {
            type = BodyDef.BodyType.StaticBody
            box(width, height) {
                filter {
                    maskBits = filter.maskBits
                    categoryBits = filter.categoryBits
                }
            }
        }.apply { setTransform(position, 0f) }
    }

    /** Creates a dynamic circle body. */
    fun circle(position: Vector2, radius: Float, filter: Filter): Body {
        return world.box2dWorld.body {
            type = BodyDef.BodyType.DynamicBody
            circle(radius) {
                filter {
                    maskBits = filter.maskBits
                    categoryBits = filter.categoryBits
                }
            }
        }.apply { setTransform(position, 0f) }
    }
}
