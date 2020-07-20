package cl.rayout.desafiobci.common.exceptions

import java.io.IOException

class ServerErrorException : IOException() {
    override val message: String?
        get() = "This is a server error exception"
    override val cause: Throwable?
        get() = this
}