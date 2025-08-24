# systembars

[![](https://jitpack.io/v/deshkaustubh/systembars.svg)](https://jitpack.io/#deshkaustubh/systembars)

**systembars** is a simple, Compose-first Android library that helps you easily manage your app’s system bars (status bar and navigation bar) with modern Material 3 support.  
If you want to control system bar colors or icon appearance in your Compose screens, this library is for you!

---

## 🚀 Getting Started

### 1. Add the JitPack repository

First, tell Gradle where to find this library by adding JitPack to your project’s repository list.

#### **If you use Kotlin DSL (`settings.gradle.kts`):**

```kotlin
dependencyResolutionManagement {
    repositories {
        // ... your other repositories
        maven { url = uri("https://jitpack.io") }
    }
}
```

#### **If you use Groovy (`settings.gradle`):**

```groovy
dependencyResolutionManagement {
    repositories {
        // ... your other repositories
        maven { url 'https://jitpack.io' }
    }
}
```

---

### 2. Add the dependency

Then, add the library to your app or module’s `build.gradle`:

**Kotlin DSL (`build.gradle.kts`):**
```kotlin
dependencies {
    implementation("com.github.deshkaustubh:systembars:v1.0.2")
}
```

**Groovy (`build.gradle`):**
```groovy
dependencies {
    implementation 'com.github.deshkaustubh:systembars:v1.0.2'
}
```

---

## 🎨 Usage Example

Here’s how you can use `SystemBars` in your Jetpack Compose screen:

```kotlin
import tech.kaustubhdeshpande.systembars.SystemBars

@Composable
fun MyScreen() {
    SystemBars(
        statusBarColor = Color.Transparent,
        navigationBarColor = Color.Black,
        darkIcons = true
    )

    // ...rest of your UI
}
```

- **statusBarColor:** The color you want for the status bar background.
- **navigationBarColor:** The color for the navigation bar background.
- **darkIcons:** Set to `true` for dark icons (on light backgrounds), or `false` for light icons (on dark backgrounds).

---

## 📋 Requirements

- **Min SDK:** 24
- **Compile SDK:** 36
- **Kotlin:** 2.0.21
- **Jetpack Compose Compiler:** 1.5.15 or newer
- **Material 3:** 1.2.1 or newer

---

## ⚠️ Notes & Troubleshooting

- **JitPack Not Working?**  
  Make sure you have both the JitPack repository and the latest version in your Gradle files.
- **AndroidManifest Warning?**  
  If you see a warning about `package="..."` in your `systembars/src/main/AndroidManifest.xml`, you can safely remove that line. The `namespace` in `build.gradle.kts` takes care of it.
- **Version matching:**  
  Make sure the version in your `dependencies` matches the [latest JitPack release](https://jitpack.io/#deshkaustubh/systembars).

---

## 🙋‍♂️ Need Help or Want to Contribute?

- Found a bug, want a new feature, or want to contribute?  
  [Open an issue or pull request!](https://github.com/deshkaustubh/systembars/issues)
- Questions? [Ask here](https://github.com/deshkaustubh/systembars/discussions) or contact the maintainer.

---

## 📄 License

This library is open source under the MIT License. See [LICENSE](LICENSE) for details.

---

## 📦 JitPack Reference

You can always find the latest build and version instructions on  
[https://jitpack.io/#deshkaustubh/systembars](https://jitpack.io/#deshkaustubh/systembars)

---

Happy coding! 🎉