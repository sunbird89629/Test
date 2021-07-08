package com.sunbird.test.coroutine

import kotlin.coroutines.*

/**
 * 作者：王豪
 * 日期：2021/6/20
 * 描述：<必填>
 */
interface Generator<T> {
    operator fun iterator(): Iterator<T>
}

interface GeneratorScope<T> {
    suspend fun yield(value: T)
}

class GeneratorImpl<T>(
    private val block: suspend GeneratorScope<T>.(T) -> Unit,
    private val parameter: T
) : Generator<T> {
    override fun iterator(): Iterator<T> {
        return GeneratorIterator(block, parameter)
    }
}

class GeneratorIterator<T>(
    private val block: suspend GeneratorScope<T>.(T) -> Unit,
    private val parameter: T
) : GeneratorScope<T>, Iterator<T>, Continuation<Any?> {
    override val context: CoroutineContext = EmptyCoroutineContext

    private var state: State

    init {
        val coroutineBlock: suspend GeneratorScope<T>.() -> Unit = { block(parameter) }
        val start = coroutineBlock.createCoroutine(this, this)
        state = State.NotReady(start)
    }

    override suspend fun yield(value: T) = suspendCoroutine<Unit> { continuation ->

        println("yield()->value:${value}")

        state = when (state) {
            is State.NotReady -> State.Ready(continuation, value)
            is State.Ready<*> -> throw IllegalStateException("Cannot yield while ready.")
            State.Done -> throw java.lang.IllegalStateException("Cannot yield while done.")
        }
    }

    private fun resume() {
        when (val currentState = state) {
            is State.NotReady -> currentState.continuation.resume(Unit)
        }
    }

    override fun hasNext(): Boolean {
        println("hasNext()")
        resume()
        return state != State.Done
    }

    override fun next(): T {
        println("next()")
        return when (val currentState = state) {
            is State.NotReady -> {
                resume()
                return next()
            }
            is State.Ready<*> -> {
                state = State.NotReady(currentState.continuation)
                (currentState as State.Ready<T>).nextValue
            }
            State.Done -> throw IndexOutOfBoundsException("No value left.")
        }
    }

    override fun resumeWith(result: Result<Any?>) {
        println("resumeWith")
        state = State.Done
        result.getOrThrow()
    }

}

sealed class State {
    class NotReady(val continuation: Continuation<Unit>) : State()
    class Ready<T>(val continuation: Continuation<Unit>, val nextValue: T) : State()
    object Done : State()
}

fun <T> generator(block: suspend GeneratorScope<T>.(T) -> Unit): (T) -> Generator<T> {
    return { parameter: T ->
        GeneratorImpl(block, parameter)
    }
}

fun main() {
    val nums = generator { start: Int ->
        for (i in 0..5) {
            yield(start + i)
        }
    }

    val seq = nums(10)

    for (j in seq) {
        println(j)
    }
}