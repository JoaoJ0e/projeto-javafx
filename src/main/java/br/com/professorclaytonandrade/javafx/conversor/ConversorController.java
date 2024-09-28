package br.com.professorclaytonandrade.javafx.conversor;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

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

    private double temperaturaMaxima;
    private double temperaturaMinima;

    private String apiLink;

    //TODO: Fazer escolha da caixa de texto mudar a cidade
    //TODO: Mostrar corretamente temp max, min, med e % chuva

    // Métodos
    public void initialize() {
        try {
            // Chama o método para obter a previsão do tempo
            obterPrevisaoDoTempo();
            maxTempLabel.setText(temperaturaMaxima+"");
            minTempLabel.setText(temperaturaMinima+"");

            configEscolhaCidade();


        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void converterTemperatura(javafx.event.ActionEvent actionEvent) {
        try {
            // Converte as Temperaturas
            Double tempCelcius = Double.parseDouble(valorTemperaturaField.getText());
            Double tempFahrenheit = (tempCelcius * 9/5) + 32;
            Double tempKelvin = tempCelcius + 273.15;

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
    }

    private void configEscolhaCidade() {
        // Configurando a opção padrão
        meuChoiceBox.setValue("Cidade");

        // Lidando com a seleção de um item
        meuChoiceBox.setOnAction(event -> {
            String selected = meuChoiceBox.getValue().toString();
            System.out.println("Selecionado: " + selected);
        });
    }

    // Método para atualizar o link da API com base na opção escolhida
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

        try {
            obterPrevisaoDoTempo(apiLink);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    // api do tempo (
    //https://api.open-meteo.com/v1/forecast?latitude=-28.4667&longitude=-49.0069&daily=temperature_2m_max,temperature_2m_min&timezone=America%2FSao_Paulo&forecast_days=1
}
