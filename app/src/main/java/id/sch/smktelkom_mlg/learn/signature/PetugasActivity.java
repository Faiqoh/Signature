package id.sch.smktelkom_mlg.learn.signature;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.github.gcacace.signaturepad.views.SignaturePad;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import id.sch.smktelkom_mlg.learn.signature.handler.RequestHandler;

public class PetugasActivity extends AppCompatActivity {

//    public static final String UPLOAD_URL = "http://192.168.43.100/Modul6/coba.php";
//    public static final String NIS = "nis";
//    public static final String NAMA = "nama";

    public static final String UPLOAD_URL = "http://192.168.43.100/Modul6/coba.php";
    public static final String NIS = "nis";
    public static final String NAMA = "nama";


    private SignaturePad mSignaturePad;
    private Button ClearButton;
    private Button SaveButton;

    private Bitmap bitmap;

    private Uri filePath;

    String id;

//    public static void verifyStoragePermissions(Activity activity) {
//        // Check if we have write permission
//        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
//
//        if (permission != PackageManager.PERMISSION_GRANTED) {
//            // We don't have permission so prompt the user
//            ActivityCompat.requestPermissions(
//                    activity,
//                    PERMISSIONS_STORAGE,
//                    REQUEST_EXTERNAL_STORAGE
//            );
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petugas);

//        Perusahaan perusahaan = (Perusahaan) getIntent().getSerializableExtra(MainActivity.PERUSAHAAN);

        mSignaturePad = (SignaturePad) findViewById(R.id.signature_pad);
        mSignaturePad.setOnSignedListener(new SignaturePad.OnSignedListener() {
            @Override
            public void onStartSigning() {
                Toast.makeText(PetugasActivity.this, "OnStartSigning", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSigned() {
                SaveButton.setEnabled(true);
                ClearButton.setEnabled(true);
            }

            @Override
            public void onClear() {
                SaveButton.setEnabled(false);
                ClearButton.setEnabled(false);
            }
        });

        ClearButton = (Button) findViewById(R.id.btnclear);
        SaveButton = (Button) findViewById(R.id.btnsave);
        id = getIntent().getStringExtra(MainActivity.PERUSAHAAN);

        ClearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSignaturePad.clear();
            }
        });

        SaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bitmap = mSignaturePad.getSignatureBitmap();
//                if (addJpgSignatureToGallery(signatureBitmap)) {
//                    Toast.makeText(PetugasActivity.this, "Signature saved into the Gallery", Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(PetugasActivity.this, CustomerActivity.class));
//                } else {
//                    Toast.makeText(PetugasActivity.this, "Unable to store the signature", Toast.LENGTH_SHORT).show();
//                }
                Log.i("BITMAPNYA", bitmap.toString());
                uploadImage();
            }
        });


    }

    private void uploadImage(){
        class UploadImage extends AsyncTask<Bitmap,Void,String> {

            ProgressDialog loading;
            RequestHandler rh = new RequestHandler();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(PetugasActivity.this, "Uploading Image", "Please wait...",true,true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Log.i("DI POST", "IYA");
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Bitmap... params) {
                Bitmap bitmap = params[0];
                String uploadImage = getStringImage(bitmap);

                HashMap<String,String> data = new HashMap<>();
                data.put(NIS, id);
                data.put(NAMA, uploadImage);

                Log.i("UPLOADIMAGE", uploadImage);
                String result = rh.sendPostRequest(UPLOAD_URL,data);

                return result;
            }
        }

        UploadImage ui = new UploadImage();
        ui.execute(bitmap);
    }

    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode,
//                                           @NonNull String permissions[], @NonNull int[] grantResults) {
//        switch (requestCode) {
//            case REQUEST_EXTERNAL_STORAGE: {
//                // If request is cancelled, the result arrays are empty.
//                if (grantResults.length <= 0
//                        || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
//                    Toast.makeText(PetugasActivity.this, "Cannot write images to external storage", Toast.LENGTH_SHORT).show();
//                }
//            }
//        }
//    }

//    public File getAlbumStorageDir(String albumName) {
//        // Get the directory for the user's public pictures directory.
//        File file = new File(Environment.getExternalStoragePublicDirectory(
//                Environment.DIRECTORY_PICTURES), albumName);
//        if (!file.mkdirs()) {
//            Log.e("SignaturePad", "Directory not created");
//        }
//        return file;
//    }

//    public void saveBitmapToJPG(Bitmap bitmap, File photo) throws IOException {
//        Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(newBitmap);
//        canvas.drawColor(Color.WHITE);
//        canvas.drawBitmap(bitmap, 0, 0, null);
//        OutputStream stream = new FileOutputStream(photo);
//        newBitmap.compress(Bitmap.CompressFormat.JPEG, 80, stream);
//        stream.close();
//    }
//
//    public boolean addJpgSignatureToGallery(Bitmap signature) {
//        boolean result = false;
//        Calendar c = Calendar.getInstance().getInstance();
//        SimpleDateFormat tgl = new SimpleDateFormat("yyyyMMdd_HHmmss");
//        String tgla = tgl.format(c.getTime());
//
//        try {
//            File photo = new File(getAlbumStorageDir("SignaturePad"), String.format(tgla + ".jpg", System.currentTimeMillis())); //format penyimpanan foto
//            saveBitmapToJPG(signature, photo);
//            scanMediaFile(photo);
//            result = true;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
//
//    private void scanMediaFile(File photo) {
//        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
//        Uri contentUri = Uri.fromFile(photo);
//        mediaScanIntent.setData(contentUri);
//        PetugasActivity.this.sendBroadcast(mediaScanIntent);
//    }



    /*public boolean addSvgSignatureToGallery(String signatureSvg) {
        boolean result = false;
        Calendar c = Calendar.getInstance().getInstance();
        SimpleDateFormat tgl = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String tglb = tgl.format(c.getTime());
        try {
            File svgFile = new File(getAlbumStorageDir("SignaturePad"), String.format(tglb + ".svg", System.currentTimeMillis()));
            OutputStream stream = new FileOutputStream(svgFile);
            OutputStreamWriter writer = new OutputStreamWriter(stream);
            writer.write(signatureSvg);
            writer.close();
            stream.flush();
            stream.close();
            scanMediaFile(svgFile);
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }*/
}
