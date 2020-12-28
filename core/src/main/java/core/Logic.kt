package core

import core.Vars.world
import core.game.Player
import core.graphics.Draw
import core.world.Group
import core.world.Tile
import core.world.load

/** The main game logic.  This updates the physics and mechanics. */
object Logic {
    var first = true

    fun initialize() {
        Serialization

        world = load("foo.pzgl")

//        world.players.add(Player(Group.BLACK, 10f, 10f))
//        world.players.add(Player(Group.WHITE, 10f, 10f).apply { offset = 30f })

        for (tile in world.tiles) {
            tile.collision()
        }

        Draw.initialize()
    }

    fun update() {
        // this is criminal, todo: fix
        if (first) {
            initialize()
        }
        first = false

        // Time since the last frame
        val delta = 1 / 60f  // todo: make this actually calculated instead of fixed, move somewhere else

        world.box2dWorld.step(1 / 60f, 1, 1)

        // Update all tiles
        world.tiles.forEach(Tile::update)

        world.players.forEach(Player::update)
    }
}
