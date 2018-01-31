package net.devbyzero.app.pascatrisakti.data;

import java.util.Arrays;
import java.util.List;

/**
 * Created by anta40 on 29-May-17.
 */

public class NewsProvider {

    public List<News> getNews(){

        return Arrays.asList(new News("Pendaftaran Mahasiswa Baru, Semester Ganjil, Tahun 2017/2018", "file:///android_asset/www/test_masuk.html"),
                new News("Kalender Akademik Perkuliahan Semester GENAP 2016/2017", "file:///android_asset/www/kalender.html"));

       // return Arrays.asList(new News("Not so important stuff",""), new News("foo bar blah bazz xyz 123",""));

    }

}
