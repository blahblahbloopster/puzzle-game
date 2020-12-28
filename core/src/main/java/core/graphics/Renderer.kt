package core.graphics

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.GL30
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.math.Vector3
import core.App.camera
import core.Vars
import core.Vars.batch
import core.Vars.world
import core.game.Player
import core.input.InputHandler
import core.world.Tile
import kotlin.math.max
import kotlin.math.min

/** The main game renderer. */
object Renderer {
    /** The target zoom level. */
    var targetZoom = 1f
    /** The target camera position. */
    var targetPosition: Vector2 = Vector2.Zero

    private fun interp(current: Float, other: Float): Float = (other - current) / 10f

    fun render() {
        targetZoom = min(max(targetZoom, 0.01f), 1f)
        camera.zoom += (targetZoom - camera.zoom) / 5f

        targetPosition = (Gdx.input.inputProcessor as InputHandler).focusedPlayer?.pos ?: Vector2.Zero
        camera.position.add(Vector3(interp(camera.position.x, targetPosition.x), interp(camera.position.y, targetPosition.y), camera.position.z))
        camera.update()
        Draw.update()

        // Clear screen
        Gdx.gl.glClearColor(Colors.WALL.r, Colors.WALL.g, Colors.WALL.b, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        Gdx.gl.glClear(GL20.GL_STENCIL_BUFFER_BIT)
        batch.begin()

        // Draw each tile
        world.tiles.forEach(Tile::render)
        Draw.color()

        world.players.forEach(Player::render)
        Draw.color()

        batch.end()
    }
}