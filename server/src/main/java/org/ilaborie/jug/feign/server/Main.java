package org.ilaborie.jug.feign.server;

import static spark.Spark.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.ilaborie.jug.feign.server.model.Cat;
import org.ilaborie.jug.feign.server.service.CatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import spark.Request;
import spark.Response;
import spark.ResponseTransformer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    private static final CatService SERVICE = new CatService();
    //private static final String JSON_MIME_TYPE = "application/json; charset=utf-8";
    private static final ResponseTransformer JSON_TRANSFORMER = new JsonTransformer();

    public static void main(String[] args) {
        port(8000);

        before(Main::logRequest);

        get("/cat",Main::findAllCats);
        get("/cat/:id", Main::findById);
        post("/cat", Main::createCat);
        put("/cat/:id", Main::updateCat);
        delete("/cat/:id", Main::deleteCat);

        exception(IllegalArgumentException.class, (e, request, response) -> error(response, 400, e));
        exception(NoSuchElementException.class, (e, request, response) -> error(response, 404, e));
    }

    private static void logRequest(Request request, Response response) {
        LOGGER.info("{} {}", request.requestMethod(), request.uri());
    }

    private static String findAllCats(Request request, Response response) throws Exception{
        return JSON_TRANSFORMER.render(SERVICE.findAll());
    }
    private static String findById(Request request, Response response) throws Exception{
        String id = request.params(":id");
        Optional<Cat> catOptional = SERVICE.findById(id);
        if (catOptional.isPresent()) {
            return JSON_TRANSFORMER.render(catOptional.get());
        }
        response.status(404);
        return null;
    }
    private static String createCat(Request request, Response response) throws Exception{
        Cat cat = parseCat(request.bodyAsBytes());
        return JSON_TRANSFORMER.render(SERVICE.create(cat.getName(), cat.getRace()));
    }
    private static String updateCat(Request request, Response response) throws Exception{

        String id = request.params(":id");
        Cat cat = parseCat(request.bodyAsBytes());
        return JSON_TRANSFORMER.render(SERVICE.update(id, cat));
    }

    private static String deleteCat(Request request, Response response) throws Exception {
        String userAgent = request.headers("Role");
        if (userAgent==null) {
            throw new IllegalArgumentException("Require a Role");
        }
        String id = request.params(":id");
        return JSON_TRANSFORMER.render(SERVICE.delete(id));
    }

    private static Cat parseCat(byte[] bytes) {
        Gson gson = new GsonBuilder().create();
        try (
                ByteArrayInputStream input = new ByteArrayInputStream(bytes);
                Reader reader = new InputStreamReader(input)) {
            return gson.fromJson(reader, Cat.class);
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }

    private static void error(Response response, int status, Exception e) {
        response.status(status);
        response.body(e.getMessage());
    }
}
