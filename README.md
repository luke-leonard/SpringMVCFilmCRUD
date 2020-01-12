## Spring MVC Film C.R.U.D. Project

### Authors

- Luke Leonard
- George Moore


### Overview

* Provides users with the ability to search for films, create, edit and delete records from the database. Input is required from a user for the film id, and search keyword.

* Film details can be displayed in two categories. An individual film or a list of films from a search keyword each displaying title, description, release year,  language id, rental duration, rental rate, length, replacement cost, rating, actor list and categories.


### Concepts

* Display a homepage welcoming users and offer the ability to display a film(s) matching a film id/search term. The homepage also offers the ability to add a new film to the database where the film id will be generated by incrementing last id assigned.

* Homepage presents two fields for interacting with our website. The user will be presented with home button links on each page of the website.

	* Inputing a search term/id in the search field and submitting via a submit button will redirect the user to a the List Films page.

    * Selecting the create film button the homepage will redirect the user to the modify film page.

       * List page returns a list of films matching the input term. Films are displayed as hyperlinks, selecting a film will redirect the user to the Film page.

        * Film Page displays all details of the film as described in above Overview. The user is presented with two options via buttons to edit or delete the film. Only user input films are capable of being deleted. Each film in the database is capable of being edited redirecting the user to modify film page.

        * Modify film page will allow the user to input all film fields described above. If the film already existed the film will be updated in the database, otherwise the film will be created in the database and given an id.

        * Delete button will delete the film and redirect to the homepage upon confirmation. If the film was unable to be deleted the user will be redirected to an error page.


### Technologies Used

*  SQL : Standardized Query Language, query utilizing select, joins, predicates and          functions.
* ORM : Object-Relational Mapping @RM
* JDBC : Java Database Connectivity
* DAO : Data Accessor Object implementation
* JSP : JavaServer Page files to display webpages
* STS : Spring Tool Suite
* Gradle
* MySQL Workbench
* Apache Server
