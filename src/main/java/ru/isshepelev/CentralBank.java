package ru.isshepelev;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.security.Key;
import java.util.*;

public class CentralBank {
    Api api = new Api();
    public void getDate() throws IOException {
        JsonNode date = api.jsonNodeArray().get("Date");
        System.out.println("Текущая дата: " + date.asText());
    }
    public Map<String,Double> mapNameValue() throws IOException {
        Map<String,Double> valuteMap = new HashMap<>();
        List<String> listCharCode = new ArrayList<>();

        JsonNode valute = api.jsonNodeArray().get("Valute");
        Iterator<String> charCodeJson = valute.fieldNames();
        while (charCodeJson.hasNext()){
            String charCode = charCodeJson.next();
            listCharCode.add(charCode);
        }


        for (String jsonListCharCode : listCharCode){
            JsonNode valuteCharCode = valute.get(jsonListCharCode);
            JsonNode nameValueJson = valuteCharCode.get("Name");
            JsonNode valueJson = valuteCharCode.get("Value");
            String nameValue = nameValueJson.textValue();
            double value = valueJson.asDouble();
            valuteMap.put(nameValue,value);
        }
        return valuteMap;
    }

    public Map<String,Integer> mapNameNominal() throws IOException {
        Map<String,Integer> nominalMap = new HashMap<>();
        List<String> listCharCode = new ArrayList<>();

        JsonNode valute = api.jsonNodeArray().get("Valute");
        Iterator<String> charCodeJson = valute.fieldNames();
        while (charCodeJson.hasNext()){
            String charCode = charCodeJson.next();
            listCharCode.add(charCode);
        }


        for (String jsonListCharCode : listCharCode){
            JsonNode valuteCharCode = valute.get(jsonListCharCode);
            JsonNode nameValueJson = valuteCharCode.get("Name");
            JsonNode nominalJson = valuteCharCode.get("Nominal");
            String nameValue = nameValueJson.textValue();
            int nominal = nominalJson.asInt();
            nominalMap.put(nameValue, nominal);
        }

        return nominalMap;
    }



    public void infoOfOneValue(String name) throws IOException {
        String currency = name;
        Double value = mapNameValue().get(currency);
        int nominal = mapNameNominal().get(currency);
        if (value != null) {
            value = value / nominal;
            System.out.println("Один " + currency + " оценивается в: " + value + " рублей");
        } else {
            System.out.println("Значение для <" + currency + "> не найдено.");
        }
    }

    public void convertingRussianIntoForeign(String name, double amountOfCurrency) throws IOException { // калькулятор для перевода русской валюты в иностранную
        String currency = name;
        double amount = amountOfCurrency;
        Double value = mapNameValue().get(currency);
        int nominal = mapNameNominal().get(currency);
        if (value != null) {
            value = value / nominal;
            amount = amount / value;
            System.out.println("на " + amountOfCurrency + " рублей, вы сможете купить: " + amount + " " + currency);
        } else {
            System.out.println("Значение для <" + currency + "> не найдено.");
        }
    }
}
