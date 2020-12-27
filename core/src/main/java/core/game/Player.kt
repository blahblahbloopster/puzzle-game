package core.game

import com.badlogic.gdx.math.Vector2
import core.graphics.Draw
import core.utils.Box2dUtils
import core.world.Group
import kotlin.random.Random

/** A circular player.  todo: find better name */
class Player(val group: Group) {
    private val radius = 0.4f
    private val body = Box2dUtils.circle(Vector2(0f, 0f), radius, group.filter)
    var offset = 0f

    init {
        body.setTransform(5f, Random(5).nextFloat() * 5 + 15, 0f)
        body.linearDamping = 2f
        body.massData.mass /= 5
    }

    fun update() {
    }

    fun render() {
        Draw.color(group.color)
        Draw.circle(body.position.x, body.position.y, radius, 32)
    }

    fun control(vector2: Vector2) {
        body.applyForce(vector2.cpy().rotateDeg(offset), body.position, true)
    }
}
