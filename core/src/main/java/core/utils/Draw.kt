@file:Suppress("LibGDXStaticResource")

package core.utils

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.Disposable
import core.App
import core.Vars

object Draw: Disposable {
    var stroke: Float = 1f
    private val shapeRenderer = ShapeRenderer()

    fun update() {
        shapeRenderer.projectionMatrix = App.camera.combined
    }

    fun color(color: Color = Color.WHITE) {
        Vars.batch.color = color
    }

    fun rect(x: Float, y: Float, width: Float, height: Float, region: TextureRegion) {
        Vars.batch.draw(region, x, y, width, height)
    }

    fun line(x: Float, y: Float, x2: Float, y2: Float) {
        Gdx.gl.glLineWidth(stroke)
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line)
        shapeRenderer.color = Vars.batch.color
        shapeRenderer.line(Vector2(x, y), Vector2(x2, y2))
        shapeRenderer.end()
    }

    override fun dispose() {
        shapeRenderer.dispose()
    }
}
