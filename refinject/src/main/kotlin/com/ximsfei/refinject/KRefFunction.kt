package com.ximsfei.refinject

import java.lang.reflect.InvocationTargetException
import java.util.*
import kotlin.reflect.*
import kotlin.reflect.jvm.isAccessible

class KRefFunction @Throws(NoSuchMethodException::class)
constructor(cls: KClass<*>, property: KProperty<*>) {
    var func: KFunction<*>

    init {
        val funcAnnotations = property.annotations.filter {
            it.annotationClass == (KFunctionParams::class)
        }
        val funcRefAnnotations = property.annotations.filter {
            it.annotationClass == (KFunctionRefParams::class)
        }
        val sameNameFuncs = cls.declaredMemberFunctions.filter {
            it.name == property.name
        }
        if (sameNameFuncs.isEmpty()) {
            throw NoSuchMethodException("No such method: ${property.name}")
        }
        var targetFuncs: List<KFunction<*>> = ArrayList()
        if (!funcAnnotations.isEmpty()) {
            targetFuncs = sameNameFuncs.filter {
                funcParametersEquals(it, (funcAnnotations[0] as KFunctionParams).value)
            }
        }
        if (targetFuncs.isEmpty() && !funcRefAnnotations.isEmpty()) {
            targetFuncs = sameNameFuncs.filter {
                funcRefParametersEquals(it, (funcRefAnnotations[0] as KFunctionRefParams).value)
            }
        }
        if (targetFuncs.isEmpty()) {
            targetFuncs = sameNameFuncs.filter {
                it.parameters.size == 1
            }
        }
        if (targetFuncs.isEmpty()) {
            throw NoSuchMethodException("No such method: ${property.name}")
        }
        func = targetFuncs.first()
        func.isAccessible = true
    }

    private fun funcParametersEquals(func1: KFunction<*>, values: Array<out KClass<*>>): Boolean {
        var equals = true
        if (func1.parameters.size == values.size + 1) {
            for (i in values.indices) {
                if (values[i].defaultType != func1.parameters[i + 1].type) {
                    equals = false
                    break
                }
            }
        }
        return equals
    }

    private fun funcRefParametersEquals(func1: KFunction<*>, values: Array<out String>): Boolean {
        var equals = true
        if (func1.parameters.size == values.size + 1) {
            for (i in values.indices) {
                if (values[i] != func1.parameters[i + 1].type.toString()) {
                    equals = false
                    break
                }
            }
        }
        return equals
    }

    fun call(receiver: Any, vararg args: Any): Any? {
        try {
            return func.call(receiver, *args) as Any
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
    fun callWithException(receiver: Any, vararg args: Any): Any? {
        try {
            return func.call(receiver, *args) as Any
        } catch (e: InvocationTargetException) {
            if (e.cause != null) {
                throw e.cause as Throwable
            }
            throw e
        }

    }

    fun paramList(): List<KParameter>? {
        return func.parameters
    }
}