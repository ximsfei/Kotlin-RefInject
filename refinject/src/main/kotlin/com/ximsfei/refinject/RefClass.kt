package com.ximsfei.refinject

import java.lang.reflect.Constructor
import java.lang.reflect.Field
import java.lang.reflect.Modifier
import java.util.*

object RefClass {

    private val REF_TYPES = HashMap<Class<*>, Constructor<*>>()

    init {
        try {
            REF_TYPES.put(RefObject::class.java, RefObject::class.java.getConstructor(Class::class.java, Field::class.java))
            REF_TYPES.put(RefMethod::class.java, RefMethod::class.java.getConstructor(Class::class.java, Field::class.java))
            REF_TYPES.put(RefInt::class.java, RefInt::class.java.getConstructor(Class::class.java, Field::class.java))
            REF_TYPES.put(RefLong::class.java, RefLong::class.java.getConstructor(Class::class.java, Field::class.java))
            REF_TYPES.put(RefFloat::class.java, RefFloat::class.java.getConstructor(Class::class.java, Field::class.java))
            REF_TYPES.put(RefDouble::class.java, RefDouble::class.java.getConstructor(Class::class.java, Field::class.java))
            REF_TYPES.put(RefBoolean::class.java, RefBoolean::class.java.getConstructor(Class::class.java, Field::class.java))
            REF_TYPES.put(RefStaticObject::class.java, RefStaticObject::class.java.getConstructor(Class::class.java, Field::class.java))
            REF_TYPES.put(RefStaticInt::class.java, RefStaticInt::class.java.getConstructor(Class::class.java, Field::class.java))
            REF_TYPES.put(RefStaticLong::class.java, RefStaticLong::class.java.getConstructor(Class::class.java, Field::class.java))
            REF_TYPES.put(RefStaticFloat::class.java, RefStaticFloat::class.java.getConstructor(Class::class.java, Field::class.java))
            REF_TYPES.put(RefStaticDouble::class.java, RefStaticDouble::class.java.getConstructor(Class::class.java, Field::class.java))
            REF_TYPES.put(RefStaticBoolean::class.java, RefStaticBoolean::class.java.getConstructor(Class::class.java, Field::class.java))
            REF_TYPES.put(RefStaticMethod::class.java, RefStaticMethod::class.java.getConstructor(Class::class.java, Field::class.java))
            REF_TYPES.put(RefConstructor::class.java, RefConstructor::class.java.getConstructor(Class::class.java, Field::class.java))
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun load(mappingClass: Class<*>, className: String): Class<*>? {
        try {
            return load(mappingClass, Class.forName(className))
        } catch (e: Exception) {
            return null
        }

    }


    fun load(mappingClass: Class<*>, realClass: Class<*>): Class<*> {
        val fields = mappingClass.declaredFields
        for (field in fields) {
            try {
                if (Modifier.isStatic(field.modifiers)) {
                    val constructor = REF_TYPES[field.type]
                    if (constructor != null) {
                        field.isAccessible = true
                        field.set(null, constructor.newInstance(realClass, field))
                    }
                }
            } catch (e: Exception) {
                // Ignore
            }

        }
        return realClass
    }

}