package com.example.yeschef;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.yeschef.jsonObjects.User;
import com.example.yeschef.jsonObjects.Comment;
import com.example.yeschef.jsonObjects.Recipe;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;

public class YesChefRepository {
    public void getAllUsers(ExecutorService srv, List<User> users) {

        srv.submit(() -> {
            try {
                URL url = new URL("http://192.168.1.3:8080/yeschef/users");

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                BufferedReader reader
                        = new BufferedReader(
                        new InputStreamReader(
                                conn.getInputStream()));

                StringBuilder buffer = new StringBuilder();
                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                JSONArray arr = new JSONArray(buffer.toString());

                for (int i = 0; i < arr.length(); i++) {
                    JSONObject current = arr.getJSONObject(i);

                    String id = current.getString("id");
                    String username = current.getString("username");
                    String password = current.getString("password");
                    LocalDateTime accountCreateTime = LocalDateTime.parse(current.getString("accountCreateTime"));
                    JSONArray commentsJson = current.getJSONArray("postedComments");
                    List<Comment> postedComments = new ArrayList<>();
                    for (int j = 0; j < commentsJson.length(); j++) {
                        JSONObject commentJson = commentsJson.getJSONObject(j);
                        Comment comment = new Comment(
                                commentJson.getString("id"),
                                commentJson.getString("content"),
                                LocalDateTime.parse(commentJson.getString("commentPostTime")),
                                commentJson.getInt("rating"),
                                username // Assuming the username for the comment is the same as the user's username
                        );
                        postedComments.add(comment);
                    }
                    User user = new User(id, username, password, accountCreateTime, postedComments);
                    users.add(user);
                }
            } catch (MalformedURLException e) {
                Log.e("DEV", e.getMessage());
            } catch (IOException e) {
                Log.e("DEV", e.getMessage());
            } catch (JSONException e) {
                Log.e("DEV", e.getMessage());
            }
        });
    }

   public void getAllRecipes(ExecutorService srv, Handler uiHandler){

       srv.submit(()->{

           HttpURLConnection connection = null;
           InputStream inputStream = null;
           ArrayList<Recipe> recipes = new ArrayList<>();
           try {

               URL url = new URL("http://192.168.1.3:8080/yeschef/recipes/popular");
               connection = (HttpURLConnection) url.openConnection();
               connection.setRequestMethod("GET");
               inputStream = connection.getInputStream();
               Scanner scanner = new Scanner(inputStream);
               StringBuilder builder = new StringBuilder();

               while (scanner.hasNextLine()) {
                   builder.append(scanner.nextLine());
               }

               JSONArray jsonArray = new JSONArray(builder.toString());
               for (int i = 0; i < jsonArray.length(); i++) {
                   JSONObject jsonObject = jsonArray.getJSONObject(i);
                   Recipe recipe = new Recipe(
                           jsonObject.getString("id"),
                           jsonObject.getString("title"),
                           jsonObject.getString("description"),
                           jsonObject.getString("type"),
                           LocalDateTime.parse(jsonObject.getString("postTime")),
                           (float) jsonObject.getDouble("ratingOverall"),
                           jsonObject.getJSONObject("creator").getString("username")
                   );
                   recipes.add(recipe);
               }

               Message msg = new Message();
               msg.obj = recipes;
               uiHandler.sendMessage(msg);

           } catch (MalformedURLException e) {
               Log.e("DEV",e.getMessage());
           } catch (IOException e) {
               Log.e("DEV",e.getMessage());
           } catch (JSONException e) {
               Log.e("DEV",e.getMessage());
           }
       });
   }

    public void getRecipeById(ExecutorService srv, Handler uiHandler, String recipeId){

        srv.submit(()->{

            HttpURLConnection connection = null;
            InputStream inputStream = null;
            Recipe recipe;
            try {

                URL url = new URL("http://192.168.1.3:8080/yeschef/recipes/searchid/" + recipeId);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                inputStream = connection.getInputStream();
                Scanner scanner = new Scanner(inputStream);
                StringBuilder builder = new StringBuilder();

                while (scanner.hasNextLine()) {
                    builder.append(scanner.nextLine());
                }

                JSONObject jsonObject = new JSONObject(builder.toString());

                recipe = new Recipe(
                        jsonObject.getString("id"),
                        jsonObject.getString("title"),
                        jsonObject.getString("description"),
                        jsonObject.getString("type"),
                        LocalDateTime.parse(jsonObject.getString("postTime")),
                        (float) jsonObject.getDouble("ratingOverall"),
                        jsonObject.getJSONObject("creator").getString("username")
                );

                Message msg = new Message();
                msg.obj = recipe;
                uiHandler.sendMessage(msg);

            } catch (MalformedURLException e) {
                Log.e("DEV",e.getMessage());
            } catch (IOException e) {
                Log.e("DEV",e.getMessage());
            } catch (JSONException e) {
                Log.e("DEV",e.getMessage());
            }
        });
    }

    public void getCommentsOfRecipe(ExecutorService srv, Handler uiHandler, String recipeId){

        srv.submit(()->{

            HttpURLConnection connection = null;
            InputStream inputStream = null;
            ArrayList<Comment> comments = new ArrayList<>();
            try {

                URL url = new URL("http://192.168.1.3:8080/yeschef/recipes/" + recipeId + "/comments");
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                inputStream = connection.getInputStream();
                Scanner scanner = new Scanner(inputStream);
                StringBuilder builder = new StringBuilder();

                while (scanner.hasNextLine()) {
                    builder.append(scanner.nextLine());
                }

                JSONArray jsonArray = new JSONArray(builder.toString());
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Comment comment = new Comment(
                            jsonObject.getString("id"),
                            jsonObject.getString("content"),
                            LocalDateTime.parse(jsonObject.getString("commentPostTime")),
                            jsonObject.getInt("rating"),
                            jsonObject.getString("creatorUserName")
                    );
                    comments.add(comment);
                }

                Message msg = new Message();
                msg.obj = comments;
                uiHandler.sendMessage(msg);

            } catch (MalformedURLException e) {
                Log.e("DEV",e.getMessage());
            } catch (IOException e) {
                Log.e("DEV",e.getMessage());
            } catch (JSONException e) {
                Log.e("DEV",e.getMessage());
            }
        });
    }

    public void postComment(ExecutorService srv, final String content, final String rating, final String userId, final String recipeId) {

        srv.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    // Create the JSON object
                    JSONObject postData = new JSONObject();
                    postData.put("content", content);
                    postData.put("rating", rating);
                    postData.put("userId", userId);
                    postData.put("recipeId", recipeId);

                    // URL for the POST request
                    URL url = new URL("http://192.168.1.3:8080/yeschef/recipes/postcomment");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json; utf-8");
                    conn.setRequestProperty("Accept", "application/json");
                    conn.setDoOutput(true);

                    // Send the JSON data
                    try(OutputStream os = conn.getOutputStream()) {
                        byte[] input = postData.toString().getBytes("utf-8");
                        os.write(input, 0, input.length);
                    }

                    // Read the response
                    try(BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"))) {
                        StringBuilder response = new StringBuilder();
                        String responseLine = null;
                        while ((responseLine = br.readLine()) != null) {
                            response.append(responseLine.trim());
                        }
                    }

                    conn.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void postRecipe(ExecutorService srv, String title, String description, String id) {

        srv.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    // Create the JSON object
                    JSONObject postData = new JSONObject();
                    postData.put("title", title);
                    postData.put("description", description);
                    postData.put("type", "Lunch");
                    postData.put("creatorId", id);

                    // URL for the POST request
                    URL url = new URL("http://192.168.1.3:8080/yeschef/recipes/create");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json; utf-8");
                    conn.setRequestProperty("Accept", "application/json");
                    conn.setDoOutput(true);

                    // Send the JSON data
                    try(OutputStream os = conn.getOutputStream()) {
                        byte[] input = postData.toString().getBytes("utf-8");
                        os.write(input, 0, input.length);
                    }

                    // Read the response
                    try(BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"))) {
                        StringBuilder response = new StringBuilder();
                        String responseLine = null;
                        while ((responseLine = br.readLine()) != null) {
                            response.append(responseLine.trim());
                        }
                    }
                    conn.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void signUser(ExecutorService srv, String username, String password) {

        srv.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    // Create the JSON object
                    JSONObject postData = new JSONObject();
                    postData.put("username", username);
                    postData.put("password", password);

                    // URL for the POST request
                    URL url = new URL("http://192.168.1.3:8080/yeschef/user/signup");
                    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json; utf-8");
                    conn.setRequestProperty("Accept", "application/json");
                    conn.setDoOutput(true);

                    // Send the JSON data
                    try(OutputStream os = conn.getOutputStream()) {
                        byte[] input = postData.toString().getBytes("utf-8");
                        os.write(input, 0, input.length);
                    }

                    // Read the response
                    try(BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"))) {
                        StringBuilder response = new StringBuilder();
                        String responseLine = null;
                        while ((responseLine = br.readLine()) != null) {
                            response.append(responseLine.trim());
                        }
                    }
                    conn.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
