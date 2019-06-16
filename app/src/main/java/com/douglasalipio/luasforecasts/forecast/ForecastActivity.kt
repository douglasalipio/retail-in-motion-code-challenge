package com.douglasalipio.luasforecasts.forecast

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import com.douglasalipio.luasforecasts.R
import com.douglasalipio.luasforecasts.data.FeatureResponse
import com.douglasalipio.luasforecasts.data.ForecastsResult
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class ForecastActivity : DaggerAppCompatActivity() {

    @Inject
    internal lateinit var forecastModel: ForecastViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        forecastModel.forecastsLiveData.observe(this, Observer {
            when (it) {
                is ForecastsResult.Error -> showError()
                is ForecastsResult.Loading -> showLoading()
                is ForecastsResult.ForecastsData -> showFeature(it.data)
            }
        })
        forecastModel.fetchData()
    }

    private fun showFeature(data: List<FeatureResponse>) {
        Log.e("test", data.toString())
    }

    private fun showError() {
        Toast.makeText(this, "Something wrong!", Toast.LENGTH_LONG).show()
    }

    private fun showLoading() {
        refreshView?.setOnRefreshListener { forecastModel.fetchData() }
    }
}
