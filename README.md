<img src="https://api.codeclimate.com/v1/badges/fd0e4471ce83727e2a97/maintainability" /> [https://codeclimate.com/github/YassinAJDI/MaterialJournal/maintainability]

# MaterialJournal
A journal application where in users can pen down their thoughts and feelings submitted for the "7 Days of code" challlenge for the GoogleAfiricaScholarship Challenge offered by Udacity and powered by Andela Learning Community (ALC) learners.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

* Android Studio 3.2 beta 2
* Java JDK

### Installing

Follow this steps if you want get a local copy of the project in your machine.

##### 1. Clone or fork the repository by running the cammand below.
	
	git clone https://github.com/YassinAJDI/MaterialJournal.git

##### 2. Import the project in AndroidStudio.
1. In Android Studio, go to File -> New -> Import project
2. Follew the dialog wizard to choose the folder where you cloned the project and click on open.
3. Androidstudio imports the projects and builds it for you. 

##### 3. Add Firebase config.
1. Go to [Firebase](https://console.firebase.google.com/) and click on + Add project to create a new project
2. Add the SHA1 fingerprint of your machine after you have created the project
3. Download google_service.json file and add to the /app folder of the project
4. Clean -> Build the project and ensure that every thing works fine 

You can now run the project in an Android Emulator or a real Android Device.

Note : A sample release apk is avaible [Here](app/release/app-release.apk) 

## Built With

* [Android support Library](https://developer.android.com/topic/libraries/support-library/revisions) - Android support Library 28
* [Room Database](https://developer.android.com/topic/libraries/architecture/room) - The Room persistence library for Android.
* [Glide](https://github.com/bumptech/glide) - An image loading and caching library for Android focused on smooth scrolling 
* [Firebase](https://firebase.google.com/) - For authentication and data persisntence.


## Authors

* **Yassin AJDI**


## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
