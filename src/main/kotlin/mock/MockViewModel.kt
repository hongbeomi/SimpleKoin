package mock

class MockViewModel(
//    private val id: Long,
    private val useCase: MockUseCase
) {

    fun showText() {
        println(useCase.invoke())
    }

}