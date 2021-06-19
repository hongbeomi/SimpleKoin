package module

import Declaration
import factory.FactoryInstanceFactory
import factory.InstanceFactory
import factory.SingleInstanceFactory
import getSimpleKoin
import java.util.concurrent.ConcurrentHashMap
import kotlin.reflect.KClass

class Module {

    val instanceRegistry: MutableMap<KClass<*>, InstanceFactory<*>> = ConcurrentHashMap()

    inline fun <reified T : Any> factory(noinline declaration: Declaration<T>) {
        val instanceFactory = FactoryInstanceFactory(declaration)
        instanceRegistry[T::class] = instanceFactory
    }

    inline fun <reified T: Any> single(noinline declaration: Declaration<T>) {
        val instanceFactory = SingleInstanceFactory(declaration)
        instanceRegistry[T::class] = instanceFactory
    }

    operator fun plus(module: Module) = listOf(module, this)

    inline fun <reified T : Any> get(): T {
        val declaration = instanceRegistry[T::class]
        var instance = declaration?.get()
        if (instance == null) {
            val simpleKoin = getSimpleKoin()
            instance = simpleKoin.instanceList[T::class]?.get()
                ?: error("Unable to find declaration of type ${T::class.qualifiedName}")
        }
        return instance as T
    }

}

operator fun List<Module>.plus(module: Module) = this + listOf(module)

fun module(block: Module.() -> Unit) = Module().apply(block)

val List<Module>.instanceRegistry: Map<KClass<*>, InstanceFactory<*>>
    get() = this.fold(this[0].instanceRegistry) { acc, module ->
        (acc + module.instanceRegistry) as MutableMap<KClass<*>, InstanceFactory<*>>
    }