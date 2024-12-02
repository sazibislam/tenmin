# Jetpack Compose Navigation Example
![](screenshots/sample1.jpg)

## Requirements
- Android Studio Koala or Higher

## Tech Stack
- Jetpack Compose
- Navigation Compose
- Compose Destination
- Clean Architecture
- Ktor
- Koin DI

## Features:
- Home Screen:
    - Displays weather based on the device's current location.
    - Shows temperature, weather condition, and icons.
- Search Feature:
   - Search for a Zila.
    - Update the weather on the Home Screen based on the selected Zila.

## Development Guidelines:
 # Architecture:

    Use Clean Architecture with layers:
    Data Layer: Handles API and JSON.
    Domain Layer: Business logic (weather fetching).
    Presentation Layer: UI with Jetpack Compose.
    Dependency Injection:

    Use Koin for dependency management (e.g., ViewModel, HttpClient).
# UI Framework:

    Leverage Jetpack Compose with Material Design.
    Implement dynamic lists (e.g., LazyColumn).
# Navigation:

    Use Compose Navigation for screen transitions:
    Home Screen → Search Screen → Back to Home Screen with updated data.

# Implementation:
    Data Layer: Fetch weather from OpenWeather API and parse Zila JSON.
    UI: Display weather, search for Zila, and update the Home Screen.
    State Management: Use ViewModel to manage UI state and weather data.

## Articles
- [Open Api Weather](https://openweathermap.org/api)
- [Clean Architecture](https://medium.com/@sazib/android-mvvm-with-clean-code-dda2bf78bb64)
- [Json from Asset](https://medium.com/@sazib/read-json-file-from-assets-346f624faf92)
- [Koin DI](https://insert-koin.io/)
- [KTOR](https://ktor.io/)

## Branches
- [master](https://github.com/sazibislam/tenmin) - Basic Compose Navigation

## License
```
Copyright 2024 Saiful Sazib

Licensed under the Apache License, Version 2.0 (the "License");

you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
