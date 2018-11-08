package winkit.android.okcore.rest

import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import org.json.JSONException
import org.json.JSONObject
import winkit.android.okcore.ParsedCallback
import java.io.IOException

abstract class JsonObjectCallback: Callback {

    final override fun onResponse(call: Call?, response: Response?) {
        try {
            val jsonString = response?.body()?.string()
            if (jsonString != null) onResponse(JSONObject(jsonString))
            else onResponse(null)
        } catch (ex: JSONException) {
            onFailure(ex)
        }
    }

    final override fun onFailure(call: Call?, e: IOException?) {
        onFailure(e)
    }

    abstract fun onResponse(json: JSONObject?)

    abstract fun onFailure(e: Exception?)

    abstract class DefaultFailure(private val callback: ParsedCallback<*>) : JsonObjectCallback () {
        override fun onFailure(e: Exception?) {
            callback.failure(e)
        }
    }
}