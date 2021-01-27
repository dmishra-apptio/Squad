# Welcome to SQUAD STACK PARKING LOT!

# Pre-requisite

 - Need to have JAVA 9 or higher version
 - To install java jdk goto [here](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
 - Set the java Home path in ***windows & linux*** by referring to [here](https://www.javatpoint.com/how-to-set-path-in-java)
 - set the java home path in ***mac*** refer [here](https://stackoverflow.com/questions/22842743/how-to-set-java-home-environment-variable-on-mac-os-x-10-9)
 -  
# CLone Project From Git
 - git clone git@github.com:dk103/Squad.git
 - Change directory to Assign using "cd" command

# Install Dependencies & Build Project

 - Run  Assign/**.init.sh** file place at root  dir of the project that does install the dependency
 -  and runs the test
 
 # Package the Project and create jar
 - Run **mvn package ** command
 - Above command create a target Folder and under which parking-lot-1.0-SNAPSHOT.jar file will be created
 
 # Run Test 
 - Run "mvn test" command
 - # Run the jar  to execute application
 -  java -DfilePath=InputFilePath -   Doutput=OutPutFilePath -jar <JarPath+Jar name>
 **Example:-**
    - java -DfilePath=InputFilePath -   Doutput=OutPutFilePath -jar parking-lot-1.0-SNAPSHOT.jar

 - filePath :-  it's the input file path
   output:- It's the output path



