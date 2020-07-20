package cl.rayout.desafiobci.common.exceptions

import java.io.IOException

class NoContentException : IOException() {
    override val message: String?
        get() = "No Content Error"
}