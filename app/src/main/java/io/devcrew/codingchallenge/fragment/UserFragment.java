package io.devcrew.codingchallenge.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import io.devcrew.codingchallenge.R;
import io.devcrew.codingchallenge.model.User;
import io.devcrew.codingchallenge.network.UserDataProvider;
import io.devcrew.codingchallenge.view.adapter.UserAdapter;

/**
 * Created by soban on 19/01/16.
 * Fragment for displaying users
 */
public class UserFragment extends Fragment {
    private UserAdapter mUserAdapter;
    private List<User> mUserList = new ArrayList<>();

    public UserFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        RecyclerView mUserRecyclerView = (RecyclerView) view.findViewById(R.id.fragment_user_rv_users);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mUserRecyclerView.setLayoutManager(mLayoutManager);
        mUserAdapter = new UserAdapter(mUserList);
        mUserRecyclerView.setAdapter(mUserAdapter);
        loadUsers();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void loadUsers() {
        UserDataProvider nameFetchApi = new UserDataProvider(getActivity(), new UserDataProvider.ResponseHandler() {
            @Override
            public void onFailure() {

            }

            @Override
            public void onSuccess(List<User> users) {
                mUserList.clear();
                mUserList.addAll(users);
                mUserAdapter.notifyDataSetChanged();
            }
        });
        nameFetchApi.executeInBackground();
    }

    public void addUser(User user) {
        mUserAdapter.addItem(user);
        mUserAdapter.notifyDataSetChanged();
    }

    public void setData(List<User> users) {
        this.mUserList = users;
    }

    public List<User> getData() {
        return mUserList;
    }
}
