package com.alura.conversorMoneda.Main;

import com.alura.conversorMoneda.Querys.QueryCurrency;
import com.alura.conversorMoneda.Records.Currency;
import com.alura.conversorMoneda.Records.HistoryCurrency;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int baseCurrency = 0;
        int targetCurrency = 0;
        double amount = 0;
        int option = 0;

        Scanner sc = new Scanner(System.in);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        QueryCurrency query = new QueryCurrency();
        Currency currencyCodes = query.showCurrencyCodes();
        ArrayList<ArrayList<String>> listCountrys = currencyCodes.supported_codes();
        ArrayList<HistoryCurrency> listHistoryCurrency = new ArrayList<>();

        try{
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
                sc = new Scanner(System.in);
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
                    sc = new Scanner(System.in);

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
                        \n**********************************************************************
                        La conversión de $%f %s a %s dio como resultado $%f %s
                        **********************************************************************\n
                        """, amount, currency.base_code(), currency.target_code(), currency.conversion_result(),
                            currency.target_code());

                    HistoryCurrency historyCurrency = new HistoryCurrency(currency.base_code(), amount,
                            currency.target_code(), currency.conversion_result());
                    listHistoryCurrency.add(historyCurrency);

                    sc = new Scanner(System.in);
                } else if (option == 2) {
                    if(listHistoryCurrency.size() == 0){
                        System.out.println("No se ha encontrado alguna conversión\n");
                    } else{
                        System.out.println("\n****************************** HISTORIAL DE CONVERSIONES ******************************");
                        for(int i = 0; i < listHistoryCurrency.size(); i++){
                            System.out.printf("""
                                    ****** CONVERSIÓN N°%d ******
                                    Moneda para convertir: %s
                                    Cantidad a convertir: $%f %s
                                    
                                    Moneda a convertir: %s
                                    Cantidad convertida: $%f %s\n\n
                                    """, i, listHistoryCurrency.get(i).base_code(), listHistoryCurrency.get(i).amountToConvert(),
                                    listHistoryCurrency.get(i).base_code(), listHistoryCurrency.get(i).target_code(),
                                    listHistoryCurrency.get(i).amountConverted(), listHistoryCurrency.get(i).target_code());
                        }
                    }

                } else if (option == 9) {
                    System.out.println("Gracias por utilizar nuestro conversor de monedas. Que tenga un buen día");
                } else {
                    System.out.println("Ingrese una opción valida");
                }
            }
            FileWriter writer = new FileWriter("historyCurrency.json");
            writer.write(gson.toJson(listHistoryCurrency));
            writer.close();
        } catch (InputMismatchException e){
            System.out.println("Debe ingresar un numero");
        } catch(IndexOutOfBoundsException e){
            System.out.printf("Elija una moneda dentro del rango de 0 hasta %d\n", listCountrys.size() - 1);
        } catch(IOException e){
            System.out.println("Ha ocurrido un error con el historial");
        } catch(RuntimeException e){
            System.out.println("Algo ha salido mal, intentelo nuevamente");
        } finally{
            System.out.println("Fin de la ejecución");
        }
    }
}
