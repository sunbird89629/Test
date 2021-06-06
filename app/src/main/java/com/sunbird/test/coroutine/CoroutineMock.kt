//package com.sunbird.test.coroutine
//
//import kotlinx.coroutines.runBlocking
//import java.util.concurrent.atomic.AtomicReference
//import kotlin.coroutines.Continuation
//import kotlin.coroutines.CoroutineContext
//
///**
// * 作者：王豪
// * 日期：2021/6/3
// * 描述：<必填>
// */
//
//
//interface Job : CoroutineContext.Element {
//    //key主要是用于将协程的Job实例存入它的上下文中，这样我们只要能够获得协程的上下文即可拿到Job的实例
//    companion object Key : CoroutineContext.Key<Job>
//
//    override val key: CoroutineContext.Key<*> get() = Job
//
//    val isActive: Boolean
//
//
//    //注册一个携程被取消时触发的回调
//    fun invokeOnCancel(onCancel: OnCancel): Disposable
//
//    //注册一个携程完成时的回调
//    fun invokeOnCompletion(onComplete: OnComplete): Disposable
//
//    fun cancel()
//
//    //移除回调
//    fun remove(disposable: Disposable)
//
//    suspend fun join()
//}
//
//sealed class DisposableList {
//    object Nil : DisposableList()
//    class Cons(
//        val head: Disposable,
//        val tail: DisposableList
//    ) : DisposableList()
//}
//
//fun DisposableList.remove(disposable: Disposable): DisposableList {
//    return when (this) {
//        DisposableList.Nil -> this
//        is DisposableList.Cons -> {
//            return tail
//        }
//        else-> {
//            DisposableList.Cons(head, tail.remove(disposable))
//        }
//    }
//}
//
//inline fun <reified T : Disposable> DisposableList.loopOn(
//    crossline action: (T) -> Unit
//) = forEach {
//    when (it) {
//        is T -> action(it)
//    }
//}
//
//tailrec fun DisposableList.forEach(action: (Disposable) -> Unit): Unit = when (this) {
//    DisposableList.Nil -> Unit
//    is DisposableList.Cons -> {
//        action(this.head)
//        this.tail.forEach(action)
//    }
//}
//
//
//sealed class CoroutineState {
//
//    private var disposableList: DisposableList = DisposableList.Nil
//
//    fun from(state: CoroutineState): CoroutineState {
//        this.disposableList = state.disposableList
//        return this
//    }
//
//    fun without(disposable: Disposable): CoroutineState {
//        this.disposableList = this.disposableList.remove(disposable)
//        return this
//    }
//
//    fun clear() {
//        this.disposableList = DisposableList.Nil
//    }
//
//    //携程启动后立即进入该状态，直到完成或者被取消。
//    class Incomplete : CoroutineState()
//
//    //携程执行中被取消后进入该状态
//    class Cancelling : CoroutineState()
//
//    //携程执行完成（包括正常返回和异常结束）时进入该状态
//    class Complete<T>(
//        val value: T? = null,
//        val exception: Throwable? = null
//    ) : CoroutineState()
//}
//
//abstract class AbstractCoroutine<T>(context: CoroutineContext) : Job, Continuation<T> {
//    protected val state = AtomicReference<CoroutineState>()
//
//    override val context: CoroutineContext
//
//    init {
//        state.set(CoroutineState.Incomplete)
//        this.context = context + this
//    }
//
//    val isCompleted
//        get() = state.get() is CoroutineState.Complete<*>
//
//    override val isActive: Boolean
//        get() = when (state.get()) {
//            is CoroutineState.Complete<*>,
//            is CoroutineState.Cancelling -> false
//            else -> true
//        }
//}
//
//
//fun main() {
//    runBlocking {  }
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
