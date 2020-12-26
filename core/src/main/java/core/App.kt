@file:Suppress("LibGDXStaticResource")

package core

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.*
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import core.Vars.batch
import core.Vars.world
import core.game.Player
import core.graphics.Renderer
import core.input.DesktopInput
import core.input.InputHandler
import core.world.Group
import core.world.SolidBlock

/** This is the main file.  It starts all the things. */
object App : Game() {
    /**
     *  lateinit means it is initialized later.  This allows us to give it a non-nullable type.
     *  `var` makes it mutable.
     */
    private lateinit var image: Texture
    val camera = OrthographicCamera()

    override fun create() {
        batch = SpriteBatch()
        image = Texture("badlogic.png")
        Gdx.input.inputProcessor = DesktopInput()
    }

    override fun render() {
        Logic.update()  // todo: move
        Renderer.render()
        (Gdx.input.inputProcessor as InputHandler).update()
    }

    /** Frees memory */
    override fun dispose() {
        batch.dispose()
        image.dispose()
    }

    override fun resize(width: Int, height: Int) {
        camera.viewportWidth = width.toFloat()
        camera.viewportHeight = height.toFloat()
    }
}
