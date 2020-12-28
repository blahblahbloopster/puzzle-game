package core.world

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.files.FileHandle
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.esotericsoftware.kryo.io.Output
import core.Serialization
import core.Vars
import core.game.Player
import java.io.File
import java.util.zip.DeflaterOutputStream

object ImageLevelReader : ApplicationAdapter() {

    override fun create() {
        Serialization
        print("Input file: ")
        val inp = readLine()
        inp ?: run { println("Provide an image"); return }
        println(inp)

        val file = FileHandle(inp)
        if (file.exists()) {
            val texture = Texture(file).textureData
            texture.prepare()
            val pixmap = texture.consumePixmap()
            val tiles = World.Tiles(pixmap.width, pixmap.height)

            println("Starting...")
            tiles.forEachXY { x, y, tile ->
                when (Color(pixmap.getPixel(x, y))) {
                    Blocks.blackGroupOnly.color -> { tile.block = Blocks.blackGroupOnly }
                    Blocks.whiteGroupOnly.color -> { tile.block = Blocks.whiteGroupOnly }
                    else -> { tile.block = Blocks.wall }
                }
            }

            print("Output file: ")
            val outputFilename = readLine() ?: return
            val file2 = File(outputFilename)
            val output = Output(DeflaterOutputStream(file2.outputStream()))
            val world = World(pixmap.width, pixmap.height)
            world.tiles = tiles
            world.players.add(Player(Group.BLACK, 10f, 10f))
            world.players.add(Player(Group.WHITE, 10f, 10f).apply { offset = 30f })
            Vars.kryo.writeObject(output, world)
            output.close()
        }
    }
}
/** Reads a level from an image.  Yes, I am using this as an excuse not to make a proper editor. */
