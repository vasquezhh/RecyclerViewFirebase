package vasquezh.rviewfirebase;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;

import vasquezh.rviewfirebase.Data.Information;
import vasquezh.rviewfirebase.Recycler.ViewAdapter;

public class MainActivity extends AppCompatActivity {

    final static String DB_URL = "https://vasquezhrecyclerview.firebaseio.com/";
    EditText nameEditText;
    Button saveBtn;
    ArrayList<Information> informations = new ArrayList<>();
    RecyclerView rv;
    ViewAdapter adapter;
    Firebase fire;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        rv= (RecyclerView) findViewById(R.id.mRecyclerID);
        rv.setLayoutManager(new LinearLayoutManager(this));

        Firebase.setAndroidContext(this);
        fire = new Firebase(DB_URL);

        this.refreshData();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayDialog();
            }
        });
    }

    private void displayDialog() {

        Dialog d = new Dialog(this);
        d.setTitle("Guardar en línea");
        d.setContentView(R.layout.dialog);

        nameEditText = (EditText) d.findViewById(R.id.nameEditText);
        saveBtn = (Button) d.findViewById(R.id.saveBtn);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveOnline(nameEditText.getText().toString());
                nameEditText.setText("");
            }
        });

        d.show();
    }

    private void saveOnline(String name) {

        Information i = new Information();
        i.setName(name);

        fire.child("Information").push().setValue(i);
    }

    private void refreshData()
    {
        fire.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                getUpdates(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                getUpdates(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    private void getUpdates(DataSnapshot dataSnapshot) {

        informations.clear();
        for (DataSnapshot ds : dataSnapshot.getChildren())
        {
            Information i=new Information();
            i.setName(ds.getValue(Information.class).getName());

            informations.add(i);
        }

        if (informations.size()>0)
        {
            adapter=new ViewAdapter(getApplicationContext(),informations);
            rv.setAdapter(adapter);
        }else {
            Toast.makeText(getApplicationContext(),"No hay datos",Toast.LENGTH_SHORT).show();
        }

    }
}
