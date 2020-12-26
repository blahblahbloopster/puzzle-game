package core.utils

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.*
import core.Vars.world
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
    fun staticBox(position: Vector2, width: Float, height: Float, filter: Filter): Body {
        val def = BodyDef()
        def.type = BodyDef.BodyType.StaticBody
        def.position.set(position)
        val collision: Body = world.box2dWorld.createBody(def)
        val fixtureDef = FixtureDef()
        fixtureDef.shape = PolygonShape().apply { setAsBox(width / 2, height / 2, Vector2.Zero, 0f) }
        fixtureDef.filter.set(filter)
        collision.createFixture(fixtureDef)
        collision.setTransform(position, 0f)
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
