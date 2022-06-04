package di

import data.repository.GameRepositoryImpl
import domain.boundary.GameRepository
import domain.usecase.*
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun initKoin() = startKoin {
    modules(
        presentationModule,
        domainModule,
        dataModule
    )
}

private val presentationModule = module {

}

private val domainModule = module {
    single { CheckMovementPossibleUseCase(get()) }
    single { CheckOriginCellUseCase() }
    single { GetAttackMovementListUseCase(get(), get()) }
    single { GetCellListInDirection(get()) }
    single { GetListOfMovementsUseCase(get(), get(), get()) }
    single { GetTravelMovementListUseCase(get(), get()) }
    single { PrepareGameUseCase(get()) }
}

private val dataModule = module {
    single<GameRepository> { GameRepositoryImpl() }
}