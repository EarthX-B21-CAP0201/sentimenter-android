package com.earthx.sentimenter.view.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.earthx.sentimenter.R
import com.earthx.sentimenter.data.model.LastActivityItem
import com.earthx.sentimenter.view.analytics.graph.adapter.ResultListAdapter

class LastActivityAdapter(val listResult: ArrayList<LastActivityItem>, val context : Context) : RecyclerView.Adapter<LastActivityAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_last_activity, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listResult.size
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var date: TextView = itemView.findViewById(R.id.tv_item_date)
        var type: TextView = itemView.findViewById(R.id.tv_item_activity)
        var keyword: TextView = itemView.findViewById(R.id.tv_item_topic)

    }

    override fun onBindViewHolder(holder: LastActivityAdapter.ListViewHolder, position: Int) {
        val result = listResult[position]
        holder.date.text = result.date
        holder.type.text = result.type
        holder.keyword.text = result.keyword
    }

}