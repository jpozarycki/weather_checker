package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.HandlerWeather;
import model.WeatherFromJson;

import java.io.IOException;

public class Controller {
    private static String cityName;
    @FXML
    private Button Button;

    @FXML
    private TextField CityInput;

    @FXML
    private Label Title;

    @FXML
    private Label tempLabel;

    @FXML
    private Label pressLabel;

    @FXML
    private Label windSpeedLabel;

    @FXML
    private Label tempOutput;

    @FXML
    private Label pressOutput;

    @FXML
    private Label windSpeedOuput;

    @FXML
    void ButtonActionListener(ActionEvent event) throws IOException {
        cityName=CityInput.getText();
        WeatherFromJson weatherFromJson = HandlerWeather.readFromWeb(cityName);
        System.out.println(weatherFromJson.getMain().getTemp()+"-temp");
        System.out.println(weatherFromJson.getMain().getPressure()+"-press");
        System.out.println(weatherFromJson.getWind().getSpeed()+"-wind speed");
        tempOutput.setText(weatherFromJson.getMain().getTemp()+"°C");
        pressOutput.setText(weatherFromJson.getMain().getPressure()+" hPa");
        windSpeedOuput.setText(weatherFromJson.getWind().getSpeed()+" km/h");

    }
}