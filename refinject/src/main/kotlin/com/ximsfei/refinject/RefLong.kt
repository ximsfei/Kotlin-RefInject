package com.ximsfei.refinject

import java.lang.reflect.Field

class RefLong @Throws(NoSuchFieldException::class)
constructor(cls: Class<*>, field: Field) {
    val field: Field

    init {
        this.field = cls.getDeclaredField(field.name)
        this.field.isAccessible = true
    }

    operator fun get(any: Any): Long {
        try {
            return this.field[any] as Long
        } catch (e: Exception) {
            return 0L
        }
    }

    operator fun set(any: Any, long: Long) {
        try {
            this.field.set(any, long)
        } catch (e: Exception) {
        }
    }
}