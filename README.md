# Backgammon
Simple Backgammon game created with JavaFX

# Description
You can play with your friends or family. The game allows you to have a break whenever you want, and you can continue your game next time. The program may generate logfiles in a Log/ directory, and also xml files to store user data. These files make a better experience for the users, please do not delete these files.

# Requirements
The program can be compiled only if Maven and JDK (min. version: 1.8) available.

# Use the program
To use the program, use the following command in the project base directory:

	mvn package

After that you run the program by executing the generated .jar file in the target folder:

	java -jar art-1.0-SNAPSHOT-jar-with-dependencies.jar

# Generate site
To generate the site for the project run the following command in the project base directory:

	mvn site

The generated site and the reports will be placed in the target/site/ folder.

To generate test coverage run:

	mvn clover:instrument clover:clover

The generated reports will be placed under /target/site/clover.


