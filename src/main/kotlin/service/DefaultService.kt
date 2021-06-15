package service

import factory.InstanceFactory
import kotlin.reflect.KClass

class DefaultService(
    override val type: KClass<*>,
    override val factory: InstanceFactory<*>
) : Service {

    companion object {
        fun createService(instance: Any, factory: InstanceFactory<*>) =
            DefaultService(instance::class, factory)
    }

}