import com.google.gson.Gson;

import java.security.Key;

public class Test {

    class My {
        private String name;
        private String password;
    }

    public static void main(String[] args) {
        Gson gson = new Gson();


        My my = gson.fromJson("{name=haha, password=11111}", My.class);

        System.out.println(my.name);

    }
}
