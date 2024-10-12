# Conversor de Monedas

Este es un conversor de monedas básico escrito en Java que permite convertir entre diferentes monedas utilizando una API de tasas de cambio. El programa ofrece las siguientes conversiones:

1. Dólar a Peso Argentino
2. Peso Argentino a Dólar
3. Dólar a Peso Brasileño
4. Peso Brasileño a Dólar
5. Dólar a Peso Colombiano
6. Peso Colombiano a Dólar

## Funcionalidad

El programa obtiene las tasas de cambio en tiempo real mediante la API [ExchangeRate-API](https://www.exchangerate-api.com/). Los usuarios pueden elegir una opción del menú para seleccionar el tipo de conversión que desean realizar y luego ingresar el monto a convertir.

## Requisitos

- **Java 11 o superior**: Este proyecto usa la clase `HttpClient` para realizar las solicitudes HTTP.
- **Gson**: Biblioteca de Google para procesar JSON. Asegúrate de incluirla en tu proyecto.

### Instalación de dependencias

Si estás usando Maven, agrega la siguiente dependencia en tu archivo `pom.xml` para la biblioteca Gson:

```xml
<dependency>
    <groupId>com.google.code.gson</groupId>
    <artifactId>gson</artifactId>
    <version>2.8.9</version>
</dependency>
