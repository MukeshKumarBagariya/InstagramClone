package com.example.insta;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Profile.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Profile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Profile extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private TextView uBio,uDob,uProfession,uHobbies,uIntrests;
    private Button submit;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Profile() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Profile.
     */
    // TODO: Rename and change types and number of parameters
    public static Profile newInstance(String param1, String param2) {
        Profile fragment = new Profile();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        uBio = view.findViewById(R.id.uBio);
        uDob = view.findViewById(R.id.uDob);
        uIntrests = view.findViewById(R.id.uIntreset);
        uHobbies = view.findViewById(R.id.uHobbies);
        uProfession = view.findViewById(R.id.uProfession);
        submit = view.findViewById(R.id.submit);
        final ParseUser parseUser = ParseUser.getCurrentUser();
         if (parseUser.get("UserBio")==null){
            uBio.setText("");
            uDob.setText("");
            uIntrests.setText("");
            uProfession.setText("");
            uHobbies.setText("");
        } else{
             uBio.setText(parseUser.get("UserBio")+" ");
             uDob.setText(parseUser.get("UserDOB")+" ");
             uIntrests.setText(parseUser.get("UserIntrests")+" ");
             uProfession.setText(parseUser.get("UserProfession")+" ");
             uHobbies.setText(parseUser.get("UserHobbies")+" ");
         }
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parseUser.put("UserBio",uBio.getText().toString());
                parseUser.put("UserDOB",uDob.getText().toString());
                parseUser.put("UserIntrests",uIntrests.getText().toString());
                parseUser.put("UserProfession",uProfession.getText().toString());
                parseUser.put("UserHobbies",uHobbies.getText().toString());
                final ProgressDialog progressDialog = new ProgressDialog(getContext());
                progressDialog.setMessage("Wait a moment...");
                progressDialog.show();
                parseUser.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null){
                            Toast.makeText(getContext(),"Details Updated successfuly",Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();
                        } else {
                            Toast.makeText(getContext(),"Something Went wrong",Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();
                        }
                    }
                });
            }
        });



        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            if (context instanceof OnFragmentInteractionListener)
                mListener = (OnFragmentInteractionListener) context;
        } catch (Exception e){

        }
        //else throw new RuntimeException(context.toString()
          //      + " must implement OnFragmentInteractionListener");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
