package controllers;

import akka.actor.ActorSystem;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;
import scala.concurrent.ExecutionContextExecutor;
import scala.concurrent.duration.Duration;
import services.Counter;
import services.PersonalCenterService;
import views.html.index;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;

@Singleton
public class PersonalCenterController extends Controller {
    private final ActorSystem actorSystem;
    private final ExecutionContextExecutor exec;

    private final PersonalCenterService personalCenterService;


    @Inject
    public PersonalCenterController(ActorSystem actorSystem,
                                    ExecutionContextExecutor exec,
                                    PersonalCenterService personalCenterService) {
        this.actorSystem = actorSystem;
        this.exec = exec;

        this.personalCenterService = personalCenterService;
    }

    /**
     *
     * RESTful API:
     *
     * @param userId
     * @return
     */
    public Result findUserInfoById(long userId) {
        return ok(Json.toJson(personalCenterService.findUserInfoById(userId)));
    }

}
