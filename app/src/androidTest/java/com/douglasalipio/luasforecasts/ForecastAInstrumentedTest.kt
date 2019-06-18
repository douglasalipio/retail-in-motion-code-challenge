package com.douglasalipio.luasforecasts

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import com.douglasalipio.luasforecasts.forecast.ForecastActivity
import getToolbarNavigationContentDescription

import org.junit.Test

import org.junit.Rule
import rotateOrientation
import rotateToLandscape

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ForecastAInstrumentedTest {

    @Rule
    @JvmField
    var featureActivityTestRule =
        ActivityTestRule(ForecastActivity::class.java, true, false)


    @Test
    fun orientationChange_DisplayedInUi() {
        loadFeature()
        featureActivityTestRule.activity.rotateOrientation()
    }

    @Test
    fun orientationLandscape_DisplayedInUi() {
        loadFeature()
        featureActivityTestRule.activity.rotateToLandscape()
    }

    @Test
    fun toolbar_DisplayedInUi() {
        loadFeature()
        featureActivityTestRule.activity.getToolbarNavigationContentDescription(R.id.forecastToolbar)
    }

    @Test
    fun scrollToItemBelowFold_clickOnItem() {
        loadFeature()
        Espresso.onView(ViewMatchers.withId(R.id.forecastRecyclerView))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))
    }

    private fun loadFeature() = startActivityWithWithStubbedTask()

    private fun startActivityWithWithStubbedTask() {
        val startIntent = Intent()
        featureActivityTestRule.launchActivity(startIntent)
    }
}
