package id.sch.smktelkom_mlg.learn.signature;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.learn.signature.adapter.PerusahaanAdapter;
import id.sch.smktelkom_mlg.learn.signature.model.Perusahaan;

public class MainActivity extends AppCompatActivity {

//    private static final String URL_DATA = "http://192.168.11.137/edii/activity-report-edii/androidsql/get_data.php";
//    public static final String ARRAY = "activity";
//    private static final String TAG_ID = "id";
//    private static final String TAG_NAMA = "nama_perusahaan";

//    private static final String URL_DATA = "https://api.themoviedb.org/3/movie/now_playing?api_key=cc2b705c11164d940874ff87f19e62f4&language=en-US&page=1";
//    public static final String ARRAY = "results";
//    private static final String TAG_NAMA= "title";
//    private static final String TAG_ID = "vote_average";

//    ArrayList<HashMap<String, String>> productsList;
//    HashMap<String, String> map;

//    Spinner spinner;
//
//    String selected;
//
//    ArrayList<String> listNama = new ArrayList<>();
//    ArrayAdapter<String> adapter;

    ArrayList<Perusahaan> mList = new ArrayList<>();
    PerusahaanAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new PerusahaanAdapter(mList);
        recyclerView.setAdapter(mAdapter);

        fillData();


//        findViewById(R.id.btnadmin).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, PetugasActivity.class));
//            }
//        });
//
//        findViewById(R.id.btncs).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, CustomerActivity.class));
//            }
//        });

//        loadRecyclerViewData();


    }

    private void fillData() {
        Resources resources = getResources();
        String[] arNama = resources.getStringArray(R.array.places);
        String[] arKeluhan = resources.getStringArray(R.array.place_desc);
//        TypedArray a = resources.obtainTypedArray(R.array.places_picture);
//        Drawable[] arFoto = new Drawable[a.length()];
//        for (int i = 0; i < arFoto.length; i++) {
//            arFoto[i] = a.getDrawable(i);
//        }
        for (int i = 0; i < arNama.length; i++) {
            mList.add(new Perusahaan(arNama[i], arKeluhan[i]));
        }
        mAdapter.notifyDataSetChanged();
    }

    private void loadRecyclerViewData() {
//        final ProgressDialog progressDialog = new ProgressDialog(this);
//        progressDialog.setMessage("Loading data...");
//        progressDialog.show();
//
//        StringRequest stringRequest = new StringRequest(Request.Method.GET,
//                URL_DATA,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String s) {
//                        progressDialog.dismiss();
//                        try{
//                            JSONObject jsonObject = new JSONObject(s);
//                            JSONArray array = jsonObject.getJSONArray(ARRAY);
//
//                            Log.i("ARRAY1", array.toString());
//
//
//                            for(int i = 0; i < array.length(); i++) {
//                                JSONObject c = array.getJSONObject(i);
//
//                                String id = c.getString(TAG_ID);
//                                String nama_perusahaan = c.getString(TAG_NAMA);
//
//                                listNama.add(nama_perusahaan);
//                                Log.i("ISI LISTITEM", listNama.toString());
//                                map = new HashMap<String, String>();
//
//                                map.put(TAG_ID, id);
//                                map.put(TAG_NAMA, nama_perusahaan);
//
//                                Log.i("ARRAY2", listNama.toString());
//                            }
//                            spinner.setAdapter(adapter);
//
//                        } catch (JSONException e){
//                            e.printStackTrace();
//                        }
//
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError volleyError) {
//                        progressDialog.dismiss();
//                        Toast.makeText(getApplication(), volleyError.getMessage(), Toast.LENGTH_LONG).show();
//                        Log.i("ERRORNYA", volleyError.getMessage());
//                    }
//                });
//
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        requestQueue.add(stringRequest);
    }
}
