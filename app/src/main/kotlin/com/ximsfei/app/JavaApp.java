package com.ximsfei.app;

/**
 * Created by ximsfei on 2017/8/9.
 */
public class JavaApp {
    public static void main(String[] args) {
        ActivityThread origin = new ActivityThread();
        System.out.println(ActivityThread.class);
        System.out.println(origin);
//        mirror.com.ximsfei.app.ActivityThread.INSTANCE.getMApplication().set(origin, "Inject Application");
//        mirror.com.ximsfei.app.ActivityThread.INSTANCE.getMInstrumentation().set(origin, "Inject Instrumentation");
//        mirror.com.ximsfei.app.ActivityThread.INSTANCE.getMBoolean().set(origin, false);
        System.out.println(mirror.com.ximsfei.app.ActivityThread.INSTANCE.getTYPE());
        System.out.println(origin);
    }
}
