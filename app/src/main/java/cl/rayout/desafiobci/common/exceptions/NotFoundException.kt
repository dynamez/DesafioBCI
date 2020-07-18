package cl.rayout.desafiobci.common.exceptions

import java.io.IOException

class NotFoundException : IOException() {
    override val message: String?
        get() = "Not found exception"
}