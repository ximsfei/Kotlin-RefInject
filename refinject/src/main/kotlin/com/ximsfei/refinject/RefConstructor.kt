package com.ximsfei.refinject

import java.lang.reflect.Constructor
import java.lang.reflect.Field

@Suppress("UNCHECKED_CAST")
class RefConstructor<out T> @Throws(NoSuchMethodException::class)
constructor(cls: Class<*>, field: Field) {
    private var ctor: Constructor<*>? = null

    init {
        if (field.isAnnotationPresent(MethodParams::class.java)) {
            val types = field.getAnnotation<MethodParams>(MethodParams::class.java).value
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
            ctor = cls.getDeclaredConstructor(*javaTypes)
        } else if (field.isAnnotationPresent(MethodReflectParams::class.java)) {
            val values = field.getAnnotation<MethodReflectParams>(MethodReflectParams::class.java).value
            val parameterTypes = arrayOfNulls<Class<*>>(values.size)
            var N = 0
            while (N < values.size) {
                try {
                    parameterTypes[N] = Class.forName(values[N])
                    N++
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
            ctor = cls.getDeclaredConstructor(*parameterTypes)
        } else {
            ctor = cls.getDeclaredConstructor()
        }
        if (ctor != null && !ctor!!.isAccessible) {
            ctor!!.isAccessible = true
        }
    }

    fun newInstance(): T? {
        try {
            return ctor!!.newInstance() as T
        } catch (e: Exception) {
            return null
        }

    }

    fun newInstance(vararg params: Any): T? {
        try {
            return ctor!!.newInstance(*params) as T
        } catch (e: Exception) {
            return null
        }

    }
}