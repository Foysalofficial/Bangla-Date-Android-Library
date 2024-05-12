# Bangla Date Time App

## Overview
This Android application allows users to display Bangla and English dates and times. It leverages utility functions from the `BanglaDateUtils` class to retrieve and format the dates and times in both languages.

## Features
- Display Bangla full date, day, month, and season
- Display English date
- Continuous updating of Bangla time

## Installation
To use this application, follow these steps:
1. Clone the repository to your local machine.
2. Open the project in Android Studio.
3. Build and run the application on an Android device or emulator.

## Usage
To integrate the date and time display functionality into your Android application, follow these steps:
1. Add the necessary layout components (`BanglaTimeTv`, `BanglaDateTv`, `EnglishDateTv`) to your XML layout file.
2. In your Java or Kotlin file, find and assign the corresponding views using `findViewById`.
3. Utilize the `BanglaDateUtils` class to retrieve Bangla and English dates and times.
4. Set the retrieved dates and times to the respective TextViews using `setText`.

# How to To get a Git project into your build:
> Step 1. Add it in your root build.gradle at the end of repositories

```gradle
  dependencyResolutionManagement {
		repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
		repositories {
			mavenCentral()
			maven { url 'https://jitpack.io' }
		}
	}
  ```
  
  >Step 2. Add the dependency
  
  ```gradle
  
  dependencies {
	        implementation ("com.github.Foysalofficial:Bangla-Date-Android-Library:2.0")
	}
  
  ```

```java
BanglaTimeTv = findViewById(R.id.BanglaTimeTv);
BanglaDateTv = findViewById(R.id.BanglaDateTv);
EnglishDateTv = findViewById(R.id.EnglishDateTv);

String banglaFullDate = BanglaDateUtils.getBanglafullDate();
String WeekDay = BanglaDateUtils.getWeekDay();
String banglaDay = BanglaDateUtils.getBanglaDay();
String banglaMonth = BanglaDateUtils.getBanglaMonth();
String banglaSeason = BanglaDateUtils.getBanglaSeason();
String englishDate = BanglaDateUtils.getEnglishDate();

BanglaDateTv.setText(banglaFullDate + " | " + banglaSeason);
BanglaDateUtils.startUpdatingTime(BanglaTimeTv, false, true);
EnglishDateTv.setText(englishDate);


Dependencies
This project does not have any external dependencies.

Contributing
Contributions are welcome! Feel free to fork the repository and submit pull requests.

License
This project is licensed under the MIT License.


This Markdown will render as formatted text on GitHub, providing clear instructions and information about your Bangla Date Time App.

Credits
This project utilizes the BanglaDateUtils class developed by Foysal Tech.
