Openweathermap Demo
------------------------------------------------
This app fetches weather data for City of London, GB

It uses http://openweathermap.org/ API

Installation
------------------------------------------------
Clone the project and import it to Android Studio.
During development I used latest stable version of Android Studio 2.3.2

Please get an openweathermap.org API key and copy it to $HOME/.gradle/gradle.properties as follows:

OpenWeatherMapApiKey="XXXX"

Future Work
------------------------------------------------
 - Parse more data from json response and add them in the model (humidity/wind)
 - Add a details view when the user clicks on a view, displaying aforementioned data
 - Add junit test cases for presenter
 - Add sqlite to store results and minimise HTTP API calls
 - Could move fetches to a job scheduler (twice a day) and store results to aforementioned db
 - Add a daily notification with the forecast of the day
