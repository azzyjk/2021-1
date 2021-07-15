package com.example.week_10_2

import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.week_10_2.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import org.jsoup.Jsoup
import org.jsoup.parser.Parser

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    lateinit var adapter:MyAdapter
    val rssUrl = "http://fs.jtbc.joins.com//RSS/culture.xml"
    val jsonUrl = "http://api.icndb.com/jokes/random"
    val scope = CoroutineScope(Dispatchers.IO)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    fun getJson(){
        scope.launch{
            val doc = Jsoup.connect(jsonUrl).ignoreContentType(true).get()
            val json = JSONObject(doc.text())
            val joke = json.getJSONObject("value")
            val jokeStr = joke.getString("joke")
        }
    }

    fun getRSSNews(){
        scope.launch{
            adapter.items.clear()
            val doc = Jsoup.connect(rssUrl).parser(Parser.xmlParser()).get()
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
        binding.swipe.setOnRefreshListener {
            binding.swipe.isRefreshing = true
            getRSSNews()
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.addItemDecoration(DividerItemDecoration(this,LinearLayoutManager.VERTICAL))
        adapter = MyAdapter(ArrayList<MyData>())
        adapter.itemClickListener = object:MyAdapter.OnItemClickListener{
            override fun OnItemClick(
                holder: MyAdapter.MyViewHolder,
                view: View,
                data: MyData,
                position: Int
            ) {
                val intent = Intent(ACTION_VIEW, Uri.parse(adapter.items[position].url))
                startActivity(intent)
            }
        }
        binding.recyclerView.adapter = adapter
        getRSSNews()
    }
}
