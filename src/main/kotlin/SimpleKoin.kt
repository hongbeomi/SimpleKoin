import context.SimpleKoinContext
import factory.InstanceFactory
import module.Module
import module.instanceRegistry
import service.ServiceLocator
import kotlin.reflect.KClass

class SimpleKoin {

    private val registry = ServiceLocator()
    lateinit var instanceList: Map<KClass<*>, InstanceFactory<*>>

    fun loadModules(modules: List<Module>) {
        instanceList = modules.instanceRegistry
        registry.loadModules(modules)
    }

    fun resolveInstance(type: KClass<*>) = registry.getService(type)

}

fun getSimpleKoin() = SimpleKoinContext.getSimpleKoin()

inline fun <reified T: Any> get(): T {
    val service = getSimpleKoin().resolveInstance(T::class)
    return service.factory.get() as T
}

inline fun <reified T: Any> inject(): Lazy<T> = lazy { get() }

typealias Declaration<T> = () -> T