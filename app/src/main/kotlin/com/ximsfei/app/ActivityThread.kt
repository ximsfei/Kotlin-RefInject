package com.ximsfei.app

class ActivityThread {
    private val mInstrumentation = "Origin mInstrumentation"
    private var mApplication = "Origin mApplication"
    private var mBoolean = true

    private val mVal = "val"

    private fun getVal() {
        println(mVal)
    }

    private fun printAAndB(a: Int, b: Int) {
        println(a + b)
    }

    private fun printAAndBRef(a: Int, b: Int) {
        println(a + b)
    }

    private fun string(): String {
        return "ActivityThread mInstrumentation = $mInstrumentation, mApplication = $mApplication, mBoolean = $mBoolean"
    }

    override fun toString(): String {
        return "toString: " + string()
    }
}
