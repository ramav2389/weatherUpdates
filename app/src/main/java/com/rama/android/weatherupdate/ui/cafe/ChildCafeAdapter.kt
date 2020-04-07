package com.rama.android.weatherupdate.ui.cafe

import android.content.Context
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.rama.android.weatherupdate.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_step2_child.view.*
import jp.wasabeef.picasso.transformations.MaskTransformation


class ChildCafeAdapter(val context: Context, private val images : List<String>)
    : RecyclerView.Adapter<ChildCafeAdapter.ViewHolder>(){

    private val displayMetrics: DisplayMetrics = context.resources.displayMetrics

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v =  LayoutInflater.from(parent.context)
            .inflate(com.rama.android.weatherupdate.R.layout.item_step2_child,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return images.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val lp: RecyclerView.LayoutParams = holder.itemView.layoutParams as RecyclerView.LayoutParams
        var mItemWidth = (displayMetrics.widthPixels * 0.74).toInt()
        var mItemHeight = (displayMetrics.heightPixels * 0.63).toInt()
        lp.width = mItemWidth
        lp.height = mItemHeight
        holder.itemView.layoutParams = lp

        val transformation = MaskTransformation(context, R.drawable.rounded_edges_cafe)

        Picasso.with(context).load(images[position]).transform(transformation)
            .placeholder(R.drawable.ic_placeholder_bg)
            .error(R.drawable.ic_placeholder_bg).into(holder.imageView)

    }


    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val imageView: ImageView = itemView.iv_grid_item
    }
}