package model;
import java.util.*;

public interface FacebookJrDBManager {
	/**
	 * Adds a person to the database.
	 * @param name person's name.
	 * @param gender possible values "M" or "F".
	 * @param relationshipStatus any string (e.g., "Single", "Married").
	 * @param email 
	 * @param photoURL name of photo file
	 * @return true if person was added and false otherwise.
	 */
	public boolean addPerson(String name, String gender, String relationshipStatus, String email, String photoURL);
	
	/**
	 * Removes a person from the database. 
	 * @param name
	 * @return true if person was removed and false otherwise.
	 */
	public boolean removePerson(String name);
	
	/**
	 * Removes the friend named friendName from the group of friends
	 * associated with personName.
	 * @param personName
	 * @param friendName
	 * @return true if friend was removed and false otherwise.
	 */
	public boolean removeFriend(String personName, String friendName);
	
	/**
	 * The person with name invitingPersonName sends a friend 
	 * invitation to invitedPersonName.
	 * @param invitingPersonName
	 * @param invitedPersonName
	 * @return true if invitation is sent and false otherwise.
	 */
	public boolean sendFriendInvite(String invitingPersonName, String invitedPersonName);
	
	/**
	 * The person with name acceptingPersonName accepts acceptedPersonName as a friend.
	 * @param acceptingPersonName
	 * @param acceptedPersonName
	 * @return true if friend accepted and false otherwise.
	 */
	public boolean acceptFriendInvite(String acceptingPersonName, String acceptedPersonName);
	
	/**
	 * The person with name refusingPersonName does not accept refusedPersonName as
	 * a friend.
	 * @param refusingPersonName
	 * @param refusedPersonName
	 * @return true if person is refused and false otherwise.
	 */
	public boolean refuseFriendInvite(String refusingPersonName, String refusedPersonName);
	
	/**
	 * A message by personWriting is posted on the wall associated with writingToPerson.
	 * Any person can post to any other person's wall.
	 * @param personWriting
	 * @param writingToPerson
	 * @param message message to post
	 * @return true if message posted and false otherwise.
	 */
	public boolean postToWall(String personWriting, String writingToPerson, String message);
	
	/**
	 * Updates the relationship status string of personName.
	 * @param personName
	 * @param relationshipStatus new status (e.g., "Complicated")
	 * @return true if status is updated and false otherwise.
	 */
	public boolean updateRelationshipStatus(String personName, String relationshipStatus);
	
	/**
	 * Updates the status (e.g., "I am working on a project") of personName
	 * @param personName
	 * @param newStatus 
	 * @return true if status is updated and false otherwise.
	 */
	public boolean updateStatus(String personName, String newStatus);
	
	/**
	 * Updates the online status of personName.
	 * @param personName
	 * @param newStatus true means the person is online.
	 * @return true if online status is updated and false otherwise.
	 */
	public boolean updateOnlineStatus(String personName, boolean newStatus);
	
	/**
	 * Adds a regional network to the database.
	 * @param networkName
	 * @param location 
	 * @return true if network added and false otherwise.
	 */
	public boolean addRegionalNetwork(String networkName, String location);
	
	/**
	 * Adds the person with name personName to the specified regional network.
	 * @param networkName
	 * @param personName
	 * @return true if the person joins the network and false otherwise.
	 */
	public boolean joinRegionalNetwork(String networkName, String personName);
	
	/**
	 * Adds a high school network to the database.
	 * @param networkName
	 * @param schoolName
	 * @return true if network added and false otherwise.
	 */
	public boolean addHighSchoolNetwork(String networkName, String schoolName);
	
	/**
	 * Adds the person with name personName and that graduated 
	 * on the specified year to the high school network.
	 * @param networkName
	 * @param personName
	 * @param year high school graduation year.
	 * @param wentToCollege whether the person attended college or not.
	 * @return true if the person joins the network and false otherwise.
	 */
	public boolean joinHighSchoolNetwork(String networkName, String personName, int year, boolean wentToCollege);
	
	/**
	 * Adds a college network to the database. 
	 * @param networkName
	 * @param schoolName
	 * @return true if network added and false otherwise.
	 */
	public boolean addCollegeNetwork(String networkName, String schoolName);
	
	/**
	 * Adds the person with name personName, that graduated on the specified year
	 * and that belongs to the specified major to the network.
	 * @param networkName
	 * @param personName
	 * @param year graduation year
	 * @param major college major
	 * @return true if person joins the network and false otherwise.
	 */
	public boolean joinCollegeNetwork(String networkName, String personName, int year, String major);
	
	/**
	 * Returns the person's name.
	 * @param personName
	 * @return string or null if person not found.
	 */
	public String getName(String personName);
	
	/**
	 * Returns the person's gender.
	 * @param personName
	 * @return "M" or "F" and null if person not found.
	 */
	public String getGender(String personName);
	
	/**
	 * Returns the person's relationship status string.
	 * @param personName
	 * @return string or null if person not found.
	 */
	public String getRelationshipStatus(String personName);
	
	/**
	 * Returns the person's status string (e.g. "I want a vacation")
	 * @param personName
	 * @return string or null if person not found.
	 */
	public String getStatus(String personName);
	
	/**
	 * Returns the person's email.
	 * @param personName
	 * @return string or null if person not found.
	 */
	public String getEmail(String personName);
	
	/**
	 * Returns the person's photo url.
	 * @param personName
	 * @return string or null if person not found.
	 */
	public String getPersonPhotoURL(String personName);
	
	/**
	 * Returns the friends of personName.
	 * @param personName
	 * @return ArrayList with the names of friends or empty list.
	 */
	public ArrayList<String> getFriends(String personName);

	/**
	 * Returns the networks of personName.
	 * @param personName
	 * @return ArrayList with the names of networks or empty list.
	 */
	public ArrayList<String> getNetworks(String personName);
	
	/**
	 * Returns a string with all the wall postings.
	 * @param personName
	 * @return string or null if person not found.
	 */
	public String getWallPostings(String personName);
	
	/**
	 * Returns a list of all the persons in the database.
	 * @return ArrayList with names of persons in the database or empty list.
	 */
	public ArrayList<String> getAllPersons();
	
	/**
	 * Returns a list of all the networks in the database.
	 * @return ArrayList with names of networks in the database or empty list.
	 */
	public ArrayList<String> getAllNetworks();
	
	/**
	 * Returns a string with information about the person.  See
	 * the public test results for format information.
	 * @param personName
	 * @return string or null if person not found.
	 */
	public String getInfoPerson(String personName);
	
	/**
	 * Returns a string with information about the network.  See
	 * the public tests results for format information.
	 * @param networkName
	 * @return string or null if network not found.
	 */
	public String getInfoNetwork(String networkName);
	
	/**
	 * Returns a list of friends that are online.
	 * @param personName
	 * @return ArrayList with names of friends online or empty list.
	 */
	public ArrayList<String> getFriendsOnline(String personName);
	
	/**
	 * Returns a list of names (not just friends) that match
	 * the specified gender and relationship status.  If any parameter is null
	 * then the parameter is ignored in the search (i.e., any value
	 * for the parameter will be accepted in the search).
	 * @param gender "M" or "F"
	 * @param relationshipStatus (e.g., "Single", "Married", etc.)
	 * @return ArrayList with the names of those satisfying the specified criteria
	 * or empty list.
	 */
	public ArrayList<String> findMatches(String gender, String relationshipStatus);
}