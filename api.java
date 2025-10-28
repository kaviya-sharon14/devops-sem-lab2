package com.example;

import static spark.Spark.*;
import com.google.gson.*;
import java.util.*;

public class todoserver {
    static class Todo {
        int id;
        String task;
        boolean done;

        Todo(int id, String task) {
            this.id = id;
            this.task = task;
            this.done = false;
        }
    }

    static List<Todo> todos = new ArrayList<>();
    static Gson gson = new Gson();

    public static void main(String[] args) {
        port(8080);
        staticFiles.location("/public");

        get("/todos", (req, res) -> {
            res.type("application/json");
            return gson.toJson(todos);
        });

        post("/todos", (req, res) -> {
            Map data = gson.fromJson(req.body(), Map.class);
            String task = (String) data.get("task");
            Todo t = new Todo(todos.size() + 1, task);
            todos.add(t);
            res.type("application/json");
            return gson.toJson(t);
        });

        put("/todos/:id", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            for (Todo t : todos) {
                if (t.id == id) {
                    t.done = !t.done;
                    break;
                }
            }
            return gson.toJson(todos);
        });

        delete("/todos/:id", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            todos.removeIf(t -> t.id == id);
            return gson.toJson(todos);
        });
    }
}
