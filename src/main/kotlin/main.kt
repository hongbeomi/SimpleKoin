import context.startSimpleKoin
import mock.*

fun main(args: Array<String>) {

    startSimpleKoin {
        modules(viewModelModule + useCaseModule + repositoryModule)
    }

    fun firstText() {
        val viewModel: MockViewModel by inject()
        viewModel.showText()
    }

    fun secondText() {
        val viewModel: MockViewModel by inject()
        viewModel.showText()
    }

    firstText()
    secondText()

}