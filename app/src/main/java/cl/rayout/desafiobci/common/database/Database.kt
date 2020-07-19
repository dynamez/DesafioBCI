package cl.rayout.desafiobci.common.database

import androidx.room.Database
import androidx.room.RoomDatabase
import cl.rayout.desafiobci.common.database.dao.DashboardPokemonDao
import cl.rayout.desafiobci.dashboard.model.DashboardPokemonModel

@Database(
    entities = [DashboardPokemonModel::class],
    version = 1,
    exportSchema = false
)

abstract class TestDatabase : RoomDatabase() {
    companion object {
        var TEST_MODE = false
    }

    abstract fun dashboardPokemonDao(): DashboardPokemonDao

}