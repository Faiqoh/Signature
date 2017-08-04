package id.sch.smktelkom_mlg.learn.signature;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

//    public static final String ARRAY = "activity";
//    private static final String URL_DATA = "http://192.168.11.137/edii/activity-report-edii/androidsql/get_data.php";
//    private static final String TAG_ID = "id";
//    private static final String TAG_NAMA = "nama_perusahaan";


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


    }


}
