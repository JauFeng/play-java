package controllers;

import akka.actor.ActorSystem;
import play.mvc.Controller;
import scala.concurrent.ExecutionContextExecutor;

import javax.inject.Inject;

public class BaseController extends Controller {

    private final ActorSystem actorSystem;
    private final ExecutionContextExecutor exec;

    @Inject
    public BaseController(ActorSystem actorSystem,
                          ExecutionContextExecutor exec) {
        this.actorSystem = actorSystem;
        this.exec = exec;
    }
}
