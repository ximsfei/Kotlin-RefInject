package com.ximsfei.refinject

import java.lang.reflect.Field

class RefInt @Throws(NoSuchFieldException::class)
constructor(cls: Class<*>, field: Field) {
    val field: Field

    init {
        this.field = cls.getDeclaredField(field.name)
        this.field.isAccessible = true
    }

    operator fun get(any: Any): Int {
        try {
            return this.field[any] as Int
        } catch (e: Exception) {
            return 0
        }
    }

    operator fun set(any: Any, int: Int) {
        try {
            this.field.set(any, int)
        } catch (e: Exception) {
        }
    }
}