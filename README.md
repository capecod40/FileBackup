# CPSC 210 Project: File Backup Application
My project for this term is an application that creates a backup of a directory. The user will be able to select a
source and destination folder. The application will copy the contents of the source folder 
(including the content of any subfolders) to the destination directory. 

This application is meant to be a portable, lightweight, and easy to use alternative to more comprehensive and feature
rich file system backup applications. The layout of the user interface will be simple and intuitive to make it 
accessible for all PC users. Aside from file backups, the user will also be able to see the timestamp of any given backup. Tentative ideas for more features are 
also listed below. 

## External Code
- JsonReader and JsonWriter from Phase 2 demo
- FileUtils library
## Core Features
 - File backup: 
	- User can select source and destination folder
		- Backup will treat source folder as a root directory and copy all content and subfolders to destination folder
		- Warns user in case of insufficient storage in destination directory
- Timestamp: 
	- User will be able to see timestamp of each backup
         - Each backup will create  a hidden text file in the root directory of the backup in the destination folder to
         - indicate timestamp of backup
## Tentative Ideas
- A graphical representation of the folder structure is created alongside each backup
    - Allows user to easily examine directory structure/size without the complexity of a file comparison feature 
- User can set timer for recurring backups
    - Throws error in case of missing source/destination directory
- Detects old backups in destination folder and provides option for overwriting previous backup
- Application has an internal log that records past backups
  - Shows timestamp, source directory, and destination of past backups
## Wait But Why?
My initial thought process before deciding on this project was to create an application that had a logging system and 
also involved writing/reading to the file system. My previous programming project was a video game written in C++ that 
focused on graphics rendering. The game didn't have any interaction with the user's file system, so this project would 
allow me to explore an unfamiliar area in creating desktop applications. Also, the tentative ideas will hopefully expose me to new types of data structures, which will be important in the future. 
## User Stories
- I want to copy a file to another folder
- I want to copy a folder to another folder
- I want to copy multiple files (contained in a folder) to another folder
- I want to be able to find the date and source directory of a backup
- I want to be able to find the timestamps of backups and see which one is newer
- I want to add a log entry that records the source path and time for every backup
- I want the user to be able to save backup log entries
- I want the user to be able to load previously saved log entries
<!--
- I want to get a warning if there is not enough storage in the destination file system
  for the backup
-->
# Instructions for Grader

- You can generate the first required event related to adding Xs to a Y by inputting the paths to the source and destination directory for the backup and pressing their respective enter buttons
- You can generate the second required event related to adding Xs to a Y by pressing the start backup button and confirming the backup
    - Pressing the start backup button will automatically input source and destination paths
- You can locate my visual component by starting the application o_o
- You can save the state of my application by pressing the save log button
- You can reload the state of my application by pressing the load button
# Phase 4: Task 2
Example output of logged events:

    Mon Nov 28 18:07:31 PST 2022
        Source:[src\test\backupTests\inTest]
        Destination:[src\test\backupTests\outTest]
    
    Mon Nov 28 18:07:38 PST 2022
        Source:[src\test\backupTests\inTest]
        Destination:[src\test\backupTests\outTest1]
    
    Mon Nov 28 18:07:42 PST 2022
        Source:[src\test\backupTests\inTest]
        Destination:[src\test\backupTests\outTest2]

# Phase 4: Task 3
- EventLog (which contains Events) and BackupData are redundant. BackupData should be refactored to resemble the structure of EventLog and Events
- JsonWriter and JsonReader should be associated with FileBackup, not FileBackupUi since they are not related to the user interface
- The Main class looks lonely. I guess that's a good thing though