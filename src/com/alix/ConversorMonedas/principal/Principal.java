package com.alix.ConversorMonedas.principal;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {
    // Método principal
    public static void main(String[] args) throws Exception {
        int opcion = 0;
        String menu = """
                ********************
                Bienvenido/a al conversor de moneda :)
                \n1) Dolar => Peso argentino
                2) Peso argentino => Dolar
                3) Dolar => Peso brasileño
                4) Peso brasileño => Dolar
                5) Dolar => Peso Colombiano
                6) Peso Colombiano => Dolar
                7) Salir
                Elija una opcion valida:
                """;

        Scanner teclado = new Scanner(System.in);
        while (opcion != 7) {
            System.out.println(menu);

            try {
                opcion = teclado.nextInt();

                if (opcion >= 1 && opcion <= 6) {
                    System.out.println("Escribe la cantidad en la moneda seleccionada: ");
                    double cantidad = teclado.nextDouble();

                    // Obtener la tasa de cambio según la opción seleccionada
                    String monedaBase = "USD";
                    String monedaDestino = "";

                    switch (opcion) {
                        case 1 -> monedaDestino = "ARS";  // Dólar a Peso Argentino
                        case 2 -> {
                            monedaBase = "ARS";  // Peso Argentino a Dólar
                            monedaDestino = "USD";
                        }
                        case 3 -> monedaDestino = "BRL";  // Dólar a Peso Brasileño
                        case 4 -> {
                            monedaBase = "BRL";  // Peso Brasileño a Dólar
                            monedaDestino = "USD";
                        }
                        case 5 -> monedaDestino = "COP";  // Dólar a Peso Colombiano
                        case 6 -> {
                            monedaBase = "COP";  // Peso Colombiano a Dólar
                            monedaDestino = "USD";
                        }
                    }

                    // Hacer la solicitud a la API de tasas de cambio
                    String direccion = "https://v6.exchangerate-api.com/v6/6645174d00bd6ed618ceb364/latest/" + monedaBase;
                    HttpClient client = HttpClient.newHttpClient();
                    HttpRequest request = HttpRequest.newBuilder()
                            .uri(URI.create(direccion))
                            .build();
                    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                    // Procesar la respuesta JSON
                    Gson gson = new Gson();
                    JsonObject jsonObject = gson.fromJson(response.body(), JsonObject.class);
                    double tasaCambio = jsonObject
                            .getAsJsonObject("conversion_rates")
                            .get(monedaDestino)
                            .getAsDouble();

                    // Realizar la conversión
                    double resultado = cantidad * tasaCambio;

                    System.out.println("El valor de la moneda de " + monedaBase + " a " + monedaDestino + " es: " + tasaCambio);
                    System.out.println("El valor total de la conversión es: " + resultado);
                } else if (opcion == 7) {
                    System.out.println("Saliendo del programa...");
                } else {
                    System.out.println("Opción no válida, por favor elige una opción válida.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Por favor, ingresa un número válido.");
                teclado.next(); // Limpia el buffer de entrada incorrecto
            }
        }
        teclado.close();
    }
}
