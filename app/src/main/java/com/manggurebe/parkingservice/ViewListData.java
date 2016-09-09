package com.manggurebe.parkingservice;

/**
 * Created by buqento on 7/20/2016.
 */
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ViewListData extends ListActivity {

    private ProgressDialog pDialog;

    private TextView des,dalamat;

    // URL to get contacts JSON
    private static String url = "";

    // JSON Node names
    private static final String TAG_MALL = "datamall";
    private static final String TAG_BLOCK = "blok";
    private static final String TAG_CAPACITY = "kapasitas";


    // contacts JSONArray
    JSONArray contacts = null;

    // Hashmap for ListView
    ArrayList<HashMap<String, String>> contactList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tampil_data);

        contactList = new ArrayList<HashMap<String, String>>();

        ListView lv = getListView();



        Bundle b = this.getIntent().getExtras();
        String kat = b.getString("kategori");
        String nm = b.getString("nama");
        String gbr = b.getString("gambar");
        String alamat = b.getString("alamat");

        int res = getResources().getIdentifier(gbr, "drawable", getPackageName());
        ImageView iv = (ImageView) findViewById(R.id.imageView1);
        iv.setImageResource(res);

        Log.d("kat", kat);
        Log.d("nm", nm);
        Log.d("gbr", gbr);
        Log.d("alamat", alamat);

        dalamat = (TextView)findViewById(R.id.address);
        dalamat.setText(alamat);
        des = (TextView)findViewById(R.id.name);
        des.setText(nm);


        url = "http://merahputihbridge.com/webservice/parking_service.php?id="+kat;

        // Listview on item click listener
        lv.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // getting values from selected ListItem
                String name = ((TextView) view.findViewById(R.id.blok))
                        .getText().toString();
                String cost = ((TextView) view.findViewById(R.id.kapasitas))
                        .getText().toString();

                // Starting single contact activity
//                Intent in = new Intent(getApplicationContext(),
//                        SingleContactActivity.class);
//                in.putExtra(TAG_NAME, name);
//                in.putExtra(TAG_EMAIL, cost);
//                in.putExtra(TAG_PHONE_MOBILE, description);
//                startActivity(in);

            }
        });

        // Calling async task to get json
        new GetContacts().execute();
    }

    /**
     * Async task class to get json by making HTTP call
     * */
    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(ViewListData.this);
            pDialog.setMessage("Memuat data...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            ServiceHandler sh = new ServiceHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);

            Log.d("Response: ", "> " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    contacts = jsonObj.getJSONArray(TAG_MALL);

                    // looping through All Contacts
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);

                        String blok = c.getString(TAG_BLOCK);
                        String kapasitas = c.getString(TAG_CAPACITY);


                        // Phone node is JSON Object
//                        JSONObject phone = c.getJSONObject(TAG_PHONE);
//                        String mobile = phone.getString(TAG_PHONE_MOBILE);
//                        String home = phone.getString(TAG_PHONE_HOME);
//                        String office = phone.getString(TAG_PHONE_OFFICE);

                        // tmp hashmap for single contact
                        HashMap<String, String> contact = new HashMap<String, String>();

                        // adding each child node to HashMap key => value
                        contact.put(TAG_BLOCK, blok);
                        contact.put(TAG_CAPACITY, kapasitas);


                        // adding contact to contact list
                        contactList.add(contact);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Tidak dapat mengunduh data dari layanan.");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
            ListAdapter adapter = new SimpleAdapter(
                    ViewListData.this, contactList,
                    R.layout.list_item_blok, new String[] { TAG_BLOCK, TAG_CAPACITY}, new int[] { R.id.blok,
                    R.id.kapasitas});

            setListAdapter(adapter);
        }

    }

}