# Garbage Incorporation Rogue Game

A Java-based terminal game developed as part of a software design and development project. The game is set in an abandoned moon facility where players manage resources, explore the environment, interact with objects and creatures, and respond to dynamically changing environmental conditions.

The project focuses heavily on object-oriented design, extensibility, testing, and the application of software engineering principles such as SOLID, DRY and KISS.

---

## Features

### Player and Game World

* Explore a grid-based moon facility.
* Manage player health, inventory and credits.
* Interact with different terrain types, items, creatures and environmental hazards.
* Perform actions based on the objects and entities present in the game world.

### Crafting System

* Craft items using different crafting stations.
* Recipes define the required crafting components and resulting items.
* Different crafting stations provide different recipes.
* Crafting can trigger additional effects such as:

  * Burning the player
  * Spawning creatures
  * Teleporting nearby actors
  * Removing inventory items
  * Affecting surrounding terrain

### Quota and Economy System

* Manage company credits and quotas through a dedicated quota management system.
* Track progression through game cycles.
* Support purchasing, selling and depositing items.
* Separate capabilities allow objects to support only the behaviours they require.

### Cuttable Objects

Certain objects in the game can be interacted with using cutting actions.

A `Cuttable` abstraction allows different types of game entities to provide their own cutting behaviour while allowing the cutting action to operate independently of their concrete implementations.

### Dynamic Weather System

The game includes a real-world weather system powered by the OpenWeather API.

The weather system supports:

* Sunny weather
* Rainy weather
* Snowy weather

Weather dynamically affects the game world. For example:

* Sunny weather can create fires and remove puddles.
* Rainy weather can create puddles and extinguish fires.
* Snowy weather can create ice and freeze actors.
* Weather-reactive objects can change their behaviour depending on the current weather.
* Shops can change their available stock based on weather conditions.
* Certain objects can melt or transform in response to weather.

The API request is dynamically influenced by the current game state rather than using a fixed location.

---

## Technology Stack

* **Java**
* **Maven**
* **JUnit**
* **JSON**
* **OpenWeather API**
* Object-oriented design principles and design patterns

The project uses the provided FIT2099 game engine as the underlying game framework.

---

# Getting Started

## Prerequisites

Install the following before running the project:

* Java 17 or later
* Maven
* Git
* Internet connection if using the weather system

You can verify your installations with:

```bash
java -version
mvn -version
git --version
```

---

## Clone the Repository

```bash
git clone <REPOSITORY_URL>
cd <PROJECT_DIRECTORY>
```

---

## Configure the Weather API

The weather system requires an OpenWeather API key.

The API key is **not stored in the source code**. Instead, the application reads it from the `MY_API_KEY` environment variable.

### macOS / Linux

```bash
export MY_API_KEY="YOUR_API_KEY_HERE"
```

Verify that it has been configured:

```bash
echo $MY_API_KEY
```

### Windows PowerShell

```powershell
$env:MY_API_KEY="YOUR_API_KEY_HERE"
```

Verify:

```powershell
echo $env:MY_API_KEY
```

Do not commit the API key to Git.

---

# Running the Project

From the project root:

```bash
mvn clean compile
```

Then run the game using the project's configured Maven execution command:

```bash
mvn exec:java
```

Alternatively, the project can be opened in IntelliJ IDEA and run using the provided `Application` entry point.

---

# Running Tests

Run the automated test suite with:

```bash
mvn test
```

The tests cover important game behaviours and provide regression protection when extending the system.

---

# Architecture

The project follows an object-oriented architecture where game behaviours are separated into abstractions and concrete implementations.

Some of the major abstractions include:

### Weather

Represents the current weather state.

Concrete implementations include:

* `Sunny`
* `Rainy`
* `Snowy`

Each weather type is responsible for its own environmental behaviour.

### WeatherReactive

Represents entities that respond to changes in weather.

Examples include:

* `Ice`
* `IceCream`
* `VendingMachine`

This allows weather-dependent behaviour to be added to entities without requiring the weather manager to know every concrete entity type.

### WeatherEffect

Represents reusable effects triggered by weather.

Examples include:

* `BurnEffect`
* `FreezeEffect`
* `ChangeTerrainEffect`
* `ChangeStockEffect`

These effects encapsulate individual behaviours and can be reused by different parts of the weather system.

### WeatherManager

`WeatherManager` manages the current weather state and handles communication with the external weather API.

Its responsibilities include:

1. Sending the API request.
2. Parsing the JSON response.
3. Mapping API weather conditions to game weather objects.
4. Registering weather-reactive entities.
5. Notifying registered entities when weather changes.

### WeatherMap

`WeatherMap` integrates the weather system into the game loop.

It is responsible for:

* Maintaining a `WeatherManager`.
* Registering weather-reactive entities.
* Tracking the weather update countdown.
* Calculating dynamic coordinates from the current game state.
* Triggering weather updates at the appropriate time.

This separates map/game-loop responsibilities from API and weather-state management.

---

# Design Principles

The project places particular emphasis on maintainable object-oriented design.

### Single Responsibility Principle

Classes are designed around focused responsibilities. For example, `WeatherManager` handles weather retrieval and state management, while individual `Weather` classes handle their own environmental behaviour.

### Open-Closed Principle

New weather types, weather effects and reactive entities can be introduced through existing abstractions without requiring large changes to unrelated systems.

### Interface Segregation Principle

Small capability interfaces such as `Cuttable`, `Sellable`, `Depositable` and `WeatherReactive` allow objects to implement only behaviours that are relevant to them.

### Dependency Inversion Principle

Higher-level components depend on abstractions rather than concrete implementations. For example, weather-related systems operate through the `Weather` and `WeatherReactive` abstractions.

### DRY

Shared behaviour is extracted into reusable abstractions and effect classes rather than being repeatedly implemented across concrete classes.

### KISS

The system avoids unnecessary complexity where a simpler abstraction is sufficient, while introducing additional abstractions where they provide meaningful extensibility.

---

# Project Structure

A simplified structure of the project is:

```text
src/
├── main/
│   └── java/
│       ├── game/
│       │   ├── actions/
│       │   ├── actors/
│       │   ├── items/
│       │   ├── statuses/
│       │   ├── terrain/
│       │   └── weather/
│       │       ├── effects/
│       │       └── weather/
│       │
│       └── ...
│
└── test/
    └── java/
        └── ...
```

The project separates game entities, actions, terrain, statuses and weather functionality into dedicated packages.

---

# External API

The weather system uses the OpenWeather API to retrieve real-world weather conditions.

The request is dynamically generated using latitude and longitude derived from the current game state:

```text
https://api.openweathermap.org/data/2.5/weather?lat=<latitude>&lon=<longitude>&appid=<API_KEY>
```

The returned JSON is parsed and mapped to one of the game's weather implementations.

The API therefore has a meaningful impact on the game world rather than simply displaying external information to the player.

---

# Security

API credentials are loaded through environment variables rather than being hard-coded into the repository.

```java
System.getenv("MY_API_KEY");
```

The API key should never be committed to Git or included directly in source files.

---

# Project Context

This project was developed as part of a university software engineering assignment focused on applying object-oriented design principles to an existing Java game engine.

The implementation involved extending the existing game with multiple independent features while maintaining compatibility with the engine and existing functionality.

The project provided practical experience with:

* Object-oriented programming
* Java
* Interfaces and abstract classes
* Polymorphism
* SOLID principles
* Design patterns
* API integration
* JSON parsing
* Automated testing
* Git and collaborative development
* Software architecture and maintainability
