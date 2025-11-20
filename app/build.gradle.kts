plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.app_movies"
    compileSdk = 34 // Usaré 34 como ejemplo, ajusta a tu versión actual si es diferente

    defaultConfig {
        applicationId = "com.example.app_movies"
        minSdk = 24
        targetSdk = 34 // Ajusta a tu versión actual
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
        compose = true
    }
}

dependencies {
    // Dependencias de Kotlin y AndroidX
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)

    // Dependencias de Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)

    // Material Design 3 (Usamos la referencia del BOM)
    // Dejo la referencia al libs.androidx.compose.material3 que es más estándar.
    implementation(libs.androidx.compose.material3)

    // 🚀 SOLUCIÓN 1: Dependencia de Íconos Extendidos con string directo para evitar el error de TOML.
    // Usamos la versión 1.6.8 que es compatible con la mayoría de los proyectos Compose recientes.
    implementation("androidx.compose.material:material-icons-extended:1.6.8")

    // ⚠️ SOLUCIÓN 2: Eliminamos la referencia ambigua que podría causar conflicto
    // Si tu libs.androidx.material3 no funciona, la eliminamos y confiamos en el BOM.
    // Asegúrate de que libs.androidx.compose.material3 esté correctamente definido si lo mantienes.
    // La línea implementation(libs.androidx.material3) fue eliminada aquí por seguridad.

    // Dependencias de Prueba
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}