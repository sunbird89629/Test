package com.sunbird.test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity2 : AppCompatActivity() {

    lateinit var rvTest: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        rvTest = findViewById(R.id.rv_test)

        initRecyclerView();
    }

    private fun initRecyclerView() {
        rvTest.apply {
            adapter = TestAdapter()
            layoutManager =
                LinearLayoutManager(this@MainActivity2, LinearLayoutManager.VERTICAL, false)
        }
    }


    class TestViewHolder : RecyclerView.ViewHolder {
        constructor(itemView: View) : super(itemView)

        fun bind() {

        }
    }


    class TestAdapter : RecyclerView.Adapter<TestViewHolder> {
        constructor() : super()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_test, parent, false)
            return TestViewHolder(view)
        }

        override fun onBindViewHolder(holder: TestViewHolder, position: Int) {

        }

        override fun getItemCount(): Int {
            return 30
        }
    }
}