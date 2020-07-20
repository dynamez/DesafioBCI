package cl.rayout.desafiobci.common.exceptions

import java.io.IOException

class UnauthorizedException : IOException() {
    override val message: String?
        get() = "Unauthorized"
    override val cause: Throwable?
        get() = this
}