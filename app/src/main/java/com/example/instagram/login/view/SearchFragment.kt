package com.example.instagram.login.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instagram.R
import de.hdodenhof.circleimageview.CircleImageView

class SearchFragment:Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rv: RecyclerView = view.findViewById(R.id.search_rv)
        rv.adapter = MainAdapter()
        rv.layoutManager = LinearLayoutManager(requireContext())
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
            val itemView =  LayoutInflater.from(parent.context).inflate(R.layout.item_search,parent,false)
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
                itemView.findViewById<CircleImageView>(R.id.img_photo).setImageResource(img)
            }
        }

    }
}