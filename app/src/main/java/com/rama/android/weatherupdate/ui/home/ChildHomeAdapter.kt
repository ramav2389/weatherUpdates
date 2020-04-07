package com.rama.android.weatherupdate.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rama.android.weatherupdate.model.ListParams
import kotlinx.android.synthetic.main.item_home_result.view.*

/**
 *Created by ramavijayapandian on 4/7/20.
 **/
class ChildHomeAdapter(var context: Context?, private var parents: List<ListParams>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var homeComponents: MutableList<ListParams> = mutableListOf()


    fun addData(data: List<ListParams>, shouldClear: Boolean) {
        if (shouldClear)
            homeComponents.clear()
        homeComponents.addAll(data as MutableList<ListParams>)
        parents = homeComponents
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val inflater = LayoutInflater.from(parent.context!!)

        var viewHolder: RecyclerView.ViewHolder

        val vh = inflater.inflate(
            com.rama.android.weatherupdate.R.layout.item_home_result,
            parent,
            false
        )
        viewHolder = RecyclerViewSimpleTextViewHolder(vh)

        return viewHolder
    }

    override fun getItemCount(): Int {
        return parents.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val vh = holder as RecyclerViewSimpleTextViewHolder
        vh.tvTemperature.text = String.format(
            "%s - %s",
            parents[position].main.temp_min,
            parents[position].main.temp_max
        )
        vh.tvSpeed.text = String.format("%s", parents[position].weather[0].description)
        vh.tvwind.text = String.format("%s", parents[position].wind.speed)
        vh.tvName.text = parents[position].name
    }


    inner class RecyclerViewSimpleTextViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.tv_name
        var tvTemperature: TextView = itemView.tv_temp
        var tvSpeed: TextView = itemView.tv_speed
        var tvwind: TextView = itemView.tv_wind

    }

}