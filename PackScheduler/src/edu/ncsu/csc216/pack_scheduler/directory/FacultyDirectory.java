package edu.ncsu.csc216.pack_scheduler.directory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;

import edu.ncsu.csc216.pack_scheduler.io.FacultyRecordIO;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

public class FacultyDirectory {

	/** List of Facultys in the directory */
	private LinkedList<Faculty> facultyDirectory;
	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";

	/**
	 * Creates an empty Faculty directory
	 */
	public FacultyDirectory() {
		newFacultyDirectory();
	}

	/**
	 * Creates an empty Faculty directory. All Facultys in the previous list are
	 * list unless saved by the user.
	 */
	public void newFacultyDirectory() {
		facultyDirectory = new LinkedList<Faculty>();
	}

	/**
	 * Constructs the Faculty directory by reading in Faculty information from the
	 * given file. Throws an IllegalArgumentException if the file cannot be found.
	 * 
	 * @param fileName file containing list of Facultys
	 */
	public void loadFacultysFromFile(String fileName) {
		try {
			facultyDirectory = FacultyRecordIO.readFacultyRecords(fileName);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to read file " + fileName);
		}
	}

	/**
	 * Adds a Faculty to the directory. Returns true if the Faculty is added and
	 * false if the Faculty is unable to be added because their id matches another
	 * Faculty's id.
	 * 
	 * This method also hashes the Faculty's password for internal storage.
	 * 
	 * @param firstName      Faculty's first name
	 * @param lastName       Faculty's last name
	 * @param id             Faculty's id
	 * @param email          Faculty's email
	 * @param password       Faculty's password
	 * @param repeatPassword Faculty's repeated password
	 * @param maxCredits     Faculty's max credits.
	 * @return true if added
	 */
	public boolean addFaculty(String firstName, String lastName, String id, String email, String password,
			String repeatPassword, int maxCredits) {
		String hashPW = "";
		String repeatHashPW = "";
		if (password == null || repeatPassword == null || "".equals(password) || "".equals(repeatPassword)) {
			throw new IllegalArgumentException("Invalid password");
		}

		try {
			MessageDigest digest1 = MessageDigest.getInstance(HASH_ALGORITHM);
			digest1.update(password.getBytes());
			hashPW = new String(digest1.digest());

			MessageDigest digest2 = MessageDigest.getInstance(HASH_ALGORITHM);
			digest2.update(repeatPassword.getBytes());
			repeatHashPW = new String(digest2.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("Cannot hash password");
		}

		if (!hashPW.equals(repeatHashPW)) {
			throw new IllegalArgumentException("Passwords do not match");
		}

		Iterator<Faculty> iter = facultyDirectory.iterator();
		while (iter.hasNext()) {
			if (iter.next().getPassword().equals(password)) {
				throw new IllegalArgumentException("Faculty already in system");
			}
		}
		// If an IllegalArgumentException is thrown, it's passed up from Faculty
		// to the GUI
		Faculty faculty = new Faculty(firstName, lastName, id, email, hashPW, maxCredits);

		for (int i = 0; i < facultyDirectory.size(); i++) {
			User s = facultyDirectory.get(i);
			if (s.getId().equals(faculty.getId())) {
				return false;
			}
		}
		return facultyDirectory.add(faculty);
	}

	/**
	 * Removes the Faculty with the given id from the list of Facultys with the
	 * given id. Returns true if the Faculty is removed and false if the Faculty is
	 * not in the list.
	 * 
	 * @param FacultyId Faculty's id
	 * @return true if removed
	 */
	public boolean removeFaculty(String FacultyId) {
		for (int i = 0; i < facultyDirectory.size(); i++) {
			User s = facultyDirectory.get(i);
			if (s.getId().equals(FacultyId)) {
				facultyDirectory.remove(i);
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns all Facultys in the directory with a column for first name, last
	 * name, and id.
	 * 
	 * @return String array containing Facultys first name, last name, and id.
	 */
	public String[][] getFacultyDirectory() {
		String[][] directory = new String[facultyDirectory.size()][3];
		for (int i = 0; i < facultyDirectory.size(); i++) {
			User s = facultyDirectory.get(i);
			directory[i][0] = s.getFirstName();
			directory[i][1] = s.getLastName();
			directory[i][2] = s.getId();
		}
		return directory;
	}

	/**
	 * Saves all Facultys in the directory to a file.
	 * 
	 * @param fileName name of file to save Facultys to.
	 */
	public void saveFacultyDirectory(String fileName) {
		try {
			FacultyRecordIO.writeFacultyRecords(fileName, facultyDirectory);
		} catch (IOException e) {
			throw new IllegalArgumentException("Unable to write to file " + fileName);
		}
	}

	/**
	 * Gets the Faculty object matches the input Faculty ID. If a Faculty is not
	 * located through the Id, then null is returned.
	 * 
	 * @return the Faculty object that matches the input id
	 * @param id of the unique faculty object
	 */
	public Faculty getFacultyById(String id) {
		for (int i = 0; i < getFacultyDirectory().length; i++) {
			if (facultyDirectory.get(i).getId().equals(id)) {
				return facultyDirectory.get(i);
			}
		}
		return null;
	}

}
