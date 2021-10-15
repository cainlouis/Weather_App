# Raspberry Pi Weather App

## Team Members:

- Nael Louis (1934115)
- Rodrigo Alfas Rivas (1910674)
- Daniel Lam (1932789)

## Description

This project consists of a Weather Dashboard that displays the Current Forecast or 7 Days Forecast of a selected city using the OpenWeather API.
It is made using Java and JavaFX while implementing many libraries to parse through the JSON from the API (Jackson API) and to build the tiles of the project (TilesFX).

The project consists of six tiles:

1. Clock Tile (displays current time and date)
2. Input City ChoiceBox Tile (allows user to select either Current Weather or 7 Day Forecast and the city)
3. Exit Tile (exits the program)
4. Current Weather Tile (displays Current Weather of selected city)
5. Temperature and Humidity Tile (uses DHT11 to continously print temperature and humidity of circuit)
6. Update Tile (updates the Weather Forecast to city - if 7 Day Forecast is chosen, display 7 Day Forecast scene)

## How To Build

The project contains many necessary dependancies that are crucial for the project. You must run build and run this project on a Raspberry Pi.

1. Install at least Java JDK 13 [(link)](https://download.bell-sw.com/java/13/bellsoft-jdk13-linux-arm32-vfp-hflt.deb) onto your Raspberry Pi.
2. Clone the project and open up Netbeans.
3. Verify that all the dependancies have been installed (Jackson API, JavaFX, TilesFX, etc)
4. Verify that your circuit is properly set up and connected to the Pi.
5. Build the project and Run it. (warning: may take some time to run and load JavaFX tiles and sense temperature)

Enjoy!

## Setting Up the circuit

To set up the circuit, you will need:

- Raspberry Pi (with 40 GPIO) x1
- GPIO Expansion Board & Ribbon Cable x1
- Breadboard x1
- Jumper Wire x4
- DHT11 sensor x1
- Resistor 10kΩ x1

[Diagram](https://imgur.com/a/6zULmj7)
