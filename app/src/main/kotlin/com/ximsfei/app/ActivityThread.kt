package com.ximsfei.app

class ActivityThread {
    val mInstrumentation = "Origin mInstrumentation"
    val mApplication = "Origin mApplication"
    val mBoolean = false

    override fun toString(): String {
        return "ActivityThread mInstrumentation = $mInstrumentation, mApplication = $mApplication, mBoolean = $mBoolean"
    }
}
