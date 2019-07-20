package realmhelper;

import java.util.List;

import io.realm.Realm;
import model.Student;

public class StudentRealmHelper {

    public static List<Student> getAllStudent() {
        try {
            Realm realm = Realm.getDefaultInstance();
            return realm.where(Student.class).findAll();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        // we use with try $ catch block
    /*finally {
            Realm realm = Realm.getDefaultInstance();
            return realm.where(Student.class).findAll();
        } */


      /*  public int delete_Details(Student student){

            int delete = realm.delete("student","stdphno=?",new String[]{String.valueOf(studentModel.getStdphno())});
            db.close();
            return delete;
        }*/

    }
}
