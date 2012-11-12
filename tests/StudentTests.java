package tests;
import model.*;
import junit.framework.TestCase;

/**
 * Please put your own test cases into this file.
*/

public class StudentTests extends TestCase {
	//Tests basic methods of the Person Class
	public void testPerson() {
		Person person = new Person("Mary", "mary@cs.notreal.edu", "F",
				"Single", "MaryPhoto.jpg");
		assertTrue(person.getEmail().equals("mary@cs.notreal.edu"));
		assertTrue(person.getName().equals("Mary"));
		assertTrue(person.getGender().equals("F"));
		assertTrue(person.getPhotoURL().equals("MaryPhoto.jpg"));
		assertTrue(person.getRelationshipStatus().equals("Single"));
		assertEquals(person.getNetworks().size(), 0);
		person.setEmail("");
		person.setName("");
		person.setPhotoURL("");
		person.setStatus("");
		
		assertTrue(person.getEmail().equals(""));
		assertTrue(person.getName().equals(""));
		assertTrue(person.getStatus().equals(""));
		assertTrue(person.getPhotoURL().equals(""));
	}
	//Tests methods of each network class
	public void testNetworks() {
		HighSchoolNetwork hs = new HighSchoolNetwork("School", "school");
		Person mary = new Person("Mary", "mary@cs.notreal.edu", "F",
				"Single", "MaryPhoto.jpg");
		mary.setHighSchool(hs, 1990, true);
		RegionalNetwork region = new RegionalNetwork("MD", "Maryland");
		mary.setRegion(region);
		CollegeNetwork college = new CollegeNetwork("College", "College");
		mary.setCollege(college, 1994, "CS");
		
		//Just check if it can print out all the networks
		System.out.print(mary.getNetworks().toString());
		assertTrue(hs.getSchool().equals("school"));
		assertTrue(hs.getYear(mary) == 1990);
		assertTrue(hs.wentCollege(mary));
		assertTrue(hs.getGroup().iterator().next().getName().equals("Mary"));
		hs.remove(mary);
		assertTrue(hs.getGroup().size() ==0);
		
		assertTrue(region.getArea().equals("Maryland"));
		assertTrue(region.getGroup().size() ==1);
		region.remove(mary);
		assertTrue(region.getGroup().size() ==0);
		
		assertTrue(college.getCollege().equals("College"));
		assertTrue(college.getYear(mary) == 1994);
		assertTrue(college.getPersonsMajor(mary).equals("CS"));
		assertTrue(college.getGroup().iterator().next().getName().equals("Mary"));
		college.remove(mary);
		assertTrue(college.getGroup().size() ==0);
	}
	//Tests what FacebookJrDB method wasn't covered by the public tests
	public void testNotCovered() {
		FacebookJrDBManager manager = new FacebookJrDB();
		
		loadData(manager);
		
		assertTrue(manager.getEmail("Jose").equals("jose@cs.notreal.edu"));
		assertTrue(manager.getGender("Jose").equals("M"));
		assertTrue(manager.getPersonPhotoURL("Jose").equals("JosePhoto.jpg"));
		manager.updateStatus("Jose", "AHHH");
		assertTrue(manager.getStatus("Jose").equals("AHHH"));
		assertTrue(manager.getNetworks("Jose").toString().equals("[]"));
		manager.acceptFriendInvite("Jose", "Mary");
		assertTrue(manager.removeFriend("Mary", "Jose"));
		assertTrue(manager.removePerson("Jose"));
		
		
	}
	//Support method to test FacebookJRDBManager
	private void loadData(FacebookJrDBManager manager) {
		manager.addPerson("Mary", "F", "Single", "mary@cs.notreal.edu", "MaryPhoto.jpg");
		manager.addPerson("Jose", "M", "Married", "jose@cs.notreal.edu", "JosePhoto.jpg");
		manager.addPerson("Peter", "M", "Single", "peter@cs.notreal.edu", null);
		manager.addPerson("Kelly", "F", "Married", "kelly@cs.notreal.edu", "KellyPhoto.jpg");
		manager.addPerson("Rachel", "F", "Married", "rachel@cs.notreal.edu", null);
		
		manager.addHighSchoolNetwork("SilverSpringHigh", "Silver Spring High");
		manager.addHighSchoolNetwork("BethesdaHigh", "Bethesda High");
		manager.addHighSchoolNetwork("WashingtonHigh", "Washington High");
		
		manager.addRegionalNetwork("California", "State of California");
		manager.addRegionalNetwork("Maine", "State of Maine");
		manager.addRegionalNetwork("Florida", "State of Florida");
		
		manager.addCollegeNetwork("UMCP", "University of Maryland College Park");
		manager.addCollegeNetwork("AU", "American University Washington DC");
		manager.addCollegeNetwork("Purdue", "Purdue University");
		manager.getAllNetworks();
	}
	
	
}