plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("maven-publish") // 1. Added the publishing plugin
}

android {
    namespace = "com.dipendra.mathlite"
    compileSdk = 34

    defaultConfig {
        minSdk = 27

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    // 2. Tell Android Gradle Plugin to prepare the release build for publishing
    // (This was a great addition by you!)
    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

// 3. Configure the Maven Publication for JitPack
afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                // Grabs the release build prepared in the android block above
                from(components["release"])

                // CORRECTED: This must be a package name, not a URL!
                groupId = "com.github.Dipendramehra"
                artifactId = "mathlite"
                version = "1.0.0"
            }
        }
    }
}