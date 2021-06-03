package com.sunbird.test.coroutine

import kotlin.coroutines.CoroutineContext

/**
 * 作者：王豪
 * 日期：2021/6/3
 * 描述：<必填>
 */


interface Job : CoroutineContext.Element {
    //key主要是用于将协程的Job实例存入它的上下文中，这样我们只要能够获得协程的上下文即可拿到Job的实例
    companion object Key : CoroutineContext.Key<Job>

    override val key: CoroutineContext.Key<*> get() = Job

    val isActive: Boolean


    //注册一个携程被取消时触发的回调
    fun invokeOnCancel(onCancel: OnCancel): Disposable

    //注册一个携程完成时的回调
    fun invokeOnCompletion(onComplete: OnComplete): Disposable

    fun cancel()

    //移除回调
    fun remove(disposable: Disposable)

    suspend fun join()
}

sealed class DisposableList {
    object Nil : DisposableList()
    class Cons(
        val head: Disposable,
        val tail: DisposableList
    ) : DisposableList()
}


sealed class CoroutineState {

    //携程启动后立即进入该状态，直到完成或者被取消。
    class Incomplete : CoroutineState()

    //携程执行中被取消后进入该状态
    class Cancelling : CoroutineState()

    //携程执行完成（包括正常返回和异常结束）时进入该状态
    class Complete<T>(
        val value: T? = null,
        val exception: Throwable? = null
    ) : CoroutineState()
}
