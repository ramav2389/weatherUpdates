package com.rama.android.weatherupdate.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rama.android.weatherupdate.R
import com.rama.android.weatherupdate.model.CityData
import com.rama.android.weatherupdate.ui.interfaces.IViewTapCallback
import com.rama.android.weatherupdate.utils.common.Toaster
import kotlinx.android.synthetic.main.item_simple.view.*


class ParentAdapter(var context: Context?, private var parents: List<CityData>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var homeComponents: MutableList<CityData> = mutableListOf()
    private var counter: Int = 0
    private var selectedCityList: MutableList<Int> = ArrayList<Int>()
    private var mViewTapListener: IViewTapCallback? = null
    private val sb = StringBuilder()

    fun addData(data: List<CityData>, shouldClear: Boolean) {
        if (shouldClear)
            homeComponents.clear()
        homeComponents.addAll(data as MutableList<CityData>)
        parents = homeComponents
        notifyDataSetChanged()
    }

    fun getSelectedList(): List<Any?>? {
        return selectedCityList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val inflater = LayoutInflater.from(parent.context!!)

        var viewHolder: RecyclerView.ViewHolder

        val vh = inflater.inflate(
            com.rama.android.weatherupdate.R.layout.item_simple,
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

    fun setViewTapListener(listener: IViewTapCallback) {
        mViewTapListener = listener
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val vh = holder as RecyclerViewSimpleTextViewHolder
        var selected: Boolean = false
        vh.tvSimple.text = parents[position].name
        vh.tvSimple.setOnClickListener(View.OnClickListener {
            if (selected == false && counter <= 5) {
                selected = true
                counter++
                vh.itemView.setBackgroundColor(R.color.blue)
                selectedCityList.add(parents[position].id)
                mViewTapListener?.onViewTapped(selectedCityList, holder)
                context?.let { it1 -> Toaster.show(it1, "Selected" + selectedCityList) }
            } else if (selected) {
                selected = false
                counter--
                selectedCityList.remove(parents[position].id)
                vh.itemView.setBackgroundColor(android.R.color.white)
                context?.let { it1 -> Toaster.show(it1, "UnSelected") }

            }
        })
    }


    inner class RecyclerViewSimpleTextViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var tvSimple: TextView = itemView.tv_simple
    }

}