package core

import com.esotericsoftware.kryo.Kryo
import com.esotericsoftware.kryo.Serializer
import com.esotericsoftware.kryo.io.Input
import com.esotericsoftware.kryo.io.Output
import core.Vars.world
import core.game.Player
import core.world.Blocks
import core.world.Group
import core.world.Tile
import core.world.World

object Serialization {
    init {
        Vars.kryo.register(Tile::class.java, TileSerializer())

        Vars.kryo.register(World.Tiles::class.java, TileArraySerializer())

        Vars.kryo.register(Group::class.java)

        Vars.kryo.register(Player::class.java, PlayerSerializer())

        Vars.kryo.register(World::class.java, WorldSerializer())
    }

    private class TileSerializer : Serializer<Tile>() {
        override fun write(kryo: Kryo?, output: Output?, `object`: Tile?) {
            `object` ?: return
            val blockId = `object`.block?.id ?: 0
            output?.writeByte(blockId)
            output?.writeByte(`object`.rotation)
            output?.writeInt(`object`.x, true)
            output?.writeInt(`object`.y, true)
        }

        override fun read(kryo: Kryo?, input: Input?, type: Class<out Tile>?): Tile? {
            input ?: return null
            type ?: return null

            val blockId = input.readByte()
            val rotation = input.readByte()
            val x = input.readInt(true)
            val y = input.readInt(true)

            val tile = Tile(x, y)
            tile.block = Blocks.blockMap[blockId.toInt()]
            tile.rotation = rotation

            return tile
        }
    }

    private class TileArraySerializer : Serializer<World.Tiles>() {

        override fun write(kryo: Kryo?, output: Output?, `object`: World.Tiles?) {
            output ?: return
            `object` ?: return
            kryo ?: return

            output.writeInt(`object`.width, true)
            output.writeInt(`object`.height, true)

            `object`.forEach { kryo.writeObject(output, it) }
        }

        override fun read(kryo: Kryo?, input: Input?, type: Class<out World.Tiles>?): World.Tiles? {
            input ?: return null
            type ?: return null
            kryo ?: return null

            val width = input.readInt(true)
            val height = input.readInt(true)
            val tiles = World.Tiles(width, height)

            for (y in 0 until height) {
                for (x in 0 until width) {
                    val tile: Tile? = kryo.readObject(input, Tile::class.java)
                    tiles[x, y]?.set(tile)
                }
            }

            return tiles
        }

    }

    private class WorldSerializer : Serializer<World>() {

        override fun write(kryo: Kryo?, output: Output?, `object`: World?) {
            kryo ?: return
            output ?: return
            `object` ?: return

            kryo.writeObject(output, `object`.tiles)
            output.writeInt(`object`.players.size, true)
            for (player in `object`.players) {
                kryo.writeObject(output, player)
            }
        }

        override fun read(kryo: Kryo?, input: Input?, type: Class<out World>?): World? {
            kryo ?: return null
            input ?: return null
            type ?: return null

            val tiles = kryo.readObject(input, World.Tiles::class.java)
            world = World(tiles.width, tiles.height)
            world.tiles = tiles
            world.players.clear()
            val numPlayers = input.readInt(true)
            for (i in 0 until numPlayers) {
                world.players.add(kryo.readObject(input, Player::class.java))
            }

            return world
        }

    }

    private class PlayerSerializer : Serializer<Player>() {
        override fun write(kryo: Kryo?, output: Output?, `object`: Player?) {
            kryo ?: return
            output ?: return
            `object` ?: return

            kryo.writeObject(output, `object`.group)
            output.writeFloat(`object`.radius)
            output.writeFloat(`object`.offset)
            output.writeFloat(`object`.pos.x)
            output.writeFloat(`object`.pos.y)
        }

        override fun read(kryo: Kryo?, input: Input?, type: Class<out Player>?): Player? {
            kryo ?: return null
            input ?: return null
            type ?: return null

            val group = kryo.readObject(input, Group::class.java)
            val radius = input.readFloat()
            val offset = input.readFloat()
            val x = input.readFloat()
            val y = input.readFloat()

            val player = Player(group, x, y)
            player.radius = radius
            player.offset = offset

            return player
        }

    }
}
