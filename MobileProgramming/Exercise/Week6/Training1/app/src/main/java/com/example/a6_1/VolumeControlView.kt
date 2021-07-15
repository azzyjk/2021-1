package com.example.a6_1

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatImageView
import kotlin.math.PI
import kotlin.math.atan2

class VolumeControlView(context: Context, attrs: AttributeSet?) :
    AppCompatImageView(context, attrs) {

    var angle = 180.0f
    var mx = 0.0f
    var my = 0.0f
    var tx = 0.0f
    var ty = 0.0f

    interface VolumeListener{
        public fun onChanged(angle:Float)
    }

    var listener:VolumeListener ?= null

    public fun setVolumeListener(list:VolumeListener){
        listener = list
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if(event!=null){
            tx = event.getX(0)
            ty = event.getY(0)
            angle = getAngle(tx, ty)
            invalidate()
            listener?.onChanged(angle)
            return true
        }
        return false
    }

    fun getAngle(x1:Float, y1:Float):Float{
        mx = x1 - (width / 2.0f)
        my = (height/2.0f) - y1
        return (atan2(mx, my) * 180.0f / PI).toFloat()
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.rotate(angle, width/2.0f, height/2.0f)
        super.onDraw(canvas)
    }
}