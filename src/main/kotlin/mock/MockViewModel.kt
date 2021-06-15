package mock

class MockViewModel(
    private val useCase: MockUseCase
) {

    fun showText() {
        println(useCase.invoke() + "\nfrom :: $this\n")
    }

}