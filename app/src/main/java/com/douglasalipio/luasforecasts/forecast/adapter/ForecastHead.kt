package com.douglasalipio.luasforecasts.forecast.adapter

import com.douglasalipio.luasforecasts.R
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.forecast_head.view.*

class ForecastHead(private val title: String, private val subtitle: String) : Item() {

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.headTitle.text = title
        viewHolder.itemView.headSubtitle.text = subtitle
    }

    override fun getLayout() = R.layout.forecast_head
}