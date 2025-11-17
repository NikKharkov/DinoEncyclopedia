plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.devtools.ksp)
    alias(libs.plugins.navigation.safeargs)
}

android {
    namespace = "com.example.dinoencyclopedia"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.dinoencyclopedia"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.swiperefreshlayout)
    // Lottie
    implementation(libs.lottie)
    // Coil
    implementation(libs.coil)
    implementation(libs.coil.network.okhttp)
    // Onboarding dots
    implementation(libs.dotsindicator)
    // Navigation
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    // Koin
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.navigation)
    // RSSParser (for getting news about paleontology)
    implementation(libs.rssparser)
    // Room
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.rxjava3)
    ksp(libs.androidx.room.compiler)
    //Retrofit + OkHTTP
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.adapter.rxjava3)
    implementation(libs.okhttp)
    // RxJava
    implementation(libs.rxjava)
    implementation(libs.rxandroid)
    implementation(libs.kotlinx.coroutines.rx3)
    implementation(libs.rxkotlin)
    // WorkManager
    implementation(libs.androidx.work.runtime.ktx)
    implementation(libs.androidx.work.rxjava3)
    // MapBox
    implementation(libs.mapbox)
}