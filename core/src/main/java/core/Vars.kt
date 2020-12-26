@file:Suppress("LibGDXStaticResource")

package core

import com.badlogic.gdx.graphics.g2d.Batch
import core.input.InputHandler
import core.world.World

/** Holds various global variables. */
object Vars {
    /** The [com.badlogic.gdx.graphics.g2d.SpriteBatch].*/
    lateinit var batch: Batch
    /** The current world. */
    val world = World(10, 10)
    /** The input handler. */
    lateinit var input: InputHandler
}
