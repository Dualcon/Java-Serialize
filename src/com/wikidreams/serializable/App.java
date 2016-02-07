package com.wikidreams.serializable;

import java.util.LinkedList;
import java.util.List;

public class App {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		/* Serialize and deserialize a single object. */

		// Creating the object.		
		Person person = new Person("John", 30);

		// Create the file to store the data.
		String personFilename = "person.dat";

		// Serialize the object.
		SerializableManager.serializeObject(person, personFilename);

		// Deserialize the object.
		Person newPerson = SerializableManager.deserializeObject(personFilename);

		// Display the new person.
		System.out.println("Displaing the person object.");
		System.out.println("Name: " + newPerson.getName() + " Age: " + newPerson.getAge());


		/* Serialize and deserialize a object list. */

		// Create a list of Persons.
		List<Person> persons = new LinkedList<Person>();
		persons.add(new Person("Daniel", 31));
		persons.add(new Person("Jessica", 30));

		// Create the file to store the data.
		String listFilename = "list.dat";

		// Serialize persons list.
		SerializableManager.serializeList(persons, listFilename);

		// Deserialize persons list.
		persons = SerializableManager.deserializeList(listFilename);

		// Display the persons list.
		System.out.println("Displaing the list objects.");
		for(Person item : persons) {
			System.out.println("Name: " + item.getName() + " Age: " + item.getAge());
		}

	}

}
