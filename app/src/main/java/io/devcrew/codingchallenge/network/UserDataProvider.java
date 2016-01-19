package io.devcrew.codingchallenge.network;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import io.devcrew.codingchallenge.R;
import io.devcrew.codingchallenge.helper.Utils;

/**
 * Created by soban on 19/01/16.
 * Provide users data
 */
public class UserDataProvider {

    private Context mContext;
    private ResponseHandler mResponseHandler;

    public UserDataProvider(Context context, ResponseHandler responseHandler) {
        mContext = context;
        mResponseHandler = responseHandler;
    }

    /**
     * This method fetch user list in asynchronously
     */
    public void executeInBackground() {
        //dummy fetch in background, reading data from file instead
        new Thread(new Runnable() {
            @Override
            public void run() {
                final String data = readFromFile();
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (data != null) {
                            Gson gson = new Gson();
                            List<User> userList = gson.fromJson(data, new TypeToken<List<User>>() {
                            }.getType());

                            mResponseHandler.onSuccess(getUsers(userList));
                        } else {
                            mResponseHandler.onFailure();
                        }
                    }
                });
            }
        }).start();
    }

    /**
     * Returns {@link io.devcrew.codingchallenge.network.UserDataProvider.User} list for fetched data
     *
     * @param userList fetched from network
     * @return list of {@link io.devcrew.codingchallenge.network.UserDataProvider.User}
     */
    private ArrayList<io.devcrew.codingchallenge.model.User> getUsers(List<User> userList) {
        ArrayList<io.devcrew.codingchallenge.model.User> users = new ArrayList<>();
        for (int i = 0; i < userList.size(); i++) {
            User user = userList.get(i);
            io.devcrew.codingchallenge.model.User newUser = new io.devcrew.codingchallenge.model.User(user.first_name, user.last_name);
            users.add(newUser);
        }
        return users;
    }

    /**
     * This method read file and returns a string containing all file data.
     * This task is being perform synchronously.
     *
     * @return content of R.raw.dummy_users_data file as string
     */
    private String readFromFile() {
        InputStream nameJsonFileInputStream = mContext.getResources().openRawResource(R.raw.dummy_users);
        return Utils.getStringFrom(nameJsonFileInputStream);
    }

    /**
     * Callback for request's response. Implement this for handling success and failure scenarios
     */
    public interface ResponseHandler {
        void onFailure();

        void onSuccess(List<io.devcrew.codingchallenge.model.User> users);
    }

    /**
     * This class is for internal parsing purpose
     */
    private static class User {
        public String first_name;
        public String last_name;
    }
}
