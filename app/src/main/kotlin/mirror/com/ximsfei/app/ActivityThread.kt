package mirror.com.ximsfei.app

import com.ximsfei.refinject.KRefProperty
import com.ximsfei.refinject.KRefClass

object ActivityThread {
    var TYPE = KRefClass.load(ActivityThread::class, "com.ximsfei.app.ActivityThread")
    var mInstrumentation: KRefProperty? = null
    var mApplication: KRefProperty? = null
    var mBoolean: KRefProperty? = null
}
