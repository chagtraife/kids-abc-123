# Proguard rules for KidsABC app

# Keep Compose classes
-keep class androidx.compose.** { *; }
-keepclasseswithmembernames class androidx.compose.** {
    native <methods>;
}

# Keep Kotlin metadata
-keepclassmembers class ** {
    *** Companion;
}

# Keep navigation classes
-keep class androidx.navigation.** { *; }

# Keep Material3 classes
-keep class androidx.compose.material3.** { *; }

# Keep lifecycle classes
-keep class androidx.lifecycle.** { *; }

# Don't warn about Kotlin
-dontwarn kotlin.reflect.**
-dontwarn org.jetbrains.kotlin.**

# Don't warn about Compose
-dontwarn androidx.compose.**
