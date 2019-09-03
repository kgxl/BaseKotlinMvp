package tc.ssf.com.external

import android.app.Activity
import android.app.Application
import android.content.ComponentCallbacks
import android.content.Context
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import java.util.concurrent.Executors


/**
 *  Created by zjy on 2018/10/7.
 *  终极无敌屏幕适配方案，根据今日头条方案
 * @see https://mp.weixin.qq.com/s/d9QCoBP6kV9VSWvVldVVwA
 */
class AutoSizeUtils {
    private var mBaseWidth: Int = -1
    private var mBaseHeight: Int = -1
    private var isVertical = true
    private var isWidth = true
    private var mScale = 0f
    private var mFirstDensity = 0f

    companion object {
        lateinit var mContext: Context
        private val instance by lazy { AutoSizeUtils() }
        fun getInstance(context: Context): AutoSizeUtils {
            mContext = context
            return instance
        }
    }

    fun init() {
        getManifestMata()
        mScale = getDisplayMetrics(mContext).scaledDensity
        mFirstDensity = getDisplayMetrics(mContext).density
        (mContext as Application).registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
            override fun onActivityPaused(activity: Activity?) {
            }

            override fun onActivityResumed(activity: Activity?) {
            }

            override fun onActivityStarted(activity: Activity?) {
            }

            override fun onActivityDestroyed(activity: Activity?) {
            }

            override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
            }

            override fun onActivityStopped(activity: Activity?) {
            }

            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                Log.e("zjy", "registerActivityLifecycleCallbacks onActivityCreated")
                AutoSizeUtils.getInstance(mContext).startAutoSize(activity)
            }
        })
        //调整手机字体大小
        (mContext as Application).registerComponentCallbacks(object : ComponentCallbacks {
            override fun onLowMemory() {

            }

            override fun onConfigurationChanged(newConfig: Configuration?) {
                newConfig?.let {
                    if (newConfig.fontScale > 0) {
                        mScale = getDisplayMetrics(mContext).scaledDensity
                    }
                }
            }
        })
    }

    fun startAutoSize(context: Context, isWidth: Boolean = true) {
        val metrics = getDisplayMetrics(context)
        var isVertical = mContext.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT
        this.isVertical = isVertical
        this.isWidth = isWidth
        val density = getDensity(isVertical, isWidth, metrics)
        metrics.scaledDensity = density * (mScale / mFirstDensity)
        setDensity(metrics, density, (density * 160).toInt())
    }

    private fun getDensity(isVertical: Boolean, isWidth: Boolean, metrics: DisplayMetrics): Float {
        return if (isVertical) {
            if (isWidth) {
                metrics.widthPixels / (mBaseWidth * 1.0f)
            } else {
                metrics.heightPixels / (mBaseHeight * 1.0f)
            }
        } else {
            if (isWidth) {
                metrics.heightPixels / (mBaseWidth * 1.0f)
            } else {
                metrics.widthPixels / (mBaseHeight * 1.0f)
            }
        }
    }

    private fun getDisplayMetrics(context: Context): DisplayMetrics {
        //兼容 MIUI
        val metricsOnMiui = getMetricsOnMiui(context.resources)
        val metrics = (metricsOnMiui ?: context.resources.displayMetrics) as DisplayMetrics
        return metrics
    }

    private fun setDensity(displayMetrics: DisplayMetrics, density: Float, densityDpi: Int) {
        displayMetrics.density = density
        displayMetrics.densityDpi = densityDpi
    }

    private fun getManifestMata() {
        Executors.newSingleThreadExecutor().execute {
            val packageManager = mContext.packageManager
            val applicationInfo = packageManager.getApplicationInfo(mContext.packageName, PackageManager.GET_META_DATA)
            val metaData = applicationInfo?.metaData
                    ?: throw Resources.NotFoundException("not found base_height and base_width!")
            metaData.let {
                if (metaData.containsKey("base_width") && metaData.containsKey("base_height")) {
                    mBaseWidth = metaData["base_width"] as Int
                    mBaseHeight = metaData["base_height"] as Int
                } else {
                    throw Resources.NotFoundException("not found base_height and base_width!")
                }
                if (mBaseWidth <= 0 && mBaseHeight <= 0)
                    throw Resources.NotFoundException("base_height and base_width not less than zero")
            }
        }
    }

    /**
     * 解决 MIUI 更改框架导致的 MIUI7 + Android5.1.1 上出现的失效问题 (以及极少数基于这部分 MIUI 去掉 ART 然后置入 XPosed 的手机)
     * 来源于: https://github.com/Firedamp/Rudeness/blob/master/rudeness-sdk/src/main/java/com/bulong/rudeness/RudenessScreenHelper.java#L61:5
     *
     * @param resources [Resources]
     * @return [DisplayMetrics], 可能为 `null`
     */
    private fun getMetricsOnMiui(resources: Resources): DisplayMetrics? {
        if ("MiuiResources" == resources.javaClass.simpleName || "XResources" == resources.javaClass.simpleName) {
            try {
                val field = Resources::class.java.getDeclaredField("mTmpMetrics")
                field.isAccessible = true
                return field.get(resources) as DisplayMetrics
            } catch (e: Exception) {
                return null
            }
        }
        return null
    }
}