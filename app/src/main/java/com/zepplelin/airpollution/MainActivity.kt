package com.zepplelin.airpollution

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.zepplelin.airpollution.data.model.AirConditionResult
import com.zepplelin.airpollution.databinding.ActivityMainBinding
import com.zepplelin.airpollution.presentation.AirConditionHorizontalAdapter
import com.zepplelin.airpollution.presentation.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val searchViewModel: SearchViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var airConditionHorizontalAdapter: AirConditionHorizontalAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUi()
        observeAirConditionResult()
    }

    private fun initUi() {
        binding.horizontalRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        airConditionHorizontalAdapter = AirConditionHorizontalAdapter()
        binding.horizontalRecyclerView.adapter = airConditionHorizontalAdapter
    }

    private fun renderView(result: AirConditionResult) {

    }

    private fun observeAirConditionResult() {
        searchViewModel.airConditionResult.observe(this) { result ->
            airConditionHorizontalAdapter.submitList(result.records)
            binding.searchHintText.visibility = if (result.records.isEmpty()) VISIBLE else GONE
        }
        searchViewModel.fetch("")
    }
}