package com.ximsfei.refinject

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class MethodReflectParams(vararg val value: String)