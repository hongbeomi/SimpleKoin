package service

import factory.InstanceFactory
import kotlin.reflect.KClass

interface Service {
    val type: KClass<*>
    val factory: InstanceFactory<*>
}