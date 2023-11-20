@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    namespace = "com.module.remote"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {

    implementation(libs.coreKtx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidxTestExtJunit)
    androidTestImplementation(libs.espressoCore)

    //region dagger
    implementation(libs.daggerAndroidSupport)
    implementation(libs.javaxInject)
    kapt(libs.daggerCompiler)
    kapt(libs.daggerAndroidProcessor)
    //endregion

    val gsonVersion = "2.10.1"
    val retrofitVersion = "2.9.0"
    val okHttpVersion = "4.11.0"
    //region retrofit
    api("com.google.code.gson:gson:$gsonVersion")
    api("com.squareup.retrofit2:retrofit:$retrofitVersion")
    api("com.squareup.retrofit2:converter-gson:$retrofitVersion")
    api("com.squareup.okhttp3:okhttp:$okHttpVersion")
    api("com.squareup.okhttp3:logging-interceptor:$okHttpVersion")
    api("com.squareup.okhttp3:okhttp-urlconnection:$okHttpVersion")
    //endregion

    api(project(":module_data"))
}