Note that following configuration can be done only for a Google admin workspace account. Since the configuration is not 
possible for a normal gmail account, this application cannot be used for to post messages to spaces in a normal gmail account
### Prerequisites
1. java 19 openjdk 
2. maven 3.8
### How to run from intellij

1. Import the project in Intellij
2. Update space_to_webhook.properties with space name and bot url. follow the below steps to create the bot url
   1. Click on the Manage Webhook menu. ![alt text](./image/1.png)
   2. Fill the name(Sender name) and image url(whatever image url). ![alt text](./image/2.png)
   3. Copy the bot url in this window. ![alt text](./image/3.png)
3. Run the App class to run the project.

### How to run the project from jar
1. Update the space_to_webhook.properties as specified above
2. run mvn clean package from project root
3. go to the target folder
4. run project with `java -jar jclass-post-message-1.0-SNAPSHOT.jar`