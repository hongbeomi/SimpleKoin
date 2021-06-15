package service

import kotlin.reflect.KClass

interface Service {
    val type: KClass<*>
    val instance: Any
}