package com.sunbird.test.layoutmanager

import android.graphics.Rect
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutParams
import org.xutils.common.util.LogUtil

/**
 * 作者：王豪
 * 日期：2021/5/18
 * 描述：<必填>
 */
class CustomLayoutManager : RecyclerView.LayoutManager() {

    private var mTotalHeight: Int = 0


    /**
     * mTheMoveDistance初始化为0，而dy每次移动都是一个从0开始便宜的变量，mTheMoveDistance将每次
     * 偏移的距离进行一个记录，这样有助于后续用这个距离来进行边界的判断。
     */
    var mTheMoveDistance = 0


    /**
     * Create a default `LayoutParams` object for a child of the RecyclerView.
     *
     *
     * LayoutManagers will often want to use a custom `LayoutParams` type
     * to store extra information specific to the layout. Client code should subclass
     * [RecyclerView.LayoutParams] for this purpose.
     *
     *
     * *Important:* if you use your own custom `LayoutParams` type
     * you must also override
     * [.checkLayoutParams],
     * [.generateLayoutParams] and
     * [.generateLayoutParams].
     *
     * @return A new LayoutParams for a child view
     */
    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        return LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
    }


    /**
     * Lay out all relevant child views from the given adapter.
     *
     * The LayoutManager is in charge of the behavior of item animations. By default,
     * RecyclerView has a non-null [ItemAnimator][.getItemAnimator], and simple
     * item animations are enabled. This means that add/remove operations on the
     * adapter will result in animations to add new or appearing items, removed or
     * disappearing items, and moved items. If a LayoutManager returns false from
     * [.supportsPredictiveItemAnimations], which is the default, and runs a
     * normal layout operation during [.onLayoutChildren], the
     * RecyclerView will have enough information to run those animations in a simple
     * way. For example, the default ItemAnimator, [DefaultItemAnimator], will
     * simply fade views in and out, whether they are actually added/removed or whether
     * they are moved on or off the screen due to other add/remove operations.
     *
     *
     * A LayoutManager wanting a better item animation experience, where items can be
     * animated onto and off of the screen according to where the items exist when they
     * are not on screen, then the LayoutManager should return true from
     * [.supportsPredictiveItemAnimations] and add additional logic to
     * [.onLayoutChildren]. Supporting predictive animations
     * means that [.onLayoutChildren] will be called twice;
     * once as a "pre" layout step to determine where items would have been prior to
     * a real layout, and again to do the "real" layout. In the pre-layout phase,
     * items will remember their pre-layout positions to allow them to be laid out
     * appropriately. Also, [removed][LayoutParams.isItemRemoved] items will
     * be returned from the scrap to help determine correct placement of other items.
     * These removed items should not be added to the child list, but should be used
     * to help calculate correct positioning of other views, including views that
     * were not previously onscreen (referred to as APPEARING views), but whose
     * pre-layout offscreen position can be determined given the extra
     * information about the pre-layout removed views.
     *
     *
     * The second layout pass is the real layout in which only non-removed views
     * will be used. The only additional requirement during this pass is, if
     * [.supportsPredictiveItemAnimations] returns true, to note which
     * views exist in the child list prior to layout and which are not there after
     * layout (referred to as DISAPPEARING views), and to position/layout those views
     * appropriately, without regard to the actual bounds of the RecyclerView. This allows
     * the animation system to know the location to which to animate these disappearing
     * views.
     *
     *
     * The default LayoutManager implementations for RecyclerView handle all of these
     * requirements for animations already. Clients of RecyclerView can either use one
     * of these layout managers directly or look at their implementations of
     * onLayoutChildren() to see how they account for the APPEARING and
     * DISAPPEARING views.
     *
     * @param recycler         Recycler to use for fetching potentially cached views for a
     * position
     * @param state            Transient state of RecyclerView
     */
    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {
        LogUtil.i("called......")
        super.onLayoutChildren(recycler, state)

        val theRecycler = recycler ?: return

        var offsetY = 0

        for (i in 0 until itemCount) {
            val scrap = theRecycler.getViewForPosition(i)
            addView(scrap)

            measureChildWithMargins(scrap, 0, 0)

            val perItemWidth = getDecoratedMeasuredWidth(scrap)
            val perItemHeight = getDecoratedMeasuredHeight(scrap)

            layoutDecorated(scrap, 0, offsetY, perItemWidth, offsetY + perItemHeight)

            offsetY += perItemHeight
        }

        mTotalHeight = offsetY


    }


    private fun layoutChildren(recycler: RecyclerView.Recycler, visibleCount: Int) {
        detachAndScrapAttachedViews(recycler)
        for (i in 0 until visibleCount) {
            val viewForPositionChild = recycler.getViewForPosition(i)
            measureChildWithMargins(viewForPositionChild, 0, 0)
            addView(viewForPositionChild)
            layoutDecorated(viewForPositionChild)
        }
    }


    /**
     * Query if vertical scrolling is currently supported. The default implementation
     * returns false.
     *
     * @return True if this LayoutManager can scroll the current contents vertically
     */
    override fun canScrollVertically(): Boolean {
        return true
    }

    /**
     * Scroll vertically by dy pixels in screen coordinates and return the distance traveled.
     * The default implementation does nothing and returns 0.
     *
     * @param dy            distance to scroll in pixels. Y increases as scroll position
     * approaches the bottom.
     * @param recycler      Recycler to use for fetching potentially cached views for a
     * position
     * @param state         Transient state of RecyclerView
     * @return The actual distance scrolled. The return value will be negative if dy was
     * negative and scrolling proceeeded in that direction.
     * `Math.abs(result)` may be less than dy if a boundary was reached.
     */
    override fun scrollVerticallyBy(
        dy: Int,
        recycler: RecyclerView.Recycler?,
        state: RecyclerView.State?
    ): Int {

        var theDy = dy
        //RecyclerView的布局高度
        val theRvVisibleHeight = getVerticalVisibleHeight()
        val theMoreHeight = mTotalHeight - theRvVisibleHeight
        LogUtil.d("mRealMoveDistance == ${mTheMoveDistance}")
        if (mTheMoveDistance + dy < 0) {//抵达上边界
            LogUtil.d("抵达上边界")
            theDy = -mTheMoveDistance

        } else if (mTotalHeight > theRvVisibleHeight && mTheMoveDistance + dy > theMoreHeight) {//抵达下边界
            LogUtil.d("抵达下边界")
            theDy = theMoreHeight - mTheMoveDistance
        } else {

        }
        offsetChildrenVertical(-theDy)
        mTheMoveDistance += theDy
        return theDy
    }


    private fun getVerticalVisibleHeight(): Int {
        return height - paddingTop - paddingBottom
    }

    /**
     * 获取可见的区域Rect
     */
    private fun getVisibleArea(): Rect {
        return Rect(
            paddingLeft,
            paddingTop + mTheMoveDistance,
            width + paddingRight,
            getVerticalVisibleHeight() + mTheMoveDistance
        )
    }

    /**
     * 算出条目均高
     */
    private fun initStepHeight(recycler: RecyclerView.Recycler): Int {
        val adam = recycler.getViewForPosition(0)
        addView(adam)
        var result = -1
        measureChildWithMargins(adam, 0, 0)
        result = getDecoratedMeasuredHeight(adam)
        removeAndRecycleAllViews(recycler)
        return result
    }

    /**
     * 初始化可见条目数，addView以及layout
     */
    private fun initVisibleCounts(stepHeight: Int): Int {
        return getVerticalVisibleHeight() / stepHeight
    }


    /**
     * 记录所有条目位置
     * 以及totalHeight
     */
    private fun initItemRectSparse(stepItemHeight: Int) {

    }

    

}