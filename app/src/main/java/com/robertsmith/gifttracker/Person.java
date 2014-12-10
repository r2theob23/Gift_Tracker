package com.robertsmith.gifttracker;

import android.net.Uri;

/**
 * Created by robertsmith on 12/4/14.
 */
public class Person
{
    String name;
    String budget;
    Uri pic;

    public String getName() {
        return name;
    }

    public String getBudget() {
        return budget;
    }

    public Uri getPic() {
        return pic;
    }

    public Person(String name, String budget, Uri pic) {
        this.name = name;
        this.budget = budget;
        this.pic = pic;
    }
}
