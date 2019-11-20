# Welcome!

<br/>

## Introduction &amp; Description

* DIY Garage allows you to keep maintenance records of the services that have already been done and (in the future)
when services need to done again so you can keep your vehicles running for as long as possible. You can add all your cars 
to the database of the app. For each of those cars you can add services according to the date and mileage. And for
each of those services you add all the different things that were done. Since all that information is being added to the
database of the app, the info stays until you delete it. 

* My aim in developing DIY Garage is to rid the world of the fear of getting down and dirty with 
personal vehicles. Why waste money paying someone else to do the job when you can do it yourself?
In future versions of the app, you can use DIY Garage to learn how to do important routine
maintenance such as tire rotations, oil changes, transmission flushes and much more. 



## Intended Users &amp; User Stories

* People who prefer to do their own car maintenance. 
* People who want to learn how to maintain their own cars.
* People who want records of their cars.

* [User stories](docs/user-stories.md)

## Current State of DIY Garage

### Bugs &amp; Unimplemented/Incomplete Elements

* The ability to add an acquisition date is not implemented yet.
* The ability to edit and delete cars, services, actions is not implemented yet.
* When in landscape mode the spinner and buttons next to it do not stretch to fill screen.
* Pressing the back button in the actions toolbar resets spinner to top item in spinner, 
not the most recently selected car.
* Clicking save in New Car alert dialog when nothing is typed in fields crashes the app.
* Clicking save in Add Service alert dialog when nothing is typed in mileage crashes the app.
* In the Nexus 5X emulator spinner and buttons next to dont fill screen.

### Cosmetic improvements needed

* Make items that are clickable in recycler views look clickable.
* Add more information in toolbar of actions recycler view (car name and year, mileage)
* Add a string that says "miles" after the mileage.
* Format appearance of dates to look better.
* Make colors more pleasing to look at.

### Functional Stretch Goals

* Take information from cars, services and actions to add YouTube links

## Diagrams

* [Entity Relationship Diagram](docs/erd.md)
* [Wireframe Diagram](docs/wireframe.md)

## Data Model Implementation 

* [Entities, DAOs, Database &amp; DDL](docs/datamodelimpl.md)

## Technical Requirements &amp; Dependencies

### Android API Versions &amp; Hardware

* Minimum API 21
* Samsung Galaxy Note8 (Android 9, API 28)
* Nexus 5X Emulator (API 28)

### Third Party Libraries

* RxJava &amp; RxAndroid
* Retrofit
* Stetho
* Room
* Apache Commons CSV

### External Services

* Google Sign In
* Vehicles CSV File from [Fuel Economy Web Services](https://www.fueleconomy.gov/feg/ws/index.shtml)

## Javadoc-generated Technical Documentation

* [Javadoc](docs/api/overview-summary.html)

## Copyrights &amp; Licenses

* [Full License](docs/full-license.md)
* [Third Party Licenses](docs/license-info.md)

## Build Instructions 

1. Go to the [GitHub repository](https://github.com/khizar-saleem/diy-garage)

2. Click on Clone or download

3. Make sure Clone with SSH is selected and click the clipboard icon to copy the SSH key

4. Use the IntelliJ Check out from Version Control/Git (from the welcome screen) or File/New/Project from Version Control/Git (from the workspace) command to clone.
  
5. DO NOT click open when prompted to do so.

6. Import the project you just cloned. In the Import Project window, select Import project from external model and then the Gradle item in the list.

7. After the project is imported add a run configuration. This should use the Android App configuration, with a Name of "app", and with app selected from the Module pull-down control.

8. Click the run icon and select the device you wish to run it on.

## Basic User Instructions

1. Launch app, click Sign In, and sign in with your Google account.

2. To add a car, click the + icon next to the spinner/drop down.

3. Enter the year, select the make and enter model of the car.

4. Click SAVE to add it to the database and spinner.

5. To add a service select a car from the spinner and click the + button at the bottom right.

6. Enter the current mileage of your car click SAVE.

7. To add an action click on the service that was just created and click the + button on the bottom right.

## DIY Garage, All Rights Reserved
```text
Copyright 2019 Khizar Saleem

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```
