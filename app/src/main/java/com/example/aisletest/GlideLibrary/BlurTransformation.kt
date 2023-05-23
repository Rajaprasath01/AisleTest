package com.example.aisletest.GlideLibrary

import android.content.Context
import android.graphics.*
import android.os.Build
import android.renderscript.Allocation
import android.renderscript.Element
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur
import androidx.annotation.RequiresApi
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import java.nio.charset.Charset
import java.security.MessageDigest


class BlurTransformation(private val context: Context) : BitmapTransformation() {

    private val radius = 25f
    private val sampling = 1

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun transform(pool: BitmapPool, toTransform: Bitmap, outWidth: Int, outHeight: Int): Bitmap {
        val width = toTransform.width / sampling
        val height = toTransform.height / sampling

        val inputBitmap = pool.get(width, height, Bitmap.Config.ARGB_8888)
        val outputBitmap = pool.get(width, height, Bitmap.Config.ARGB_8888)

        val canvas = Canvas(inputBitmap)
        val paint = Paint().apply {
            flags = Paint.FILTER_BITMAP_FLAG or Paint.DITHER_FLAG
        }

        val srcRect = Rect(0, 0, toTransform.width, toTransform.height)
        val destRect = Rect(0, 0, width, height)
        canvas.drawBitmap(toTransform, srcRect, destRect, paint)

        val renderScript = RenderScript.create(context)
        val blurScript = ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript))
        val inputAllocation = Allocation.createFromBitmap(renderScript, inputBitmap)
        val outputAllocation = Allocation.createFromBitmap(renderScript, outputBitmap)

        blurScript.setRadius(radius)
        blurScript.setInput(inputAllocation)
        blurScript.forEach(outputAllocation)

        outputAllocation.copyTo(outputBitmap)

        inputAllocation.destroy()
        outputAllocation.destroy()

        renderScript.destroy()

        return Bitmap.createScaledBitmap(outputBitmap, outWidth, outHeight, true)
    }

    override fun equals(other: Any?): Boolean {
        return other is BlurTransformation
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }

    override fun updateDiskCacheKey(messageDigest: MessageDigest) {
        messageDigest.update("blurTransformation".toByteArray())
    }
}
