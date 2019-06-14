package com.douglasalipio.luasforecasts.util

import android.app.Activity
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.widget.Toolbar
import androidx.annotation.IdRes
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import androidx.test.runner.lifecycle.Stage


fun Activity.rotateToLandscape() {
    requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
}

fun Activity.rotateToPortrait() {
    requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
}

fun Activity.rotateOrientation() {
    when (resources.configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> rotateToPortrait()
        Configuration.ORIENTATION_PORTRAIT -> rotateToLandscape()
        else -> rotateToLandscape()
    }
}

fun Activity.getToolbarNavigationContentDescription(@IdRes toolbarId: Int): String {
    val toolbar = findViewById<Toolbar>(toolbarId)
    return if (toolbar != null) {
        toolbar.navigationContentDescription as String
    } else {
        throw RuntimeException("No toolbar found.")
    }
}

fun Activity.getCurrentActivity(): Activity? {
    // The array is just to wrap the Activity and be able to access it from the Runnable.
    val resumedActivity = arrayOfNulls<Activity>(1)
    getInstrumentation().runOnMainSync {
        val resumedActivities = ActivityLifecycleMonitorRegistry.getInstance()
            .getActivitiesInStage(Stage.RESUMED)
        if (resumedActivities.iterator().hasNext()) {
            resumedActivity[0] = resumedActivities.iterator().next() as Activity
        } else {
            throw IllegalStateException("No Activity in stage RESUMED")
        }
    }
    return resumedActivity[0]
}