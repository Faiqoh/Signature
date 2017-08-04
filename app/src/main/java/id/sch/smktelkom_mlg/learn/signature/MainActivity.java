package id.sch.smktelkom_mlg.learn.signature;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Spinner.OnItemSelectedListener {

    public static final String ARRAY = "activity";
    private static final String URL_DATA = "http://192.168.11.137/edii/activity-report-edii/androidsql/get_data.php";
    private static final String TAG_ID = "id";
    private static final String TAG_NAMA = "nama_perusahaan";

    private Spinner spinner;
    private ArrayList<String> Nama_per;
    private JSONArray result;
    private TextView textViewNama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnadmin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PetugasActivity.class));
            }
        });

        findViewById(R.id.btncs).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CustomerActivity.class));
            }
        });

        Nama_per = new ArrayList<String>();
        spinner = (Spinner) findViewById(R.id.spinnerNama);
        spinner.setOnItemSelectedListener(this);
        textViewNama = (TextView) findViewById(R.id.textViewNama);
        getData();

    }

    private void getData() {
        StringRequest stringRequest = new StringRequest(Config.URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject j = null;
                        try {
                            j = new JSONObject(response);
                            result = j.getJSONArray(Config.ARRAY);
                            getNama_per(result);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void getNama_per(int position) {
        String nama_per = "";
        try {
            JSONObject json = result.getJSONObject(position);
            nama_per = json.getString(Config.TAG_NAMA);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        spinner.setAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, Nama_per));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        textViewNama.setText(getNama_per(position));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        textViewNama.setText("");
    }
}
