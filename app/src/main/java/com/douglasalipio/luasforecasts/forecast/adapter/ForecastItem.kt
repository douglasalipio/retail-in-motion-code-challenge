package com.douglasalipio.luasforecasts.forecast.adapter

import androidx.core.text.isDigitsOnly
import com.douglasalipio.luasforecasts.data.Tram
import com.douglasalipio.luasforecasts.R
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.forecast_item.view.*


class ForecastItem(private val tram: Tram) : Item() {

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.destinationText.text = tram.destination
        viewHolder.itemView.timeText.text = tram.dueMins

        if (!tram.dueMins.isDigitsOnly())
            viewHolder.itemView.timeLabel.text = ""

    }

    override fun getLayout() = R.layout.forecast_item

}