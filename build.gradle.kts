// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {

    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(libs.comAndroidTools)
        classpath(libs.kotlinGradlePlugin)
        classpath(libs.androidxNavigationPlugin)
    }
}
