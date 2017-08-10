package com.ximsfei.refinject

import kotlin.reflect.*
import kotlin.reflect.jvm.isAccessible
import kotlin.reflect.jvm.javaField

class KRefProperty @Throws(NoSuchFieldException::class)
constructor(cls: KClass<*>, property: KProperty<*>) {
    companion object {
        val propNullable: KRefProperty? = null
    }

    val property: KProperty1<out Any, Any?>

    init {
        val targetProperties = cls.declaredMemberProperties.filter {
            it.name == property.name
        }
        if (!targetProperties.isEmpty()) {
            this.property = targetProperties.first()
        } else {
            throw NoSuchFieldException("No such field: ${property.name}")
        }
    }

    operator fun get(any: Any): Any? {
        return property.getter.call(any)
    }

    operator fun set(any: Any, value: Any) {
        if (property is KMutableProperty1) {
            property.setter.isAccessible = true
            property.setter.call(any, value)
        } else {
            property.javaField?.isAccessible = true
            property.javaField?.set(any, value)
        }
    }
}
