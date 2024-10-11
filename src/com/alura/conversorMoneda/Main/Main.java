package com.alura.conversorMoneda.Main;

import com.alura.conversorMoneda.Querys.QueryCurrency;
import com.alura.conversorMoneda.Records.Currency;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        QueryCurrency query = new QueryCurrency();
        Currency currencyCodes = query.showCurrencyCodes();
        ArrayList<ArrayList<String>> listCountrys = currencyCodes.supported_codes();

        int baseCurrency;
        int targetCurrency;
        double amount;
        int option = 0;

        while(option != 9){
            System.out.println("""
                    ************************************************************
                    ********** BIENVENIDO AL CONVERSOR DE MONEDAS **********
                    ************************************************************
                    
                    ********** Elija la operación que desea realizar **********
                    1 - Hacer la conversión de moneda
                    2 - Revisar el historial de conversiones
                    9 - Salir
                    """);
            option = sc.nextInt();
            if(option == 1){
                System.out.println("""
                        **************************************************
                        LISTA DE PAISES DISPONIBLES
                        """);
                for(int i = 0; i < listCountrys.size(); i++){
                    System.out.printf("%d) %s - %s\n", i, listCountrys.get(i).get(0), listCountrys.get(i).get(1));
                }
                System.out.println("**************************************************\n");

                System.out.println("Elija desde que moneda desea realizar la conversión ingresando el número");
                baseCurrency = sc.nextInt();

                System.out.printf("Ha seleccionado la moneda: %s - %s\n\n",
                        listCountrys.get(baseCurrency).get(0), listCountrys.get(baseCurrency).get(1));

                System.out.println("Elija la moneda a la que desea convertir");
                targetCurrency = sc.nextInt();

                System.out.printf("Ha seleccionado la moneda: %s - %s\n\n",
                        listCountrys.get(targetCurrency).get(0), listCountrys.get(targetCurrency).get(1));

                System.out.printf("""
                **************************************************
                Su conversión de moneda será de %s a %s
                **************************************************\n
                Elija la cantidad de dinero que desea convertir
                """, listCountrys.get(baseCurrency).get(0), listCountrys.get(targetCurrency).get(0));
                amount = sc.nextDouble();

                Currency currency = query.searchCurrency(listCountrys.get(baseCurrency).get(0),
                        listCountrys.get(targetCurrency).get(0), amount);

                System.out.printf("""
                        **********************************************************************
                        La conversión de $%f %s a %s dio como resultado $%f %s
                        **********************************************************************\n
                        """, amount, currency.base_code(), currency.target_code(), currency.conversion_result(),
                        currency.target_code());

            } else if (option == 2) {

            } else if (option == 9) {
                System.out.println("Gracias por utilizar nuestro conversor de monedas. Que tenga un buen día");
            } else {
                System.out.println("Ingrese una opción valida");
            }
        }
    }
}
