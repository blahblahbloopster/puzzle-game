@file:Suppress("LibGDXStaticResource")

package core

import com.badlogic.gdx.graphics.g2d.Batch
import core.game.Player
import core.input.InputHandler
import core.world.World

/** Holds various global variables. */
object Vars {
    /** The [com.badlogic.gdx.graphics.g2d.SpriteBatch].*/
    lateinit var batch: Batch
    /** The current world. */
    val world = World(10, 10)
    /** The list of players. */
    val players = mutableListOf<Player>()
    /** Collision groups: */
    const val WALL = 0b0100000000000000.toShort()
}
