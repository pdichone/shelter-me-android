package sheltermeapp.magadistudio.com.shelterme;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import sheltermeapp.magadistudio.com.shelterme.adapter.CustomAnimalAdapter;
import sheltermeapp.magadistudio.com.shelterme.app.AppController;
import sheltermeapp.magadistudio.com.shelterme.model.Animal;

public class RottentsActivity extends Activity {


    private final String TAG = CatsActivity.class.getSimpleName();
    private final String urlString = "http://app-service.pawsonitmedia.com/api/shelter/id/1/species/3/format/json"; //rabbits
    private ListView list;
    private List<Animal> rabbits = new ArrayList<Animal>();
    // private Animal mData[] = null;
    private ProgressDialog pDialog;
    private CustomAnimalAdapter adapter;
    private TextView animalId;
    private TextView name;

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rabbit_screen);

        list = (ListView) findViewById(R.id.list);
        adapter = new CustomAnimalAdapter(this, R.layout.list_row, rabbits);

        list.setAdapter(adapter);

        getRabbits(urlString);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), rabbits.get(position).getName().toString(), Toast.LENGTH_LONG).show();
            }
        });
	}

    private void getRabbits(String url){

        pDialog = new ProgressDialog(this);

        pDialog.setMessage("Loading....");
        pDialog.show();


        JsonObjectRequest movieReq = new JsonObjectRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.v(TAG, response.toString());
                hidePDialog();


                try {
                    // JSONObject obj =
                    // response.getJSONObject("movies");
                    JSONArray obj = response.getJSONArray("animals");
                    for (int i = 0; i < obj.length(); i++) {

                        JSONObject catObj = obj.getJSONObject(i);

                        Animal cat = new Animal();
                        cat.setName(catObj.getString("name"));
                        cat.setSex(catObj.getString("sex"));
                        cat.setPrimaryBreed(catObj.getString("primary_breed"));
                        cat.setSecondaryBreed(catObj.getString("secondary_breed"));
                        cat.setPhotoUrl(catObj.getString("photo"));
                        cat.setAnimalId(catObj.getString("id"));

                        rabbits.add(cat);


                    }

                    Log.v("JSON OBJECT ===>", response.toString());
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                // Notify list adapter of data changes
                adapter.notifyDataSetChanged();

            }

        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hidePDialog();

            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(movieReq);

    }

    private void hidePDialog() {

        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }

    }
}
