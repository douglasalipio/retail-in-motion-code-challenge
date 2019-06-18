package com.douglasalipio.luasforecasts.forecast

import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.douglasalipio.luasforecasts.data.Direction
import com.douglasalipio.luasforecasts.data.ForecastsResponse
import com.douglasalipio.luasforecasts.data.get
import com.douglasalipio.luasforecasts.R
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

class ForecastActivity : DaggerAppCompatActivity(), ForecastContract.View {

    @Inject
    internal lateinit var forecastPresenter: ForecastContract.Presenter
    private val groupAdapter = GroupAdapter<ViewHolder>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.forecast_view)
        forecastPresenter.takeView(this)
        forecastPresenter.fetchForecasts()
        refreshView?.setOnRefreshListener {
            groupAdapter.clear()
            forecastPresenter.fetchForecasts()
            stateIndicator(false)
        }
    }

    override fun showForecasts(forecast: ForecastsResponse) {
        forecastRecyclerView?.let {
            it.adapter = groupAdapter
            it.layoutManager = LinearLayoutManager(this)
            addSections(getDirection(forecast), forecast.created.formatDate())
        }
        initToolbar(forecast)
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

    override fun showDataError() {
        stateIndicator(false)
        Log.e("test", "feature error.")
    }

    private fun initToolbar(forecastsResponse: ForecastsResponse) {
        forecastToolbar?.let {
            it.title = forecastsResponse.stop
            it.subtitle = forecastsResponse.message
        }
    }

    private fun stateIndicator(isLineStatus: Boolean) {
        refreshView?.isRefreshing = isLineStatus
    }

}
