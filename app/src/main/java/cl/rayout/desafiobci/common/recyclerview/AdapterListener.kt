package cl.rayout.desafiobci.common.recyclerview

import android.view.View

interface AdapterListener {
    fun listen(click: AdapterClick?, itemView: View?)
}