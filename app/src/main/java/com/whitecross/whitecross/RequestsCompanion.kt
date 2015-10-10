package com.whitecross.whitecross

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.support.v4.util.LruCache
import android.widget.TextView
import android.widget.Toast

import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.ImageLoader
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

/**
 * Created by Mrdae on 03.09.2015.
 */
public class RequestsCompanion(context : Context) {

    private var mRequestQueue : RequestQueue? = null
    //private var mImageLoader : ImageLoader? = null

    init {

        RequestsCompanion.mCtx = context
        mRequestQueue = getRequestQueue()

        /*mImageLoader = ImageLoader(mRequestQueue,
            object : ImageLoader.ImageCache {
                private final val cache = LruCache<String, Bitmap>(20);

                override fun getBitmap(url : String) : Bitmap{
                    return cache.get(url)
                }

                override fun putBitmap(url : String, bitmap : Bitmap) {
                    cache.put(url, bitmap)
                }
            });*/

    }

        private fun getRequestQueue() : RequestQueue {
            if (mRequestQueue == null) {
                Toast.makeText(mCtx!!.getApplicationContext(), "Creating request queue", Toast.LENGTH_SHORT).show()
                mRequestQueue = Volley.newRequestQueue(mCtx?.getApplicationContext(), 1024*1024*10) //10 Mb max cache size
            }
            return mRequestQueue!!
        }

        private fun <T> addToRequestQueue(req : Request<T>) {
            getRequestQueue().add(req)
        }

        /*public fun getImageLoader() : ImageLoader {
            return mImageLoader!!
        }*/

    public interface Callback <T> {

        fun call(response : T)

        fun error(error : String, message : String)
    }

    companion object {

        private var mCtx : Context? = null
        private var mInstance : RequestsCompanion? = null

        private synchronized fun getInstance(context : Context) : RequestsCompanion {
            if (mInstance == null) {
                mInstance = RequestsCompanion(context)
            }
            return mInstance!!
        }

        fun requestJSON(context: Context, url : String, callback : Callback<JSONObject>) {

            val jsObjRequest = JsonObjectRequest(Request.Method.GET, url, "",
                    object : Response.Listener<JSONObject> {

                        override fun onResponse(response : JSONObject) {
                            callback.call(response)
                        }
                    },
                    object : Response.ErrorListener {
                        override fun onErrorResponse(error: VolleyError) {
                            callback.error(error.javaClass.getSimpleName(), error.getMessage().toString())
                        }
                    })

            getInstance(context).addToRequestQueue(jsObjRequest);
        }

        /*fun work(activity: Activity) {
            // Instantiate the RequestQueue.
            val queue = Volley.newRequestQueue(activity)
            val url = "http://www.google.com"

            // Request a string response from the provided URL.
            val stringRequest = StringRequest(Request.Method.GET, url, object : Response.Listener<String> {
                override fun onResponse(response: String) {
                    // Display the first 500 characters of the response string.
                    (activity.findViewById(R.id.section_label) as TextView).setText("Response is: " + response.substring(0, 500))
                }
            }, object : Response.ErrorListener {
                override fun onErrorResponse(error: VolleyError) {
                    (activity.findViewById(R.id.section_label) as TextView).setText("Error is: " + error.toString())
                }
            })
            // Add the request to the RequestQueue.
            queue.add(stringRequest)
        }*/

    }

}
