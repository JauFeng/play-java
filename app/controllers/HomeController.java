package controllers;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Query;
import models.Test;
import play.mvc.*;

import views.html.*;

import java.util.List;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {


    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
        return ok(index.render("Your new application is ready."));
    }

    public Result func() {
        List<Test> testList = Test.find.all();
        List<Test> test30000 = Test.find.where()
                .ilike("user_name", "test30000").findList();



        return ok(test30000.stream().toString());
    }

}