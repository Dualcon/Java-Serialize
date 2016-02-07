# Writing And Reading Objects and Arrays To A Binary File


Java provides a mechanism, called object serialization where an object can be represented as a sequence of bytes that includes the object's data as well as information about the object's type and the types of data stored in the object.
After a serialized object has been written into a file, it can be read from the file and deserialized that is, the type information and bytes that represent the object and its data can be used to recreate the object in memory.
Most impressive is that the entire process is JVM independent, meaning an object can be serialized on one platform and deserialized on an entirely different platform.
Classes ObjectInputStream and ObjectOutputStream are high-level streams that contain the methods for serializing and deserializing an object.


Here is an example.


## Person class.


package com.wikidreams.serializable;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Person implements Serializable {

	private String name;
	private int age;

	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}


## Main class.


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


## SerializableManager class.


package com.wikidreams.serializable;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.List;

public class SerializableManager {


	/* Serialize objects */

	public static void serializeObject(Person person, String filename) {
		// Creating the space in memory for the file.
		ObjectOutputStream outputStream = null;
		// Writing the object to the file.
		try {
			outputStream = new ObjectOutputStream(new FileOutputStream(filename));
			outputStream.writeObject(person);
			// close the stream.
			outputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Person deserializeObject(String filename) {
		// Now that we are done writing the object to the binary file we will need to read the information back into a new object.
		// We are going to create a new input stream in the same way that we made the output stream.
		ObjectInputStream inputStream = null;
		try {
			inputStream = new ObjectInputStream(new FileInputStream(filename));
		} catch (Exception e) {
			e.printStackTrace();
		}
		//Create new blank object to store the data.
		Person person = null;
		// add data to the object.
		try {
			person = (Person)inputStream.readObject();
			// close the stream
			inputStream.close();
			return person;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}


	/* Serialize Lists */

	@SuppressWarnings("rawtypes")
	public static void serializeList(List list, String filename) {
		try {
			OutputStream file = new FileOutputStream(filename);
			OutputStream buffer = new BufferedOutputStream(file);
			ObjectOutput output = new ObjectOutputStream(buffer);
			output.writeObject(list);
			output.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	@SuppressWarnings("rawtypes")
	public static List deserializeList(String filename) {
		try {
			InputStream file = new FileInputStream(filename);
			InputStream buffer = new BufferedInputStream(file);
			ObjectInput input = new ObjectInputStream (buffer);
			List recoveredQuarks = (List)input.readObject();
			input.close();
			return recoveredQuarks;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}


Produced by [Wiki Dreams.github.io](https://WikiDreams.github.io/).