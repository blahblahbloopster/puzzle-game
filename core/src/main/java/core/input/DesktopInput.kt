package core.input

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.math.Vector2
import core.Vars

class DesktopInput : InputHandler() {

    override fun update() {
        val movement = Vector2()
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) movement.add(0f, 1f)
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) movement.add(0f, -1f)
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) movement.add(-1f, 0f)
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) movement.add(1f, 0f)
        movement.limit(1f)
        movement.scl(20f)
        for (player in Vars.players) {
            player.control(movement)
        }
    }
}
