package com.example.mvvmretrofit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mvvmretrofit.db.AppDatabase;
import com.example.mvvmretrofit.db.User;

public class AddNewUserActivity extends AppCompatActivity {
    private Bundle bundle;
    TextView textViewid;
    EditText firstNameInput;
    EditText lastNameInput;
    int id;
    String firstname;
    String lastname;

    public static final String ID = "id";
    static final String F_NAME = "fname";
    static final String L_NAME = "lname";


   /* private UserListAdapter userListAdapter;
    RecyclerView recyclerView;
    List<User> userList;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_user);


        textViewid = findViewById(R.id.id);
        firstNameInput = findViewById(R.id.firstNameInput);
        lastNameInput = findViewById(R.id.lastNameInput);
        Button saveButton = findViewById(R.id.saveButton);
        Button updateButton = findViewById(R.id.updateButton);


          /*  id = Integer.parseInt(bundle.getString("id"));
            firstname = bundle.getString("tvFirstName");
            lastname = bundle.getString("tvLastName");*/

        bundle = getIntent().getExtras();

        if (bundle != null) {
            id = bundle.getInt("id");
            firstname = bundle.getString("tvFirstName");
            lastname = bundle.getString("tvLastName");
        }


        /*int id = Integer.parseInt(getIntent().getStringExtra("id"));
        String firstname = getIntent().getStringExtra("tvFirstName");
        String lastname = getIntent().getStringExtra("tvLastName");*/

        textViewid.setText(String.valueOf(id));
        firstNameInput.setText(firstname);
        lastNameInput.setText(lastname);



        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNewUser(firstNameInput.getText().toString(), lastNameInput.getText().toString());

            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                updateUser(firstNameInput.getText().toString(), lastNameInput.getText().toString());
                updateNote();
            }
        });
    }

    public void updateNote() {
        String f_name = firstNameInput.getText().toString();
        String l_name = lastNameInput.getText().toString();
        Intent resultIntent = new Intent();
        resultIntent.putExtra(ID, id);
        resultIntent.putExtra(F_NAME, f_name);
        resultIntent.putExtra(L_NAME, l_name);
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    /*private void updateUser(String firstName, String lastName) {

        AppDatabase db = AppDatabase.getDbInstance(getApplicationContext());

        User user = new User();

        user.firstName = firstName;
        user.lastName = lastName;

        db.userDao().updateUser(user);

        Toast.makeText(this, "data updated", Toast.LENGTH_SHORT).show();

    }
*/

    private void saveNewUser(String firstName, String lastName) {
        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());

        User user = new User();
        user.firstName = firstName;
        user.lastName = lastName;
        db.userDao().insertAll(user);

        finish();

    }

}