package mirror.com.ximsfei.app

import com.ximsfei.refinject.RefBoolean
import com.ximsfei.refinject.RefClass
import com.ximsfei.refinject.RefObject

object ActivityThread {
    var TYPE = RefClass.load(ActivityThread::class.java, "com.ximsfei.app.ActivityThread")
    var mInstrumentation: RefObject<String>? = null
    var mApplication: RefObject<String>? = null
    var mBoolean: RefBoolean? = null
}
