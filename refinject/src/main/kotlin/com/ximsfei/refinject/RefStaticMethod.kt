package com.ximsfei.refinject

import java.lang.reflect.Field
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method

@Suppress("UNCHECKED_CAST")
class RefStaticMethod<out T> @Throws(NoSuchFieldException::class)
constructor(cls: Class<*>, field: Field) {
    var method: Method = null!!

    init {
        if (field.isAnnotationPresent(MethodParams::class.java)) {
            val types = field.getAnnotation(MethodParams::class.java).value
            val javaTypes = Array<Class<*>>(types.size, null!!)
            for (i in types.indices) {
                val clazz = types[i]
                if (clazz.java.classLoader === javaClass.classLoader) {
                    try {
                        javaTypes[i] = clazz.java.getField("TYPE").get(null).javaClass
                    } catch (e: Throwable) {
                        throw RuntimeException(e)
                    }
                }
            }
            this.method = cls.getDeclaredMethod(field.name, *javaTypes)
            this.method.isAccessible = true
        } else if (field.isAnnotationPresent(MethodReflectParams::class.java)) {
            val typeNames = field.getAnnotation(MethodReflectParams::class.java).value
            val javaTypes = Array<Class<*>>(typeNames.size, null!!)
            for (i in typeNames.indices) {
                javaTypes[i] = Class.forName(typeNames[i])
            }
            this.method = cls.getDeclaredMethod(field.name, *javaTypes)
            this.method.isAccessible = true
        } else {
            for (method in cls.declaredMethods) {
                if (method.name == field.name) {
                    this.method = method
                    this.method.isAccessible = true
                    break
                }
            }
        }
    }

    fun call(vararg args: Any): T? {
        try {
            return this.method.invoke(null, *args) as T
        } catch (e: InvocationTargetException) {
            if (e.cause != null) {
                e.cause!!.printStackTrace()
            } else {
                e.printStackTrace()
            }
        } catch (e: Throwable) {
            e.printStackTrace()
        }

        return null
    }

    @Throws(Throwable::class)
    fun callWithException(vararg args: Any): T {
        try {
            return this.method.invoke(null, *args) as T
        } catch (e: InvocationTargetException) {
            if (e.cause != null) {
                throw e.cause!!
            }
            throw e
        }

    }

    fun paramList(): Array<Class<*>> {
        return method.parameterTypes
    }
}