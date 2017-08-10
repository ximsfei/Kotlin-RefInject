package com.ximsfei.refinject

import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD, AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
annotation class KFunctionParams(vararg val value: KClass<*>)