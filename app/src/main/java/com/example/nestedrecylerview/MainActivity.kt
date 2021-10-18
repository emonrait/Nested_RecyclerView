package com.example.nestedrecylerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    private lateinit var parentRecyclerView: RecyclerView
    private var ParentAdapter: RecyclerView.Adapter<*>? = null
    var parentModelArrayList: ArrayList<ParentModel> = ArrayList()
    private lateinit var parentLayoutManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //set the Categories for each array list set in the `ParentViewHolder`
        parentModelArrayList.add(ParentModel("Category1"))
        parentModelArrayList.add(ParentModel("Category2"))
        parentModelArrayList.add(ParentModel("Category3"))
        parentModelArrayList.add(ParentModel("Category4"))
        parentModelArrayList.add(ParentModel("Category5"))
        parentModelArrayList.add(ParentModel("Category6"))
        parentRecyclerView = findViewById(R.id.Parent_recyclerView)
        parentRecyclerView.setHasFixedSize(true)
        parentLayoutManager = LinearLayoutManager(this)
        ParentAdapter = ParentAdaptar(parentModelArrayList, this@MainActivity,

            object : ParentAdaptar.OnItemClickListener {
                override fun onItemClick(item: ParentModel?) {
                    Toast.makeText(
                        this@MainActivity,
                        item!!.movieCategory.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
        parentRecyclerView.setLayoutManager(parentLayoutManager)
        parentRecyclerView.setAdapter(ParentAdapter)
        ParentAdapter!!.notifyDataSetChanged()
    }
}