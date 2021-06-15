package service

import module.Module
import toService
import java.util.concurrent.ConcurrentHashMap
import kotlin.reflect.KClass

class ServiceLocator {

    private val serviceMap: MutableMap<KClass<*>, Service> = ConcurrentHashMap()

    fun <T: Any> getService(clazz: KClass<T>): Service {
        return serviceMap[clazz] ?: error("Unable to find definition of $clazz")
    }

    private fun addService(service: Service) {
        serviceMap[service.type] = service
    }

    fun loadModules(modules: List<Module>) {
        modules.forEach(::registerModule)
    }

    private fun registerModule(module: Module) {
        module.declarationRegistry.forEach {
            addService(it.value.toService())
        }
    }

}