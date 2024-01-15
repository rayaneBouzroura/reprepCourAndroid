plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.tp1gui"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.tp1gui"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    //enable binding
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    //implement recyclerView
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    //implement drawerLayout
    implementation("androidx.drawerlayout:drawerlayout:1.2.0")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.5.0")
    implementation("com.squareup.retrofit2:converter-gson:2.5.0")
    implementation("com.squareup.retrofit2:converter-scalars:2.5.0")

    //OkHttp3 login interceptor
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.1")
    //jitpackio DTO classes
    implementation("com.github.departement-info-cem:KickMyB-Library:31d81e9843")

    //special package to fix the guava truc
    implementation("com.google.guava:listenablefuture:9999.0-empty-to-avoid-conflict-with-guava")

    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}