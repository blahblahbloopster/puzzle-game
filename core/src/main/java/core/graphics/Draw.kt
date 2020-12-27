@file:Suppress("LibGDXStaticResource")

package core.graphics

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.utils.Disposable
import core.App
import core.Vars
import core.Vars.batch

object Draw: Disposable {
    var stroke: Float = 1f
    private val shapeRenderer = ShapeRenderer()

    fun initialize() {
    }

    fun alpha(alpha: Float) {
        Vars.batch.setColor(batch.color.r, batch.color.g, batch.color.b, alpha)
    }

    fun update() {
        shapeRenderer.projectionMatrix = App.camera.combined
    }

    fun color(color: Color = Color.WHITE) {
        batch.color = color
    }

    fun rect(x: Float, y: Float, width: Float, height: Float, region: Texture) {
        Vars.batch.draw(region, x, y, width, height)
    }

    fun line(x: Float, y: Float, x2: Float, y2: Float) {
        Gdx.gl.glLineWidth(stroke)
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line)
        shapeRenderer.color = batch.color
        shapeRenderer.line(Vector2(x, y), Vector2(x2, y2))
        shapeRenderer.end()
    }

    fun rectFilled(x: Float, y: Float, width: Float, height: Float) {
        Gdx.gl.glLineWidth(stroke)
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled)
        shapeRenderer.color = batch.color
        shapeRenderer.rect(x, y, width, height)
        shapeRenderer.end()
    }

    fun circle(x: Float, y: Float, radius: Float) {
        Gdx.gl.glLineWidth(stroke)
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled)
        shapeRenderer.color = batch.color
        shapeRenderer.circle(x, y, radius)
        shapeRenderer.end()
    }

    fun circle(x: Float, y: Float, radius: Float, numSegments: Int) {
        Gdx.gl.glLineWidth(stroke)
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled)
        shapeRenderer.color = batch.color
        shapeRenderer.circle(x, y, radius, numSegments)
        shapeRenderer.end()
    }

    override fun dispose() {
        shapeRenderer.dispose()
    }
}
