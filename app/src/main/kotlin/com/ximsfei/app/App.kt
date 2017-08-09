package com.ximsfei.app

fun main(args: Array<String>) {
    val origin = ActivityThread()
    println(ActivityThread::class)
    println(origin)
    mirror.com.ximsfei.app.ActivityThread.mApplication?.set(origin, "Inject Application")
    mirror.com.ximsfei.app.ActivityThread.mInstrumentation?.set(origin, "Inject Instrumentation")
    mirror.com.ximsfei.app.ActivityThread.mBoolean?.set(origin, true)
    println(mirror.com.ximsfei.app.ActivityThread.TYPE)
    println(origin)
}