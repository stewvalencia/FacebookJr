package tests;
import model.*;
import junit.framework.TestCase;

public class PublicTests extends TestCase {
	public void testLoadData() {
		FacebookJrDBManager manager = new FacebookJrDB();
		
		loadData(manager);
		StringBuffer result = new StringBuffer();
		result.append("After adding some people\n");
		result.append(manager.getAllPersons());
		result.append("\nAfter adding some networks\n");
		result.append(manager.getAllNetworks());
		assertTrue(TestingSupport.correctResults("pubLoadData.txt", result.toString()));
	}
	
	public void testAddingFriends() {
		FacebookJrDBManager manager = new FacebookJrDB();
		loadData(manager);
		StringBuffer result = new StringBuffer();
		result.append("Mary's friends");
		result.append(manager.getFriends("Mary"));
		result.append("\nMary is asking Jose to be her friend");
		manager.sendFriendInvite("Mary", "Jose");
		result.append("\nMary's friends after the invitation");
		result.append(manager.getFriends("Mary"));
		result.append("\nJose's friends after the invitation");
		result.append(manager.getFriends("Jose"));
	
		manager.acceptFriendInvite("Jose", "Mary");
		result.append("\nJose accepted Mary's invitation");
		result.append("\nMary's friends are ");
		result.append(manager.getFriends("Mary"));
		result.append("\nJose's friends are ");
		result.append(manager.getFriends("Jose"));
		
		result.append("\nMary is sending friend invitations to Kelly, Rachel, Peter");
		for (String name : new String[]{"Kelly", "Rachel", "Peter"})
			manager.sendFriendInvite("Mary", name);
		
		result.append("\nKelly, Rachel, Peter accepted Mary's invitation");
		for (String name : new String[]{"Kelly", "Rachel", "Peter"})
			manager.acceptFriendInvite(name, "Mary");
		
		result.append("\nMary's friends are ");
		result.append(manager.getFriends("Mary"));
		assertTrue(TestingSupport.correctResults("pubAddingFriends.txt", result.toString()));
	}
	
	public void testRefuseFriend() {
		FacebookJrDBManager manager = new FacebookJrDB();
		
		StringBuffer result = new StringBuffer();
		loadData(manager);
		manager.sendFriendInvite("Mary", "Jose");
		manager.acceptFriendInvite("Jose", "Mary");
		
		manager.sendFriendInvite("Kelly", "Rachel");
		manager.acceptFriendInvite("Rachel", "Kelly");

		result.append("Kelly's friends are ");
		result.append(manager.getFriends("Kelly"));
		result.append("\nMary's friends are ");
		result.append(manager.getFriends("Mary"));
		result.append("\nMary is asking Kelly to be her friend");
		manager.sendFriendInvite("Mary", "Kelly");
		result.append("\nMary's friends after the invitation are ");
		result.append(manager.getFriends("Mary"));
		result.append("\nKelly's friends after the invitation are ");
		result.append(manager.getFriends("Kelly"));
	
		manager.refuseFriendInvite("Kelly", "Mary");
		result.append("\nKelly refused Mary's invitation");
		result.append("\nMary's friends are ");
		result.append(manager.getFriends("Mary"));
		result.append("\nKelly's friends are ");
		result.append(manager.getFriends("Kelly"));
		assertTrue(TestingSupport.correctResults("pubRefuseFriend.txt", result.toString()));
	}

	public void testPostingToWall() {
		FacebookJrDBManager manager = new FacebookJrDB();
		StringBuffer result = new StringBuffer();
		loadData(manager);
		
		manager.sendFriendInvite("Mary", "Jose");
		manager.sendFriendInvite("Mary", "Kelly");
		manager.acceptFriendInvite("Jose", "Mary");
		manager.acceptFriendInvite("Kelly", "Mary");
		manager.sendFriendInvite("Jose", "Kelly");
		manager.acceptFriendInvite("Kelly", "Jose");
		
		result.append("Mary's friends are " + manager.getFriends("Mary"));
		result.append("\nKelly's friends are " + manager.getFriends("Kelly"));
		result.append("\nJose's friends are " + manager.getFriends("Jose"));
		
		result.append("\nPosting to Mary's wall");
		manager.postToWall("Kelly", "Mary", "Mary let's go to the party.  Kelly :)");
		manager.postToWall("Jose", "Mary", "Hello Mary, this is Jose. I hope the semester is going well");
		manager.postToWall("Kelly", "Mary", "Mary I am still working on the 132 project.  Kelly :(");

		result.append("\nMary's Wall\n");
		result.append(manager.getWallPostings("Mary"));
		assertTrue(TestingSupport.correctResults("pubPostingToWall.txt", result.toString()));		
	}
	
	public void testStatus() {
		FacebookJrDBManager manager = new FacebookJrDB();
		StringBuffer result = new StringBuffer();
		loadData(manager);
		manager.updateRelationshipStatus("Mary", "Complicated");
		result.append(manager.getRelationshipStatus("Mary"));
		assertTrue(TestingSupport.correctResults("pubStatus.txt", result.toString()));
	}
	
	public void testFindMatches() {
		FacebookJrDBManager manager = new FacebookJrDB();
		StringBuffer result = new StringBuffer();
		loadData(manager);
		
		result.append("Searching for Singles: ");
		result.append(manager.findMatches(null, "Single"));

		result.append("\nSearching for Married males: ");
		result.append(manager.findMatches("M", "Married"));

		result.append("\nSearching for males: ");
		result.append(manager.findMatches("M", null));
		assertTrue(TestingSupport.correctResults("pubFindMatches.txt", result.toString()));
	}
	
	public void testFriendsOnline() {
		FacebookJrDBManager manager = new FacebookJrDB();
		StringBuffer result = new StringBuffer();
		loadData(manager);
		
		for (String name : new String[]{"Jose", "Kelly", "Rachel"}) {
			manager.sendFriendInvite("Mary", name);
			manager.acceptFriendInvite(name, "Mary");
		}
		manager.updateOnlineStatus("Jose", true);
		manager.updateOnlineStatus("Rachel", true);
		manager.updateOnlineStatus("Peter", true);
		
		result.append("Friends of Mary that are online: ");
		result.append(manager.getFriendsOnline("Mary"));
		assertTrue(TestingSupport.correctResults("pubFriendsOnline.txt", result.toString()));
	}
	
	public void testNetworksPersonInfo() {
		FacebookJrDBManager manager = new FacebookJrDB();
		loadData(manager);
		StringBuffer result = new StringBuffer();
		result.append("All Networks\n");
		result.append(manager.getAllNetworks());
		
		result.append("\nMary and Jose are joining California regional network\n");
		manager.joinRegionalNetwork("California", "Mary");
		manager.joinRegionalNetwork("California", "Jose");
		result.append("Information about California regional network\n");
		result.append(manager.getInfoNetwork("California"));
		
		result.append("\nMary, Peter, Kelly, Jose are joining a high school network\n");
		manager.joinHighSchoolNetwork("BethesdaHigh", "Mary", 1999, true);
		manager.joinHighSchoolNetwork("BethesdaHigh", "Peter", 1985, true);
		manager.joinHighSchoolNetwork("BethesdaHigh", "Kelly", 1985, false);
		manager.joinHighSchoolNetwork("BethesdaHigh", "Jose", 1985, false);
		result.append("After joining the high school network\n");
		result.append(manager.getInfoNetwork("BethesdaHigh"));
		
		result.append("\nMary, Kelly, Peter are joining a college network\n");
		manager.joinCollegeNetwork("UMCP", "Mary", 2005, "CS");
		manager.joinCollegeNetwork("UMCP", "Kelly", 2004, "CS");
		manager.joinCollegeNetwork("UMCP", "Peter", 2005, "PSYC");
		result.append("After joining college network\n");
		result.append(manager.getInfoNetwork("UMCP"));
				
		result.append("\nMary is asking Jose and Kelly to be her friend");
		manager.sendFriendInvite("Mary", "Jose");
		manager.sendFriendInvite("Mary", "Kelly");
		manager.acceptFriendInvite("Jose", "Mary");
		manager.acceptFriendInvite("Kelly", "Mary");
		manager.postToWall("Jose", "Mary", "Hello Mary, this is Jose");
		manager.postToWall("Kelly", "Mary", "Hello Mary, this is Kelly");

		result.append("\nInformation about Mary\n");
		result.append(manager.getInfoPerson("Mary"));
		assertTrue(TestingSupport.correctResults("pubNetworksPersonInfo.txt", result.toString()));
	}
	
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