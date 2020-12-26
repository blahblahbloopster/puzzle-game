package core.utils

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.Filter
import core.Vars
import core.Vars.world
import core.world.World
import ktx.box2d.BodyDefinition
import ktx.box2d.box
import ktx.box2d.circle

object Box2dUtils {
    val nullFilter = Filter()
    init {
        nullFilter.categoryBits = 0
        nullFilter.maskBits = 0
    }
    val nullCollision = nullCollision()

    /** Returns an immovable box body. */
    fun staticBox(position: Vector2, width: Float, height: Float): Body {
        val def = BodyDef()
        def.type = BodyDef.BodyType.StaticBody
        def.position.set(position)
        val collision: Body = world.box2dWorld.createBody(def)
        collision.box(width, height)
        return collision
    }

    /** Creates a null collision. */
    fun nullCollision(): Body {
        val def = BodyDef()
        def.type = BodyDef.BodyType.StaticBody
        val collision: Body = world.box2dWorld.createBody(def)
        collision.box(1f, 1f).filterData.set(nullFilter)
        return collision
    }

    /** Creates a dynamic circle body. */
    fun circle(position: Vector2, radius: Float): Body {
        val def = BodyDef()
        def.type = BodyDef.BodyType.DynamicBody
        val collision: Body = world.box2dWorld.createBody(def)
        collision.circle(radius, position)
        return collision
    }
}
