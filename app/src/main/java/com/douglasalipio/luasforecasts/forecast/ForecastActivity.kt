package com.douglasalipio.luasforecasts.forecast

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.douglasalipio.luasforecasts.R
import com.douglasalipio.luasforecasts.data.Direction
import com.douglasalipio.luasforecasts.data.ForecastsResponse
import com.douglasalipio.luasforecasts.data.ForecastsResult
import com.douglasalipio.luasforecasts.data.get
import com.douglasalipio.luasforecasts.forecast.adapter.ForecastHead
import com.douglasalipio.luasforecasts.forecast.adapter.ForecastItem
import com.douglasalipio.luasforecasts.util.formatDate
import com.douglasalipio.luasforecasts.util.isInboundTime
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
        forecastModel.forecastResultLiveData.observe(this, Observer {
            when (it) {
                is ForecastsResult.Error -> showError()
                is ForecastsResult.Loading -> stateIndicator(it.status)
                is ForecastsResult.ForecastsData -> showForecasts(it.forecastResponse)
            }
        })
        forecastModel.fetchData(getStop())
        refreshView?.setOnRefreshListener {
            groupAdapter.clear()
            forecastModel.fetchData(getStop())
        }
    }

    private fun getStop(): String {
        return if (isInboundTime()) this.getString(R.string.inbounds_destination)
        else this.getString(R.string.outbounds_destination)
    }

    private fun showForecasts(forecastsResponse: ForecastsResponse) {
        forecastRecyclerView?.let {
            it.adapter = groupAdapter
            it.layoutManager = LinearLayoutManager(this)
            addSections(getDirection(forecastsResponse), forecastsResponse.created.formatDate())
        }
        initToolbar(forecastsResponse)
    }

    private fun getDirection(forecastsResponse: ForecastsResponse): Direction {
        if (isInboundTime())
            return forecastsResponse.directions.get(this.getText(R.string.text_inbound).toString())

        return forecastsResponse.directions.get(this.getText(R.string.text_outbound).toString())
    }

    private fun addSections(direction: Direction, dateLabel: String) {
        val section = Section()
        section.setHeader(ForecastHead(direction.name, dateLabel))
        direction.trams.forEach { tram -> section.add(ForecastItem(tram)) }
        groupAdapter.add(section)

    }

    private fun showError() {
        Toast.makeText(this, getString(R.string.forecasts_error_message), Toast.LENGTH_LONG).show()
    }

    private fun stateIndicator(isLineStatus: Boolean) {
        refreshView?.isRefreshing = isLineStatus
    }

    private fun initToolbar(forecastsResponse: ForecastsResponse) {
        forecastToolbar?.let {
            it.title = forecastsResponse.stop
            it.subtitle = forecastsResponse.message
        }
    }
}
