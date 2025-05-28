/*
 * Copyright 2024 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
plugins {
  id("com.android.security.autorepro.apptest")
  id("com.android.application")
  id("org.jetbrains.kotlin.android")
}

android {
  kotlinOptions {
    jvmTarget = "1.8"
  }
}

// Note: the Gradle package name is appended to the namespace "com.android.security"
appTest {
  minSdk = 35
  compileSdk = 35
  targetSdk = 35
}

dependencies {
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.8.1")
  implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.0")
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.9.0")
  implementation("org.jetbrains.anko:anko-commons:0.10.8")
  implementation("org.jetbrains.anko:anko-sdk27:0.10.8")
  implementation("org.jetbrains.anko:anko-sdk27-coroutines:0.10.8")


  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.0")
  implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.0")
  implementation("androidx.appcompat:appcompat:1.4.0")
  implementation("org.jetbrains.kotlin:kotlin-reflect:1.7.0")
  implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
}

