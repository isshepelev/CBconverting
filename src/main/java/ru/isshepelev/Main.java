package ru.isshepelev;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        CentralBank centralBank = new CentralBank();
        System.out.println("-------------------------------------------\n" +
                "[1] - выход\n" +
                "[2] - узнать актуальную дату курса валют\n" +
                "[3] - вывести все курсы имеющихся валют\n" +
                "[4] - вывести определенный курс для валюты\n" +
                "[5] - перевод рублей в другую валюту\n" +
                "-------------------------------------------");

        Scanner scanner = new Scanner(System.in);
        int numberInterface = scanner.nextInt();
        while (numberInterface != 1){
            if (numberInterface == 2){
                centralBank.getDate();
                System.out.println("-------------------------------------------\n" +
                        "[1] - выход\n" +
                        "[2] - узнать актуальную дату курса валют\n" +
                        "[3] - вывести все курсы имеющихся валют\n" +
                        "[4] - вывести определенный курс для валюты\n" +
                        "[5] - перевод рублей в другую валюту\n" +
                        "-------------------------------------------");
                numberInterface = scanner.nextInt();
            }else if (numberInterface == 3){
                for (Map.Entry<String, Double> entry : centralBank.mapNameValue().entrySet()) {
                    System.out.println(entry.getKey() + ": " + entry.getValue());
                }
                System.out.println("-------------------------------------------\n" +
                        "[1] - выход\n" +
                        "[2] - узнать актуальную дату курса валют\n" +
                        "[3] - вывести все курсы имеющихся валют\n" +
                        "[4] - вывести определенный курс для валюты\n" +
                        "[5] - перевод рублей в другую валюту\n" +
                        "-------------------------------------------");
                numberInterface = scanner.nextInt();
            }else if (numberInterface == 4){
                scanner.nextLine();
                System.out.println("введите название конктерной валюты");
                String name = scanner.nextLine();
                centralBank.infoOfOneValue(name);
                System.out.println("-------------------------------------------\n" +
                        "[1] - выход\n" +
                        "[2] - узнать актуальную дату курса валют\n" +
                        "[3] - вывести все курсы имеющихся валют\n" +
                        "[4] - вывести определенный курс для валюты\n" +
                        "[5] - перевод рублей в другую валюту\n" +
                        "-------------------------------------------");
                numberInterface = scanner.nextInt();
            }else if (numberInterface == 5){
                scanner.nextLine();
                System.out.println("введите название валюты");
                String name = scanner.nextLine();
                System.out.println("введите кол-во руйблей которые хотите перевести в " + name);
                double amountOfCurrency = scanner.nextInt();
                centralBank.convertingRussianIntoForeign(name,amountOfCurrency);
                System.out.println("-------------------------------------------\n" +
                        "[1] - выход\n" +
                        "[2] - узнать актуальную дату курса валют\n" +
                        "[3] - вывести все курсы имеющихся валют\n" +
                        "[4] - вывести определенный курс для валюты\n" +
                        "[5] - перевод рублей в другую валюту\n" +
                        "-------------------------------------------");
                numberInterface = scanner.nextInt();
            }
        }

    }
}
