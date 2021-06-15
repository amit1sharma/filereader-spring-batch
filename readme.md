## Large file reader

You can change database details to connect with database in application-dev.properties

To build application run following gradle command at root location of project

gradle clean build

executable jar file will be created at location

* /build/libs/filereader.jar

start jar file using following commmand

java -jar filereader.jar

#### Endpoints

* /upload/file [post] This is used to upload multipart file
* /upload/file [get] List all the uploaded files
* /upload/file/{fileid} [get] List single record with fileid

To test upload feature there is a small html page available which can be accessed from root url path ie. http://localhost:8080/ 

#### Tables used

* FILES 
    * file_id
    * file_name
    * file_type
    * file_size

