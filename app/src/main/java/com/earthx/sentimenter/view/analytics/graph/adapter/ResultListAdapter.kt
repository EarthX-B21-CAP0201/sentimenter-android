package com.earthx.sentimenter.view.analytics.graph.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.earthx.sentimenter.R
import com.earthx.sentimenter.data.model.ResultGraphItem


class ResultListAdapter(val listResult: ArrayList<ResultGraphItem>, val context : Context) : RecyclerView.Adapter<ResultListAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_graph, viewGroup, false)
        return ListViewHolder(view)
    }


    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val result = listResult[position]
        holder.number.text= (position+1).toString()
        holder.result.text = result.name
        holder.count.text = result.count.toString()
    }

    override fun getItemCount(): Int {
        return listResult.size
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var number: TextView = itemView.findViewById(R.id.text_number)
        var result: TextView = itemView.findViewById(R.id.text_result)
        var count: TextView = itemView.findViewById(R.id.text_count)

    }

}