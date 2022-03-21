package com.flavia.webinar.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.flavia.webinar.databinding.ItemRecyclerBinding
import com.flavia.webinar.entity.Categories

/*
    Created by: Flavia Figueroa
    Email: ffigueroa052000@gmail.com
    
    Dark Squad - Dark Zone
*/

typealias CategoryClick = (category: Categories) -> Unit
class Adapter: RecyclerView.Adapter<Adapter.ViewHolder>() {

    var list: List<Categories> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var categoryClick: CategoryClick?= null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.ViewHolder = ViewHolder(
        ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: Adapter.ViewHolder, position: Int) {
        holder.bind(this.list[position])
    }

    override fun getItemCount(): Int = this.list.size

    inner class ViewHolder(private val binding: ItemRecyclerBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(category: Categories){
            binding.apply {
                this.tvName.text = category.name
                this.btnCategory.setOnClickListener {
                    this@Adapter.categoryClick?.let {
                        it(category)
                    }
                }
            }
        }
    }
}