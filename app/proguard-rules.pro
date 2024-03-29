-dontobfuscate

# --- Butter Knife ---
# Finder.castParam() is stripped when not needed and ProGuard notes it
# unnecessarily. When castParam() is needed, it's not stripped. e.g.:
#
#  @OnItemSelected(value = R.id.history_entry_list)
#  void foo(ListView bar) {
#      L.d("baz");
#  }

-dontnote butterknife.internal.**
# --- /Butter Knife ---

# --- Retrofit2 ---
# Platform calls Class.forName on types which do not exist on Android to determine platform.
-dontnote retrofit2.Platform
# Platform used when running on Java 8 VMs. Will not be used at runtime.
-dontwarn retrofit2.Platform$Java8
# Retain generic type information for use by reflection by converters and adapters.
-keepattributes Signature
# Retain declared checked exceptions for use by a Proxy instance.
-keepattributes Exceptions
# --- /Retrofit ---

# --- OkHttp + Okio ---
-dontwarn okhttp3.**
-dontwarn okio.**
# --- /OkHttp + Okio ---

# --- Spotify ---
-dontwarn com.spotify.**
# --- /Spotify ---

# --- Gson ---
# https://github.com/google/gson/blob/master/examples/android-proguard-example/proguard.cfg

# Gson uses generic type information stored in a class file when working with fields. Proguard
# removes such information by default, so configure it to keep all of it.
-keepattributes Signature

# For using GSON @Expose annotation
-keepattributes *Annotation*

# Gson specific classes
-dontwarn sun.misc.**
#-keep class com.google.gson.stream.** { *; }

# Application classes that will be serialized/deserialized over Gson
-keep class com.google.gson.examples.android.model.** { *; }

# Prevent proguard from stripping interface information from TypeAdapterFactory,
# JsonSerializer, JsonDeserializer instances (so they can be used in @JsonAdapter)
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer
-keep class sun.misc.Unsafe { *; }
-keep class org.conscrypt.Conscrypt { *; }
-keep class com.google.android.gms.location.LocationRequest { *; }

-dontnote sun.misc.Unsafe
# --- /Gson ---

# --- Wikipedia ---
-keep class org.wikipedia.** { <init>(...); *; }
-keep enum org.wikipedia.** { <init>(...); *; }
# --- /Wikipedia ---
