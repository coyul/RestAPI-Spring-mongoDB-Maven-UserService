package serverApp.Model;

import java.util.Date;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Person {

	@Id
	private String id;
	private String name;
	private String surname;
	private Date birthDate;
	private String email;
	private String password;

	public Person() {
	}

	public Person(String name, String surname, Date birthDate, String email, String password) {
		this.name = name;
		this.surname = surname;
		this.birthDate = birthDate;
		this.email = email;
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@JsonFormat(pattern = "dd.MM.yyyy", timezone = "GMT+3")
	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
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

}
