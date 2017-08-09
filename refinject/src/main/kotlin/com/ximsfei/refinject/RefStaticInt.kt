package com.ximsfei.refinject

import java.lang.reflect.Field

class RefStaticInt @Throws(NoSuchFieldException::class)
constructor(cls: Class<*>, field: Field) {
    val field: Field

    init {
        this.field = cls.getDeclaredField(field.name)
        this.field.isAccessible = true
    }

    fun get(): Int {
        try {
            return this.field[null] as Int
        } catch (e: Exception) {
            return 0
        }
    }

    fun set(int: Int) {
        try {
            this.field.set(null, int)
        } catch (e: Exception) {
        }
    }
}