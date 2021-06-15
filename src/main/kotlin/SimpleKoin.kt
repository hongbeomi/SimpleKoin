import context.SimpleKoinContext
import module.Module
import module.declarationRegistry
import service.ServiceLocator
import kotlin.reflect.KClass

class SimpleKoin {

    private val registry = ServiceLocator()
    lateinit var declarations: Map<KClass<*>, Declaration<Any>>

    fun loadModules(modules: List<Module>) {
        declarations = modules.declarationRegistry
        registry.loadModules(modules)
    }

    fun resolveInstance(type: KClass<*>) = registry.getService(type)

}

fun getSimpleKoin() = SimpleKoinContext.getSimpleKoin()

inline fun <reified T: Any> get(): T {
    val service = getSimpleKoin().resolveInstance(T::class)
    return service.instance as T
}

inline fun <reified T: Any> inject(): Lazy<T> = lazy { get() }