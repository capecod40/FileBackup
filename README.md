# CPSC 210 Project: File Backup Application
My project for this term is an application that creates a backup of a file structure. The user will be able to select a source and destination folder. The application will copy the contents of the source folder (including the content of any subfolders) to the destination directory. 

This application is meant to be a portable, lightweight, and easy to use alternative to more comprehensive and feature rich file system backup applications. Aside from file backups, this application will also feature a logging system. From the log, a user will be able to see the timestamp of any given backup. Tentative ideas for more features are also listed below. 
## Features
 - File backup: 
	- User can select source and destination folder
		- Backup will treat source folder as a root directory and copy all content and subfolders to destination folder
		- Warns user in case of insufficient free space in destination directory
- Logging system: 
	- User will be able to see timestamp of each backup
		 - Tentative implementation idea: Each backup will create  a hidden text file in the root directory of the backup in the destination folder to indicate timestamp of backup
## Tentative Ideas
- A graphical representation of the folder structure of each backup is created alongside each backup
	- Allows user to easily identify differences between backups without the complexity of a file comparison feature 
 - User can set timer for recurring backups
	 - Throws error in case of missing source/destination directory
## Wait But Why?
My initial though process before deciding on this project was to create an application that had some form of logging system that also involved writing/reading to a file system. My previous project was a video game written in C++ that focused on graphics rendering. The game didn't have any interaction with the user's file system, so this project would allow me to explore an unfamiliar area in creating desktop applications. Also, the logging system (especially the graphical representation of the folder structure if I can manage it) will expose me to new types of data structures, which are quite important if I want to make a living doing this :P
## User Stories
 - I want to create a backup of this folder and all of its subfolders
 - I want to compare two backups and be able to see which one is newer