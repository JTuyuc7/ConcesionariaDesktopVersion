# Concesionaria Drive X Port

 Project that uses Java UI.form


### Continuing with the project of Programacion II at Mariano Galvez

* # How is the project structure
  * assets
  * data
  * DbConnection
  * Utils
  * docker-compose
  * UI
  * UI.form

### Assests
Inside the assest file, you can find all the icons used in the app

### Data
Inside the data file, we sotored the init.sql, which gets executed when the compose file gets mounted, it runs a migration scritp which will create the table and insert some records on the DB

### DBConnection
In the file we can find all the functions that gets connected to the DB and executes the functions
* Connection to the DB
* Insert a new record on the DB
* Get a single record
* Update a single record
* Delete a record
* Close the DB connection

### Utils
The file contains some utilities to split the app and handle it better, these includes
* PdfReport
  * Class where we stored the two functions to create a table that will include all the records from the db
* Queries
  * Class that stores all the functions and the required queries to pass it to the DB
* Validations
  * Class where we stored a function that validates if an string is empty

### Compose file
The file contains the logic to run two containers, ```MySql``` using the version ```8.1.0``` and the latest version of ```phpmyadmin/phpmyadmin```

## UI
The main file on the app, where all the logic, of the UI components live, buttons, labels, text field, icons, and the list of the components


## Want to run the project?

- Clone the repository on your local
  - duplicate the ```compose-variables-template.env``` and raname it to ```compose-variables.env``` inside the file you can find some variables that you have replace

  

    
     
    MYSQL_ROOT_PASSWORD=yourpassword
    MYSQL_DATABASE=yourdbname
    MYSQL_PASSWORD=yourpassword
    #* MYSQL container name
    PMA_HOST=containername #Same name on your compose file line 5


  replace the variables with the corresponding data based on your project


* Inside the ```DBConnection.java```
add your URL connection which should be something like this ```jdbc:mysql://localhost:3357/my_db```the ```user``` has to be ```root``` and the ```password``` has to be set to the same you have in your ```compose-variables.env``` file

once you have configured all your variables, you have to run ```docker-compose up -d``` where your compose file is set up, that should bring up the two containers, if your containers are up and running you should be able to get to ```http://localhost:8081``` to see the PHP My Admin interface and inside your db with 5 dummy data inserted. 

if all set up, you should be able to execute the UI file, and get running the project











