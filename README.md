# Android Clean Arquitecture
Simple proyect to showcase clean arquitecture and and modern android development skills.

[![Kotlin Version](https://img.shields.io/badge/Kotlin-1.4.1-blue.svg)](https://kotlinlang.org)
[![AGP](https://img.shields.io/badge/AGP-4.1.0-blue?style=flat)](https://developer.android.com/studio/releases/gradle-plugin)
[![Gradle](https://img.shields.io/badge/Gradle-6.5.0-blue?style=flat)](https://gradle.org)

[![CircleCI](https://circleci.com/gh/gonzaloguzzardi/android-clean-arquitecture/tree/main.svg?style=shield)](https://circleci.com/gh/gonzaloguzzardi/android-clean-arquitecture/main/teesloane-patch-5)

Require Android Studio 4.+

## Project characteristics
* 100% [Kotlin](https://kotlinlang.org/)
* Modern architecture (Clean Architecture, Model-View-ViewModel)
* [Android Jetpack](https://developer.android.com/jetpack)
* Single-activity architecture ([Navigation component](https://developer.android.com/guide/navigation/navigation-getting-started))
* Reactive UI
* Testing (Unit, UI) --> PENDING
* Static analysis tools
* Material design

## Tech-stack

<img src="assets/app-gif.gif" width="280" align="right" hspace="20">

Min API level is set to [`21`](https://android-arsenal.com/api?level=21)

* Tech-stack
    * [Kotlin](https://kotlinlang.org/) + [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - perform background operations
    * [Retrofit](https://square.github.io/retrofit/) - networking
    * [Jetpack](https://developer.android.com/jetpack)
        * [Navigation](https://developer.android.com/topic/libraries/architecture/navigation/) - deal with whole in-app navigation
        * [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - notify views about database change
        * [Room](https://developer.android.com/topic/libraries/architecture/room) - deal with SQLite database interactions
        * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - store and manage UI-related data in a lifecycle conscious way
* Architecture
    * Clean Architecture
    * MVVM (presentation layer)
    * [Android Architecture components](https://developer.android.com/topic/libraries/architecture) ([ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel), [LiveData](https://developer.android.com/topic/libraries/architecture/livedata), [Navigation](https://developer.android.com/jetpack/androidx/releases/navigation), [SafeArgs](https://developer.android.com/guide/navigation/navigation-pass-data#Safe-args) plugin)
* Tests - Pending (Next)
    * [Unit Tests](https://en.wikipedia.org/wiki/Unit_testing) ([JUnit](https://junit.org/junit4/))
* Gradle
    * Plugins ([Ktlint](https://github.com/JLLeitschuh/ktlint-gradle), [Detekt](https://github.com/arturbosch/detekt#with-gradle), [SafeArgs](https://developer.android.com/guide/navigation/navigation-pass-data#Safe-args))
    
## Architecture

<p align="center">
  <img src="https://github.com/gonzaloguzzardi/android-clean-arquitecture/blob/main/assets/clean-arquitecture-image.png?raw=true" width="450" />
</p>

#### Presentation layer

This layer is closest to what the user sees on the screen. The `presentation` layer uses `MVVM` using `ViewModel` to preserve data on configuration changes)

Presentation layer depends on Domain and Data layers.

Components:
- **Views (Fragment)** - presents data on the screen and pass user interactions to View Model
- **ViewModel** - dispatches (through `LiveData`) state changes to the view and deals with user interactions
- **ViewState** - common state for a single view

#### Domain layer

This is the core layer of the application. Notice that the `domain` layer is independent of any other layers. This allows to make domain models and business logic independent from other layers.

Components:
- **UseCase** - contains business logic
- **DomainModel** - defines the core structure of the data that will be used within the application
- **Repository interface** - required to keep the `domain` layer independent from the `data layer`

#### Data layer

Manages application data and exposes these data sources as repositories to the `domain` layer. Used to retrieve data from the internet uing retrofit and caching this data locally in a SQLite database using room.

Components:
- **Repository** - Handles data and exposes it to the `domain` layer.
- **Mapper** - maps `data model` to `domain model` (to keep `domain` layer independent from the `data` layer). Here mappers are implemented as functions in Model classes.
- **RetrofitService** - defines a set of API endpoints.
- **DataModel** - defines the structure of the data retrieved from the network or databases and contains annotations

## Getting started
To open this project follow any of the next steps:
### Android Studio 4.1

1. Android Studio -> File -> New -> From Version control -> Git
2. Enter `https://github.com/gonzaloguzzardi/android-clean-arquitecture.git` into URL field

### Command-line + Android Studio 4.1

1. Run `git clone https://github.com/gonzaloguzzardi/android-clean-arquitecture.git`
2. Android Studio -> File -> Open
