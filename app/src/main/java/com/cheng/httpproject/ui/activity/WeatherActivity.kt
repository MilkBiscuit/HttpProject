package com.cheng.httpproject.ui.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import com.cheng.httpproject.R
import com.cheng.httpproject.service.WeatherService
import com.cheng.httpproject.ui.activity.base.BaseActivity
import com.cheng.httpproject.ui.fragment.CurrentWeatherFragment
import com.cheng.httpproject.util.applySchedulers
import com.cheng.httpproject.util.debounceOneSecond
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import kotlinx.android.synthetic.main.activity_weather.*

class WeatherActivity : BaseActivity(), OnQueryTextListener {

    val weatherService = WeatherService.getInstance()
    var userInputSubject = BehaviorSubject.create<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_weather)

        loadingView = findViewById(R.id.layout_loading)
        sv_city_name.setOnQueryTextListener(this)

        startObserve()
    }

    override fun onQueryTextSubmit(s: String?): Boolean {
        userInputSubject.onNext(s ?: "")

        return true
    }

    override fun onQueryTextChange(s: String?): Boolean {
        userInputSubject.onNext(s ?: "")

        return true
    }

    fun startObserve() {
        val subject = userInputSubject.filter { it.length >= 2 }.debounceOneSecond()
        var disposable = subject.applySchedulers().doOnNext{showLoading()}.subscribe()
        addDisposable(disposable)

        disposable = subject.flatMap {
                    weatherService.fetchCurrentWeather(it).applySchedulers().doOnError { error ->
                        hideLoading()
                        showToast(error.localizedMessage ?: "LOL")
                    }.onErrorResumeNext(Observable.empty())
                }
                .applySchedulers()
                .subscribe({result ->
                    hideLoading()
                    val fragment = CurrentWeatherFragment.newInstance(result)
                    replaceFragment(fragment)
                }, {}, {
                    showToast("It completed! I should never appear!")
                })
        addDisposable(disposable)
    }

    fun replaceFragment(fragment: Fragment) {
        replaceFragment(R.id.layout_current_weather, fragment)
    }

}
