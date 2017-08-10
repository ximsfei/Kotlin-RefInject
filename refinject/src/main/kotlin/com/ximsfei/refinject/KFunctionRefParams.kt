package com.ximsfei.refinject

@Target(AnnotationTarget.FIELD, AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
annotation class KFunctionRefParams(vararg val value: String)