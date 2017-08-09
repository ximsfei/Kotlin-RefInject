package com.ximsfei.refinject

import java.lang.reflect.Field

class RefDouble @Throws(NoSuchFieldException::class)
constructor(cls: Class<*>, field: Field) {
    val field: Field

    init {
        this.field = cls.getDeclaredField(field.name)
        this.field.isAccessible = true
    }

    operator fun get(any: Any): Double {
        try {
            return this.field[any] as Double
        } catch (e: Exception) {
            return 0.0
        }
    }

    operator fun set(any: Any, double: Double) {
        try {
            this.field.set(any, double)
        } catch (e: Exception) {
        }
    }
}