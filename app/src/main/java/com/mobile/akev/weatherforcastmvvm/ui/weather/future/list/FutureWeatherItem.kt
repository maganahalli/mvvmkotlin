package com.mobile.akev.weatherforcastmvvm.ui.weather.future.list

import com.mobile.akev.weatherforcastmvvm.R
import com.mobile.akev.weatherforcastmvvm.data.db.unitlocalized.future.list.MetricFutureWeather
import com.mobile.akev.weatherforcastmvvm.data.db.unitlocalized.future.list.UnitSpecificFutureWeather
import com.mobile.akev.weatherforcastmvvm.internal.glide.GlideApp
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_future_weather.*
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.FormatStyle

class FutureWeatherItem (
    val weatherEntry: UnitSpecificFutureWeather
): Item(){
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.apply {
            textView_condition.text = weatherEntry.conditionText
            updateDate()
            updateTemperature()
            updateConditionImage()
        }
    }

    override fun getLayout() = R.layout.item_future_weather

    private fun ViewHolder.updateDate() {
        val dtFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)
        textView_date.text = weatherEntry.date.format(dtFormatter)
    }

    private fun ViewHolder.updateTemperature() {
        val unitAbbreviation = if (weatherEntry is MetricFutureWeather) "°C"
        else "°F"
        textView_temperature.text = "${weatherEntry.avgTemperature}$unitAbbreviation"
    }

    private fun ViewHolder.updateConditionImage() {
        GlideApp.with(this.containerView)
            .load("https:" + weatherEntry.conditionIconUrl)
            .into(imageView_condition_icon)
    }
}