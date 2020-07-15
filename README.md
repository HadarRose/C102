# C102
 ## Bootcamp Lab C102: Simple Java Project- Tweeting
 ### Dependencies and  Requirements 
 * JDK 8
 * [twitter4j](http://twitter4j.org/en/) 
 * Twitter Dev account (Note: to see the default account this lab is connected to, go 
 [here](https://twitter.com/HadarRozenberg))
 
 ### Compiling
 Using IntelliJ, go to project structure -> artifacts -> + (add) to add a new artifact. 
 Choose the option JAR and C102_Runner as your main class. For the official IntelliJ documentation on the topic, go 
 [here](https://www.jetbrains.com/help/idea/compiling-applications.html#compile_module).
 
When working on this code, you'll need a twitter4j.properties file with the appropriate information in the 'src' folder.
more on the twitter4j.properties file can ber found [here](http://twitter4j.org/en/configuration.html).
 
 
### Instructions
* Posting a tweet:
```java -jar C102.jar post "post content"``` <br> "post content" must adhere to Twitter's standards (i.e. not an empty 
string, not above 280 characters)
* Retrieving timeline:
```java -jar C102.jar timeline```
* Retrieving timeline as a different user: 
```java -jar C102.jar timeline <key> <key secret> <token> <token secret>```
