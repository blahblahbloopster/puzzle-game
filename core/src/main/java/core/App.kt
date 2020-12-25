package core

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch

/** This is the main file.  It starts all the things. */
class App : ApplicationAdapter() {
    /**
     *  `lateinit` means it is initialized later.  This allows us to give it a non-nullable type.
     *  `var` makes it mutable.
     */
    private lateinit var batch: SpriteBatch
    private lateinit var image: Texture

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
        batch.draw(image, 165f, 180f)
        batch.end()
    }

    /** Frees memory */
    override fun dispose() {
        batch.dispose()
        image.dispose()
    }
}
