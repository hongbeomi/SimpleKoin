package service

import kotlin.reflect.KClass

class DefaultService(
    override val type: KClass<*>,
    override val instance: Any
) : Service {

    companion object {
        fun createService(instance: Any) = DefaultService(instance::class, instance)
    }

}