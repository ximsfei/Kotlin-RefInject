package com.ximsfei.refinject

import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class MethodParams(vararg val value: KClass<*>)