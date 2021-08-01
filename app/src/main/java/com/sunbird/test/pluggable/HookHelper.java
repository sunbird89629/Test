package com.sunbird.test.pluggable;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

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

    public static final String EXTRA_TARGET_INTENT = "extra_target_intent";


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
    }

    public static void attachBaseContext() throws Exception {
        Object currentActivityThread = RefInvoke.getStaticFieldObject("android.app.ActivityThread", "sCurrentActivityThread");
        Handler mH = (Handler) RefInvoke.getFieldObject(currentActivityThread, "mH");
        RefInvoke.setFieldObject(Handler.class, mH, "mCallback", new HandlerCallbackMock(mH));
    }
}

class SingletonInvocationHandler implements InvocationHandler {
    private Object target;

    public SingletonInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        LogUtil.i("before>>method name:" + method.getName());
        Object result = null;
        if (method.getName().equals("startActivity")) {
            Intent rawIntent = null;
            int intentIndex = -1;
            for (int i = 0; i < args.length; i++) {
                if (args[i] instanceof Intent) {
                    rawIntent = (Intent) args[i];
                    intentIndex = i;
                    break;
                }
            }

            Intent newIntent = new Intent();

            String stubPackage = rawIntent.getComponent().getPackageName();

            //SecondHookActivity 不在 Manifiest 文件中声明，按规则不能启动
            //这里我们将Activity临时替换为StubActivity,以通过AMS的验证
            //把真正要启动的Activity放到新的Intent中
            //真正启动的时候再替换回来

            ComponentName componentName = new ComponentName(stubPackage, StubActivity.class.getName());
            newIntent.setComponent(componentName);
            newIntent.putExtra(HookHelper.EXTRA_TARGET_INTENT, rawIntent);
            args[intentIndex] = newIntent;

            result = method.invoke(target, args);
        } else {
            result = method.invoke(target, args);
        }
        return result;
    }
}


class HandlerCallbackMock implements Handler.Callback {
    private Handler mBase;

    public HandlerCallbackMock(Handler mBase) {
        this.mBase = mBase;
    }

    @Override
    public boolean handleMessage(@NonNull Message msg) {
        if(msg.what==159){
            handleLaunchActivity(msg);
        }
        mBase.handleMessage(msg);
        return true;
    }

    private void handleLaunchActivity(Message msg) {
        Object clientTransaction = msg.obj;
        // ActivityLifecycleItem
        Object activityLifeCycleItem = RefInvoke.invokeInstanceMethod(clientTransaction, "getLifecycleStateRequest");

        if (activityLifeCycleItem != null) {
            LogUtil.i("activityLifeCycleItem:" + activityLifeCycleItem.getClass().getSimpleName());
        }

        Intent intent = (Intent) RefInvoke.getFieldObject(clientTransaction, "intent");
        if (intent != null) {
            Intent targetIntent = intent.getParcelableExtra(HookHelper.EXTRA_TARGET_INTENT);
            intent.setComponent(targetIntent.getComponent());
        }
    }
}