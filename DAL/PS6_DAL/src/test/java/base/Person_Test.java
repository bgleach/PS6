package base;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import domain.PersonDomainModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person_Test {
		
	private static PersonDomainModel person1;
	private static PersonDomainModel person2;
	private static UUID person1UUID = UUID.randomUUID();	
	private static UUID person2UUID = UUID.randomUUID();
	
	@BeforeClass
	public static void personInstance() throws Exception{
		
		Date person1Birth = null;
		Date person2Birth = null;

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		 person1 = new PersonDomainModel();
		 
		try {
			person1Birth = dateFormat.parse("1994-11-27");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		person1.setPersonID(person1UUID);
		person1.setFirstName("Mingkun");
		person1.setMiddleName("a");
		person1.setLastName("Chen");
		person1.setBirthday(person1Birth);
		person1.setCity("Elkton");
		person1.setStreet("702 Stone Gate Blvd");
		person1.setPostalCode(21921);
		
		person2 = new PersonDomainModel();

		try {
			person2Birth = dateFormat.parse("1997-03-12");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		person2.setPersonID(person2UUID);
		person2.setFirstName("Bill");
		person2.setMiddleName("t");
		person2.setLastName("Smith");
		person2.setBirthday(person2Birth);
		person2.setCity("Wilmington");
		person2.setStreet("27 Sanford Street");
		person2.setPostalCode(24631);
		
	}
	
	@Test
	public void addPerson() {
		PersonDAL.addPerson(person2);
		assertEquals(PersonDAL.getPerson(person2.getPersonID()).getPersonID(), person2.getPersonID());
	}

	@Test
	public void updatePerson() {
		person1.setMiddleName("Bob");
		PersonDAL.updatePerson(person1);
		assertEquals(PersonDAL.getPerson(person1.getPersonID()).getMiddleName(), "Bob");
	}

	@Test
	public void deletePerson() {
		PersonDAL.deletePerson(person2UUID);
		assertEquals(PersonDAL.getAllPersons().get(0).getPersonID(), person1.getPersonID());
	}

	@Test
	public void getAllPersons() {
		assertEquals(PersonDAL.getAllPersons().get(1).getPersonID(), person2.getPersonID());
	}

	@Test
	public void getPerson() {
		assertEquals(PersonDAL.getPerson(person1.getPersonID()), person1.getPersonID());
}
	
	

}
