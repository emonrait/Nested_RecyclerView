package com.example.nestedrecylerview

import android.app.Dialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import java.util.ArrayList

class ChildAdaptar(
    private var accountList: ArrayList<ChildModel>,
    context: Context,
    //listenerInit: OnItemClickListener
) :
    RecyclerView.Adapter<ChildAdaptar.MyViewHolder>(),
    Filterable {

    var requestFilterList = ArrayList<ChildModel>()
    lateinit var mcontext: Context

    //var listener: OnItemClickListener


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var hero_image: ImageView
        var movie_name: TextView

        init {
            hero_image = itemView.findViewById(R.id.hero_image) as ImageView
            movie_name = itemView.findViewById(R.id.movie_name) as TextView

        }
    }
    init {
        requestFilterList = accountList
        //listener = listenerInit

    }



    interface OnItemClickListener {
        fun onItemClick(item: ChildModel?)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val atmListView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.child_recyclerview_items, parent, false)
        val sch = MyViewHolder(atmListView)
        mcontext = parent.context
        return sch
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = requestFilterList[position]


        holder.hero_image.setImageResource(currentItem.hero_image)
        holder.movie_name.setText(currentItem.movieName)
    }

    override fun getItemCount(): Int {
        return requestFilterList.size
        notifyDataSetChanged()
        Log.e("atmlistValue--->", requestFilterList.size.toString())
    }


    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                if (constraint.toString().isEmpty()) {
                    requestFilterList = accountList
                } else {

                    val resultList = ArrayList<ChildModel>()
                    for (row in accountList) {
                        if (
                            row.movieName.toString().lowercase()
                                ?.contains(constraint.toString().lowercase())
                        ) {
                            resultList.add(row)
                        }
                    }
                    requestFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = requestFilterList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                requestFilterList = results?.values as ArrayList<ChildModel>
                notifyDataSetChanged()
            }

        }
    }


}