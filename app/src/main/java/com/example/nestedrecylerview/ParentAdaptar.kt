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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import java.util.ArrayList

class ParentAdaptar(
    private var parentModelArrayList: ArrayList<ParentModel>,
    context: Context,
    listenerInit: OnItemClickListener
) :
    RecyclerView.Adapter<ParentAdaptar.MyViewHolder>(),
    Filterable {

    var requestFilterList = ArrayList<ParentModel>()
    lateinit var mcontext: Context

    var listener: OnItemClickListener


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var category: TextView
        var childRecyclerView: RecyclerView

        init {
            category = itemView.findViewById(R.id.Movie_category)
            childRecyclerView = itemView.findViewById(R.id.Child_RV)
        }
    }

    init {
        requestFilterList = parentModelArrayList
        listener = listenerInit

    }


    interface OnItemClickListener {
        fun onItemClick(item: ParentModel?)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val atmListView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.parent_recyclerview_items, parent, false)
        val sch = MyViewHolder(atmListView)
        mcontext = parent.context
        return sch
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = requestFilterList[position]

        val layoutManager: RecyclerView.LayoutManager =
            LinearLayoutManager(mcontext, LinearLayoutManager.HORIZONTAL, false)
        holder.childRecyclerView.layoutManager = layoutManager
        holder.childRecyclerView.setHasFixedSize(true)
        holder.category.text = currentItem.movieCategory
        val arrayList: ArrayList<ChildModel> = ArrayList()

        // added the first child row
        if (currentItem.movieCategory.equals("Category1")) {
            arrayList.add(ChildModel(R.drawable.themartian, "Movie Name11"))
            arrayList.add(ChildModel(R.drawable.moana, "Movie Name12"))
            arrayList.add(ChildModel(R.drawable.mov2, "Movie Name13"))
            arrayList.add(ChildModel(R.drawable.blackp, "Movie Name14"))
            arrayList.add(ChildModel(R.drawable.moviedubbedinhindi2, "Movie Name15"))
            arrayList.add(ChildModel(R.drawable.hollywood1, "Movie Name16"))
        }

        // added in second child row
        if (currentItem.movieCategory.equals("Category2")) {
            arrayList.add(ChildModel(R.drawable.moviedubbedinhindi2, "Movie Name21"))
            arrayList.add(ChildModel(R.drawable.moviedubbedinhindi3, "Movie Name22"))
            arrayList.add(ChildModel(R.drawable.moviedubbedinhindi1, "Movie Name23"))
            arrayList.add(ChildModel(R.drawable.moviedubbedinhindi4, "Movie Name24"))
            arrayList.add(ChildModel(R.drawable.moviedubbedinhindi5, "Movie Name25"))
            arrayList.add(ChildModel(R.drawable.moviedubbedinhindi6, "Movie Name26"))
        }

        // added in third child row
        if (currentItem.movieCategory.equals("Category3")) {
            arrayList.add(ChildModel(R.drawable.hollywood6, "Movie Name31"))
            arrayList.add(ChildModel(R.drawable.hollywood5, "Movie Name32"))
            arrayList.add(ChildModel(R.drawable.hollywood4, "Movie Name33"))
            arrayList.add(ChildModel(R.drawable.hollywood3, "Movie Name34"))
            arrayList.add(ChildModel(R.drawable.hollywood2, "Movie Name35"))
            arrayList.add(ChildModel(R.drawable.hollywood1, "Movie Name36"))
        }

        // added in fourth child row
        if (currentItem.movieCategory.equals("Category4")) {
            arrayList.add(ChildModel(R.drawable.bestofoscar6, "Movie Name"))
            arrayList.add(ChildModel(R.drawable.bestofoscar5, "Movie Name"))
            arrayList.add(ChildModel(R.drawable.bestofoscar4, "Movie Name"))
            arrayList.add(ChildModel(R.drawable.bestofoscar3, "Movie Name"))
            arrayList.add(ChildModel(R.drawable.bestofoscar2, "Movie Name"))
            arrayList.add(ChildModel(R.drawable.bestofoscar1, "Movie Name"))
        }

        // added in fifth child row
        if (currentItem.movieCategory.equals("Category5")) {
            arrayList.add(ChildModel(R.drawable.moviedubbedinhindi4, "Movie Name"))
            arrayList.add(ChildModel(R.drawable.hollywood2, "Movie Name"))
            arrayList.add(ChildModel(R.drawable.bestofoscar4, "Movie Name"))
            arrayList.add(ChildModel(R.drawable.mov2, "Movie Name"))
            arrayList.add(ChildModel(R.drawable.hollywood1, "Movie Name"))
            arrayList.add(ChildModel(R.drawable.bestofoscar1, "Movie Name"))
        }

        // added in sixth child row
        if (currentItem.movieCategory.equals("Category6")) {
            arrayList.add(ChildModel(R.drawable.hollywood5, "Movie Name"))
            arrayList.add(ChildModel(R.drawable.blackp, "Movie Name"))
            arrayList.add(ChildModel(R.drawable.bestofoscar4, "Movie Name"))
            arrayList.add(ChildModel(R.drawable.moviedubbedinhindi6, "Movie Name"))
            arrayList.add(ChildModel(R.drawable.hollywood1, "Movie Name"))
            arrayList.add(ChildModel(R.drawable.bestofoscar6, "Movie Name"))
        }
        val childRecyclerViewAdapter =
            ChildAdaptar(arrayList, holder.childRecyclerView.context)
        holder.childRecyclerView.adapter = childRecyclerViewAdapter
        holder.category.setOnClickListener {
            listener.onItemClick(currentItem)
        }

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
                    requestFilterList = parentModelArrayList
                } else {

                    val resultList = ArrayList<ParentModel>()
                    for (row in parentModelArrayList) {
                        if (
                            row.movieCategory.toString().lowercase()
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
                requestFilterList = results?.values as ArrayList<ParentModel>
                notifyDataSetChanged()
            }

        }
    }


}