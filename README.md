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
