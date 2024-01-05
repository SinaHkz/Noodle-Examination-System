package noodle;

import com.example.noodleexaminationsystem.DataBase;
import com.example.noodleexaminationsystem.User.Gender;
import com.example.noodleexaminationsystem.User.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDate;

public class NoodleTest {

     private User user1;

    @BeforeEach
    void setUp(){
        user1 = User.signUp("user1" ,"password1" , "setti" , "saeidi","jfojaw", null, LocalDate.of(1999 , 12,12) , "FEMALE");
    }
    @Test
    public void signuptestsuccess(){
        User user = User.signUp("use2" , "password1" , "setti" , "saeidi","jfojaw", null , LocalDate.of(1999 , 11 ,11) , "male");
        Assertions.assertEquals(DataBase.getUsers().get("use2"), user);
    }

    @Test
    public void signuptestfail(){
        User user2 = User.signUp("user1" ,"password1" , "setti" , "saeidi","jfojaw", null, LocalDate.of(1999 , 12,12) , "FEMALE");
        Assertions.assertEquals(null, user2);
    }





}
