package com.ximsfei.refinject

import java.util.*
import kotlin.reflect.*
import kotlin.reflect.jvm.isAccessible
import kotlin.reflect.jvm.javaField

object KRefClass {

    private val REF_TYPES = HashMap<String?, KFunction<*>?>()

    init {
        REF_TYPES.put(KRefProperty::class.qualifiedName, KRefProperty::class.primaryConstructor)
    }

    fun load(mappingClass: KClass<*>, className: String): KClass<*>? {
        try {
            return load(mappingClass, Class.forName(className).kotlin)
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }


    fun load(mappingClass: KClass<*>, realClass: KClass<*>): KClass<*> {
        mappingClass.declaredMemberProperties.forEach {
            val func = REF_TYPES[it.returnType.toString().replace("?", "")]
            if (it is KMutableProperty1) {
                it.setter.isAccessible = true
                it.setter.call(mappingClass.objectInstance, func?.call(realClass, it))
            }
        }
        return realClass
    }

}