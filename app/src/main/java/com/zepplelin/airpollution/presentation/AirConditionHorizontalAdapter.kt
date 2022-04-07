package com.zepplelin.airpollution.presentation

import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zepplelin.airpollution.data.model.Record
import com.zepplelin.airpollution.databinding.ListitemHorizontalInfoBinding


class AirConditionHorizontalAdapter :
    ListAdapter<Record, AirConditionHorizontalAdapter.AirConditionViewHolder>(DiffCallback()) {

    private class DiffCallback : DiffUtil.ItemCallback<Record>() {
        override fun areItemsTheSame(oldItem: Record, newItem: Record) =
            oldItem.SiteId == newItem.SiteId

        override fun areContentsTheSame(oldItem: Record, newItem: Record) =
            oldItem == newItem
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AirConditionViewHolder {
        val itemBinding =
            ListitemHorizontalInfoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

        return AirConditionViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    override fun onBindViewHolder(holder: AirConditionViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class AirConditionViewHolder(private val binding: ListitemHorizontalInfoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            val airConditionInfo = getItem(position)
            binding.idTextView.text = airConditionInfo.SiteId
            binding.siteNameTextView.text = airConditionInfo.SiteName
            binding.pm25TextView.text = airConditionInfo.PM25
            binding.countyNameTextView.text = airConditionInfo.County
            binding.statusTextView.text = airConditionInfo.Status

            val border = GradientDrawable()
            border.setColor(-0x1)
            border.setStroke(1, -0x1000000)
            binding.siteAirCondition.background = border
        }
    }
}
