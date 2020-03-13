package com.mvp_kotlin_login_register_retrofit_example.ui

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.mvp_kotlin_login_register_retrofit_example.R
import com.mvp_kotlin_login_register_retrofit_example.classes.Post

class Adapter(
    private val mContext: Context,
    private val posts: ArrayList<Post>
) : RecyclerView.Adapter<Adapter.ViewHolderProfile>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderProfile {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.adapter_post, parent, false)
        return ViewHolderProfile(view)
    }

    override fun onBindViewHolder(holder: ViewHolderProfile, position: Int) {
        Log.d(TAG, "onBindViewHolder: called.")
        holder.title.text = posts[position].name
        holder.desc.text = posts[position].desc

    }

    override fun getItemCount(): Int {
        return posts.size
    }

    inner class ViewHolderProfile(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var title: TextView
        internal var desc: TextView



        init {
            title = itemView.findViewById(R.id.list_title)
            desc = itemView.findViewById(R.id.list_description)
        }
    }

    companion object {
        private val TAG = "RecyclerViewAdapter"
    }
}