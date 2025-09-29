# PROJECT Mobile Programming 📱

This university project consists of the development of two mobile applications: one Android Native and the other Cross-Platform. The goal was to design, implement, and compare different approaches to mobile development, highlighting strengths, limitations, and performance differences between the two solutions.

---

## Table of Contents 🧭
- [Overview](#overview-ℹ️)
- [Goals & Learning Outcomes](#goals--learning-outcomes-🎯)
- [Features (Planned / Implemented)](#features-planned--implemented-✨)
- [Architecture & Design Patterns](#architecture--design-patterns-🏗️)
- [Technologies & Tools](#technologies--tools-🧰)
- [Repository Structure](#repository-structure-🗂️)
- [Build & Run (Android)](#build--run-android-🚀)
- [Build & Run (Xamarin)](#build--run-xamarin-🪟)
- [Testing](#testing-🧪)
- [Roadmap](#roadmap-🗺️)
- [Contributing](#contributing-🤝)
- [License](#license-📜)
- [Acknowledgements](#acknowledgements-🙏)

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

> You may want to list the minimum SDK, target SDK, and main application ID here (extract from `app/build.gradle`).

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

---

## Testing 🧪

Planned or Recommended:
- Unit tests (business logic)
- Instrumentation tests (Android: Espresso)
- UI tests (Xamarin: Xamarin.UITest or MAUI migration path)
- Static analysis (Android Lint, detekt / ktlint if Kotlin)

Add a `tests/` directory or dedicated modules as the project evolves.

---

## Roadmap 🗺️

| Phase | Focus | Status |
|-------|-------|--------|
| 1 | Set up native Android project | ✅ |
| 2 | Add Xamarin parallel solution | ✅ |
| 3 | Align features across both | ⏳ |
| 4 | Introduce architecture refactor (MVVM) | ⏳ |
| 5 | Add automated tests | ⏳ |
| 6 | Continuous Integration (GitHub Actions) | ⏳ |
| 7 | Documentation polishing / English report | ⏳ |
| 8 | (Optional) Migrate Xamarin -> .NET MAUI | 🔍 |

---

## Contributing 🤝

This appears to be a personal / academic project.  
If you accept contributions:

1. Fork the repository
2. Create a feature branch: `git checkout -b feature/your-feature`
3. Commit changes with clear messages
4. Open a Pull Request describing intent + screenshots (if UI related)

---

## License 📜

No license file is currently present.  
Until a license (e.g., MIT, Apache 2.0) is added, the default is “all rights reserved”.  
Add a `LICENSE` file to clarify usage permissions.

---

## Acknowledgements 🙏

- Course / academic instructors (add names if appropriate)
- Official Android & Xamarin documentation
- Open-source libraries (list once confirmed)

---

> Feel free to request an Italian summary or a localized README variant.

Happy coding! 🚀
