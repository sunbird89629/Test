package com.sunbird.test.pluggable;

import org.xutils.common.util.LogUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 作者：王豪
 * 日期：2021/7/27
 * 描述：<必填>
 */

public class HookHelper {
    public static void hookActivityTaskManager() {
        try {
            Object single = RefInvoke.getStaticFieldObject("android.app.ActivityTaskManager", "IActivityTaskManagerSingleton");


            Object instance = RefInvoke.getFieldObject("android.util.Singleton", single, "mInstance");

            Class[] interfaces = new Class[]{Class.forName("android.app.IActivityTaskManager")};

            Object proxyInstance = Proxy.newProxyInstance(HookHelper.class.getClassLoader(), interfaces, new SingletonInvocationHandler(instance));

            RefInvoke.setFieldObject("android.util.Singleton", single, "mInstance", proxyInstance);

            LogUtil.i("called......");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }


//        MySingleton mySingleton = new MySingleton();
//
//        RefInvoke.setStaticFieldObject("android.app.ActivityTaskManager", "IActivityTaskManagerSingleton", mySingleton);


    }
}

class SingletonInvocationHandler implements InvocationHandler {
    private Object target;

    public SingletonInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        LogUtil.i(method.getName() + " called......");
        return method.invoke(target, args);
    }
}


//class MySingleton extends android.util.Singleton<android.app.IActivityTaskManager> {
//    protected android.app.IActivityTaskManager create() {
//        final IBinder b = ServiceManager.getService(Context.ACTIVITY_TASK_SERVICE);
//        return IActivityTaskManager.Stub.asInterface(b);
//    }
//}
