package com.ximsfei.app

fun main(args: Array<String>) {
    val origin = ActivityThread()
    println(origin)
    mirror.com.ximsfei.app.ActivityThread.string?.call(origin)
    mirror.com.ximsfei.app.ActivityThread.mApplication?.set(origin, "Inject Application")
    mirror.com.ximsfei.app.ActivityThread.mInstrumentation?.set(origin, "Inject Instrumentation")
    mirror.com.ximsfei.app.ActivityThread.mBoolean?.set(origin, false)
//    println(mirror.com.ximsfei.app.ActivityThread.getVal)
    mirror.com.ximsfei.app.ActivityThread.getVal?.call(origin)
    mirror.com.ximsfei.app.ActivityThread.printAAndB?.call(origin, 1, 2)
    mirror.com.ximsfei.app.ActivityThread.printAAndBRef?.call(origin, 5, 3)
    println(origin)
    mirror.com.ximsfei.app.ActivityThread.string?.call(origin)
}
