package isuru117.conversionwebservice;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {

    private Button btnSubmit;
    String responseText;
    StringBuffer response;
    URL url;
    Activity activity;
    ArrayList<Currency> countries=new ArrayList<Currency>();
    private ProgressDialog progressDialog;
    ListView listView;

    private String path = "https://api.exchangeratesapi.io/latest";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            activity = this;
            //btnSubmit = (Button) findViewById(R.id.btnSubmit);
            listView = (ListView) findViewById(android.R.id.list);
            new GetServerData().execute();

        }

        class GetServerData extends AsyncTask
        {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                // Showing progress dialog
                progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setMessage("Fetching Exchange Rates..");
                progressDialog.setCancelable(false);
                progressDialog.show();

            }

            @Override
            protected Object doInBackground(Object[] objects) {
                return getWebServiceResponseData();
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);

                // Dismiss the progress dialog
                if (progressDialog.isShowing())
                    progressDialog.dismiss();
                // For populating list data
                CustomCurrencyList customCurrencyList = new CustomCurrencyList(activity, countries);
                listView.setAdapter(customCurrencyList);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        Toast.makeText(getApplicationContext(),"You Selected "+countries.get(position).getValue()+ " as Currency", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), CalcActivity.class);
                        intent.putExtra("CUR",countries.get(position).getId());
                        intent.putExtra("RATE",countries.get(position).getValue());
                        startActivity(intent);
                    }

                });


            }
        }
        protected Void getWebServiceResponseData() {

            try {

                url = new URL(path);
                Log.d(TAG, "ServerData: " + path);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("GET");

                int responseCode = conn.getResponseCode();

                Log.d(TAG, "Response code: " + responseCode);
                if (responseCode == HttpsURLConnection.HTTP_OK) {
                    // Reading response from input Stream
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(conn.getInputStream()));
                    String output;
                    response = new StringBuffer();

                    while ((output = in.readLine()) != null) {
                        response.append(output);
                    }
                    in.close();
                }}
             catch(Exception e){
                    e.printStackTrace();
            }

                responseText = response.toString();
                //Call ServerData() method to call webservice and store result in response
              //  response = service.ServerData(path, postDataParams);
                Log.d(TAG, "data:" + responseText);
                try {
                    JSONObject jsonarray = new JSONObject(responseText);
                    JSONObject rates = jsonarray.getJSONObject("rates");

                    Iterator<?> keys = rates.keys();

                    while(keys.hasNext() ) {
                        String key = (String)keys.next();
                        String value = rates.getString(key);
                        Log.d(TAG, "id:" + key);
                        Log.d(TAG, "Currency:" + value);
                        Currency CurrencyObj=new Currency(key,value);
                        countries.add(CurrencyObj);
                        Log.d(TAG,countries.toString());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return null;
            }
    }
