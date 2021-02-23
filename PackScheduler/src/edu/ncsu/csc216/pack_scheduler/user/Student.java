package edu.ncsu.csc216.pack_scheduler.user;

/**
 * student class for student information.
 * 
 * @author meles
 *
 */
public class Student implements Comparable<Student> {

	/** filed for last name */
	private String firstName;
	/** filed for last name */
	private String lastName;
	/** filed id */
	private String id;
	/** filed for email */
	private String email;
	/** filed for hashPW */
	private String password;
	/** field for max credit */
	private int maxCredits;
	/** Constant for maximum credit */
	public final static int MAX_CREDITS = 18;

	/**
	 * first name field that passes through setFristName
	 * 
	 * @param firstName  lastName field that passes through setLastName
	 * @param lastName   id field that passes through setID
	 * @param id         email field that passes through setEmail
	 * @param email      hashPW filed that passes through setHashPW
	 * @param hashPW     manxCredit that passes as an integer
	 * @param maxCredits
	 * 
	 *                   Student constructor if maxCredit is passed
	 * 
	 */
	public Student(String firstName, String lastName, String id, String email, String hashPW, int maxCredits) {
		setFirstName(firstName);
		setLastName(lastName);
		setId(id);
		setEmail(email);
		setPassword(hashPW);
		setMaxCredits(maxCredits); 

	}

	/**
	 * 
	 * 
	 * first name field that passes through setFristName with default MAX_CREDIT
	 * 
	 * @param firstName lastName field that passes through setLastName with default
	 *                  MAX_CREDIT
	 * 
	 * @param lastName  id field that passes through setID with default MAX_CREDIT
	 * @param id        email field that passes through setEmail with default
	 *                  MAX_CREDIT
	 * @param email     hashPW filed that passes through setHashPW with default
	 *                  MAX_CREDIT
	 * @param hashPW
	 * 
	 *                  maxCredit that passes as an integer of 18
	 * @constant 18 calls the default constructors with the default max credit value
	 *           of 18
	 * 
	 */
	public Student(String firstName, String lastName, String id, String email, String hashPW) {
		this(firstName, lastName, id, email, hashPW, 18);
	}

	/**
	 * Returns email address from the parameter
	 * 
	 * @return email(null, LAST_NAME, ID, EMAIL, PASSWORD, CREDITS);
	 */

	public String getEmail() {
		return email;
	}

	/**
	 * sets email to the giving parameter and checks if email is null or empty also
	 * that it contains the char "@" "." if not @throws IAE
	 * 
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		if (email == null || "".equals(email)) {
			throw new IllegalArgumentException("Invalid email");
		}

		int firstIndexOfAtSymbol = email.indexOf('@');
		int lastIndexOfDotSymbol = email.lastIndexOf('.');

		if (firstIndexOfAtSymbol == -1 || lastIndexOfDotSymbol == -1 || lastIndexOfDotSymbol < firstIndexOfAtSymbol) {
			throw new IllegalArgumentException("Invalid email");

		}
		this.email = email;
	}

	/**
	 * returns the password from the parameter indexOfAtSymbol == -1
	 * 
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * returns the value of hashPW if it is not null or empty Otherwise it @throws
	 * IAE
	 * 
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		if (password == null || "".equals(password)) {
			throw new IllegalArgumentException("Invalid password");
		}
		this.password = password;
	}

	/**
	 * returns the value of MaxCredit from the parameter
	 * 
	 * @return the maxCredits
	 */

	public int getMaxCredits() {
		return maxCredits;
	}

	/** constant for MIN_CREDIT to use in the below method */
	final static int MIN_CREDIT = 3;

	/**
	 * sets the maxCredit to the given parameter
	 * 
	 * @param maxCredits Checks if the parameter is less than 3 or greater than 18
	 *                   if it is @throw IAE
	 * 
	 */

	public void setMaxCredits(int maxCredits) {
		if (maxCredits < 0 || maxCredits > MAX_CREDITS) {
			throw new IllegalArgumentException("Invalid max credits");
		}

		this.maxCredits = maxCredits;
	}

	/**
	 * sets the first name to the given parameter if it is not null or empty if so
	 * it @throws an exception
	 * 
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		if (firstName == null || "".equals(firstName)) {
			throw new IllegalArgumentException("Invalid first name");
		}
		this.firstName = firstName;
	}

	/**
	 * sets the last name to the given parameter if it is not null or empty if so it
	 * 
	 * @throw an exception
	 * 
	 * @param lastName the firstName to set
	 */
	public void setLastName(String lastName) {
		if (lastName == null || "".equals(lastName)) {
			throw new IllegalArgumentException("Invalid last name");
		}
		this.lastName = lastName;
	}

	/**
	 * set the value of id by checking if it is null or empty if so @throw IAE
	 * 
	 * @param id the id to set
	 */
	private void setId(String id) {
		if (id == null || "".equals(id)) {
			throw new IllegalArgumentException("Invalid id");
		}
		this.id = id;
	}

	/**
	 * returns firstName from the parameter
	 * 
	 * @return firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * returns the value of lastName from the parameter
	 * 
	 * @return lastName
	 */
	public String getLastName() {

		return lastName;
	}

	/**
	 * returns the value of id from the parameter
	 * 
	 * @return id
	 */
	public String getId() {

		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + maxCredits;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (maxCredits != other.maxCredits)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return firstName + "," + lastName + "," + id + "," + email + "," + password + "," + maxCredits;
	}
 
	
	/**
	 * questions to ask TA's 
	 * is my code correct and what does the values from comapreTo mean?
	 * how do i add the ClasscastEception in the comaPARE CODE -- this is automatic 
	 */
	@Override
	
	public int compareTo(Student s) {  
		if (s == null) {
			throw new NullPointerException();
		}
		if(this.lastName.compareToIgnoreCase(s.getLastName()) == 0 && this.firstName.compareToIgnoreCase(s.getFirstName()) == 0 && this.id.compareToIgnoreCase(s.getId()) == 0) {
			return 0;
		}
	
		if (this.lastName.compareToIgnoreCase(s.getLastName()) < 0) {
			return -1;
			// this M    > A  npositive  value 
			// 
		} else if (this.lastName.compareToIgnoreCase(s.getLastName()) > 0) {
			return 1; 
		} else if (this.lastName.compareToIgnoreCase(s.getLastName()) == 0) {
			if (this.firstName.compareToIgnoreCase(s.getFirstName()) < 0) {
				return -1;
			} else if (this.firstName.compareToIgnoreCase(s.getFirstName()) > 0) {
				return 1;
			} else if (this.firstName.compareToIgnoreCase(s.getFirstName()) == 0) {
				if(this.id.compareToIgnoreCase(s.getId()) < 0) {
					return -1;
				}
				else if(this.id.compareToIgnoreCase(s.getId()) > 0) {
					return 1;
				}
				
			}

		} 
			return 0;
		
	}

}
