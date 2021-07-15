package com.example.week_10_2

import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.week_10_2.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.parser.Parser

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    lateinit var adapter:MyAdapter

    val url = "http://fs.jtbc.joins.com//RSS/culture.xml"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun getNews(){
        CoroutineScope(Dispatchers.IO).launch {
            val doc = Jsoup.connect(url).parser(Parser.xmlParser()).get()
            val headlines = doc.select("item")
            for(news in headlines){
                adapter.items.add(MyData(news.select("title").text(), news.select("link").text()))
            }

            withContext(Dispatchers.Main){
                adapter.notifyDataSetChanged()
                binding.swipe.isRefreshing = false
            }
        }

    }

    private fun init(){
        binding.apply {
            swipe.setOnRefreshListener {
                swipe.isRefreshing = true
                getNews()
            }
            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            recyclerView.addItemDecoration(DividerItemDecoration(this@MainActivity, LinearLayoutManager.VERTICAL))

            adapter = MyAdapter(ArrayList<MyData>())
            adapter.onitemClickListener = object:MyAdapter.OnItemClickListener{
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

        }
        getNews()
    }
}