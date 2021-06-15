package dsl

import Declaration
import module.Module
import kotlin.reflect.KClass

fun module(block: Module.() -> Unit) = Module().apply(block)

val List<Module>.instanceRegistry: Map<KClass<*>, Declaration<Any>>
    get() = this.fold(this[0].declarationRegistry) { acc, module ->
        (acc + module.declarationRegistry) as MutableMap<KClass<*>, Declaration<Any>>
    }