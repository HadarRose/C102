# C102
 ## Bootcamp Lab C102: Simple Java Project- Tweeting
 ### Dependencies and  Requirements 
 * JDK 8
 * [twitter4j](http://twitter4j.org/en/) 
 * Twitter Dev account (Note: to see the default account this lab is connected to, go 
 [here](https://twitter.com/HadarRozenberg))
 
### Instructions
* Compiling: ```mvn package```

* Posting a tweet:
```java -jar C102_Runner.jar post "post content"``` <br> "post content" must adhere to Twitter's standards (i.e. not an empty 
string, not above 280 characters)
* Retrieving timeline:
```java -jar C102_Runner.jar timeline```
