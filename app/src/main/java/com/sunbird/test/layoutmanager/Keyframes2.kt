package com.sunbird.test.layoutmanager

import android.graphics.Path
import android.graphics.PathMeasure
import kotlin.math.atan2

/**
 * 作者：王豪
 * 日期：2021/5/22
 * 描述：<必填>
 */
class Keyframes2(private val path: Path) {

    companion object {
        val PRECISION = 0.5f
    }


    private var mNumPoints = 0
    var mX = FloatArray(1)
    var mY = FloatArray(1)
    var mAngle = FloatArray(1)

    private var mMaxX = 0f
    private var mMaxY = 0f


    init {
        initPath(path)
    }

    private fun initPath(path: Path) {
        val pathMeasure = PathMeasure(path, false)
        do {
            val pathLength = pathMeasure.length
            val numPoints = (pathLength / PRECISION + 1).toInt()

            val x = FloatArray(numPoints)
            val y = FloatArray(numPoints)
            val angle = FloatArray(numPoints)
            val position = FloatArray(2)
            val tangent = FloatArray(2)

            for (i in 0 until numPoints) {
                val distance = i * (pathLength / (numPoints - 1))
                pathMeasure.getPosTan(distance, position, tangent)

                mMaxX = mMaxX.coerceAtLeast(position[0])
                mMaxY = mMaxY.coerceAtLeast(position[1])

                x[i] = position[0]
                y[i] = position[1]
                angle[i] = fixAngle(
                    (atan2(
                        tangent[1].toDouble(),
                        tangent[0].toDouble()
                    ) * 180F / Math.PI).toFloat()
                )
                mNumPoints += numPoints

                val tmpX = FloatArray(mX.size + x.size)
                System.arraycopy(mX, 0, tmpX, 0, mX.size)
                System.arraycopy(x, 0, tmpX, mX.size, x.size)
                mX = tmpX

                val tmpY = FloatArray(mY.size + y.size)
                System.arraycopy(mY, 0, tmpY, 0, mY.size)
                System.arraycopy(y, 0, tmpY, mY.size, y.size)
                mY = tmpY

//                val tmpAngle=FloatArray
            }


        } while (pathMeasure.nextContour())
    }

    /**
     * 调整角度，使其在0~360F之间
     *
     * @param rotation 当前角度
     * @return 调整后的角度
     */
    private fun fixAngle(rotation: Float): Float {
        val angle = 360f
        return when {
            rotation < 0 -> rotation + angle
            rotation > 360 -> rotation % angle
            else -> rotation
        }
    }
}