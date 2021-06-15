package factory

import Declaration

class FactoryInstanceFactory<T>(declaration: Declaration<T>): InstanceFactory<T>(declaration) {

    override fun get(): T {
        return create()
    }

    override fun drop() {}

    override fun isCreate(): Boolean = false

}