package id.sch.smktelkom_mlg.learn.signature.model;

import java.io.Serializable;

/**
 * Created by Faiqoh on 07/08/2017.
 */

public class Perusahaan implements Serializable {
    public String nama;
    public String keluhan;
    //public Drawable foto;

    public Perusahaan(String nama, String keluhan) {
        this.nama = nama;
        this.keluhan = keluhan;
        //this.foto = foto;
    }
}
