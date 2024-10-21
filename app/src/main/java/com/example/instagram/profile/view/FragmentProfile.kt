package com.example.instagram.profile.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instagram.R

class FragmentProfile:Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.profile_fragment,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rv: RecyclerView = view.findViewById(R.id.profile_rv)
        rv.adapter = MainAdapter()
        rv.layoutManager = GridLayoutManager(requireContext(),3)
    }
    private class MainAdapter():RecyclerView.Adapter<MainAdapter.MainViewHolder>(){

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
            val itemView =  LayoutInflater.from(parent.context).inflate(R.layout.item_profile_grid,parent,false)
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
                itemView.findViewById<ImageView>(R.id.profile_img_grid).setImageResource(img)
            }
        }

    }
}