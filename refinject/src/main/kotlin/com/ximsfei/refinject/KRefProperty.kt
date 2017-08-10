package com.ximsfei.refinject

import kotlin.reflect.*
import kotlin.reflect.jvm.isAccessible
import kotlin.reflect.jvm.javaField

class KRefProperty
constructor(cls: KClass<*>, property: KProperty<*>) {
    val property: KProperty1<out Any, Any?>?

    init {
        this.property = cls.declaredMemberProperties.filter {
            it.name == property.name
        }.first()
    }

    operator fun get(any: Any): Any? {
        return property?.getter?.call(any)
    }

    operator fun set(any: Any, value: Any) {
        if (property is KMutableProperty1) {
            property.setter.isAccessible = true
            property.setter.call(any, value)
        } else {
            property?.javaField?.isAccessible = true
            property?.javaField?.set(any, value)
        }
    }
}
