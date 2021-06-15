import context.startSimpleKoin
import context.stopKoin
import mock.*

fun main(args: Array<String>) {
    startSimpleKoin {
        modules(viewModelModule + useCaseModule + repositoryModule)
    }


    fun a() {
        val viewModel: MockViewModel by inject()

        println(viewModel)

        viewModel.showText()
    }


    fun b() {
        val viewModel: MockViewModel by inject()

        println(viewModel)

        viewModel.showText()
    }
    a()
    b()
}