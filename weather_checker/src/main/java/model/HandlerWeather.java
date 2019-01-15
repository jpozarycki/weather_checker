package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.scene.control.Alert;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;

public class HandlerWeather {

    private static String URLaddress = "http://api.openweathermap.org/data/2.5/weather?q=";
    private static String appId= "&units=metric&appid=c400ea9290e1612b76168fdb952b0182";

    public static WeatherFromJson readFromWeb(String cityName) throws IOException {
        String data = "";
        URL url = new URL(URLaddress+cityName+appId);
        InputStream is = url.openStream();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = br.readLine()) != null) {
                data += line;
                System.out.println(line);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("The city "+cityName+" was not found");
            alert.setContentText("Please, try again");
            alert.show();
            throw new MalformedURLException("URL is malformed!!");
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException();
        } catch (RuntimeException e){
            e.printStackTrace();
            throw new RuntimeException();
        }


        Gson gson = new GsonBuilder().create();
        WeatherFromJson weatherFromJson = gson.fromJson(data,WeatherFromJson.class);
        is.close();
        return weatherFromJson;
    }
}
