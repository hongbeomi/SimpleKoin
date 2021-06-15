package mock

import dsl.module

val viewModelModule = module {
    factory { MockViewModel(get()) }
}

val useCaseModule = module {
    factory { MockUseCase(get()) }
}

val repositoryModule = module {
    factory { MockRepository() }
}