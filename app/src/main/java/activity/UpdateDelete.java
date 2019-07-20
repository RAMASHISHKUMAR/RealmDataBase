package activity;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.realmdatabase.R;

import io.realm.Realm;
import io.realm.RealmResults;
import model.Student;

public class UpdateDelete extends AppCompatActivity {

    private EditText edtUDname,edtUDemail,edtUDmobno,edtUDaddress;
    private Button btnUDupdate,btnUDdelete;
    private Realm realm;
    private Student currentStudent;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);

        edtUDname = findViewById(R.id.edtUDname);
        edtUDemail = findViewById(R.id.edtUDemail);
        edtUDmobno = findViewById(R.id.edtUDmobno);
        edtUDaddress = findViewById(R.id.edtUDaddress);

        btnUDupdate = findViewById(R.id.btnUDupdate);
        btnUDdelete = findViewById(R.id.btnUDdelete);

        cardViewOperation(); // OncardView Operation
        updateOperation();   // onupdate Operation
        deleteOperation();   // onDeleteOperation
    }

    public void cardViewOperation(){
        // get data throgh card view
        Bundle bundle = getIntent().getExtras();

        email = bundle.getString("email");

        realm = Realm.getDefaultInstance();


        RealmResults<Student> students = realm.where(Student.class).equalTo("email",email).findAll();

        if (students != null && students.size()>0) {

            currentStudent = students.first();    /*  *******    */

            edtUDname.setText(currentStudent.getName());
            edtUDemail.setText(currentStudent.getEmail());
            edtUDmobno.setText(currentStudent.getMobileno());
            edtUDaddress.setText(currentStudent.getAddress());
        }

    }

    // update operation
    public void updateOperation(){
        btnUDupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
                    realm.executeTransactionAsync(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {

                            RealmResults<Student> students = realm.where(Student.class).equalTo("email", email).findAll();
                            if (students != null && students.size() > 0) {
                                currentStudent = students.first();


                                currentStudent.setName(edtUDname.getText().toString());
                                // currentStudent.setEmail(edtUDemail.getText().toString());
                                currentStudent.setMobileno(edtUDmobno.getText().toString());
                                currentStudent.setAddress(edtUDaddress.getText().toString());

                            }
                        }
                    }, new Realm.Transaction.OnSuccess() {
                        @Override
                        public void onSuccess() {
                            Toast.makeText(UpdateDelete.this, "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }, new Realm.Transaction.OnError() {
                        @Override
                        public void onError(Throwable error) {
                            Toast.makeText(UpdateDelete.this, "Unable to Update", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    });
                }catch (Exception e){
                    Toast.makeText(UpdateDelete.this, "Unable to Update", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }


    // Delete Operations

    public void deleteOperation(){
        btnUDdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //alertDialogBox();
                AlertDialog.Builder builder = new AlertDialog.Builder(UpdateDelete.this);
                builder.setMessage("Are you sure you want to delete?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                alertDialogBox();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();                          // new things

                            }
                        }).show();
            }
        });

    }

    // alertDialogbox operation

    public void alertDialogBox(){
        try{
            realm.executeTransactionAsync(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    RealmResults<Student> students = realm.where(Student.class).equalTo("email", email).findAll();
                    if (students != null && students.size() > 0) {
                        currentStudent=students.first();
                        currentStudent.deleteFromRealm();  // delete from realm
                    }
                }
            }, new Realm.Transaction.OnSuccess() {
                @Override
                public void onSuccess() {
                    Toast.makeText(UpdateDelete.this, "Data Deleted Successfully", Toast.LENGTH_SHORT).show();
                    finish();

                }
            }, new Realm.Transaction.OnError() {
                @Override
                public void onError(Throwable error) {
                    Toast.makeText(UpdateDelete.this, "Error in delletion", Toast.LENGTH_SHORT).show();
                    finish();

                }
            });
        }catch (Exception e){
            Toast.makeText(UpdateDelete.this, "Error in delletion", Toast.LENGTH_SHORT).show();

        }
    }
}
