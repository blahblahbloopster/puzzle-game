package core.graphics

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.GL20
import core.App.camera
import core.Vars
import core.Vars.players
import core.Vars.world
import core.game.Player
import core.world.Tile
import kotlin.math.max
import kotlin.math.min

/** The main game renderer. */
object Renderer {
    /** The target zoom level. */
    var targetZoom = 1f

    fun render() {
        targetZoom = min(max(targetZoom, 0.01f), 1f)
        camera.zoom += (targetZoom - camera.zoom) / 5f
        camera.update()
        Draw.update()

        // Clear screen
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        Vars.batch.begin()

        // Draw each tile
        world.tiles.forEach(Tile::render)
        Draw.color()

        players.forEach(Player::render)
        Draw.color()

        Vars.batch.end()
    }
}