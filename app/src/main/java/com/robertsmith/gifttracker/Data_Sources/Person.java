package com.robertsmith.gifttracker.Data_Sources;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by robertsmith on 12/4/14.
 */
public class Person implements Serializable
{
    String name;
    String budget;
    String pic;
    ArrayList<Gift> gifts;

    private static final long serialVersionUID = 1L;

    public ArrayList<Gift> getGifts()
    {
        return gifts;
    }

    public void setGifts(ArrayList<Gift> gifts)
    {
        this.gifts = gifts;
    }

    public String getName()
    {
        return name;
    }

    public String getBudget()
    {
        return budget;
    }

    public String getPic()
    {
        return pic;
    }

    public Person(String name, String budget, String pic)
    {
        this.name = name;
        this.budget = budget;
        this.pic = pic;
    }

//    // Parcelling part
//    public Person(Parcel in)
//    {
//        in.readInt();
//        this.name = in.readString();
//        this.budget = in.readString();
//        //this.pic = Uri.parse(in.readString());
//    }
//
//    public int describeContents()
//    {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags)
//    {
//        dest.writeString(this.name);
//        dest.writeString(this.budget);
//       // dest.writeString(String.valueOf(this.pic));
//
//    }
//    public static final Parcelable.Creator CREATOR = new Parcelable.Creator()
//    {
//        public Person createFromParcel(Parcel in)
//        {
//            return new Person(in);
//        }
//
//        public Person[] newArray(int size)
//        {
//            return new Person[size];
//        }
//    };
}
