package com.example.instagram.login.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.instagram.R
import de.hdodenhof.circleimageview.CircleImageView

class SearchImageFragment:Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search_image,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rv: RecyclerView = view.findViewById(R.id.search_img_rv)
        rv.adapter = MainAdapter()
        rv.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setHasOptionsMenu(true)
//    }

//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater.inflate(R.menu.menu_profile,menu)
//        super.onCreateOptionsMenu(menu, inflater)
//    }
    private class MainAdapter():RecyclerView.Adapter<MainAdapter.MainViewHolder>(){

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
            val itemView =  LayoutInflater.from(parent.context).inflate(R.layout.item_image_search,parent,false)
            return MainViewHolder(itemView)
        }

        override fun getItemCount(): Int {
            return 30
        }

        override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
           holder.build(R.drawable.ua)
        }
        class MainViewHolder(ItemView: View):RecyclerView.ViewHolder(ItemView){

            fun build(img:Int){
                itemView.findViewById<ImageView>(R.id.img_search).setImageResource(img)
            }
        }

    }
}