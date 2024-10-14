package com.alura.conversorMoneda.Querys;

import com.alura.conversorMoneda.Records.Currency;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class QueryCurrency {
    private String apiKey = "c6b78493c77b8545bd8c3d70";

    public Currency searchCurrency(String base_code, String target_code, double amount){
        //https://v6.exchangerate-api.com/v6/YOUR-API-KEY/pair/EUR/GBP/AMOUNT
        String direction = "https://v6.exchangerate-api.com/v6/" + this.apiKey + "/pair/"
                + base_code + "/" + target_code + "/" + amount;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(direction))
                .build();

        try {
            HttpResponse<String> response = null;
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), Currency.class);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public Currency showCurrencyCodes(){
        //https://v6.exchangerate-api.com/v6/YOUR-API-KEY/codes
        String direction = "https://v6.exchangerate-api.com/v6/" + this.apiKey + "/codes";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(direction))
                .build();

        try {
            HttpResponse<String> response = null;
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), Currency.class);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
