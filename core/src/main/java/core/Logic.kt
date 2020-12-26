package core

import core.Vars
import core.world.Tile

/** The main game logic.  This updates the physics and mechanics. */
object Logic {

    fun update() {
        // Time since the last frame
        val delta = 1 / 60f  // todo: make this actually calculated instead of fixed, move somewhere else

        // Update all tiles
        Vars.world.tiles.forEach(Tile::update)
    }
}
