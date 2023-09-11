# Concesionaria Drive X Port

 Project that uses Java UI.form

# Content
1. [Developer](#devGuide)


Continuing with the project of Programacion II at Mariano Galvez

* How is the project structure
  * assets
  * data
  * DbConnection
  * Utils
  * docker-compose
  * UI
  * UI.form

Assests
Inside the assest file, you can find all the icons used in the app

Data
Inside the data file, we sotored the init2.sql, which gets executed when the compose file gets mounted, it runs a migration scritp which will create the table and insert some records on the DB

## Developer

DBConnection
In the file we can find all the functions that gets connected to the DB and executes the functions
* Connection to the DB
* Insert a new record on the DB
* Get a single record
* Update a single record
* Delete a record
* Close the DB connection

Utils
The file contains some utilities to split the app and handle it better, these includes
* PdfReport
  * Class where we stored the two functions to create a table that will include all the records from the db
* Queries
  * Class that stores all the functions and the required queries to pass it to the DB
* Validations
  * Class where we stored a function that validates if an string is empty

Compose file
The file contains the logic to run two containers, ```MySql``` using the version ```8.1.0``` and the latest version of ```phpmyadmin/phpmyadmin```

UI
The main file on the app, where all the logic, of the UI components live, buttons, labels, text field, icons, and the list of the components

Login
The file contains the logic to validate email, password of the user before given access to it.

Register
the file contains the logic and UI styles to create a new accunt, having some validations.
* Email to be a valid one
* Validate the email is not beeing used before
* Password length of a minimun of 6 characters

Create invoce
This file contains the logic of creating an invoice when a product is extracted from DB, it has validations that until the user fill all the fields it updates the DB with the corresponding data, same library was used ```itextpdf@5.5.9``` to achive the goal.

New seed created.
 When running the ```docker-compose up -d``` file at the first time this will create a new volume and it will iseert 2 users. one which is the super adming and a regular one, by default all the other users wil have a basic role.

Want to run the project?

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

* Adding a new functionality to create invoices, a simple form was added to get the user data and create the invoice

<a name="dev-guide"/>
## Dev guide
some content here

## Invoice creation
![Captura de pantalla 2023-09-11 092149](https://github.com/JTuyuc7/ConcesionariaDesktopVersion/assets/50525507/a6b1d9fa-1612-40cd-84fd-a08bd0a34343)

## Invoice form
![formInvoice](https://github.com/JTuyuc7/ConcesionariaDesktopVersion/assets/50525507/de1fd539-2857-415c-b22c-d89a70479be8)


##UI Login
![Login](https://github.com/JTuyuc7/ConcesionariaDesktopVersion/assets/50525507/5dd03799-bec3-4195-8e95-8996aec1fdde)

## Create Account
![RegisterAccount](https://github.com/JTuyuc7/ConcesionariaDesktopVersion/assets/50525507/5e42f35e-43c8-4a45-80a4-9012e2fe4a4f)


## Images
![ProyectoFinal](https://github.com/JTuyuc7/ConcesionariaDesktopVersion/assets/50525507/5a7816d3-4e10-4bbc-ad38-bf888aac2d2e)


![ProductoAgregado](https://github.com/JTuyuc7/ConcesionariaDesktopVersion/assets/50525507/f0789fc7-5769-4eac-8ae7-026938d303fb)


![EditAndUpdate](https://github.com/JTuyuc7/ConcesionariaDesktopVersion/assets/50525507/b8e4b832-642d-48c1-a9c7-41618a607968)




