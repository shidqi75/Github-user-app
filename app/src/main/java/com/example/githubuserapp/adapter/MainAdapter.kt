package com.example.githubuserapp.adapter

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubuserapp.R
import com.example.githubuserapp.model.UserItem
import com.example.githubuserapp.view.activity.DetailActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_user.view.*
import kotlin.random.Random

class MainAdapter : RecyclerView.Adapter<MainAdapter.ListViewHolder>() {

    private val listUsers = ArrayList<UserItem>()

    fun setData(items: Collection<UserItem>) {
        listUsers.clear()
        listUsers.addAll(items)
        notifyDataSetChanged()
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(user: UserItem?) {
            user?.let {
                with(itemView) {
                    Picasso.get().load(it.avatar_url).into(item_user_image)
                    item_user_tv_username.text = "@${it.login}"
                    item_user_image_bg.setBackgroundColor(
                        Color.argb(
                            255,
                            Random.nextInt(256),
                            Random.nextInt(256),
                            Random.nextInt(256)
                        )
                    )
                    item_user_cardView.setOnClickListener {
                        val intent = Intent(context, DetailActivity::class.java)
                        intent.putExtra("user", user)
                        context.startActivity(intent)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MainAdapter.ListViewHolder =
        ListViewHolder(
            LayoutInflater.from(viewGroup.context).inflate(R.layout.item_user, viewGroup, false)
        )

    override fun onBindViewHolder(holder: MainAdapter.ListViewHolder, position: Int) {
        holder.bindView(listUsers[position])
    }

    override fun getItemCount(): Int = listUsers.size

}