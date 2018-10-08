<h1 align="center">MaterialJournal</h1>
<h4 align="center">
	A journal application where in users can pen down their thoughts and feelings submitted for the "7 Days of code" challlenge for 	the GoogleAfiricaScholarship Challenge offered by Udacity and powered by Andela Learning Community (ALC) learners.
</h4>
<p align="center">
<a href="./LICENSE">
	<img src="https://img.shields.io/github/license/mashape/apistatus.svg" />
</a>
<a class="badge-align" href="https://www.codacy.com/app/YassinAJDI/MaterialJournal?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=YassinAJDI/MaterialJournal&amp;utm_campaign=Badge_Grade"><img src="https://api.codacy.com/project/badge/Grade/8483a7a6743b45ceb773a0b2b635e2cf"/></a>
<a href="https://codeclimate.com/github/YassinAJDI/MaterialJournal/maintainability">
	<img src="https://api.codeclimate.com/v1/badges/fd0e4471ce83727e2a97/maintainability" />
</a>
</p>

<h2 align="center">Screenshots</h2>
<h4 align="center">
<img src="screenshots/Screenshot%201.jpg" width=240>
<img src="screenshots/Screenshot%202.jpg" width=240>
<img src="screenshots/Screenshot%203.jpg" width=240>
</h4>

## Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites
*   Android Studio 3.2
*   Java JDK

### Installing
Follow this steps if you want get a local copy of the project in your machine.

#### 1. Clone or fork the repository by running the cammand below	
	git clone https://github.com/YassinAJDI/MaterialJournal.git

#### 2. Import the project in AndroidStudio
1.  In Android Studio, go to File -> New -> Import project
2.  Follew the dialog wizard to choose the folder where you cloned the project and click on open.
3.  Androidstudio imports the projects and builds it for you. 

#### 3. Add Firebase config
1.  Go to [Firebase](https://console.firebase.google.com/) and click on + Add project to create a new project
2.  Add the SHA1 fingerprint of your machine after you have created the project
3.  Download google_service.json file and add to the /app folder of the project
4.  Clean -> Build the project and ensure that every thing works fine 

You can now run the project in an Android Emulator or a real Android Device.

Note : A sample release apk is avaible [Here](app/release/app-release.apk) 

## Built With
*   [Android support Library](https://developer.android.com/topic/libraries/support-library/revisions) - Android support Library 28
*   [Room Database](https://developer.android.com/topic/libraries/architecture/room) - The Room persistence library for Android.
*   [Glide](https://github.com/bumptech/glide) - An image loading and caching library for Android focused on smooth scrolling 
*   [Firebase](https://firebase.google.com/) - For authentication and data persisntence.

## Authors
*   **Yassin AJDI**

## License
```
MIT License

Copyright (c) 2018 Yassin Ajdi

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE. 
```
