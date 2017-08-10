package com.ximsfei.app

class ActivityThread {
    private val mInstrumentation = "Origin mInstrumentation"
    private var mApplication = "Origin mApplication"
    private var mBoolean = true

    override fun toString(): String {
        return "ActivityThread mInstrumentation = $mInstrumentation, mApplication = $mApplication, mBoolean = $mBoolean"
    }
}
