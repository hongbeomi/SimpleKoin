package factory

import Declaration
import service.DefaultService
import service.Service

abstract class InstanceFactory<T>(
    private val declaration: Declaration<T>
) {

    abstract fun isCreate(): Boolean

    open fun create(): T {
        return declaration.invoke()
    }

    abstract fun get(): T

    abstract fun drop()

    fun toService(): Service {
        val instance = get() as Any
        return DefaultService.createService(instance, this)
    }

}