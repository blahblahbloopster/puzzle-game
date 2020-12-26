@file:Suppress("LibGDXStaticResource")

package core

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.*
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import core.utils.Draw

/** This is the main file.  It starts all the things. */
object App : ApplicationAdapter() {
    /**
     *  `lateinit` means it is initialized later.  This allows us to give it a non-nullable type.
     *  `var` makes it mutable.
     */
    lateinit var batch: Batch
    private lateinit var image: Texture
    val camera = OrthographicCamera()

    override fun create() {
        batch = SpriteBatch()
        image = Texture("badlogic.png")
    }

    override fun render() {
        // Clear screen
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        batch.begin()
        // Draw logo
//        batch.draw(image, 165f, 180f)
        Draw.color(Color.BLACK)
        Draw.line(0f, 0f, 100f, 100f)
        batch.end()
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
