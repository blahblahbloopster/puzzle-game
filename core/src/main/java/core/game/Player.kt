package core.game

import com.badlogic.gdx.files.FileHandle
import com.badlogic.gdx.graphics.g2d.ParticleEffect
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.*
import core.graphics.Draw
import core.utils.Box2dUtils
import core.world.Group

/** A circular player.  todo: find better name */
class Player(val group: Group, x: Float, y: Float) {
    var radius = 0.4f
    var body: Body
    var offset = 0f
    val pos: Vector2 get() = body.position
//    val effect = ParticleEffect()

    init {
//        effect.load(FileHandle("bonk.p"), FileHandle("badlogic.png"))

        body = Box2dUtils.circle(Vector2(0f, 0f), radius, group.filter)
        body.setTransform(x, y, 0f)
        body.linearDamping = 2f
        body.massData.mass /= 5

        body.fixtureList[0]
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
