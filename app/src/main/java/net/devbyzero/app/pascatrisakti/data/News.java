package net.devbyzero.app.pascatrisakti.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Cipta-NB on 07-May-17.
 */

public class News implements Parcelable {

    private String title;
    private String url;

    public News(){
        this("", "");
    }

    public News(String title, String url){
        this.title = title;
        this.url = url;
    }

    protected News(Parcel in){
        title = in.readString();
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setUrl(String url){
        this.url = url;
    }

    public String getTitle(){
        return title;
    }

    public String getUrl(){
        return url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
    }

    public static final Creator<News> CREATOR = new Creator<News>(){
        @Override
        public News createFromParcel(Parcel in) {
            return new News(in);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };
}
