Coverage: 72.5% - tests for the data access objects of items and orders are failing although the implementation of the tests was the same as for customers which is passing all tests.
# Project Title

One Paragraph of project description goes here
In this project I have been tasked with creating an Inventory Management System (IMS) that uses CRUD functionality so a user is able to manipulate data in an SQL database from a command line interface. Unfortunately this program does not allow the feature of adding or deleting items from a single order but orders can be added and deleted to and from one customer. Also, there is an additional functionality of updating any parameter of an order. In this project I have also had to write test cases with an aim of 80% test coverage for my program which is the industry standard.

## Getting Started

Fork the repository and open as a maven project in your IDE or run from a command line (see deployment).

### Prerequisites

What things you need to install the software

- IDE compatible with Java: Recommended to use Eclipse - https://www.eclipse.org/downloads/packages/installer#:~:text=5%20Steps%20to%20Install%20Eclipse%201%20Download%20the,available%20to%20Eclipse%20users.%20...%20More%20items...%20
- MySQL installed  locally: This is where the data will be stored - https://www.mysql.com/downloads/
Once installed, you will need to work from a database named 'ims'for the program to work.
- Maven: Required to build the project and compatible with Eclipse IDE
- Java 14
- Github account to access the repository and access to a command terminal e.g. Git bash

### Installing

To get a development environment up and running clone the repository onto your device and open in Eclipse as a maven project. You will then run as a Java application in order to use the program. See deployment for instructions on how to run the program from a command line.

## Running the tests

Instructions on how to run the tests:

- Go to src\test\java
- the controllers package contains tests for the crud functionality for customers, items  and orders which we use mockito for 
- the persistence packages contains the tests for the domains (Customer/Item/Order) and the data access objects of those domains
- Open the domain/dao/controller class you would like to test
- Right click on the workspace and select Run > As JUnit Test.
You can also run the whole project as a JUnit by right clicking on the project and selecting Run > As JUnit Test.

### Unit Tests 

Unit tests were used to test the domains and the data access objects of the domains

@Test
	public void testUpdate() {
		final Customer updated = new Customer(1L, "chris", "perrins");
		assertEquals(updated, DAO.update(updated));
		}

This is an example of a unit test to test the update method in the data access object for customer.

### Integration Tests 
Explain what these tests test, why and how to run them
Integration testing using mockito tests if the controller classes interpret the dummy data in the expected way and dish out appropriate results.

@Test
	public void testUpdate() {
		Customer updated = new Customer(1L, "chris", "perrins");

		Mockito.when(this.utils.getLong()).thenReturn(1L);
		Mockito.when(this.utils.getString()).thenReturn(updated.getFirstName(), updated.getSurname());
		Mockito.when(this.dao.update(updated)).thenReturn(updated);

		assertEquals(updated, this.controller.update());

		Mockito.verify(this.utils, Mockito.times(1)).getLong();
		Mockito.verify(this.utils, Mockito.times(2)).getString();
		Mockito.verify(this.dao, Mockito.times(1)).update(updated);
	}
	
This is an example of using testing using mockito on the customer controller class.

## Deployment

From the command line, once you fork the repository, change the directory to \target and run the .jar file using: java -jar ims-0.0.1-jar-with-dependencies.jar 
Use mvn clean package in the target to build out application again.

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

We use [SemVer](http://semver.org/) for versioning.

## Authors

* **Chris Perrins** - *Initial work* - [christophperrins](https://github.com/christophperrins)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

## Acknowledgments

A big thank you to Morgan Walsh and Aswene Sivaraj for guidance through this project.
