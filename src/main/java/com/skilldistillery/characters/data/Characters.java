package com.skilldistillery.characters.data;

public class Characters {
	private int id;
	private String name;
	private int age;
	private String gender; //make this a dropdown list if there's time
	private String role; //make this a dropdown list if there's time
	private String backstory;
	
	
	
	public Characters() {
	}



	public Characters(int id, String name, int age, String gender, String role, String backstory) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.role = role;
		this.backstory = backstory;
	}
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getBackstory() {
		return backstory;
	}

	public void setBackstory(String backstory) {
		this.backstory = backstory;
	}

	@Override
	public String toString() {
		return "Characters [name=" + name + ", age=" + age + ", gender=" + gender + ", position=" + role
				+ ", backstory=" + backstory + "]";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((backstory == null) ? 0 : backstory.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
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
		Characters other = (Characters) obj;
		if (age != other.age)
			return false;
		if (backstory == null) {
			if (other.backstory != null)
				return false;
		} else if (!backstory.equals(other.backstory))
			return false;
		if (gender == null) {
			if (other.gender != null)
				return false;
		} else if (!gender.equals(other.gender))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		return true;
	}

	
		
}
