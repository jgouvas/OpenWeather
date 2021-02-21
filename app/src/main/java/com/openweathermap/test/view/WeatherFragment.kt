package com.openweathermap.test.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.openweathermap.test.*
import com.openweathermap.test.services.OpenWeatherMapApi
import com.openweathermap.test.services.OpenWeatherMapService
import com.openweathermap.test.services.WeatherInteract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.hour_temp_layout.view.*
import kotlinx.android.synthetic.main.next_day_forecast_layout.view.*
import kotlinx.android.synthetic.main.weather_fragment_layout.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*


class WeatherFragment : Fragment(), WeatherView {

    private lateinit var presenter: WeatherPresenter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.weather_fragment_layout, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = WeatherPresenter(
            this,
            AndroidSchedulers.mainThread(),
            Schedulers.io(),
            WeatherInteract(OpenWeatherMapService(initApi())),
            CompositeDisposable()
        )

        presenter.present()
    }

    override fun showLoading() {
        loading_layout.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        loading_layout.visibility = View.GONE
    }

    @SuppressLint("SimpleDateFormat")
    override fun setupTempratureGraph(cityWeatherList: List<Entry>) {
        temp_graph.setTouchEnabled(false)
        temp_graph.setPinchZoom(false)

        val xAxis = temp_graph.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setLabelCount(cityWeatherList.size, true)
        xAxis.valueFormatter = object : ValueFormatter() {
            private val dateFormatter: SimpleDateFormat = SimpleDateFormat("h")
            override fun getFormattedValue(value: Float): String {
                val millis = value.toLong() * 1000L
                return dateFormatter.format(Date(millis)) + "h"
            }
        }

        val lineDataSet = LineDataSet(cityWeatherList, getString(R.string.temperature))
        lineDataSet.circleRadius = 10.0f
        val lineData = LineData(lineDataSet)
        temp_graph.data = lineData
        temp_graph.invalidate()
    }

    override fun setupDayForecast(dayNumber: Int, cityWeatherList: List<CityWeather>) {
        setForecastTimeIntervals()
        when (dayNumber) {
            1 -> setForecastDay1(cityWeatherList)
            2 -> setForecastDay2(cityWeatherList)
            3 -> setForecastDay3(cityWeatherList)
            4 -> setForecastDay4(cityWeatherList)
            5 -> setForecastDay5(cityWeatherList)
        }
    }

    private fun setForecastDay1(cityWeatherList: List<CityWeather>) {
        forecast_day_1.date_view.text = cityWeatherList[0].date
        forecast_day_1.forecast_0000.forecast_temp.text = getString(
            R.string.celsius_unit,
            cityWeatherList[0].temperature
        )
        forecast_day_1.forecast_0300.forecast_temp.text = getString(
            R.string.celsius_unit,
            cityWeatherList[1].temperature
        )
        forecast_day_1.forecast_0600.forecast_temp.text = getString(
            R.string.celsius_unit,
            cityWeatherList[2].temperature
        )
        forecast_day_1.forecast_0900.forecast_temp.text = getString(
            R.string.celsius_unit,
            cityWeatherList[3].temperature
        )
        forecast_day_1.forecast_1200.forecast_temp.text = getString(
            R.string.celsius_unit,
            cityWeatherList[4].temperature
        )
        forecast_day_1.forecast_1500.forecast_temp.text = getString(
            R.string.celsius_unit,
            cityWeatherList[5].temperature
        )
        forecast_day_1.forecast_1800.forecast_temp.text = getString(
            R.string.celsius_unit,
            cityWeatherList[6].temperature
        )
        forecast_day_1.forecast_2100.forecast_temp.text = getString(
            R.string.celsius_unit,
            cityWeatherList[7].temperature
        )
    }

    private fun setForecastDay2(cityWeatherList: List<CityWeather>) {
        forecast_day_2.date_view.text = cityWeatherList[0].date
        forecast_day_2.forecast_0000.forecast_temp.text = getString(
            R.string.celsius_unit,
            cityWeatherList[0].temperature
        )
        forecast_day_2.forecast_0300.forecast_temp.text = getString(
            R.string.celsius_unit,
            cityWeatherList[1].temperature
        )
        forecast_day_2.forecast_0600.forecast_temp.text = getString(
            R.string.celsius_unit,
            cityWeatherList[2].temperature
        )
        forecast_day_2.forecast_0900.forecast_temp.text = getString(
            R.string.celsius_unit,
            cityWeatherList[3].temperature
        )
        forecast_day_2.forecast_1200.forecast_temp.text = getString(
            R.string.celsius_unit,
            cityWeatherList[4].temperature
        )
        forecast_day_2.forecast_1500.forecast_temp.text = getString(
            R.string.celsius_unit,
            cityWeatherList[5].temperature
        )
        forecast_day_2.forecast_1800.forecast_temp.text = getString(
            R.string.celsius_unit,
            cityWeatherList[6].temperature
        )
        forecast_day_2.forecast_2100.forecast_temp.text = getString(
            R.string.celsius_unit,
            cityWeatherList[7].temperature
        )
    }

    private fun setForecastDay3(cityWeatherList: List<CityWeather>) {
        forecast_day_3.date_view.text = cityWeatherList[0].date
        forecast_day_3.forecast_0000.forecast_temp.text = getString(
            R.string.celsius_unit,
            cityWeatherList[0].temperature
        )
        forecast_day_3.forecast_0300.forecast_temp.text = getString(
            R.string.celsius_unit,
            cityWeatherList[1].temperature
        )
        forecast_day_3.forecast_0600.forecast_temp.text = getString(
            R.string.celsius_unit,
            cityWeatherList[2].temperature
        )
        forecast_day_3.forecast_0900.forecast_temp.text = getString(
            R.string.celsius_unit,
            cityWeatherList[3].temperature
        )
        forecast_day_3.forecast_1200.forecast_temp.text = getString(
            R.string.celsius_unit,
            cityWeatherList[4].temperature
        )
        forecast_day_3.forecast_1500.forecast_temp.text = getString(
            R.string.celsius_unit,
            cityWeatherList[5].temperature
        )
        forecast_day_3.forecast_1800.forecast_temp.text = getString(
            R.string.celsius_unit,
            cityWeatherList[6].temperature
        )
        forecast_day_3.forecast_2100.forecast_temp.text = getString(
            R.string.celsius_unit,
            cityWeatherList[7].temperature
        )
    }

    private fun setForecastDay4(cityWeatherList: List<CityWeather>) {
        forecast_day_4.date_view.text = cityWeatherList[0].date
        forecast_day_4.forecast_0000.forecast_temp.text = getString(
            R.string.celsius_unit,
            cityWeatherList[0].temperature
        )
        forecast_day_4.forecast_0300.forecast_temp.text = getString(
            R.string.celsius_unit,
            cityWeatherList[1].temperature
        )
        forecast_day_4.forecast_0600.forecast_temp.text = getString(
            R.string.celsius_unit,
            cityWeatherList[2].temperature
        )
        forecast_day_4.forecast_0900.forecast_temp.text = getString(
            R.string.celsius_unit,
            cityWeatherList[3].temperature
        )
        forecast_day_4.forecast_1200.forecast_temp.text = getString(
            R.string.celsius_unit,
            cityWeatherList[4].temperature
        )
        forecast_day_4.forecast_1500.forecast_temp.text = getString(
            R.string.celsius_unit,
            cityWeatherList[5].temperature
        )
        forecast_day_4.forecast_1800.forecast_temp.text = getString(
            R.string.celsius_unit,
            cityWeatherList[6].temperature
        )
        forecast_day_4.forecast_2100.forecast_temp.text = getString(
            R.string.celsius_unit,
            cityWeatherList[7].temperature
        )
    }

    private fun setForecastDay5(cityWeatherList: List<CityWeather>) {
        cityWeatherList[0].date.let { forecast_day_5.date_view.text = it }
        cityWeatherList[0].temperature.let { forecast_day_5.forecast_0000.forecast_temp.text =
            getString(R.string.celsius_unit, it)
        }
        cityWeatherList[1].temperature.let { forecast_day_5.forecast_0300.forecast_temp.text =
            getString(R.string.celsius_unit, it)
        }
        cityWeatherList[2].temperature.let { forecast_day_5.forecast_0600.forecast_temp.text =
            getString(R.string.celsius_unit, it)
        }
        cityWeatherList[3].temperature.let { forecast_day_5.forecast_0900.forecast_temp.text =
            getString(R.string.celsius_unit, it)
        }
        cityWeatherList[4].temperature.let { forecast_day_5.forecast_1200.forecast_temp.text =
            getString(R.string.celsius_unit, it)
        }
        cityWeatherList[5].temperature.let { forecast_day_5.forecast_1500.forecast_temp.text =
            getString(R.string.celsius_unit, it)
        }
        cityWeatherList[6].temperature.let { forecast_day_5.forecast_1800.forecast_temp.text =
            getString(R.string.celsius_unit, it)
        }
        cityWeatherList[7].temperature.let { forecast_day_5.forecast_2100.forecast_temp.text =
            getString(R.string.celsius_unit, it)
        }
    }

    private fun initApi(): OpenWeatherMapApi {
        val builder: Retrofit.Builder = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        val retrofit = builder.build()
        return retrofit.create(OpenWeatherMapApi::class.java)
    }

    private fun setForecastTimeIntervals() {
        forecast_day_1.forecast_0000.forecast_time.text = INTERVAL_ONE
        forecast_day_2.forecast_0000.forecast_time.text = INTERVAL_ONE
        forecast_day_3.forecast_0000.forecast_time.text = INTERVAL_ONE
        forecast_day_4.forecast_0000.forecast_time.text = INTERVAL_ONE
        forecast_day_5.forecast_0000.forecast_time.text = INTERVAL_ONE

        forecast_day_1.forecast_0300.forecast_time.text = INTERVAL_TWO
        forecast_day_2.forecast_0300.forecast_time.text = INTERVAL_TWO
        forecast_day_3.forecast_0300.forecast_time.text = INTERVAL_TWO
        forecast_day_4.forecast_0300.forecast_time.text = INTERVAL_TWO
        forecast_day_5.forecast_0300.forecast_time.text = INTERVAL_TWO

        forecast_day_1.forecast_0600.forecast_time.text = INTERVAL_THREE
        forecast_day_2.forecast_0600.forecast_time.text = INTERVAL_THREE
        forecast_day_3.forecast_0600.forecast_time.text = INTERVAL_THREE
        forecast_day_4.forecast_0600.forecast_time.text = INTERVAL_THREE
        forecast_day_5.forecast_0600.forecast_time.text = INTERVAL_THREE

        forecast_day_1.forecast_0900.forecast_time.text = INTERVAL_FOUR
        forecast_day_2.forecast_0900.forecast_time.text = INTERVAL_FOUR
        forecast_day_3.forecast_0900.forecast_time.text = INTERVAL_FOUR
        forecast_day_4.forecast_0900.forecast_time.text = INTERVAL_FOUR
        forecast_day_5.forecast_0900.forecast_time.text = INTERVAL_FOUR

        forecast_day_1.forecast_1200.forecast_time.text = INTERVAL_FIVE
        forecast_day_2.forecast_1200.forecast_time.text = INTERVAL_FIVE
        forecast_day_3.forecast_1200.forecast_time.text = INTERVAL_FIVE
        forecast_day_4.forecast_1200.forecast_time.text = INTERVAL_FIVE
        forecast_day_5.forecast_1200.forecast_time.text = INTERVAL_FIVE

        forecast_day_1.forecast_1500.forecast_time.text = INTERVAL_SIX
        forecast_day_2.forecast_1500.forecast_time.text = INTERVAL_SIX
        forecast_day_3.forecast_1500.forecast_time.text = INTERVAL_SIX
        forecast_day_4.forecast_1500.forecast_time.text = INTERVAL_SIX
        forecast_day_5.forecast_1500.forecast_time.text = INTERVAL_SIX

        forecast_day_1.forecast_1800.forecast_time.text = INTERVAL_SEVEN
        forecast_day_2.forecast_1800.forecast_time.text = INTERVAL_SEVEN
        forecast_day_3.forecast_1800.forecast_time.text = INTERVAL_SEVEN
        forecast_day_4.forecast_1800.forecast_time.text = INTERVAL_SEVEN
        forecast_day_5.forecast_1800.forecast_time.text = INTERVAL_SEVEN

        forecast_day_1.forecast_2100.forecast_time.text = INTERVAL_EIGHT
        forecast_day_2.forecast_2100.forecast_time.text = INTERVAL_EIGHT
        forecast_day_3.forecast_2100.forecast_time.text = INTERVAL_EIGHT
        forecast_day_4.forecast_2100.forecast_time.text = INTERVAL_EIGHT
        forecast_day_5.forecast_2100.forecast_time.text = INTERVAL_EIGHT
    }

    companion object {
        const val INTERVAL_ONE = "00h00"
        const val INTERVAL_TWO = "03h00"
        const val INTERVAL_THREE = "06h00"
        const val INTERVAL_FOUR = "09h00"
        const val INTERVAL_FIVE = "12h00"
        const val INTERVAL_SIX = "15h00"
        const val INTERVAL_SEVEN = "18h00"
        const val INTERVAL_EIGHT = "21h00"
    }
}