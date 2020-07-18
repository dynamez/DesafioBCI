import dependencies.AnnotationProcessorsDependencies
import dependencies.DebugDependencies
import dependencies.Dependencies
import extensions.addTestsDependencies
import extensions.kapt

plugins {
    id(BuildPlugins.ANDROID_APPLICATION)
    id(BuildPlugins.KOTLIN_ANDROID)
    id(BuildPlugins.KOTLIN_ANDROID_EXTENSIONS)
    id(BuildPlugins.KOTLIN_KAPT)
    id(BuildPlugins.KOTLIN_ALLOPEN)
    id(BuildPlugins.NAVIGATION_SAFE_ARGS)
}
android {
    compileSdkVersion(BuildAndroidConfig.COMPILE_SDK_VERSION)
    defaultConfig {
        applicationId = BuildAndroidConfig.APPLICATION_ID
        minSdkVersion(BuildAndroidConfig.MIN_SDK_VERSION)
        targetSdkVersion(BuildAndroidConfig.TARGET_SDK_VERSION)
        buildToolsVersion(BuildAndroidConfig.BUILD_TOOLS_VERSION)

        versionCode = BuildAndroidConfig.VERSION_CODE
        versionName = BuildAndroidConfig.VERSION_NAME

        vectorDrawables.useSupportLibrary = BuildAndroidConfig.SUPPORT_LIBRARY_VECTOR_DRAWABLES
        testInstrumentationRunner = BuildAndroidConfig.TEST_INSTRUMENTATION_RUNNER
        testInstrumentationRunnerArguments =
            BuildAndroidConfig.TEST_INSTRUMENTATION_RUNNER_ARGUMENTS
    }

    buildTypes {
        getByName(BuildType.RELEASE) {
            proguardFiles("proguard-android-optimize.txt", "proguard-rules.pro")
            //signingConfig = signingConfigs.getByName(name)
            isMinifyEnabled = BuildTypeRelease.isMinifyEnabled
        }

        getByName(BuildType.DEBUG) {
            applicationIdSuffix = BuildTypeDebug.applicationIdSuffix
            versionNameSuffix = BuildTypeDebug.versionNameSuffix
            isMinifyEnabled = BuildTypeDebug.isMinifyEnabled
        }
    }
    flavorDimensions(BuildProductDimensions.ENVIRONMENT)
    productFlavors {
        ProductFlavorDevelop.appCreate(this)
        ProductFlavorQA.appCreate(this)
        ProductFlavorProduction.appCreate(this)
    }
    dataBinding {
        isEnabled = true
        isEnabledForTests = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
    androidExtensions {
        isExperimental = true
    }
}


dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Dependencies.KOTLIN)
    implementation(Dependencies.KOTLINREFLECT)
    implementation(Dependencies.APPCOMPAT)
    implementation(Dependencies.MATERIAL)
    implementation(Dependencies.CORE_KTX)
    implementation(Dependencies.NAVIGATION_FRAGMENT)
    implementation(Dependencies.NAVIGATION_UI)
    implementation(Dependencies.CONSTRAIN_LAYOUT)
    implementation(Dependencies.LIFE_LIVEDATA_KTX)
    implementation(Dependencies.SAVEDSTATES)
    implementation(Dependencies.LIFECYCLE_VIEWMODEL)
    kapt(AnnotationProcessorsDependencies.LIFECYCLE_COMPILER)
    implementation(Dependencies.RECYCLE_VIEW)
    implementation(Dependencies.SWIPE_REFRESH_LAYOUT)
    implementation(Dependencies.COIL)
    implementation(Dependencies.LOTTIE)

    //DI
    implementation(Dependencies.KOINCORE)
    implementation(Dependencies.KOINANDROID)
    implementation(Dependencies.KOINSCOPE)
    implementation(Dependencies.KOINVIEWMODEL)

    //network
    implementation(Dependencies.RETROFIT)
    implementation(Dependencies.RETROFIT_MOSHI)
    implementation(Dependencies.OKHTTP)
    implementation(Dependencies.LOGGING)

    //DB
    implementation(Dependencies.ROOM)
    kapt(AnnotationProcessorsDependencies.ROOM)
    implementation(Dependencies.ROOM_KTX)

    //Coroutines
    implementation(Dependencies.COROUTINES_ANDROID)
    implementation(Dependencies.COROUTINES)

    //Extras
    implementation(Dependencies.TIMBER)

    addTestsDependencies()
    debugImplementation(DebugDependencies.KOTLINJUNIT)
}