package winkit.android.okcore.example

import org.json.JSONArray
import winkit.android.okcore.CoreController
import winkit.android.okcore.ParsedCallback
import winkit.android.okcore.rest.JsonArrayCallback
import winkit.android.okcore.uielements.CoreUi

class DevController (view: CoreUi): CoreController<MockyRest>(view, MockyRest()) {

    fun getAll(callback: ParsedCallback<List<Developer>>) {
        enqueue(true, rest.getAllDevs(object: JsonArrayCallback.Secure<List<Developer>>(callback) {
            override fun onSecureResponse(json: JSONArray): List<Developer> = parse(json)
        }))
    }

    fun parse (array: JSONArray): List<Developer> {
        val result = ArrayList<Developer>()
        for (i in 0..(array.length() - 1)) {
            result.add(Developer(array.getJSONObject(i)))
        }
        return result
    }
}