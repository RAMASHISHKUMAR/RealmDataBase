package model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Student   extends RealmObject {

    private String name;
    @PrimaryKey
    private String email;
    private String mobileno;
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", mobileno='" + mobileno + '\'' +
                ", address='" + address + '\'' +
                '}';
    }


  /*  public static List<Student> getAllStudent() {
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
        }
    }*/

}