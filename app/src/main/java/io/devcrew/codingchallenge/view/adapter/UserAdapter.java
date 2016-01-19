package io.devcrew.codingchallenge.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import io.devcrew.codingchallenge.R;
import io.devcrew.codingchallenge.helper.Utils;
import io.devcrew.codingchallenge.model.User;

/**
 * Created by soban on 19/01/16.
 */
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private List<User> mUsers;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nameTextView;

        public ViewHolder(View v) {
            super(v);
            nameTextView = (TextView) v.findViewById(R.id.item_user_tv_name);
        }
    }

    public UserAdapter(List<User> users) {
        mUsers = users;
    }

    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.nameTextView.setText(getFormattedName(mUsers.get(position)));
    }

    private String getFormattedName(User user) {
        return Utils.getNameFormattedString(user.getLastName()) + ", " + Utils.getNameFormattedString(user.getFirstName());
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public void addItem(User user) {
        mUsers.add(user);
    }

    /**
     * Removes old users and load new users
     *
     * @param users list of users to be loaded
     */
    public void resetListTo(List<User> users) {
        mUsers.clear();
        mUsers.addAll(users);
    }
}
