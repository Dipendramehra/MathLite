# MathLite 📐

A high-performance, lightweight, and 100% offline math rendering engine for Android. 

Traditional math rendering on Android often relies on heavy `WebView` implementations, which are notorious for slow load times, memory leaks, scrolling glitches, and poor offline support. **MathLite** solves this by providing a robust, native-feeling alternative that renders complex equations, calculus problems, and engineering formulas instantly—without the overhead of a web browser component. 

Whether your users are taking a test on a subway or studying in a remote area, your math content will render flawlessly and instantly.



## ✨ Features
* 🚀 **Zero WebView Overhead:** Eliminates the lag and nested-scrolling issues of traditional MathJax/KaTeX WebView wrappers.
* 📶 **100% Offline:** Fully local rendering engine. No internet connection required.
* 🧩 **Universal Compatibility:** Works seamlessly in both traditional XML layouts and modern Jetpack Compose screens.
* 📐 **Comprehensive Parsing:** Supports advanced calculus, linear algebra, geometry, Greek symbols, and complex engineering notation.

---

## 🛠️ Installation

**Step 1.** Add the JitPack repository to your build file. In your root `settings.gradle` or project-level `build.gradle`, add:

```gradle
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") } 
    }
}
```

**Step 2.** Add the dependency to your app-level `build.gradle` file:

```gradle
dependencies {
    implementation("com.github.Dipendramehra:MathLite:1.0.3")
}
```

---

## 🚀 Quick Start: Math Rendering

### 1. Add MathView to your XML
```xml
<com.dipendra.mathlite.MathView
    android:id="@+id/myMathView"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:minHeight="80dp" />
```

### 2. Set your formula in Java / Kotlin
*⚠️ **Important:** When writing LaTeX directly in Java/Kotlin strings, you must double-escape backslashes (`\\`).*

```java
MathView mathView = findViewById(R.id.myMathView);

// Standard Quadratic Formula
String formula = "x = \\frac{-b \\pm \\sqrt{b^2 - 4ac}}{2a}";

mathView.setLatex(formula);
```

---

## 📖 LaTeX Syntax Guide

MathLite uses standard LaTeX formatting. Here is a quick cheat sheet on how to write common mathematical symbols within your Java/Kotlin strings.

### 1. Basic Algebra & Formatting

| Description | Java/Kotlin String Format |
| :--- | :--- |
| **Fractions** | `"\\frac{a}{b}"` |
| **Square Roots** | `"\\sqrt{x+1}"` |
| **Exponents** | `"x^2"` |
| **Subscripts** | `"x_1"` |
| **Bold Text** | `"\\textbf{Title}"` |
| **Normal Text** | `"\\text{(Note)}"` |
| **Line Break** | `"\\\\ \\\\"` |

### 2. Greek Symbols

Simply type the name of the symbol preceded by a double backslash.

* **Alpha:** `"\\alpha"`
* **Beta:** `"\\beta"`
* **Gamma:** `"\\gamma"`
* **Theta:** `"\\theta"`
* **Pi:** `"\\pi"`
* **Sigma (Capital):** `"\\Sigma"`

### 3. Calculus & Advanced Math

| Concept | Java/Kotlin String Format |
| :--- | :--- |
| **Limits** | `"\\lim_{x \\to 0} \\frac{\\sin x}{x} = 1"` |
| **Derivatives** | `"\\frac{d}{dx} (x^n) = n x^{n-1}"` |
| **Integrals** | `"\\int_{a}^{b} x^2 \\, dx"` |
| **Infinity** | `"\\infty"` |
| **Summations** | `"\\sum_{k=1}^{n} k^2"` |
| **Matrices** | `"\\begin{pmatrix} 1 & 2 \\\\ 3 & 4 \\end{pmatrix}"` |

## 📐 Geometry & Diagram Rendering

MathLite isn't just for equations; it includes a built-in `DiagramView` to render clean, offline geometric shapes. By simply passing a JSON configuration, the library will automatically draw the shape and label its vertices!

### 1. Add DiagramView to XML
```xml
<com.dipendra.mathlite.DiagramView
    android:id="@+id/myDiagramView"
    android:layout_width="match_parent"
    android:layout_height="250dp" />
```

### 2. Draw a Triangle (Java / Kotlin)
```java
DiagramView diagramView = findViewById(R.id.myDiagramView);

// Define a Right-Angled Triangle
String triangleJson = "{" +
        "\"type\": \"triangle\"," +
        "\"points\": [" +
        "  {\"x\": 100, \"y\": 200}, " + // Point A (Bottom Left)
        "  {\"x\": 300, \"y\": 200}, " + // Point B (Bottom Right)
        "  {\"x\": 100, \"y\": 50} " +   // Point C (Top Left)
        "]" +
        "}";

diagramView.setDiagram(triangleJson);

