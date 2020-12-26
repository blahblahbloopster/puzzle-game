package core.input

import com.badlogic.gdx.InputAdapter
import core.graphics.Renderer.targetZoom
import kotlin.math.max
import kotlin.math.min

abstract class InputHandler : InputAdapter() {

    override fun scrolled(amountX: Float, amountY: Float): Boolean {
        targetZoom *= (amountY / 4) + 1
        targetZoom = min(max(targetZoom, 0.01f), 1f)
        return false
    }
}
