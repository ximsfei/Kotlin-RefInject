package com.ximsfei.refinject

import java.util.*
import kotlin.reflect.*
import kotlin.reflect.jvm.isAccessible

object KRefClass {

    private val REF_TYPES = HashMap<String?, KFunction<*>?>()

    init {
        REF_TYPES.put(KRefProperty::class.qualifiedName, KRefProperty::class.primaryConstructor)
        REF_TYPES.put(KRefFunction::class.qualifiedName, KRefFunction::class.primaryConstructor)
    }

    fun load(mappingClass: KClass<*>, className: String): KClass<*>? {
        try {
            return load(mappingClass, Class.forName(className).kotlin)
        } catch (e: Exception) {
            return null
        }
    }


    fun load(mappingClass: KClass<*>, realClass: KClass<*>): KClass<*> {
        mappingClass.declaredMemberProperties.forEach {
            try {
                if (it is KMutableProperty1) {
                    it.setter.isAccessible = true
                    it.setter.call(mappingClass.objectInstance,
                            REF_TYPES[it.returnType.toString().replace("?", "")]?.call(realClass, it))
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return realClass
    }
}