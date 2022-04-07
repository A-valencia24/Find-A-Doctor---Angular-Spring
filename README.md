<h1>Server calls</h1>


All (patient/doctor/appointment)
-----------------------
*Brackets "()" signify a variable

**(GET)**

`localhost:5050/(patient)`
*-Returns all info from that table*

`localhost:5050/(patient)/(id)`
*-Returns object with the specified id*


**(POST)**

`localhost:5050/(patient)`
*-Saves object (ids are created server-side)*

`localhost:5050/(patient)/group`
*-Saves multiple objects*


**(PUT)**

`localhost:5050/(patient)/(id)`
*-Updates object with the specified id*

**(DELETE)**

`localhost:5050/(patient)`
*-Deletes object with the specified id*

`localhost:5050/(patient)/deleteAll`
*-Deletes whole table*

Patients
-----------------------
**(GET)**

`localhost:5050/patient/login/(name@email.com)/(password)`
*-Searches for specified email/password combo in database, returns patient object*

Doctors
-----------------------
**(GET)**

`localhost:5050/doctor/login/(name@email.com)/(password)`
*-Searches for specified email/password combo in database, returns doctor object*

`localhost:5050/doctor/search/(lastName)/(state)/(specialty)`
*-Searches table based on any combination of search terms, returns doctor(s)

-(use "null" to signify an empty search term)*

Appointments
-----------------------
**(GET)**

`localhost:5050/appointment/patient/(id)`
*-Returns list of appointments containing the specified patient id*

`localhost:5050/appointment/doctor/(id)`
*-Returns list of appointments containing the specified doctor id*

