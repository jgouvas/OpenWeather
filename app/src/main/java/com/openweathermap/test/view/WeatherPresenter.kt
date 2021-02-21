package com.openweathermap.test.view

import com.github.mikephil.charting.data.Entry
import com.openweathermap.test.CityWeather
import com.openweathermap.test.services.WeatherInteract
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable

class WeatherPresenter(
    private val view: WeatherView,
    private val viewScheduler: Scheduler,
    private val networkScheduler: Scheduler,
    private val weatherInteract: WeatherInteract,
    private val disposables: CompositeDisposable
) {

    fun present() {
        handleGetPortoWeather()
    }

    private fun handleGetPortoWeather() {
        disposables.add(weatherInteract.getPortoWeather(METRIC_UNITS)
            .subscribeOn(networkScheduler)
            .observeOn(viewScheduler)
            .doOnSubscribe { view.showLoading() }
            .doOnSuccess {
                view.hideLoading()
                setupView(it)
            }
            .subscribe()
        )
    }

    private fun setupView(cityWeatherList: List<CityWeather>) {
        val forecastDay0List = ArrayList<Entry>()
        val forecastDay1List = ArrayList<CityWeather>()
        val forecastDay2List = ArrayList<CityWeather>()
        val forecastDay3List = ArrayList<CityWeather>()
        val forecastDay4List = ArrayList<CityWeather>()
        val forecastDay5List = ArrayList<CityWeather>()
        val listIterator = cityWeatherList.iterator()

        for (weather in listIterator) {
            when (weather.dayNumber) {
                0 -> forecastDay0List.add(Entry(weather.dt.toFloat(),
                    weather.temperature.toFloat()))
                1 -> forecastDay1List.add(weather)
                2 -> forecastDay2List.add(weather)
                3 -> forecastDay3List.add(weather)
                4 -> forecastDay4List.add(weather)
                5 -> forecastDay5List.add(weather)

            }
        }

        if (forecastDay0List.isEmpty() || forecastDay0List.size >= 3) {
            forecastDay0List.add(Entry(cityWeatherList[0].dt.toFloat(),
                cityWeatherList[0].temperature.toFloat()))
            forecastDay0List.add(Entry(cityWeatherList[1].dt.toFloat(),
                cityWeatherList[1].temperature.toFloat()))
            forecastDay0List.add(Entry(cityWeatherList[2].dt.toFloat(),
                cityWeatherList[2].temperature.toFloat()))
        }

        view.setupTempratureGraph(forecastDay0List)
        view.setupDayForecast(1, forecastDay1List)
        view.setupDayForecast(2, forecastDay2List)
        view.setupDayForecast(3, forecastDay3List)
        view.setupDayForecast(4, forecastDay4List)
        view.setupDayForecast(5, forecastDay5List)
    }

    companion object {
        const val METRIC_UNITS = "metric"
    }
}