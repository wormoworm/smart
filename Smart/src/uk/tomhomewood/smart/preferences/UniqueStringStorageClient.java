package uk.tomhomewood.smart.preferences;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class UniqueStringStorageClient{

	private SharedPreferences preferences;

	public UniqueStringStorageClient(Context context){
		preferences = PreferenceManager.getDefaultSharedPreferences(context);
	}

	public ArrayList<String> getValuesByKey(String key){
		ArrayList<String> values = new ArrayList<String>();

		String valuesString = preferences.getString(key, null);
		if(valuesString!=null){
			try {
				JSONObject valuesJson = new JSONObject(valuesString);
				@SuppressWarnings("unchecked")
				Iterator<String> iterator = valuesJson.keys();
				while(iterator.hasNext()){
					values.add(iterator.next());
				}
				if(values.isEmpty()){
					return null;
				}
			}
			catch (JSONException e) {
				return null;
			}
			return values;
		}
		else{
			return null;
		}
	}

	public void storeValue(String key, String value){
		JSONObject valuesJson;
		String valuesString = preferences.getString(key, null);
		try {
			if(valuesString==null){
				valuesJson = new JSONObject();
			}
			else{
				valuesJson = new JSONObject(valuesString);
			}
			valuesJson.put(value, "a");
			preferences
				.edit()
					.putString(key, valuesJson.toString())
						.commit();
		}
		catch (JSONException e) {
		}
	}
}