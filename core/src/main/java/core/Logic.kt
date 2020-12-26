package core

import core.game.Player
import core.world.Group
import core.world.Tile

/** The main game logic.  This updates the physics and mechanics. */
object Logic {
    var first = true

    fun initialize() {
        Vars.players.add(Player(Group.BLACK))
        Vars.players.add(Player(Group.BLACK).apply { offset = 30f })
        for (tile in Vars.world.tiles) {
            tile.collision()
        }
    }

    fun update() {
        // this is criminal, todo: fix
        if (first) {
            initialize()
        }
        first = false

        // Time since the last frame
        val delta = 1 / 60f  // todo: make this actually calculated instead of fixed, move somewhere else

        Vars.world.box2dWorld.step(delta, 1, 1)

        // Update all tiles
        Vars.world.tiles.forEach(Tile::update)

        Vars.players.forEach(Player::update)
    }
}
