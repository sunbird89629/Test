package com.sunbird.test.layoutmanager

import android.graphics.Path
import android.graphics.PointF
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutParams

/**
 * 作者：王豪
 * 日期：2021/5/21
 * 描述：<必填>
 */
class PathLayoutManager(
    path: Path? = null,
    var mItemOffset: Int = 0,
    val orientation: Int = RecyclerView.VERTICAL
) :
    RecyclerView.LayoutManager(), RecyclerView.SmoothScroller.ScrollVectorProvider {

    companion object {
        /**
         * 普通模式
         */
        val SCROLL_MODE_NORMAL = 0

        /**
         * 溢出模式
         */
        val SCROLL_MODE_OVERFLOW = 1

        /**
         * 无线循环模式
         */
        val SCROLL_MODE_LOOP = 2
    }

    lateinit var mKeyFrames: Keyframes

    private var mFirstVisibleItemPos = 0
    private var mItemCountInScreen = 0

    private var isAnimationInitialized = false

    private var isItemDirectionFixed = false//Item是否保持垂直

    var mScrollMode = SCROLL_MODE_NORMAL

    private lateinit var mRecycler: RecyclerView.Recycler
    private lateinit var mState: RecyclerView.State


    init {
        updatePath(path)
    }


    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        return LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
    }


    override fun onMeasure(
        recycler: RecyclerView.Recycler,
        state: RecyclerView.State,
        widthSpec: Int,
        heightSpec: Int
    ) {
        val withMode = View.MeasureSpec.getMode(widthSpec)
        val heightMode = View.MeasureSpec.getMode(heightSpec)
        //如果RecyclerView宽度设置了wrap_content
        //那就吧宽度设置为Path的宽度
        val theWidthSpec = when (withMode) {
            View.MeasureSpec.AT_MOST -> View.MeasureSpec.makeMeasureSpec(
                mKeyFrames.maxX,
                View.MeasureSpec.EXACTLY
            )
            else -> widthSpec
        }
        //如果RecyclerView高度设置了wrap_content
        //那就把高度设置为Path的高度
        val theHeightSpec = when (heightMode) {
            View.MeasureSpec.AT_MOST -> View.MeasureSpec.makeMeasureSpec(
                mKeyFrames.maxY,
                View.MeasureSpec.EXACTLY
            )
            else -> heightSpec
        }
        super.onMeasure(recycler, state, theWidthSpec, theHeightSpec)
    }

    override fun isAutoMeasureEnabled(): Boolean = false

    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {
        state ?: return
        recycler ?: return
        if (state.itemCount == 0) {
            removeAndRecycleAllViews(recycler)
        }

        mRecycler = recycler
        mState = state

        if (!isAnimationInitialized) {
            initItemAnimator()
            isAnimationInitialized = true
        }

        detachAndScrapAttachedViews(recycler)
        relayoutChildren(recycler, state)

//        super.onLayoutChildren(recycler, state)
    }


    /**
     * 检查状态并进行布局和回收旧Item
     */
    private fun relayoutChildren(recycler: RecyclerView.Recycler, state: RecyclerView.State) {
        val needLayoutItems: List<PosTan> = getNeedLayoutItems()

        if (needLayoutItems.isEmpty() || state.itemCount == 0 || mKeyFrames == null) {
            removeAndRecycleAllViews(recycler)
            return
        }

        onLayout(recycler, needLayoutItems)
//        recycleChildren(recycler)
    }

    private fun getNeedLayoutItems(): List<PosTan> {
        checkKeyframe()
        val result = mutableListOf<PosTan>()

        //item个数
        val itemCount = itemCount

        //满足无线滚动
        if (isSatisfiedLoopScroll()) {
            initNeedLayoutLoopScrollItems(result, itemCount)
        } else {
            initNeedLayoutItems(result, itemCount)
        }

        return result
    }

    /**
     * 初始化需要布局的Item数据（无限滚动模式）
     *
     * @param result 结果
     * @param itemCount Item总数
     */
    private fun initNeedLayoutLoopScrollItems(result: MutableList<PosTan>, itemCount: Int) {
        val overflowCount = getOverflowCount()
        //得出第一个可见的item
        mFirstVisibleItemPos = overflowCount - mItemCountInScreen - 1
        val currentDistance = 0f
        //百分比
        val fraction = 0f

        var posTan: PosTan? = null

        var pos: Int = 0

        for (i in mFirstVisibleItemPos until overflowCount) {
            pos = i % itemCount
        }
    }

    private fun isSatisfiedLoopScroll(): Boolean {
        checkKeyframe()
        val pathLength = mKeyFrames.pathLength
        val itemLength = getItemLength()
        return isLoopScrollMode() && itemLength - pathLength > mItemOffset
    }

    private fun isLoopScrollMode(): Boolean {
        return mScrollMode == SCROLL_MODE_LOOP
    }

    private fun checkKeyframe() {
        if (mKeyFrames == null) {
            throw NullPointerException("Path not set!")
        }
    }

    /**
     * 确定Item位置,角度以及尺寸
     *
     * @param needLayoutItems 需要布局的Item
     */
    private fun onLayout(recycler: RecyclerView.Recycler, needLayoutItems: List<PosTan>) {
        var x = 0
        var y = 0

        lateinit var item: View

        for (tmp in needLayoutItems) {
            item = recycler.getViewForPosition(tmp.index)
            addView(item)
            measureChild(item, 0, 0)

            //Path线条在View中间

            x = (tmp.x - getDecoratedMeasuredWidth(item) / 2).toInt()
            y = (tmp.y - getDecoratedMeasuredHeight(item) / 2).toInt()

            layoutDecorated(
                item,
                x,
                y,
                x + getDecoratedMeasuredWidth(item),
                y + getDecoratedMeasuredHeight(item)
            )

//            item.rotation = if (isItemDirectionFixed) {
//
//            }

        }
    }


    /**
     * 通过反射替换默认的Item动画（解决在某些机型上的crash问题）
     */
    private fun initItemAnimator() {
        //not implemented for now

//        try {
//            val field = RecyclerView.LayoutManager::class.java.getDeclaredField("mRecyclerView")
//            field.isAccessible = true
//
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
    }

    override fun canScrollVertically(): Boolean {
        return orientation == RecyclerView.VERTICAL
    }

    override fun canScrollHorizontally(): Boolean {
        return orientation == RecyclerView.HORIZONTAL
    }


    override fun scrollVerticallyBy(
        dy: Int,
        recycler: RecyclerView.Recycler?,
        state: RecyclerView.State?
    ): Int {
        return super.scrollVerticallyBy(dy, recycler, state)
    }

    fun updatePath(path: Path?) {

    }


    /**
     * 初始化需要布局的Item数据
     *
     * @param   result 结果
     * @param   itemCount Item总数
     */
    private fun initNeedLayoutItems(result: MutableList<PosTan>, itemCount: Int) {
        var currentDistance = 0f
        //必须从第一个Item开始，因为要拿到最小的，也就是最先的
        for (i in 0 until itemCount) {
            currentDistance = i * mItemOffset - getScrollOffset()
            //判断当前距离>=0的表示可见
            if (currentDistance >= 0) {
                //得到第一个可见的position
                mFirstVisibleItemPos = i
                break
            }
        }

        //结束的position
        var endIndex = mFirstVisibleItemPos + mItemCountInScreen

        if (endIndex > getItemCount()) {
            endIndex = getItemCount()
        }


        var fraction = 0f
        var posTan: PosTan
        for (i in mFirstVisibleItemPos until endIndex) {
            //得到当前距离
            currentDistance = i * mItemOffset - getScrollOffset()
            //得到百分比
            fraction = currentDistance / mKeyFrames.getPathLength()
            //根据百分比从Keyframes中获取对应的坐标和角度
            posTan = mKeyFrames.getValue(fraction)

            if (posTan == null) {
                continue
            }

            result.add(PosTan(posTan, i, fraction))
        }
    }

    fun getScrollOffset(): Float {
//        return when (orientation) {
//            RecyclerView.VERTICAL -> mOffsetY
//            else -> mOffsetX
//        }
        return 0f
    }


    /**
     * 获取溢出Item的个数
     */
    private fun getOverflowCount(): Int {
        //Item的总长度
        val itemLength = getItemLength()

        //path的长度
        val pathLength = mKeyFrames.pathLength

        //第一个item较Path终点的偏移量，这个偏移量是以Path的终点为起点的，例如 现在一共有10个item:
        //                            0___1___2___3___4___5 现在的偏移量是>0的，直到：
        //                            5___6___7___8___9___0 时为0，这个时候继续向右滚动的话，就编程负数了
        val firstItemScrollOffset = (getScrollOffset() + pathLength).toInt()

        val lastItemScrollOffset = firstItemScrollOffset - itemLength

        //item的总长度+path的总长度
        val lengthOffset = itemLength + pathLength

        //当最后一个item划出屏幕时（根据上面的例子来讲，是向左边滑）：
        //                      9_|_0___1___2___3___4
        //开始计算的偏移量(正数)，因为如果超出了屏幕而不做处理的话，
        //下面计算空缺距离的时候，最大值只能是itemLength

        val lastItemOverflowOffset = if (firstItemScrollOffset > lengthOffset) {
            firstItemScrollOffset - lengthOffset
        } else {
            0
        }

        //空缺的距离
        val vacantDistance = lastItemScrollOffset % itemLength + lastItemOverflowOffset

        //空缺的距离/item之间的距离=需补上的item个数
        return vacantDistance / mItemOffset
    }


    /**
     * @return Item总长度
     */
    private fun getItemLength(): Int {
        //这里+1是为了让最后一个item显示出来（让最后一个item的距离相对于Path长度的百分比<1,
        //即使其满足mKeyframes.getValue()方法里面获取有效坐标点的条件）
        return itemCount * mItemOffset - mItemOffset + 1
    }

    /**
     * Should calculate the vector that points to the direction where the target position
     * can be found.
     *
     *
     * This method is used by the [LinearSmoothScroller] to initiate a scroll towards
     * the target position.
     *
     *
     * The magnitude of the vector is not important. It is always normalized before being
     * used by the [LinearSmoothScroller].
     *
     *
     * LayoutManager should not check whether the position exists in the adapter or not.
     *
     * @param targetPosition the target position to which the returned vector should point
     *
     * @return the scroll vector for a given position.
     */
    override fun computeScrollVectorForPosition(targetPosition: Int): PointF? {
        TODO("Not yet implemented")
    }
}