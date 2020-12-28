package core.input

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.math.Vector2
import core.Vars.world

class DesktopInput : InputHandler() {

    override fun update() {
        val movement = Vector2()
        if (Gdx.input.isKeyPressed(Input.Keys.W)) movement.add(0f, 1f)
        if (Gdx.input.isKeyPressed(Input.Keys.S)) movement.add(0f, -1f)
        if (Gdx.input.isKeyPressed(Input.Keys.A)) movement.add(-1f, 0f)
        if (Gdx.input.isKeyPressed(Input.Keys.D)) movement.add(1f, 0f)
        movement.limit(1f)
        movement.scl(20f)
        for (player in world.players) {
            player.control(movement)
        }

        if (focusedPlayer == null) {
            focusedPlayer = world.players.getOrNull(0)
        }
        // Switch focused player
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            if (focusedPlayer != null) {
                val index = world.players.indexOf(focusedPlayer) + 1
                focusedPlayer = world.players[index % world.players.size]
            }
        }
    }
}
