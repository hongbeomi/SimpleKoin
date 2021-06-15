package dsl

import factory.InstanceFactory
import module.Module
import kotlin.reflect.KClass

fun module(block: Module.() -> Unit) = Module().apply(block)

val List<Module>.instanceRegistry: Map<KClass<*>, InstanceFactory<*>>
    get() = this.fold(this[0].instanceRegistry) { acc, module ->
        (acc + module.instanceRegistry) as MutableMap<KClass<*>, InstanceFactory<*>>
    }