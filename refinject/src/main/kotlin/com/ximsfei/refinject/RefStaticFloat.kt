package com.ximsfei.refinject

import java.lang.reflect.Field

class RefStaticFloat @Throws(NoSuchFieldException::class)
constructor(cls: Class<*>, field: Field) {
    val field: Field

    init {
        this.field = cls.getDeclaredField(field.name)
        this.field.isAccessible = true
    }

    fun get(): Float {
        try {
            return this.field[null] as Float
        } catch (e: Exception) {
            return 0f
        }
    }

    fun set(float: Float) {
        try {
            this.field.set(null, float)
        } catch (e: Exception) {
        }
    }
}