package sg.edu.rp.c346.p12_mydatabook;


import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class BioFragment extends Fragment {

    TextView tvBio;
    Button btnEdit;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference bioRef;

    public BioFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bio, container, false);

        tvBio = (TextView) view.findViewById(R.id.tvBio);
        btnEdit = (Button) view.findViewById(R.id.buttonEdit);

        firebaseDatabase = FirebaseDatabase.getInstance();
        bioRef = firebaseDatabase.getReference("Bio");

        bioRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // TODO: Task 4 - Get the latest value from the dataSnapshot and display on the UI using the EditText message
                // This method will get fired everytime the "message" value updates in the realtime database. We're getting our data back as a DataSnapshot
                Bio bio = dataSnapshot.getValue(Bio.class);
                tvBio.setText(bio.getBio());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // This method will be invoked if there is any error
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout bio =
                        (LinearLayout) inflater.inflate(R.layout.bio, null);
                final EditText etBio = (EditText) bio
                        .findViewById(R.id.editTextBio);

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Edit Bio")
                        .setView(bio)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                String name = etBio.getText().toString();
                                Bio bio = new Bio(name);
                                bioRef.setValue(bio);
                            }
                        });
                builder.setNegativeButton("Cancel", null);

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });


        return view;
    }

}
