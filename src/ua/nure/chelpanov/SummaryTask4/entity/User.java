package ua.nure.chelpanov.SummaryTask4.entity;

public class User extends Entity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2827414946465914504L;
	private String Username;
	private String FullName;
	private String email;
	private String password;
	private int roleID;

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getFullName() {
		return FullName;
	}

	public void setFullName(String fullName) {
		FullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRoleID() {
		return roleID;
	}

	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((FullName == null) ? 0 : FullName.hashCode());
		result = prime * result + ((Username == null) ? 0 : Username.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + roleID;
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
		User other = (User) obj;
		if (FullName == null) {
			if (other.FullName != null)
				return false;
		} else if (!FullName.equals(other.FullName))
			return false;
		if (Username == null) {
			if (other.Username != null)
				return false;
		} else if (!Username.equals(other.Username))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (roleID != other.roleID)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [Username=" + Username + ", FullName=" + FullName + ", email=" + email + ", password=" + password
				+ ", roleID=" + roleID + "]";
	}

}
