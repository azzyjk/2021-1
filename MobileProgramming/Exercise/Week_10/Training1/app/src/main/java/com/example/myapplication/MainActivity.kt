package com.example.myapplication

import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import org.jsoup.Jsoup

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    lateinit var adapter:MyAdapter

    val site = "https://www.daum.net"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun getNews(){
        CoroutineScope(Dispatchers.IO).launch {
            val doc = Jsoup.connect(site).get()
            val result = doc.select("ul.list_txt >li>a")
            for(i in result)
                adapter.item.add(MyData(i.text(), i.absUrl("href")))
            withContext(Dispatchers.Main){
                adapter.notifyDataSetChanged()
                binding.swipe.isRefreshing = false
            }
        }
    }

    private fun init(){
        binding.apply{
            swipe.setOnRefreshListener {
                swipe.isRefreshing = true
                getNews()
            }
            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            recyclerView.addItemDecoration(DividerItemDecoration(this@MainActivity, LinearLayoutManager.VERTICAL))

            adapter = MyAdapter(ArrayList<MyData>())
            adapter.itemClickListener = object:MyAdapter.OnItemClickListener{
                override fun onItemClicked(
                    holder: MyAdapter.MyViewHolder,
                    view: View,
                    data: MyData,
                    position: Int
                ) {
                    val intent = Intent(ACTION_VIEW, Uri.parse(data.url))
                    startActivity(intent)
                }

            }
            recyclerView.adapter = adapter
            getNews()
        }
    }
}