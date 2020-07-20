package cl.rayout.desafiobci.common.exceptions

import java.io.IOException

class UnknownHostException : IOException() {
    override val message: String?
        get() = "UnknownHost"
    override val cause: Throwable?
        get() = this
}