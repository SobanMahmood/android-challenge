package io.devcrew.codingchallenge.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import io.devcrew.codingchallenge.R;
import io.devcrew.codingchallenge.model.User;

/**
 * A simple {@link DialogFragment} subclass enabling user to provide another user by entering first name and last name.
 * Activities that contain this fragment must implement the
 * {@link AddUserDialogListener} interface
 * to handle interaction events.
 */
public class AddUserDialogFragment extends DialogFragment {

    private AddUserDialogListener mListener;
    private TextView mFirstNameTextView;
    private TextView mLsatNameTextView;

    public AddUserDialogFragment() {
        // Required empty public constructor
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_user, null);
        mFirstNameTextView = (TextView) view.findViewById(R.id.dialog_add_user_et_first_name);
        mLsatNameTextView = (TextView) view.findViewById(R.id.dialog_add_user_et_last_name);
        builder.setView(view)
                .setPositiveButton(R.string.add, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        onAddButtonPressed();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        AddUserDialogFragment.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }

    private void onAddButtonPressed() {
        if (mListener != null) {
            String firstName = mFirstNameTextView.getText().toString();
            String lastName = mLsatNameTextView.getText().toString();
            User user = new User(firstName, lastName);
            mListener.onAddUser(user);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof AddUserDialogListener) {
            mListener = (AddUserDialogListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * The activity that creates an instance of this dialog fragment must
     * implement this interface in order to receive event callbacks.
     * Each method passes the DialogFragment in case the host needs to query it.
     */
    public interface AddUserDialogListener {
        void onAddUser(User user);
    }
}
