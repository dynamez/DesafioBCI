package cl.rayout.desafiobci.common.exceptions

import java.io.IOException

class NoConnectionException : IOException() {
    override val message: String?
        get() = "No Connection Error"
}