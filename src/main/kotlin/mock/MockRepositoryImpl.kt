package mock

class MockRepositoryImpl: MockRepository {
    override fun getText() = "from :: $this"
}