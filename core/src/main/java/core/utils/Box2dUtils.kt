package core.utils

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.Filter
import core.Vars
import core.world.World
import ktx.box2d.box

object Box2dUtils {
    val nullFilter = Filter()
    init {
        nullFilter.categoryBits = 0
        nullFilter.maskBits = 0
    }
    val nullCollision = nullCollision(Vars.world)

    /** Returns an immovable box body. */
    fun staticBox(world: World, position: Vector2, width: Float, height: Float): Body {
        val def = BodyDef()
        def.type = BodyDef.BodyType.StaticBody
        def.position.set(position)
        val collision: Body = world.box2dWorld.createBody(BodyDef())
        collision.box(width, height)
        return collision
    }

    fun nullCollision(world: World): Body {
        val def = BodyDef()
        def.type = BodyDef.BodyType.StaticBody
        val collision: Body = world.box2dWorld.createBody(BodyDef())
        collision.box(1f, 1f).filterData.set(nullFilter)
        return collision
    }
}
