Openweathermap Demo
------------------------------------------------
This app fetches weather data for City of London, GB__
It uses http://openweathermap.org/ API__

Installation
------------------------------------------------
Clone the project and import it to Android Studio.__
During development I used latest stable version of Android Studio 2.3.2__
Please get an openweathermap.org API key and copy it to $HOME/.gradle/gradle.properties as follows:__

OpenWeatherMapApiKey="XXXX"__

Future Work
------------------------------------------------
Parse more data from json response and add them in the model (humidity/wind)__
Add a details view when the user clicks on a view, displaying aforementioned data__
Add junit test cases for presenter__
Add sqlite to store results and minimise HTTP API calls__
Could move fetches to a job scheduler (twice a day) and store results to aforementioned db__
Add a daily notification with the forecast of the day__
