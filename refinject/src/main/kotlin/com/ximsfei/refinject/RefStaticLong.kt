package com.ximsfei.refinject

import java.lang.reflect.Field

class RefStaticLong @Throws(NoSuchFieldException::class)
constructor(cls: Class<*>, field: Field) {
    val field: Field

    init {
        this.field = cls.getDeclaredField(field.name)
        this.field.isAccessible = true
    }

    fun get(): Long {
        try {
            return this.field[null] as Long
        } catch (e: Exception) {
            return 0L
        }
    }

    fun set(long: Long) {
        try {
            this.field.set(null, long)
        } catch (e: Exception) {
        }
    }
}