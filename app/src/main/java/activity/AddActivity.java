package activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.realmdatabase.R;

import io.realm.Realm;
import model.Student;

public class AddActivity extends AppCompatActivity {
    private EditText name,email,mobno,address;
    private Button addbutton;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);


        name = findViewById(R.id.edtTextname);
        email = findViewById(R.id.edtemail);
        mobno = findViewById(R.id.edtmobileno);
        address = findViewById(R.id.edtaddress);

        addbutton = findViewById(R.id.btnAddDetails);
        // initi
        Realm.init(this);
        realm = Realm.getDefaultInstance();
        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validEmai(email)) {

                    realm.beginTransaction();
                    Student student = new Student();
                    student.setName(name.getText().toString());
                    student.setMobileno(mobno.getText().toString());
                    student.setEmail(email.getText().toString());
                    student.setAddress(address.getText().toString());
                    realm.copyToRealmOrUpdate(student);
                    realm.commitTransaction();

                    Toast.makeText(AddActivity.this, "Add userdata Successfully", Toast.LENGTH_SHORT).show();
                    finish();

                }else{
                    Toast.makeText(getApplicationContext(),"Enter valid email address",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }



    private boolean validEmai(EditText email) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

// onClick of button perform this simplest code.
        if (email.getText().toString().matches(emailPattern))
        {
            return  true;
        }
        else
        {
            return false;
        }
    }
}
