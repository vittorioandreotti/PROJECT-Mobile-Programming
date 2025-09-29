# PROJECT Mobile Programming 📱

This university project consists of the development of two mobile applications: one Android Native and the other Cross-Platform. The goal was to design, implement, and compare different approaches to mobile development, highlighting strengths, limitations, and performance differences between the two solutions.

---

## Table of Contents 🧭
- [Overview](#overview-ℹ️)
- [Goals & Learning Outcomes](#goals--learning-outcomes-🎯)
- [Architecture & Design Patterns](#architecture-&-design-patterns-🏗️)
- [Technologies & Tools](#technologies--tools-🧰)
- [Repository Structure](#repository-structure-🗂️)
- [Build & Run (Android)](#build--run-android-🚀)
- [Build & Run (Xamarin)](#build--run-xamarin-🪟)

---

## Overview ℹ️

This project contains two parallel implementations of the same mobile application:

1. A **native Android** project (`AndroidMobileProgrammingProject/`) built with Android Studio.
2. A **Xamarin** solution (`XamarinApp/`) targeting cross‑platform development (Android/iOS).

---

## Goals & Learning Outcomes 🎯

- Understand lifecycle differences between Android native and Xamarin.
- Practice **UI layer separation** and **MVP**.
- Explore distribution (APK generation) and configuration.
- Document and reflect (the included ZIP likely contains a written report in Italian).

---

## Architecture & Design Patterns 🏗️

Used Approaches:
- **Layered Architecture**: UI / Domain / Data separation.
- **MVVM (Model–View–ViewModel)**
- **Repository Pattern**: Abstract data sources (remote / local).
- **Dependency Injection**: Dagger/Hilt (Android) or built‑in service containers (Xamarin).
- **Observer / Reactive Paradigms**: LiveData / Flow (Android) vs. INotifyPropertyChanged (Xamarin).

Advantages of this comparative setup:
- Encourages identifying **platform-specific friction** (navigation, threading, lifecycle).
- Helps evaluate **code reuse strategies** (e.g., extract pure logic).
- Reinforces **separation of concerns** early.

---

## Technologies & Tools 🧰

Native Android (Gradle project):
- Gradle Wrapper (`gradlew`, `gradlew.bat`)
- Java
- Android SDK / Build Tools

Xamarin:
- .NET / C#
- Xamarin.Android (and optionally Xamarin.Forms or MAUI if migrated)
- NuGet package management

General / Other:
- Git & GitHub for version control
- IDEs: Android Studio / IntelliJ (`.idea` folders present), Visual Studio for Xamarin
- Documentation & Distribution: ZIP bundle with report + APKs

---

## Repository Structure 🗂️

```
/
├── AndroidMobileProgrammingProject/      # Native Android Gradle project
│   ├── build.gradle
│   ├── settings.gradle
│   ├── gradle.properties
│   ├── gradlew / gradlew.bat             # Wrapper scripts
│   ├── app/                              
│   └── functions/                        
├── XamarinApp/                           # Cross-platform (Xamarin) app solution
├── Relazione e APK compilati.zip         # Report (Italian) + compiled APK(s)
└── .idea/                                
```

---

## Build & Run (Android) 🚀

Clone repository:
```bash
git clone https://github.com/vittorioandreotti/PROJECT-Mobile-Programming.git
cd PROJECT-Mobile-Programming/AndroidMobileProgrammingProject
```

Build (debug):
```bash
./gradlew assembleDebug
```

Install on connected device/emulator:
```bash
./gradlew installDebug
```

Clean:
```bash
./gradlew clean
```
---

## Build & Run (Xamarin) 🪟

Open the `XamarinApp` solution in **Visual Studio** (Windows or Mac):

1. Restore NuGet packages (should happen automatically).
2. Select the target (emulator or device).
3. Build & deploy (Start / F5).

If using Xamarin.Forms:
- Shared UI lives in a `.NET Standard` project.
If platform-specific only:
- Logic is duplicated — consider refactoring into a shared library.
