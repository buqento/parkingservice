package com.manggurebe.parkingservice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;


public class ListMall extends ActionBarActivity {

    private ListView lv;
    ArrayAdapter<String> adapter;
    ArrayList<HashMap<String, String>> menu;
    public String lippo,jcm,galleria,malioboro,jogjatronik,amplaz,ramai;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_mall);

        lippo = "Lippo Plaza";
        jcm = "Jogja City Mall";
        galleria = "Galleria Mall";
        malioboro = "Malioboro Mall";
        jogjatronik = "Jogjatronik";
        amplaz = "Ambarukkmo Plaza";
        ramai = "Ramai Family Mall";

        String[] menu = new String[]{
                lippo,jcm,galleria,malioboro,jogjatronik,amplaz,ramai
        };

        lv = (ListView) findViewById(R.id.list_view);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                String pilihan = (lv.getItemAtPosition(position).toString());
                tampilkanMenu(pilihan);

            }

            private void tampilkanMenu(String pilihan) {
                // TODO Auto-generated method stub
                Intent i = null;
                if (pilihan.equals(lippo)) {
                    Bundle b = new Bundle();
                    i = new Intent(ListMall.this, ViewListData.class);
                    b.putString("kategori", "1");
                    b.putString("nama", "Lippo Plaza");
                    b.putString("gambar", "lippoplaza");
                    b.putString("alamat", "Jl. Laksda Adisucipto");
                    i.putExtras(b);
                } else if (pilihan.equals(jcm)) {
                    Bundle b = new Bundle();
                    i = new Intent(ListMall.this, ViewListData.class);
                    b.putString("kategori", "2");
                    b.putString("nama", "Jogja City Mall");
                    b.putString("gambar", "jcm");
                    b.putString("alamat", "Jl. Magelang");
                    i.putExtras(b);
                } else if (pilihan.equals(galleria)) {
                    Bundle b = new Bundle();
                    i = new Intent(ListMall.this, ViewListData.class);
                    b.putString("kategori", "3");
                    b.putString("nama", "Galleria Mall");
                    b.putString("gambar", "galleria");
                    b.putString("alamat", "Jl. Jend Sudirman");
                    i.putExtras(b);
                } else if (pilihan.equals(malioboro)) {
                    Bundle b = new Bundle();
                    i = new Intent(ListMall.this, ViewListData.class);
                    b.putString("kategori", "4");
                    b.putString("nama", "Malioboro Mall");
                    b.putString("gambar", "malioboro");
                    b.putString("alamat", "Jl. Malioboro");
                    i.putExtras(b);
                } else if (pilihan.equals(jogjatronik)) {
                    Bundle b = new Bundle();
                    i = new Intent(ListMall.this, ViewListData.class);
                    b.putString("kategori", "5");
                    b.putString("nama", "Jogjatronik Mall");
                    b.putString("gambar", "jogjatronik");
                    b.putString("alamat", "Jl. Brigjen Katamso");
                    i.putExtras(b);
                } else if (pilihan.equals(amplaz)) {
                    Bundle b = new Bundle();
                    i = new Intent(ListMall.this, ViewListData.class);
                    b.putString("kategori", "6");
                    b.putString("nama", "Ambarrukmo Plaza");
                    b.putString("gambar", "amplaz");
                    b.putString("alamat", "Jl. Laksda Adisucipto");
                    i.putExtras(b);
                } else if (pilihan.equals(ramai)) {
                    Bundle b = new Bundle();
                    i = new Intent(ListMall.this, ViewListData.class);
                    b.putString("kategori", "7");
                    b.putString("nama", "Ramai Family Mall");
                    b.putString("gambar", "ramai");
                    b.putString("alamat", "Jl. Jend Ahmad Yani");
                    i.putExtras(b);
                }

                startActivity(i);
            }
        });

        adapter = new ArrayAdapter<String>(this, R.layout.list_menu, R.id.nama_menu, menu);
        lv.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
