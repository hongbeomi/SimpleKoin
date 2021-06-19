package mock

import module.module

val viewModelModule = module {
    factory { MockViewModel(get()) }
}

val useCaseModule = module {
    factory { MockUseCase(get()) }
}

val repositoryModule = module {
    single<MockRepository> { MockRepositoryImpl() }
}