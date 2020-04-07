package com.rama.android.weatherupdate.ui.cafe

import android.content.Context
import android.text.Html
import android.text.SpannedString
import android.text.method.LinkMovementMethod
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rama.android.weatherupdate.R
import com.rama.android.weatherupdate.model.Step2List
import com.rama.android.weatherupdate.ui.home.ParentAdapter
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.MaskTransformation
import kotlinx.android.synthetic.main.item_step2.view.*
import kotlinx.android.synthetic.main.item_step2_grid.view.*
import kotlinx.android.synthetic.main.item_simple.view.*
import java.util.regex.Pattern

@Suppress("DEPRECATION")
class ParentCafeAdapter(val context: Context?, private val step2List: ArrayList<Step2List>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val CAFE = 0
        const val GALLERY = 1
        const val GENERAL = 2
    }

    private val displayMetrics: DisplayMetrics = context!!.resources.displayMetrics

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val inflater = LayoutInflater.from(parent.context!!)

        var viewHolder: RecyclerView.ViewHolder

        when (viewType) {

            CAFE -> {
                var vhtextview: View =
                    inflater.inflate(R.layout.item_step2, parent, false)
                viewHolder = CafeWelcomeViewHolder(vhtextview)
            }
            GALLERY -> {
                var vhgrid: View =
                    inflater.inflate(R.layout.item_step2_grid, parent, false)
                viewHolder = CafeGridViewHolder(vhgrid)
            }
            else -> {
                val vh = inflater.inflate(R.layout.item_simple, parent, false)
                viewHolder = RecyclerViewSimpleTextViewHolder(vh)
            }
        }

        return viewHolder

    }

    override fun getItemViewType(position: Int): Int {
        return if (step2List.get(position).description.isNotEmpty()) {
            CAFE
        } else if (step2List.get(position).image.isNotEmpty()) {
            GALLERY
        } else {
            GENERAL
        }
    }


    override fun getItemCount(): Int {

        return step2List.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder.itemViewType) {

            CAFE -> {
                val vh = holder as CafeWelcomeViewHolder
                configureCafeWelcomeViewHolder(vh, position)
            }

            GALLERY -> {

                val vh = holder as CafeGridViewHolder
                configureGridViewHolder(vh, position)
            }

            else -> {

                val vh = holder as ParentAdapter.RecyclerViewSimpleTextViewHolder
            }
        }

    }

    private fun configureGridViewHolder(vh: ParentCafeAdapter.CafeGridViewHolder, position: Int) {

        val lp: RecyclerView.LayoutParams = vh.itemView.layoutParams as RecyclerView.LayoutParams

        lp.height = LinearLayout.LayoutParams.WRAP_CONTENT
        vh.itemView.layoutParams = lp

        vh.recyclerView?.apply {

            layoutManager = LinearLayoutManager(vh.recyclerView!!.context, LinearLayout.HORIZONTAL, false)
            adapter = ChildCafeAdapter(context,step2List[position].image)
        }

    }

    private fun configureCafeWelcomeViewHolder(vh: CafeWelcomeViewHolder, position: Int) {

        val lp: RecyclerView.LayoutParams = vh.itemView.layoutParams as RecyclerView.LayoutParams
        lp.height = LinearLayout.LayoutParams.WRAP_CONTENT
        vh.itemView.layoutParams = lp

        val transformation = MaskTransformation(context, R.drawable.rounded_convers_transformation)

        Picasso.with(context).load(step2List[position].banner).transform(transformation).fit()
            .placeholder(R.drawable.ic_placeholder_bg)
            .error(R.drawable.ic_placeholder_bg).into(vh.ivCafe)


        var string = step2List[position].description

        val spanTag =
            "span style=\\\"margin: 0px; padding: 0px; border: 0px; outline: 0px; font-variant-caps: inherit; font-stretch: inherit; line-height: inherit; vertical-align: baseline; font-family: inherit; font-style: inherit; font-weight: 600;\\\""
        val regexPattern = Pattern.compile("<$spanTag>(.+?)</span>", Pattern.DOTALL);

        val m = regexPattern.matcher(string);
        while (m.find()) {
            if (!(m.group(1).contains("<"))) {
                string = string.replaceFirst(m.group(1), "<b>" + m.group(1) + "</b>")
            }

        }

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {

            vh.tvCafeWelcome.text = SpannedString(
                Html.fromHtml(
                    string,
                    Html.FROM_HTML_MODE_LEGACY
                )
            )

        } else {
            vh.tvCafeWelcome.text = SpannedString((Html.fromHtml(string)))
        }

        vh.tvCafeWelcome.movementMethod = LinkMovementMethod.getInstance()

    }

    fun appendData(dataList: List<Step2List>) {
        val oldCount = itemCount
        this.step2List.addAll(dataList)
        val currentCount = itemCount
        if (oldCount == 0 && currentCount > 0)
            notifyDataSetChanged()
        else if (oldCount > 0 && currentCount > oldCount)
            notifyItemRangeChanged(oldCount - 1, currentCount - oldCount)
    }

    inner class CafeGridViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var recyclerView: RecyclerView? = itemView.rv_child
    }

    inner class CafeWelcomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvCafeWelcome: TextView = itemView.tv_cafe
        var ivCafe: ImageView = itemView.iv_cafe
    }

    inner class RecyclerViewSimpleTextViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvSimple: TextView = itemView.tv_simple
    }

}