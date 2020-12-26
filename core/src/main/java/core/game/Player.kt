package core.game

import com.badlogic.gdx.math.Vector2
import core.graphics.Draw
import core.utils.Box2dUtils
import core.world.Group

/** A circular player.  todo: find better name */
class Player(val group: Group) {
    private val body = Box2dUtils.circle(Vector2(0f, 0f), 1f)
    var offset = 0f

    init {
        body.setTransform(5f, 20f, 0f)
    }

    fun update() {
    }

    fun render() {
        Draw.color(group.color)
        Draw.circle(body.position.x, body.position.y, 0.5f, 32)
    }

    fun control(vector2: Vector2) {
        body.applyForce(vector2.cpy().rotateDeg(offset), body.position, true)
    }
}
