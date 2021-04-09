package edu.ncsu.csc216.pack_scheduler.user;

/**
 * User is an abstract class that contains shared fields and methods that are
 * used in the user and registrar child classes
 * 
 * @author Alex Bernard
 *
 */
public abstract class User {

	/** The user's first name. */
	private String firstName;
	/** The user's last name. */
	private String lastName;
	/** The user's id. */
	private String id;
	/** The user's email. */
	private String email;
	/** The user's password */
	private String password;

	/**
	 * The User constructor sets the firstName, lastName, id, email and password
	 * fields that are present in all child classes
	 * 
	 * @param firstName The firstName of the user object
	 * @param lastName The lastName of the user object
	 * @param id The id of the user object
	 * @param email The email of the user object
	 * @param password The password of the user object
	 */
	public User(String firstName, String lastName, String id, String email, String password) {
		setFirstName(firstName);
		setLastName(lastName);
		setId(id);
		setEmail(email);
		setPassword(password);
	}

	/**
	 * Gives the first name stored in the user object
	 * 
	 * @return The user's first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the given first name
	 * 
	 * @param firstName The name to set
	 */
	public void setFirstName(String firstName) {
		if (firstName == null || firstName.length() == 0)
			throw new IllegalArgumentException("Invalid first name");
		else
			this.firstName = firstName;
	}

	/**
	 * Gives the last name stored in the user object
	 * 
	 * @return The user's last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the given last name.
	 * 
	 * @param lastName The given user's last name
	 */
	public void setLastName(String lastName) {
		if (lastName == null || lastName.length() == 0)
			throw new IllegalArgumentException("Invalid last name");
		else
			this.lastName = lastName;
	}

	/**
	 * Gives the id of the user object
	 * 
	 * @return The user's id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the given user Id
	 * 
	 * @param id the Id to set
	 */
	protected void setId(String id) {
		if (id == null || id.length() == 0)
			throw new IllegalArgumentException("Invalid id");
		else
			this.id = id;
	}

	/**
	 * Gives the stored user email
	 * 
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the given user email if it falls within the given criteria.
	 * 
	 * @param email the email to set.
	 * @throws IllegalArgumentException If the email is null or it does not have an
	 *                                  '@' symbol before a '.' symbol.
	 */
	public void setEmail(String email) {
		boolean hasAtSymbol = false;
		boolean hasPeriod = false;
		int periodIndex = 0;
		int symbolIndex = 0;
		if (email == null || email.length() == 0)
			throw new IllegalArgumentException("Invalid email");
		for (int i = 0; i < email.length(); i++) {
			if (email.charAt(i) == '@') {
				symbolIndex = i;
				hasAtSymbol = true;
			} else if (email.charAt(i) == '.') {
				periodIndex = i;
				hasPeriod = true;
			}
		}
		if (periodIndex < symbolIndex || !(hasAtSymbol && hasPeriod))
			throw new IllegalArgumentException("Invalid email");
		this.email = email;
	}

	/**
	 * Gives the stored user password.
	 * 
	 * @return the password.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the given password.
	 * 
	 * @param password the password to set.
	 * @throws IllegalArgumentException If password is null or empty.
	 */
	public void setPassword(String password) {
		if (password == null || password.length() == 0)
			throw new IllegalArgumentException("Invalid password");
		this.password = password;
	}

	/**
	 * Generates a hashCode based on the firstName, lastName, id, email, and
	 * password fields of User.java
	 * 
	 * @return A hashCode generated from User.java's fields
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}

	/**
	 * Determines if the given and current object are equal based on the contents of
	 * both objects' firstName, lastName, id, email, and password fields
	 * 
	 * @return True if the two objects are of the same type and the fields of each
	 *         one are equal.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
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
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

}