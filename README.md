# C102
 ## Bootcamp Lab C102: Simple Java Project- Tweeting
 ### Dependencies and  Requirements 
 * JDK 8
 * [twitter4j](http://twitter4j.org/en/) 
 * Twitter Dev account (Note: to see the default account this lab is connected to, go 
 [here](https://twitter.com/HadarRozenberg))
 
 ### Compiling and Running
 1. Setup: In the '_src_' folder, edit _twitter4j.properties_ to include your credentials. More on the 
 twitter4j.properties file can be found [here](http://twitter4j.org/en/configuration.html). 
 2. IntelliJ Build (Go [here](https://www.jetbrains.com/help/idea/compiling-applications.html#compile_module) for 
 more information): 
    1. project structure (top right corner, or cmd+;) -> artifacts -> + (add) to add a new artifact
    2. Choose the option JAR 
    3. Specify C102_Runner.java as the main class
    4. Choose 'OK' or 'Apply'
        - **NOTE:** You will may be asked to create a manifest file. Allow this. You may also see and error stating 
        that the manifest file is already in use. In that case, simply delete the folder META-INF and try again.
    5. In the toolbar, go to Build->Build Artifacts...
    6. In the new menu, choose build under C102:JAR
    7. The new JAR file will be created under out/artifcats/C102_jar
     
 
### Using the Program
* Posting a tweet:
```java -jar C102.jar post "post content"``` <br> "post content" must adhere to Twitter's standards (i.e. not an empty 
string, not above 280 characters)
* Retrieving timeline:
```java -jar C102.jar timeline```
* Retrieving timeline as a different user: 
```java -jar C102.jar timeline <key> <key secret> <token> <token secret>```


