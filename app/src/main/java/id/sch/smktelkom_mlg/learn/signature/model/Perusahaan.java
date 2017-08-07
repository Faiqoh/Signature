package id.sch.smktelkom_mlg.learn.signature.model;

import android.graphics.drawable.Drawable;

/**
 * Created by Faiqoh on 07/08/2017.
 */

public class Perusahaan {
    public String nama;
    public String keluhan;
    public Drawable foto;

    public Perusahaan(String nama, String keluhan, Drawable foto) {
        this.nama = nama;
        this.keluhan = keluhan;
        this.foto = foto;
    }
}
