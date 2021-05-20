package com.sunbird.test

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.view.NestedScrollingParent3
import org.xutils.common.util.LogUtil

/**
 * 作者：王豪
 * 日期：2021/5/16
 * 描述：<必填>
 */
class MyLinearLayout : LinearLayoutCompat, NestedScrollingParent3 {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    /**
     * React to a descendant view initiating a nestable scroll operation, claiming the
     * nested scroll operation if appropriate.
     *
     *
     * This method will be called in response to a descendant view invoking
     * [ViewCompat.startNestedScroll]. Each parent up the view hierarchy will be
     * given an opportunity to respond and claim the nested scrolling operation by returning
     * `true`.
     *
     *
     * This method may be overridden by ViewParent implementations to indicate when the view
     * is willing to support a nested scrolling operation that is about to begin. If it returns
     * true, this ViewParent will become the target view's nested scrolling parent for the duration
     * of the scroll operation in progress. When the nested scroll is finished this ViewParent
     * will receive a call to [.onStopNestedScroll].
     *
     *
     * @param child Direct child of this ViewParent containing target
     * @param target View that initiated the nested scroll
     * @param axes Flags consisting of [ViewCompat.SCROLL_AXIS_HORIZONTAL],
     * [ViewCompat.SCROLL_AXIS_VERTICAL] or both
     * @param type the type of input which cause this scroll event
     * @return true if this ViewParent accepts the nested scroll operation
     */
    override fun onStartNestedScroll(child: View, target: View, axes: Int, type: Int): Boolean {
        LogUtil.d("child:${child}")
        LogUtil.d("called......")
        return true
    }

    /**
     * React to the successful claiming of a nested scroll operation.
     *
     *
     * This method will be called after
     * [onStartNestedScroll][.onStartNestedScroll] returns true. It
     * offers an opportunity for the view and its superclasses to perform initial configuration
     * for the nested scroll. Implementations of this method should always call their superclass's
     * implementation of this method if one is present.
     *
     * @param child Direct child of this ViewParent containing target
     * @param target View that initiated the nested scroll
     * @param axes Flags consisting of [ViewCompat.SCROLL_AXIS_HORIZONTAL],
     * [ViewCompat.SCROLL_AXIS_VERTICAL] or both
     * @param type the type of input which cause this scroll event
     * @see .onStartNestedScroll
     * @see .onStopNestedScroll
     */
    override fun onNestedScrollAccepted(child: View, target: View, axes: Int, type: Int) {
        LogUtil.i("called......")
    }

    /**
     * React to a nested scroll operation ending.
     *
     *
     * Perform cleanup after a nested scrolling operation.
     * This method will be called when a nested scroll stops, for example when a nested touch
     * scroll ends with a [MotionEvent.ACTION_UP] or [MotionEvent.ACTION_CANCEL] event.
     * Implementations of this method should always call their superclass's implementation of this
     * method if one is present.
     *
     * @param target View that initiated the nested scroll
     * @param type the type of input which cause this scroll event
     */
    override fun onStopNestedScroll(target: View, type: Int) {
        LogUtil.i("called......")
    }

    /**
     * React to a nested scroll in progress.
     *
     *
     * This method will be called when the ViewParent's current nested scrolling child view
     * dispatches a nested scroll event. To receive calls to this method the ViewParent must have
     * previously returned `true` for a call to
     * [.onStartNestedScroll].
     *
     *
     * Both the consumed and unconsumed portions of the scroll distance are reported to the
     * ViewParent. An implementation may choose to use the consumed portion to match or chase scroll
     * position of multiple child elements, for example. The unconsumed portion may be used to
     * allow continuous dragging of multiple scrolling or draggable elements, such as scrolling
     * a list within a vertical drawer where the drawer begins dragging once the edge of inner
     * scrolling content is reached.
     *
     *
     * This method is called when a nested scrolling child invokes
     * [NestedScrollingChild3.dispatchNestedScroll]} or
     * one of methods it overloads.
     *
     *
     * An implementation must report how many pixels of the the x and y scroll distances were
     * consumed by this nested scrolling parent by adding the consumed distances to the
     * `consumed` parameter. If this View also implements [NestedScrollingChild3],
     * `consumed` should also be passed up to it's nested scrolling parent so that the
     * parent may also add any scroll distance it consumes. Index 0 corresponds to dx and index 1
     * corresponds to dy.
     *
     * @param target The descendant view controlling the nested scroll
     * @param dxConsumed Horizontal scroll distance in pixels already consumed by target
     * @param dyConsumed Vertical scroll distance in pixels already consumed by target
     * @param dxUnconsumed Horizontal scroll distance in pixels not consumed by target
     * @param dyUnconsumed Vertical scroll distance in pixels not consumed by target
     * @param type the type of input which cause this scroll event
     * @param consumed Output. Upon this method returning, will contain the scroll
     * distances consumed by this nested scrolling parent and the scroll distances
     * consumed by any other parent up the view hierarchy
     *
     * @see NestedScrollingChild3.dispatchNestedScroll
     */
    override fun onNestedScroll(
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        type: Int,
        consumed: IntArray
    ) {
        LogUtil.i("called......")
    }

    /**
     * React to a nested scroll in progress.
     *
     *
     * This method will be called when the ViewParent's current nested scrolling child view
     * dispatches a nested scroll event. To receive calls to this method the ViewParent must have
     * previously returned `true` for a call to
     * [.onStartNestedScroll].
     *
     *
     * Both the consumed and unconsumed portions of the scroll distance are reported to the
     * ViewParent. An implementation may choose to use the consumed portion to match or chase scroll
     * position of multiple child elements, for example. The unconsumed portion may be used to
     * allow continuous dragging of multiple scrolling or draggable elements, such as scrolling
     * a list within a vertical drawer where the drawer begins dragging once the edge of inner
     * scrolling content is reached.
     *
     * @param target The descendent view controlling the nested scroll
     * @param dxConsumed Horizontal scroll distance in pixels already consumed by target
     * @param dyConsumed Vertical scroll distance in pixels already consumed by target
     * @param dxUnconsumed Horizontal scroll distance in pixels not consumed by target
     * @param dyUnconsumed Vertical scroll distance in pixels not consumed by target
     * @param type the type of input which cause this scroll event
     */
    override fun onNestedScroll(
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        type: Int
    ) {
        LogUtil.i("called......")
    }

    /**
     * React to a nested scroll in progress before the target view consumes a portion of the scroll.
     *
     *
     * When working with nested scrolling often the parent view may want an opportunity
     * to consume the scroll before the nested scrolling child does. An example of this is a
     * drawer that contains a scrollable list. The user will want to be able to scroll the list
     * fully into view before the list itself begins scrolling.
     *
     *
     * `onNestedPreScroll` is called when a nested scrolling child invokes
     * [View.dispatchNestedPreScroll]. The implementation should
     * report how any pixels of the scroll reported by dx, dy were consumed in the
     * `consumed` array. Index 0 corresponds to dx and index 1 corresponds to dy.
     * This parameter will never be null. Initial values for consumed[0] and consumed[1]
     * will always be 0.
     *
     * @param target View that initiated the nested scroll
     * @param dx Horizontal scroll distance in pixels
     * @param dy Vertical scroll distance in pixels
     * @param consumed Output. The horizontal and vertical scroll distance consumed by this parent
     * @param type the type of input which cause this scroll event
     */
    override fun onNestedPreScroll(target: View, dx: Int, dy: Int, consumed: IntArray, type: Int) {
        LogUtil.i("called......")
    }


    //parent是否需要消耗当前的速度事件
    override fun onNestedPreFling(target: View, velocityX: Float, velocityY: Float): Boolean {
        LogUtil.i("called......")
        return false
    }

    override fun onNestedFling(
        target: View,
        velocityX: Float,
        velocityY: Float,
        consumed: Boolean
    ): Boolean {
        LogUtil.i("called......")
        return true
    }


}