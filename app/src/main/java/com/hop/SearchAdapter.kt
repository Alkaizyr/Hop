package com.hop

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class CustomAdapter(private var version: ArrayList<String>?) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_layout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tv_versionname.text = version!![position]
    }

    override fun getItemCount(): Int {
        return version!!.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var tv_versionname: TextView

        init {

            tv_versionname = itemView.findViewById(R.id.tv_versionname) as TextView
        }
    }

    fun filterList(filterdNames: ArrayList<String>) {
        this.version = filterdNames
        notifyDataSetChanged()
    }
}