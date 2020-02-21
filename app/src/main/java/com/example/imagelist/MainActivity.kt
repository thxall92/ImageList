package com.example.imagelist

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import org.jsoup.Jsoup

class MainActivity : AppCompatActivity() {

    private val webUrl = "https://www.gettyimages.com/photos/collaboration"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MyAsyncTask().execute(webUrl)
    }

    inner class MyAsyncTask : AsyncTask<String, String, String>(){
        var newsList: ArrayList<Item> = arrayListOf()

        override fun onPreExecute() {
            super.onPreExecute()
            progressBar.visibility = View.VISIBLE
        }

        override fun doInBackground(vararg params: String?) : String {
            val doc = Jsoup.connect("$webUrl").get()
            val elts = doc.select("div.search-content__gallery-assets gi-asset")

            elts.forEachIndexed{ _ , elem ->
                val aHref = elem.select("a").attr("href")
                val thumbImg = elem.select("img").attr("src")

                val mNews = Item(aHref, thumbImg)
                newsList.add(mNews)
            }

            return doc.title()
        }

        override fun onPostExecute(result: String?) {
            progressBar.visibility = View.GONE

            val adapter = MyAdapter(newsList, this@MainActivity)
            rv_news_list.adapter = adapter

        }

    }

    data class Item(val url:String, val thumb: String)
}
