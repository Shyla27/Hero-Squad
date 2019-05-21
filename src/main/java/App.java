import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.setPort;
import static spark.Spark.staticFileLocation;

import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    ProcessBuilder process = new ProcessBuilder();
     Integer port;
     if (process.environment().get("PORT") != null) {
         port = Integer.parseInt(process.environment().get("PORT"));
     } else {
         port = 4567;
     }

    setPort(port);

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      // model.put("heroes", request.session().attribute("heroes"));
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


    get("/heroes", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("heroes", Hero.all());
      model.put("template", "templates/heroes.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/heroes/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Hero hero = Hero.find(Integer.parseInt(request.params(":id")));
      model.put("hero", hero);
      model.put("template", "templates/hero.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


    get("squads/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/squad-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/squads", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("name");
      int size = Integer.parseInt(request.queryParams("size"));
      String cause = request.queryParams("cause");
      new Squad(name, size, cause);
      model.put("template", "templates/squad-success.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/squads", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("squads", Squad.all());
      model.put("template", "templates/squads.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/squads/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Squad squad = Squad.find(Integer.parseInt(request.params(":id")));
      model.put("squad", squad);
      model.put("template", "templates/squad.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("squads/:id/heroes/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Squad squad = Squad.find(Integer.parseInt(request.params(":id")));
      model.put("squad", squad);
      model.put("template", "templates/squad-heroes-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/heroes", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();

      Squad squad = Squad.find(Integer.parseInt(request.queryParams("squadId")));

      String name = request.queryParams("name");
      int age = Integer.parseInt(request.queryParams("age"));
      String power = request.queryParams("power");
      String weakness = request.queryParams("weakness");
      Hero newHero = new Hero(name, age, power, weakness);

      if (Squad.heroAlreadyExists(newHero)) {
        String heroExists = "Hero " + name + " already exists in a squad";
        model.put("heroExists", heroExists);
       }
       else if (squad.getHeroes().size() >= squad.getSize()) {
         String sizeMet = "Squad size already met";
         model.put("sizeMet", sizeMet);
       }
       else{
         squad.addHero(newHero);
       }

      model.put("squad", squad);
      model.put("template", "templates/squad-heroes-success.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

  }
}
