package com.example.a5_3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    var data:ArrayList<MyData> = ArrayList()
    var ttsReady = false
    lateinit var adapter:MyAdapter
    lateinit var recyclerView:RecyclerView
    lateinit var tts:TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
        initData()
        initTTS()
    }

    override fun onStop() {
        super.onStop()
        tts.stop()
    }

    override fun onDestroy() {
        super.onDestroy()
        tts.shutdown()
    }

    private fun initTTS(){
        tts= TextToSpeech(this, TextToSpeech.OnInitListener {
            ttsReady = true
            tts.language = Locale.US
        })
    }

    private fun initRecyclerView(){
        recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = MyAdapter(data)
        adapter.itemClickListener = object:MyAdapter.OnItemClickListener{
            override fun OnItemClick(holder: RecyclerView.ViewHolder, view: View, data: MyData, position: Int) {
                Toast.makeText(applicationContext, data.meaning, Toast.LENGTH_SHORT).show()
                if(ttsReady) tts.speak(data.word, TextToSpeech.QUEUE_ADD, null, null)
            }
        }
        recyclerView.adapter = adapter

        val simpleItemTouchCallback = object:ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN, ItemTouchHelper.LEFT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                adapter.moveItem(viewHolder.adapterPosition, target.adapterPosition)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                adapter.removeItem(viewHolder.adapterPosition)
            }
        }
        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private fun initData(){
        val scan = Scanner(resources.openRawResource(R.raw.words))

        while(scan.hasNextLine()){
            val word = scan.nextLine().toString()
            val meaning = scan.nextLine().toString()
            data.add(MyData(word, meaning))
        }
        scan.close()
    }
}
