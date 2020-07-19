package cl.rayout.desafiobci.common.di

import androidx.room.Room
import cl.rayout.desafiobci.common.database.TestDatabase
import cl.rayout.desafiobci.common.network.LiveNetworkMonitor
import cl.rayout.desafiobci.dashboard.network.PokemonApiFactory
import cl.rayout.desafiobci.dashboard.repository.DashBoardRepository
import cl.rayout.desafiobci.dashboard.viewmodel.DashboardViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val myModules = module {
    /*DI modules for koin initialization
       each get() represents the initialization of the dependency
    */
    //DI for viewmodels
    viewModel { DashboardViewModel(get()) }
//    viewModel { AddCounterViewModel(get()) }
    //Single DI for repositories
    //each repository that depends on network implementation receives and instance of an api connection
    single { DashBoardRepository(PokemonApiFactory.pokemonApi) }

//    Room database DI and test modules

//Live NetworkMonitor
    single { LiveNetworkMonitor(androidContext()) }

    single {
        Room.databaseBuilder(
            androidContext(),
            TestDatabase::class.java, "test.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
    single { get<TestDatabase>().dashboardPokemonDao() }
}

