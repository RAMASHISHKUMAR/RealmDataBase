package activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.realmdatabase.R;

import java.util.List;

import adapter.Adapter;
import model.Student;
import realmhelper.StudentRealmHelper;

public class ShowActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    CardView cardView;

    List<Student> studentList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);


        recyclerView = findViewById(R.id.recyclerview);
        //  cardView = findViewById(R.id.cardview);

       /* studentList = StudentRealmHelper.getAllStudent();


        if (studentList != null && studentList.size() > 0) {
            Adapter adapter = new Adapter(studentList, this);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(adapter);

        }*/
    }

    private void updaterecyclerView() {

        studentList = StudentRealmHelper.getAllStudent();

        if (studentList != null && studentList.size() > 0) {
            Adapter adapter = new Adapter(studentList, this);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(adapter);

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        updaterecyclerView();

    }
}
