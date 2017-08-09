package com.ximsfei.refinject

import java.lang.reflect.Field
import kotlin.reflect.KClass
import kotlin.reflect.KProperty
import kotlin.reflect.declaredMemberProperties
import kotlin.reflect.jvm.javaField

class RefBoolean @Throws(NoSuchFieldException::class)
constructor(cls: Class<*>, field: Field) {
    val field: Field?

    init {
        this.field = cls.getDeclaredField(field.name)
        this.field?.isAccessible = true
    }

    operator fun get(any: Any): Boolean {
        try {
            return this.field?.get(any) as Boolean
        } catch (e: Exception) {
            return false
        }
    }

    operator fun set(any: Any, boolean: Boolean) {
        try {
            this.field?.set(any, boolean)
        } catch (e: Exception) {
        }
    }
}