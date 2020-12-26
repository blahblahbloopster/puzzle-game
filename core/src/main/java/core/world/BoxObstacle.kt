package core.world

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Body
import core.utils.Box2dUtils

/** A filled tile. */
class BoxObstacle(world: World, group: Group, position: Vector2, width: Float, height: Float) : Obstacle {

    override val collision: Body = Box2dUtils.staticBox(world, position, width, height)
    override val negative: Body = Box2dUtils.nullCollision(world)

    override fun render() {
        
    }
}
