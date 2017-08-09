package com.ximsfei.refinject

import java.lang.reflect.Field

class RefStaticBoolean @Throws(NoSuchFieldException::class)
constructor(cls: Class<*>, field: Field) {
    val field: Field

    init {
        this.field = cls.getDeclaredField(field.name)
        this.field.isAccessible = true
    }

    fun get(): Boolean {
        try {
            return this.field[null] as Boolean
        } catch (e: Exception) {
            return false
        }
    }

    fun set(boolean: Boolean) {
        try {
            this.field.set(null, boolean)
        } catch (e: Exception) {
        }
    }
}