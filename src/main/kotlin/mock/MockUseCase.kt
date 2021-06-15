package mock

class MockUseCase(private val repo: MockRepository) {

    operator fun invoke() = repo.getText() + "\nfrom $this"

}