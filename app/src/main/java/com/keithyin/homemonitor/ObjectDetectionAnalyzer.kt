package com.keithyin.homemonitor

import android.content.Context
import android.content.res.AssetManager
import android.util.Log
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.core.content.contentValuesOf
import org.pytorch.LiteModuleLoader
import org.pytorch.Module

typealias Notifier = (luma: Double) -> Unit

class ObjectDetectionAnalyzer(val mainActivity: MainActivity, val listener: Notifier): ImageAnalysis.Analyzer {
    private var mModule: Module? = null
    companion object {
        private val TAG = "ObjectDetectionAnalyzer"
    }
    private var lastAnalysisTimestamp = 0L
    private val windowSize = 2000L
    private var analyzerCounter = 0L
    override fun analyze(image: ImageProxy) {
        if (mModule == null) {
           Log.e(TAG, mainActivity.assets.list("/").toString())
        }
        analyzerCounter += 1
        Log.e(TAG, "Counter=$analyzerCounter")
        var currentTime = System.currentTimeMillis()
        while ((currentTime - lastAnalysisTimestamp) < windowSize) {
            Log.e(TAG, "sleeping")
            Thread.sleep(windowSize / 2)
            currentTime = System.currentTimeMillis()
        }



        lastAnalysisTimestamp = currentTime
        listener(1.1)
        image.close()
    }
}