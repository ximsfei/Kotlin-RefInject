package com.ximsfei.refinject

import java.lang.reflect.Field

class RefStaticDouble @Throws(NoSuchFieldException::class)
constructor(cls: Class<*>, field: Field) {
    val field: Field

    init {
        this.field = cls.getDeclaredField(field.name)
        this.field.isAccessible = true
    }

    fun get(): Double {
        try {
            return this.field[null] as Double
        } catch (e: Exception) {
            return 0.0
        }
    }

    fun set(double: Double) {
        try {
            this.field.set(null, double)
        } catch (e: Exception) {
        }
    }
}