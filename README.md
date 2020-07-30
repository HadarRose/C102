# C102
 ## Bootcamp Lab C102: Simple Java Project- Tweeting
 ### Dependencies and  Requirements 
 * JDK 8
 * [twitter4j](http://twitter4j.org/en/) 
 * Twitter Dev [account](https://developer.twitter.com/en/portal/register/welcome)
 
### Instructions
* **Setup:** In the root folder, create a *config.yml* that includes your credentials. You will need to make your 
own file, but you can see *config_example*.yml, also in the root folder, for reference. Simply copy the content 
of the example file to your new configuration.yml file, and replace the XXX placeholders with your credentials. 
You will also need to generate these credentials by making a Twitter Dev (see Dependencies and Requirements). 

* **Compiling:** ```mvn package``` or ```mvn clean package```
* **Starting the server:** ```java -jar target/C102.jar server config.yml```
* **Endpoints:**
    * Posting a tweet: http://localhost:8080/api/1.0/tweet 
        * Request body: needs to be a JSON of the form {"message": "your message goes here"}
        * you post must adhere to Twitter's standards (i.e. not an empty string, not above 280 characters)
    * Retrieving timeline: http://localhost:8080/api/1.0/twitter/timeline.
* **Generating Test Coverage Results:**: run ```mvm test```, ```mvn install``` or ```mvn package```. 
Go to target/site/jacoco to see the reports. To view general report open index.html with a browser.