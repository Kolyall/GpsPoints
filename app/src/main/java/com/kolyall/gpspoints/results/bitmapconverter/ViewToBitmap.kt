package com.kolyall.gpspoints.results.bitmapconverter

import android.graphics.Bitmap
import android.os.Environment
import android.util.Log
import android.view.View
import androidx.core.content.PermissionChecker.PermissionResult
import com.kolyall.gpspoints.results.views.chart.ChartView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.text.SimpleDateFormat
import java.util.Date
import kotlin.math.abs

object ViewToBitmap {

    suspend fun saveViewAsImageToFilePath(yourFolderName: String, view: View): String {
        val mediaStorageDir = mediaStorageDir(yourFolderName)

        // Create a media file name
        val selectedOutputPath = selectedOutputPath(mediaStorageDir)
        val bitmap = view.toBitmap()
        saveBitmap(selectedOutputPath, bitmap)
        return selectedOutputPath
    }

    private suspend fun saveBitmap(selectedOutputPath: String, bitmap: Bitmap) = withContext(Dispatchers.IO) {
        var fOut: OutputStream? = null
        try {
            val file = File(selectedOutputPath)
            fOut = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut)
            fOut.flush()
        } catch (e: Exception) {
            e.printStackTrace()
            Log.w(ChartView.TAG, "getSaveImageFilePath: ", e)
        } finally {
            fOut?.close()
        }
    }

    private suspend fun View.toBitmap(): Bitmap = withContext(Dispatchers.Main){
        isDrawingCacheEnabled = true
        buildDrawingCache()
        var bitmap: Bitmap = Bitmap.createBitmap(drawingCache)
        val maxSize = 1080
        val bWidth = bitmap.width
        val bHeight = bitmap.height
        bitmap = if (bWidth > bHeight) {
            val imageHeight =
                abs(maxSize * (bitmap.width.toFloat() / bitmap.height.toFloat())).toInt()
            Bitmap.createScaledBitmap(bitmap, maxSize, imageHeight, true)
        } else {
            val imageWidth =
                abs(maxSize * (bitmap.width.toFloat() / bitmap.height.toFloat())).toInt()
            Bitmap.createScaledBitmap(bitmap, imageWidth, maxSize, true)
        }
        isDrawingCacheEnabled = false
        destroyDrawingCache()
        bitmap
    }

    private fun selectedOutputPath(mediaStorageDir: File): String {
        val imageName = imageName()
        val selectedOutputPath = mediaStorageDir.path + File.separator + imageName
        Log.d(TAG, "selected camera path $selectedOutputPath")
        return selectedOutputPath
    }

    private fun imageName(): String {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        return "IMG_$timeStamp.jpg"
    }

    private fun mediaStorageDir(yourFolderName: String): File {
        val mediaStorageDir = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
            yourFolderName
        )
        // Create a storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(yourFolderName, "Failed to create directory")
            }
        }
        return mediaStorageDir
    }

    private const val TAG: String = "ViewToBitmap"

}