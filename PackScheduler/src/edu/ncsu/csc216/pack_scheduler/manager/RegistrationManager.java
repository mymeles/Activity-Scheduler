package edu.ncsu.csc216.pack_scheduler.manager;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.directory.FacultyDirectory;
import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * This class creates the Registration Manager login session by utilizing the
 * Singleton model, after validating the user's login credentials and
 * determining there is not a current active instance of Registration Manager
 * 
 * @author Darren Gonsalves, Alex Bernard, Meles Meles
 */
public class RegistrationManager {

	/** Singleton instance of RegistrationManager */
	private static RegistrationManager instance;
	/** Catalog of available courses */
	private CourseCatalog courseCatalog;
	/** Directory of currently enrolled students */
	private StudentDirectory studentDirectory;
	/** Directory of currently teaching faculty */
	private FacultyDirectory facultyDirectory;
	/** Registrar user that is performing Manager functions */
	private User registrar;
	/** The current user of the PackScheduler */
	private User currentUser;
	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";
	/** Properties file */
	private static final String PROP_FILE = "registrar.properties";

	/**
	 * Private constructor of the RegistartionManager session
	 */
	private RegistrationManager() {
		createRegistrar();
		studentDirectory = new StudentDirectory();
		courseCatalog = new CourseCatalog();
		facultyDirectory = new FacultyDirectory();
	}

	/**
	 * Attempts to create a valid Registrar session by reading the PROP_FILE
	 */
	private void createRegistrar() {
		
		Properties prop = new Properties();

		try (InputStream input = new FileInputStream(PROP_FILE)) {
			prop.load(input);

			String hashPW = hashPW(prop.getProperty("pw"));

			registrar = new Registrar(prop.getProperty("first"), prop.getProperty("last"), prop.getProperty("id"),
					prop.getProperty("email"), hashPW);
		} catch (IOException e) {
			throw new IllegalArgumentException("Cannot create registrar.");
		}
	}

	/**
	 * Method that attempts to hash the received password to validate login session
	 * after it is compared against the stored hashed passwords
	 * 
	 * @param pw is the password received from the UI for hashing
	 * @return the successfully SHA-256 hashed password
	 * 
	 */
	private String hashPW(String pw) {
		try {
			MessageDigest digest1 = MessageDigest.getInstance(HASH_ALGORITHM);
			digest1.update(pw.getBytes());
			return new String(digest1.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("Cannot hash password");
		}
	} 

	/**
	 * Validates that there is no current instance of the Registration Manager
	 * 
	 * @return a new instance if there is not current Registrar instance
	 */
	public static RegistrationManager getInstance() {
		if (instance == null) {
			instance = new RegistrationManager();
		}
		return instance;
	}

	/**
	 * Gets the requested Course Catalog for the manager to utilize
	 * 
	 * @return the requested Course Catalog from the files
	 */
	public CourseCatalog getCourseCatalog() {
		return courseCatalog;
	}

	/**
	 * Gets the requested Student Directory after being called by the UI
	 * 
	 * @return returns the requested directory
	 */
	public StudentDirectory getStudentDirectory() {
		return studentDirectory;
	}
	
	/**
	 * Gets the requested Faculty Directory after being called by the UI
	 * 
	 * @return returns the requested directory
	 */
	public FacultyDirectory getFacultyDirectory() {
		return facultyDirectory;
	}
 
	/**
	 * Validates the Registrar's session against the known hashed password in the
	 * log
	 * 
	 * @param id       unique to the user logging in
	 * @param password matching password to the user logging in
	 * @return true if the user's ID and hashed password match what is stored in the
	 *         system otherwise returns false.
	 * 
	 * @throws IllegalArgumentException if the hash algorithm is not working
	 * @throws IllegalArgumentException if student is null. 
	 */
	public boolean login(String id, String password) {
		if (currentUser != null) {
			return false;
		}
 
		if (registrar.getId().equals(id)) {

			MessageDigest digest;
			try {
				digest = MessageDigest.getInstance(HASH_ALGORITHM);
				digest.update(password.getBytes());
				String localHashPW = new String(digest.digest());
				if (registrar.getPassword().equals(localHashPW)) {
					currentUser = registrar;
					return true;
				}
			} catch (NoSuchAlgorithmException e) {
				throw new IllegalArgumentException();
			}
		
		} else if(facultyDirectory.getFacultyById(id) != null) {
			Faculty f = facultyDirectory.getFacultyById(id);
			try {
				MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
				digest.update(password.getBytes());
				String localHashPW = new String(digest.digest());
				if (f.getPassword().equals(localHashPW)) {
					currentUser = f;
					return true;
				}
			} catch (NoSuchAlgorithmException e) {
				throw new IllegalArgumentException();
			}
		}
		
		else {
			Student s = studentDirectory.getStudentById(id);
			if (s == null) {
				throw new IllegalArgumentException("User doesn't exist.");
			}
			try {
				MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
				digest.update(password.getBytes());
				String localHashPW = new String(digest.digest());
				if (s.getPassword().equals(localHashPW)) {
					currentUser = s;
					return true;
				}
			} catch (NoSuchAlgorithmException e) {
				throw new IllegalArgumentException();
			}
		}
		return false;

	}

	/**
	 * Log the current user out of the WolfScheduler
	 */
	public void logout() {
		currentUser = null; // registrar 
	}

	/**
	 * Gets the current user that is logging into the WolfScheduler for verification
	 * of a valid login session
	 * 
	 * @return current user login session
	 */
	public User getCurrentUser() {
		return currentUser;
	}
	/**
	 * Returns true if the logged in student can enroll in the given course.
	 * @param c Course to enroll in
	 * @return true if enrolled
	 */
	public boolean enrollStudentInCourse(Course c) {
	    if (!(currentUser instanceof Student)) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    try {
	        Student s = (Student)currentUser;
	        Schedule schedule = s.getSchedule();
	        CourseRoll roll = c.getCourseRoll();
	        
	        if (s.canAdd(c) && roll.canEnroll(s)) {
	            schedule.addCourseToSchedule(c);
	            roll.enroll(s);
	            return true;
	        }
	        
	    } catch (IllegalArgumentException e) {
	        return false;
	    }
	    return false;
	}

	/**
	 * Returns true if the logged in student can drop the given course.
	 * @param c Course to drop
	 * @return true if dropped
	 */
	public boolean dropStudentFromCourse(Course c) {
	    if (!(currentUser instanceof Student)) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    try {
	        Student s = (Student)currentUser;
	        c.getCourseRoll().drop(s);
	        return s.getSchedule().removeCourseFromSchedule(c);
	    } catch (IllegalArgumentException e) {
	        return false; 
	    }
	}

	/**
	 * Resets the logged in student's schedule by dropping them
	 * from every course and then resetting the schedule.
	 */
	public void resetSchedule() {
	    if (!(currentUser instanceof Student)) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    try {
	        Student s = (Student)currentUser;
	        Schedule schedule = s.getSchedule();
	        String [][] scheduleArray = schedule.getScheduledCourses();
	        for (int i = 0; i < scheduleArray.length; i++) {
	            Course c = courseCatalog.getCourseFromCatalog(scheduleArray[i][0], scheduleArray[i][1]);
	            c.getCourseRoll().drop(s);
	        }
	        schedule.resetSchedule();
	    } catch (IllegalArgumentException e) {
	        //do nothing 
	    } 
	}

	/** 
	 * Method for clearing the current course catalog and clearing the current
	 * Student Directory should the method be called.
	 */
	public void clearData() {
		courseCatalog = new CourseCatalog();
		studentDirectory = new StudentDirectory();
		facultyDirectory = new FacultyDirectory();
	}
 
	/**
	 * Inner class of the RegistrationManager that logs the user in but as a
	 * Registrar
	 * 
	 * @author darrengonsalves
	 *
	 */
	private static class Registrar extends User {
		/**
		 * Create a registrar
		 * 
		 * @param firstName of the verified registrar user from client input
		 * @param lastName of the verified registrar user from client input
		 * @param id of the verified registrar user from client input
		 * @param email of the verified registrar user from client input
		 * @param hashPW of the verified registrar user from client input
		 */
		public Registrar(String firstName, String lastName, String id, String email, String hashPW) {
			super(firstName, lastName, id, email, hashPW);
		}
	}
}
