package com.example.imagelist

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.list_item.view.*

class MyAdapter(
    private val items: ArrayList<MainActivity.Item>,
    private  val context : Context
) : RecyclerView.Adapter<MyAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(items[position].thumb)
            .centerCrop()
            .placeholder(android.R.drawable.stat_sys_upload)
            .into(holder.itemView.iv_thumb)

        holder.itemView.setOnClickListener{
            holder.itemView.setOnClickListener{
                val openURL = Intent(Intent.ACTION_VIEW)
                openURL.data = Uri.parse("https://www.gettyimages.com${items[position].url}")
                ContextCompat.startActivity(context, openURL, null)
            }
        }
    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view)
}