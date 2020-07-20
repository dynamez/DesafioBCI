package cl.rayout.desafiobci.common.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cl.rayout.desafiobci.dashboard.model.DashboardPokemonModel

@Dao
interface DashboardPokemonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(counters: List<DashboardPokemonModel>?)

    @Query("Select * from pokemon")
    fun getCounters(): LiveData<List<DashboardPokemonModel>>

    @Query("Delete from pokemon")
    fun deleteAllCounters()
}