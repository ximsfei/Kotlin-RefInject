package mirror.com.ximsfei.app

import com.ximsfei.refinject.*

object ActivityThread {
    var TYPE = KRefClass.load(ActivityThread::class, "com.ximsfei.app.ActivityThread")
    var mInstrumentation: KRefProperty? = null
    var mApplication: KRefProperty? = null
    var mBoolean: KRefProperty? = null
    @KFunctionParams(String::class)
    var getVal: KRefFunction? = null
    @KFunctionParams(Int::class, Int::class)
    var printAAndB: KRefFunction? = null
    @KFunctionRefParams("kotlin.Int", "kotlin.Int")
    var printAAndBRef: KRefFunction? = null
    var string: KRefFunction? = null
}
