package com.example.mvvmretrofit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmretrofit.adapter.UserListAdapter;
import com.example.mvvmretrofit.db.AppDatabase;
import com.example.mvvmretrofit.db.User;

import java.util.List;

public class RoomDatabaseActivity extends AppCompatActivity {

    public static final int UPDATE_NOTE_ACTIVITY_REQUEST_CODE = 2;
    private UserListAdapter userListAdapter;
    RecyclerView recyclerView;
    List<User> userList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_database);


        Button addNewUserButton = findViewById(R.id.addNewUserButton);
        addNewUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), AddNewUserActivity.class);
                startActivityForResult(intent, 100);
            }
        });


        initRecyclerView();

        loadUserList();

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.DOWN | ItemTouchHelper.UP) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
//                Toast.makeText(ListActivity.this, "on Move", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {


                AppDatabase db = AppDatabase.getDbInstance(getApplicationContext());

                int position = viewHolder.getAdapterPosition();
                db.userDao().delete(userList.get(position));
                userListAdapter.removeItem(position);


            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);


    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        userListAdapter = new UserListAdapter(this);
        recyclerView.setAdapter(userListAdapter);
    }

    private void loadUserList() {
        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());
        userList = db.userDao().getAllUser();
        userListAdapter.setUserList(userList);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == 100) {
            loadUserList();
        } else if (requestCode == UPDATE_NOTE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {

            AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());

            User user = new User();

            user.uid = data.getExtras().getInt(AddNewUserActivity.ID);
            user.firstName = data.getStringExtra(AddNewUserActivity.F_NAME);
            user.lastName = data.getStringExtra(AddNewUserActivity.L_NAME);

            db.userDao().updateUser(user);
            loadUserList();

        }


        super.onActivityResult(requestCode, resultCode, data);
    }


}