package com.douglasalipio.luasforecasts.forecast

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.douglasalipio.luasforecasts.R
import com.douglasalipio.luasforecasts.data.ForecastsResponse
import com.douglasalipio.luasforecasts.data.ForecastsResult
import com.douglasalipio.luasforecasts.data.get
import com.douglasalipio.luasforecasts.util.formatDate
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.ViewHolder
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.app_bar.*
import kotlinx.android.synthetic.main.forecast_content.*
import javax.inject.Inject

class ForecastActivity : DaggerAppCompatActivity() {

    @Inject
    internal lateinit var forecastModel: ForecastViewModel
    private val groupAdapter = GroupAdapter<ViewHolder>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.forecast_view)
        forecastModel.forecastsLiveData.observe(this, Observer {
            when (it) {
                is ForecastsResult.Error -> showError()
                is ForecastsResult.Loading -> showLoading()
                is ForecastsResult.ForecastsData -> showForecasts(it.forecastResponse)
            }
        })
        forecastModel.fetchData("ran")
    }

    private fun showForecasts(forecastsResponse: ForecastsResponse) {
        forecastRecyclerView?.let {
            it.adapter = groupAdapter
            it.layoutManager = LinearLayoutManager(this)
            val outbound = forecastsResponse.directions.get(this.getText(R.string.text_outbound).toString())
            val section = Section()
            section.setHeader(ForecastHead(outbound.name, forecastsResponse.created.formatDate()))
            outbound.trams.forEach { tram -> section.add(ForecastItem(tram)) }
            groupAdapter.add(section)
        }

        initToolbar(forecastsResponse)
    }

    private fun initToolbar(forecastsResponse: ForecastsResponse) {
        forecastToolbar?.let {
            it.title = forecastsResponse.stop
            it.subtitle = forecastsResponse.message
        }
    }

    private fun showError() {
        Toast.makeText(this, "Something wrong!", Toast.LENGTH_LONG).show()
    }

    private fun showLoading() {
        refreshView?.setOnRefreshListener { forecastModel.fetchData("ran") }
    }
}
