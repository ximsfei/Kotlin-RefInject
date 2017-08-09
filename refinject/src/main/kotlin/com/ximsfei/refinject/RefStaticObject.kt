package com.ximsfei.refinject

import java.lang.reflect.Field

@Suppress("UNCHECKED_CAST")
class RefStaticObject<T> @Throws(NoSuchFieldException::class)
constructor(cls: Class<*>, field: Field) {
    private val field: Field

    init {
        this.field = cls.getDeclaredField(field.name)
        this.field.isAccessible = true
    }

    fun get(): T {
        var obj: T? = null
        try {
            obj = this.field.get(null) as T
        } catch (e: Exception) {
            //Ignore
        }

        return obj!!
    }

    fun set(obj: T) {
        try {
            this.field.set(null, obj)
        } catch (e: Exception) {
            //Ignore
        }

    }
}