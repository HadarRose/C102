# C102
 ## Bootcamp Lab C102: Simple Java Project- Tweeting
 ### Dependencies and  Requirements 
 * JDK 8
 * [twitter4j](http://twitter4j.org/en/) 
 * Twitter Dev [account](https://developer.twitter.com/en/portal/register/welcome)
 
### Instructions
* **Setup:** In the root folder, create a *twitter4j.properties* that includes your credentials. You will need to make your 
own file, but you can see twitter4j_example.properties, also in the root folder, for reference. Simply copy the content 
of the example file to your new twitter4j.properties file, and fill in your credentials. 
More on the twitter4j.properties file can be found [here](http://twitter4j.org/en/configuration.html).
You will also need to generate these credentials by making a Twitter Dev (see Dependencies and Requirements). 

* **Compiling:** ```mvn package``` or ```mvn clean package```
* **Starting the server:** ```java -jar target/C102.jar server```
* **Endpoints:**
    * Posting a tweet: http://localhost:8080/api/1.0/tweet 
        * Request body: needs to be a JSON of the form {"message": "your message goes here"}
        * you post must adhere to Twitter's standards (i.e. not an empty string, not above 280 characters)
    * Retrieving timeline: http://localhost:8080/representations/1.0/twitter/timeline.
