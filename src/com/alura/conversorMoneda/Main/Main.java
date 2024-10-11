package com.alura.conversorMoneda.Main;

import com.alura.conversorMoneda.Querys.QueryCurrency;
import com.alura.conversorMoneda.Records.Currency;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        QueryCurrency query = new QueryCurrency();
        int baseCurrency;
        int targetCurrency;
        double amount;
        int option = 0;



        while(option != 9){
            System.out.println("""
                    ********** Elija la operación que desea realiza r**********
                    1 - Hacer la conversión de moneda
                    2 - Revisar el historial de conversiones
                    9 - Salir
                    """);
            option = sc.nextInt();
            if(option == 1){
                Currency currencyCodes = query.showCurrencyCodes();

                ArrayList<ArrayList<String>> listCountrys = currencyCodes.supported_codes();

                for(int i = 0; i < listCountrys.size(); i++){
                    System.out.printf("%d) %s - %s\n", i, listCountrys.get(i).get(0), listCountrys.get(i).get(1));
                }

                System.out.println("Elija desde que moneda desea realizar la conversión ingresando el número");
                baseCurrency = sc.nextInt();

                System.out.println("Elija la moneda a la que desea convertir");
                targetCurrency = sc.nextInt();

                System.out.println("Elija la cantidad de dinero que desea convertir");
                amount = sc.nextDouble();

                Currency currency = query.searchCurrency(listCountrys.get(baseCurrency).get(0),
                        listCountrys.get(targetCurrency).get(0), amount);
                System.out.println(currency);

            } else if (option == 2) {

            } else if (option == 9) {
                System.out.println("Gracias por utilizar nuestro conversor de monedas. Que tenga un buen día");
            } else {
                System.out.println("Ingrese una opción valida");
            }
        }
    }
}
