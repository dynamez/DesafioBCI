package cl.rayout.desafiobci.common.recyclerview

interface RecyclerItem {
    val id: String?
    override fun equals(other: Any?): Boolean
}