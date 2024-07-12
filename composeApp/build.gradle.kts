import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.BitcodeEmbeddingMode
import org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
    kotlin("plugin.serialization") version "1.9.10"
    kotlin("native.cocoapods")
    alias(libs.plugins.com.google.devtools.ksp)



}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    cocoapods {
        version = "1.0.0"
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "15.4"
        podfile = project.file("../iosApp/Podfile") // why doesn't it load the cocoapods from the iosApp podfile?
        framework {
            baseName = "composeApp"
            isStatic = true
            @OptIn(ExperimentalKotlinGradlePluginApi::class)
            transitiveExport = false // This is default.
            // Bitcode embedding
            embedBitcode(BitcodeEmbeddingMode.DISABLE)
        }

        // Must define the pods that are in the Podfile (Is this just the way it works?)
        pod("GoogleMaps") {
            version = libs.versions.pods.google.maps.get()
            //    version = "7.4.0" // for GoogleMapsUtils 4.2.2 (doesn't build for some c-interop reason, waiting for 5.0.0)
            extraOpts += listOf("-compiler-option", "-fmodules")
        }

        //    pod("Google-Maps-iOS-Utils") {
        //        version = libs.versions.pods.google.mapsUtils.get() // waiting for 5.0.0 to be released!
        //        //  source = path(project.file("../GoogleMapsUtils"))
        //        //  packageName = "Google_Maps_iOS_Utils"
        //    }

        // Maps custom Xcode configuration to NativeBuildType
        xcodeConfigurationToNativeBuildType["CUSTOM_DEBUG"] = NativeBuildType.DEBUG
        xcodeConfigurationToNativeBuildType["CUSTOM_RELEASE"] = NativeBuildType.RELEASE
    }
    
    sourceSets {

        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
        
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.koin.android)
            implementation(libs.ktor.client.okhttp)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.navigation)
            implementation(libs.voyager.koin)
            implementation(libs.voyager.tabNavigator)
            implementation(libs.voyager.transitions)
            implementation(libs.voyager.screenModel)
            implementation(libs.voyager.bottomSheetNavigator)
            implementation(libs.voyager.navigator)
            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.coroutines)
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.negotiation)
            implementation(libs.ktor.client.cia)
            implementation(libs.ktor.client.json)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.webview)
            implementation(libs.napier)
            implementation(libs.ktor.client.logging)
            implementation(libs.setting)
            implementation(libs.setting.noargs)
            implementation(libs.setting.serialization)
            implementation(libs.setting.coroutines)
            // Required
            implementation(libs.lyricist)
        }
    }
}

dependencies {
    add("kspCommonMainMetadata", libs.lyricist.processor)
}

tasks.withType<org.jetbrains.kotlin.gradle.dsl.KotlinCompile<*>>().all {
    if(name != "kspCommonMainKotlinMetadata") {
        dependsOn("kspCommonMainKotlinMetadata")
    }
}

kotlin.sourceSets.commonMain {
    kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")
}

ksp {
    arg("lyricist.internalVisibility", "true")
    arg("lyricist.generateStringsProperty", "true")
}




android {
    namespace = "tm.gps.ytm"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "tm.gps.ytm"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
    dependencies {
        debugImplementation(compose.uiTooling)
        implementation(libs.kotlinx.coroutines.android)
    }
}

