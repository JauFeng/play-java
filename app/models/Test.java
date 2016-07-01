package models;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "test")
public class Test extends Model {

    String userName;

    @Constraints.Min(11)
    int age;

    public static Finder<String, Test> find = new Finder<>(Test.class);


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}


