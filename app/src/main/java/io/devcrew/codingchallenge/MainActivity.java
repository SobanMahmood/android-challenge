package io.devcrew.codingchallenge;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import io.devcrew.codingchallenge.dialog.AddUserDialogFragment;
import io.devcrew.codingchallenge.fragment.UserFragment;
import io.devcrew.codingchallenge.model.User;

/**
 * Created by soban on 19/01/16.
 */
public class MainActivity extends AppCompatActivity implements AddUserDialogFragment.AddUserDialogListener {

    private UserFragment userFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.activity_main_fab_add_user);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddUserDialog();
            }
        });

        FragmentManager fm = getSupportFragmentManager();
        userFragment = (UserFragment) fm.findFragmentById(R.id.activity_main_user_fragment);
    }

    private void showAddUserDialog() {
        AddUserDialogFragment addUserDialogFragment = new AddUserDialogFragment();
        addUserDialogFragment.show(getSupportFragmentManager(), "add_user");
    }

    @Override
    public void onAddUser(User user) {
        userFragment.addUser(user);
    }
}
