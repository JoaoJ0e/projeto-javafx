package conversor;

import org.json.JSONArray;
import org.json.JSONObject;

import mains.fxml.FXML;
import mains.scene.control.Button;
import mains.scene.control.ChoiceBox;
import mains.scene.control.Label;
import mains.scene.control.TextField;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.rmi.UnexpectedException;

public class ConversorController {

    @FXML
    private Button converterTemperaturaButton;

    @FXML
    private Label resultadoCelsiusLabel;

    @FXML
    private Label resultadoFahrenheitLabel;

    @FXML
    private Label resultadoKelvinLabel;

    @FXML
    private TextField valorTemperaturaField;

    @FXML
    private Label erroLabel;

    @FXML
    private ChoiceBox meuChoiceBox;

    // Previsão do tempo
    @FXML
    private Label maxTempLabel;

    @FXML
    private Label minTempLabel;

    @FXML
    private Label medTempLabel;

    @FXML
    private Label maxTempLabelF;

    @FXML
    private Label minTempLabelF;

    @FXML
    private Label medTempLabelF;

    @FXML
    private Label maxTempLabelK;

    @FXML
    private Label minTempLabelK;

    @FXML
    private Label medTempLabelK;

    private double temperaturaMaxima;
    private double temperaturaMinima;
    private double temperaturaMaximaF;
    private double temperaturaMinimaF;
    private double temperaturaMaximaK;
    private double temperaturaMinimaK;

    private String apiLink;

    // Métodos
    public void initialize() {
        try {
            configEscolhaCidade();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public Double converteCelciusParaFahrenheit(Double tempCelcius) {
        Double tempFahrenheit = (tempCelcius * 9/5) + 32;
        return tempFahrenheit;
    }
    public Double converteCelciusParaKelvin(Double tempCelcius) {
        Double tempKelvin = tempCelcius + 273.15;
        return tempKelvin;
    }

    public void converterTemperatura(javafx.event.ActionEvent actionEvent) {
        try {
            // Converte as Temperaturas
            Double tempCelcius = Double.parseDouble(valorTemperaturaField.getText());
            Double tempFahrenheit = converteCelciusParaFahrenheit(tempCelcius);
            Double tempKelvin = converteCelciusParaKelvin(tempCelcius);

            // Mostra na Tela
            resultadoCelsiusLabel.setText(tempCelcius+"");
            resultadoFahrenheitLabel.setText(tempFahrenheit+"");
            resultadoKelvinLabel.setText(tempKelvin+"");
        } catch (Exception e) {
            limpaCampos();
            erroLabel.setText("BOTA UM NUMERO, BURRO!");
        }
    }
    public void limpaCampos() {
        resultadoCelsiusLabel.setText("");
        resultadoKelvinLabel.setText("");
        resultadoFahrenheitLabel.setText("");
    }

    public void obterPrevisaoDoTempo(String apiLink) throws IOException, InterruptedException {
        String url = apiLink;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String jsonString = response.body();
        JSONObject jsonObject = new JSONObject(jsonString);

        JSONObject daily = jsonObject.getJSONObject("daily");

        JSONArray temperatureMaxArray = daily.getJSONArray("temperature_2m_max");
        JSONArray temperatureMinArray = daily.getJSONArray("temperature_2m_min");

        temperaturaMaxima = temperatureMaxArray.getDouble(0);
        temperaturaMinima = temperatureMinArray.getDouble(0);

        atualizaCamposPrevisao();
    }
    private void atualizaCamposPrevisao() {

        // °C
        maxTempLabel.setText(temperaturaMaxima + "");
        minTempLabel.setText(temperaturaMinima + "");
        medTempLabel.setText(calculaMediaArredondada(temperaturaMaxima, temperaturaMinima) + "");

        // °F
        temperaturaMaximaF = converteCelciusParaFahrenheit(temperaturaMaxima);
        temperaturaMinimaF = converteCelciusParaFahrenheit(temperaturaMinima);
        maxTempLabelF.setText(temperaturaMaximaF + "");
        minTempLabelF.setText(temperaturaMinimaF + "");
        medTempLabelF.setText(converteCelciusParaFahrenheit(calculaMediaArredondada(temperaturaMaxima, temperaturaMinima)) + ""); // Média em °F

        // °K
        temperaturaMaximaK = converteCelciusParaKelvin(temperaturaMaxima);
        temperaturaMinimaK = converteCelciusParaKelvin(temperaturaMinima);
        maxTempLabelK.setText(temperaturaMaximaK + "");
        minTempLabelK.setText(temperaturaMinimaK + "");
        medTempLabelK.setText(converteCelciusParaKelvin(calculaMediaArredondada(temperaturaMaxima, temperaturaMinima)) + ""); // Média em °K
    }


    private Double calculaMediaArredondada(Double numero1, Double numero2) {

        double media = (numero1 + numero2) / 2.0;
        String mediaArrendodada = String.format("%.2f", media);
        mediaArrendodada = mediaArrendodada.replace(',', '.');
        return Double.parseDouble(mediaArrendodada);
    }
    private void configEscolhaCidade() {
        // Configurando a opção padrão
        meuChoiceBox.setValue("Escolha uma cidade");

        // Lidando com a seleção de um item
        meuChoiceBox.setOnAction(event -> {
            String selected = meuChoiceBox.getValue().toString();
            atualizarLinkDaAPI(selected);
            try {
                obterPrevisaoDoTempo(apiLink);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }
    private void atualizarLinkDaAPI(String opcao) {
        switch (opcao) {
            case "Capivari":
                apiLink = "https://api.open-meteo.com/v1/forecast?latitude=-28.4447&longitude=-48.9578&daily=temperature_2m_max,temperature_2m_min,precipitation_probability_max&forecast_days=1";
                break;
            case "Jaguaruna":
                apiLink = "https://api.open-meteo.com/v1/forecast?latitude=-28.6214&longitude=-49.0253&daily=temperature_2m_max,temperature_2m_min,precipitation_probability_max&forecast_days=1";
                break;
            case "Sangão":
                apiLink = "https://api.open-meteo.com/v1/forecast?latitude=-28.7333&longitude=-49.4&daily=temperature_2m_max,temperature_2m_min,precipitation_probability_max&forecast_days=1";
                break;
            default:
                apiLink = "https://api.open-meteo.com/v1/forecast?latitude=-28.4667&longitude=-49.0069&daily=temperature_2m_max,temperature_2m_min,precipitation_probability_max&forecast_days=1";
                break;
        }
    }
}
