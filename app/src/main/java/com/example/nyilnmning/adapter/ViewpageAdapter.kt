package com.example.nyilnmning.adapter

import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import javax.inject.Inject

class ViewpageAdapter @Inject constructor() : RecyclerView.Adapter<ViewpageAdapter.ViewpageHolder>(){

    private val images: MutableList<String> = mutableListOf()

    fun setImages(imageList: List<String>){
        images.clear()
        images.addAll(imageList)
        notifyDataSetChanged()
    }

    class ViewpageHolder(val imageView: ImageView) : RecyclerView.ViewHolder(imageView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewpageHolder {
        val imageView = ImageView(parent.context).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            scaleType = ImageView.ScaleType.CENTER_CROP
        }
        return ViewpageHolder(imageView)
    }

    override fun getItemCount(): Int = images.size

    override fun onBindViewHolder(holder: ViewpageHolder, position: Int) {
        Glide.with(holder.imageView.context)
            .load(images[position])
            .into(holder.imageView)
    }


}