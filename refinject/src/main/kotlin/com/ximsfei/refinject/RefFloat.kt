package com.ximsfei.refinject

import java.lang.reflect.Field

class RefFloat @Throws(NoSuchFieldException::class)
constructor(cls: Class<*>, field: Field) {
    val field: Field

    init {
        this.field = cls.getDeclaredField(field.name)
        this.field.isAccessible = true
    }

    operator fun get(any: Any): Float {
        try {
            return this.field[any] as Float
        } catch (e: Exception) {
            return 0f
        }
    }

    operator fun set(any: Any, float: Float) {
        try {
            this.field.set(any, float)
        } catch (e: Exception) {
        }
    }
}