package io.devcrew.codingchallenge.model;

/**
 * Created by soban on 19/01/16.
 */
public class User {

    private String mFirstName;
    private String mLastName;

    public User(String firstName, String lastName) {
        mFirstName = firstName;
        mLastName = lastName;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public String getLastName() {
        return mLastName;
    }
}