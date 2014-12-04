package com.robertsmith.gifttracker;

/**
 * Created by robertsmith on 12/4/14.
 */
public class Person
{
    String name;
    String budget;
    int pic;

    public String getName() {
        return name;
    }

    public String getBudget() {
        return budget;
    }

    public int getPic() {
        return pic;
    }

    public Person(String name, String budget, int pic) {
        this.name = name;
        this.budget = budget;
        this.pic = pic;
    }
}
