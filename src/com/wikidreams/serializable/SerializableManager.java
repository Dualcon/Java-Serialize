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

