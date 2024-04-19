plugins {
   alias(libs.plugins.androidApplication)
   alias(libs.plugins.jetbrainsKotlinAndroid)
   id("com.google.devtools.ksp")
   alias(libs.plugins.ktlint)
}

android {
   namespace = "com.mc.todolist"
   compileSdk = 34

   defaultConfig {
      applicationId = "com.mc.todolist"
      minSdk = 26
      targetSdk = 34
      versionCode = 1
      versionName = "1.0"

      testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
      vectorDrawables {
         useSupportLibrary = true
      }
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
   buildFeatures {
      compose = true
   }
   composeOptions {
      kotlinCompilerExtensionVersion = "1.5.1"
   }
   packaging {
      resources {
         excludes += "/META-INF/{AL2.0,LGPL2.1}"
      }
   }
}

dependencies {

   implementation(libs.bundles.androidx)
   implementation(libs.bundles.compose)
   implementation(libs.bundles.composeDebug)
   implementation(libs.bundles.network)
   implementation(libs.bundles.coroutines)
   implementation(libs.bundles.koin)
   implementation(libs.bundles.room)
   ksp(libs.roomCompiler)
   implementation(libs.timber)
   testImplementation(libs.bundles.unittests)
}
