package core

import com.esotericsoftware.kryo.io.Input
import com.esotericsoftware.kryo.io.Output
import core.Vars.kryo
import core.Vars.world
import core.game.Player
import core.graphics.Draw
import core.world.Group
import core.world.Tile
import core.world.World
import java.io.File
import java.util.zip.DeflaterOutputStream
import java.util.zip.InflaterInputStream

/** The main game logic.  This updates the physics and mechanics. */
object Logic {
    var first = true

    fun initialize() {
        Serialization
        Vars.players.add(Player(Group.BLACK))
        Vars.players.add(Player(Group.WHITE).apply { offset = 30f })

//        world.load("test1.pzgl")

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

        Vars.world.box2dWorld.step(delta, 1, 1)

        // Update all tiles
        world.tiles.forEach(Tile::update)

        Vars.players.forEach(Player::update)
    }
}
