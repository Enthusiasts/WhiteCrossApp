package com.whitecross.whitecross

import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import android.widget.Toast
import org.json.JSONObject
import java.util.*

/**
 * Created by Mrdae on 22.08.2015.
 */
public class UniversityFragment : PageFragment() {

    override var layoutId: Int = R.layout.university_page_fragment

    override fun initUI() {

        val url = "https://aqueous-ravine-6367.herokuapp.com/university/all/300"

        var activity = getActivity()

        var callback = object : RequestsCompanion.Callback<JSONObject> {
            override fun call(response: JSONObject) {
                (activity.findViewById(R.id.section_label) as TextView).setText("Response is: $response")
                //exceptions throw
                val data =  response.getJSONArray("content")
                var i = 0
                var university : JSONObject
                var uni : Triple<Int,String,String>? = null
                val out = StringBuilder()
                while (i < data.length()) {
                    university = data[i++] as JSONObject
                    uni = Triple(university.get("id") as Int, university.get("name") as String, university.get("image") as String)
                    out.append("id:${uni.first} - ${uni.second}\n")
                }

                (activity.findViewById(R.id.section_label) as TextView).setText("Response:\n${out.toString()}")
            }

            override fun error(error: String, message: String) {
                (activity.findViewById(R.id.section_label) as TextView).setText("Error is: $error \nMessage is $message" )
            }

        }

        RequestsCompanion.requestJSON(activity.getApplicationContext(), url, callback)

    }


}

