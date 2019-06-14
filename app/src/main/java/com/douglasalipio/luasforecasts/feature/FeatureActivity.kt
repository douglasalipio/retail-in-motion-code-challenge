package com.douglasalipio.luasforecasts.feature

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import com.douglasalipio.luasforecasts.R
import com.douglasalipio.luasforecasts.data.FeatureResponse
import com.douglasalipio.luasforecasts.data.FeatureResult
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class FeatureActivity : DaggerAppCompatActivity() {

    @Inject
    internal lateinit var featureModel: FeatureViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        featureModel.featureLiveData.observe(this, Observer {
            when (it) {
                is FeatureResult.Error -> showError()
                is FeatureResult.Loading -> showLoading()
                is FeatureResult.FeatureData -> showFeature(it.data)
            }
        })
        featureModel.fetchData()
    }

    private fun showFeature(data: List<FeatureResponse>) {
        Log.e("test", data.toString())
    }

    private fun showError() {
        Toast.makeText(this, "Something wrong!", Toast.LENGTH_LONG).show()
    }

    private fun showLoading() {

    }
}
