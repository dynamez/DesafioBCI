package cl.rayout.desafiobci.common.exceptions

import java.io.IOException

class UnhandledNetworkException : IOException() {
    override val message: String?
        get() = "Unhandled error exception"
    override val cause: Throwable?
        get() = this
}