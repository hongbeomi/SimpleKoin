package factory

import Declaration

class SingleInstanceFactory<T>(declaration: Declaration<T>) : InstanceFactory<T>(declaration) {

    private var value: T? = null

    private fun getValue() = value ?: error("Single instance created couldn't return value")

    override fun create(): T {
        return if (value == null) {
            super.create()
        } else {
            getValue()
        }
    }

    override fun isCreate(): Boolean {
        return value != null
    }

    override fun get(): T {
        if (!isCreate()) {
            value = create()
        }
        return getValue()
    }

    override fun drop() {
        value = null
    }


}