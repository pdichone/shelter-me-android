package sheltermeapp.magadistudio.com.shelterme;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Html;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import sheltermeapp.magadistudio.com.shelterme.app.AppController;
import sheltermeapp.magadistudio.com.shelterme.model.Animal;
import sheltermeapp.magadistudio.com.shelterme.model.AnimalDetails;

/**
 * Created by paulodichone on 11/26/14.
 */
public class DetailsActivity extends Activity {

    //private static String URL_PART_1 = "http://app-service.pawsonitmedia.com/api/animal/id/1/animalid/4325808/format/json";

    private  static String TAG = DetailsActivity.class.getSimpleName();

    private static String URL_PART_1 = "http://app-service.pawsonitmedia.com/api/animal/id/1/animalid/";
    private static String URL_PART_2 = "/format/json";
    private String animId = null;

    private TextView animalId;
    private TextView animalName;
    private TextView breedTextView;
    private TextView ageTextView;
    private TextView genderTextView;
    private TextView spayedTextView;
    private ProgressDialog pDialog;
    private TextView sizeTextView;
    private TextView colorTextView;
    private TextView descriptionTextView;
    private NetworkImageView thumbnail;
    private TextView arrivedTextView;
    private TextView priceTextView;
    private TextView declawedTextView;

    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    private static String finalURL = null;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_details_screen);

           Bundle extras = getIntent().getExtras();

        if (extras != null){
            String mId = extras.getString("animalId");
            String name = extras.getString("name");
            String breed = extras.getString("breed");
            String age = extras.getString("months");
            String gender = extras.getString("gender");
            String spayed = extras.getString("spayed");

           // Toast.makeText(this, mId, Toast.LENGTH_SHORT).show();

            animalId = (TextView) findViewById(R.id.animalId);
            animalId.setText("Animal Id: " + mId);

            animalName = (TextView) findViewById(R.id.animalName);
            animalName.setText(name);

            breedTextView = (TextView) findViewById(R.id.detailsBreed);
            breedTextView.setText("Breed: " + breed);

            ageTextView = (TextView) findViewById(R.id.detailsAge);
            ageTextView.setText("Age: " + age );

            genderTextView = (TextView) findViewById(R.id.detailsGender);
            genderTextView.setText("Gender: " + gender);

            spayedTextView = (TextView) findViewById(R.id.detailsSpayed);
            spayedTextView.setText("Spayed/Neutered: " + spayed );

            sizeTextView = (TextView) findViewById(R.id.detailsSize);
            colorTextView = (TextView) findViewById(R.id.detailsColor);
            descriptionTextView = (TextView) findViewById(R.id.detailsDescription);
            thumbnail = (NetworkImageView) findViewById(R.id.detailsThumbnail);
            arrivedTextView = (TextView) findViewById(R.id.detailsArrived);
            priceTextView = (TextView) findViewById(R.id.detailsPrice);
            declawedTextView = (TextView) findViewById(R.id.detailsDeclawed);



            //Create url
            finalURL = URL_PART_1+ mId + URL_PART_2;

            getDetails(finalURL);


        }

    }

    private void getDetails(String url){

         if (url == ""){

         }else{

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

                         JSONObject res = response.getJSONObject("details");

                         AnimalDetails animal = new AnimalDetails();
                         animal.setColor(res.getString("primary_color"));
                         animal.setSize(res.getString("size"));
                         animal.setDescription(res.getString("description"));
                         animal.setIntakeDate(res.getString("intake_date"));
                         animal.setPrice(res.getInt("price"));
                         animal.setDeclawed(res.getString("declawed"));

                         //Get photo array
                         JSONArray photoArray = res.getJSONArray("photos");
                         for (int i = 0; i < photoArray.length(); i++){
                             animal.setPhotoUrl(photoArray.getString(0)); // get the firt one for now

                         }

                         // Wire everything into the Views
                         sizeTextView.setText("Size: " + animal.getSize());
                         colorTextView.setText("Color: " + animal.getColor());

                          if (animal.getDescription().isEmpty()){
                              descriptionTextView.setText("Description not available.");
                          }else {
                              descriptionTextView.setText(Html.fromHtml(animal.getDescription()));
                          }

                         SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

                         String date = animal.getIntakeDate();

                         try{
                            Date mDate = df.parse(date);
                             arrivedTextView.setText("Arrived: " +df.format(mDate).toString()/*+ animal.getIntakeDate()*/);
                           //  Toast.makeText(getApplicationContext(), "Arrived:" + df.format(mDate).toString(), Toast.LENGTH_LONG).show();

                         }catch (ParseException e){
                             e.printStackTrace();
                         }

                         thumbnail.setImageUrl(animal.getPhotoUrl(), imageLoader);
                       //  arrivedTextView.setText("Arrived: "+ animal.getIntakeDate());
                         priceTextView.setText("Price: " + "$"+String.valueOf(animal.getPrice()) + " USD");
                         declawedTextView.setText("Declawed: " + animal.getDeclawed());





                        // Log.v("JSON OBJECT ===>", res.toString());
                     } catch (JSONException e) {
                         // TODO Auto-generated catch block
                         e.printStackTrace();
                     }



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

    }

    private void hidePDialog() {

        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }

    }

}
