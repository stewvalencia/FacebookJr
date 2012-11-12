package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
/*FacebookJrDB will be where it manages everything for the person
 * Ex. add network, remove people, change status, etc.
 */
public class FacebookJrDB implements FacebookJrDBManager {
	//Instance variables are databases of persons and networks
    private Set<Person> peopleDB;
    private Map<String, Networks> networksDB;

    //Basic constructor
    public FacebookJrDB(){
    	 peopleDB = new HashSet<Person>();
    	 networksDB = new HashMap<String, Networks>();
    }

	@Override
	public boolean acceptFriendInvite(String acceptingPersonName,
			String acceptedPersonName) {
		//Makes two iterator that finds each person
		Iterator<Person> list = peopleDB.iterator();
		Iterator<Person> findFriend = peopleDB.iterator();
		while(list.hasNext() == true) {
			Person person = list.next();
			String name = person.getName();
			if(acceptingPersonName.equals(name) ==true ) {
				while(findFriend.hasNext()) {
					Person friend = findFriend.next();
					String friendName = friend.getName();
					if(acceptedPersonName.equals(friendName) == true) {
						//Will add and return true if both is found
						person.addFriend(friend);
						friend.addFriend(person);
						return true;
					}
					}
			}
		}
		return false;//false if one isn't found
	}

	@Override
	//Will add college network only if the network isn't in the database
	public boolean addCollegeNetwork(String networkName, String schoolName) {
		CollegeNetwork college = new CollegeNetwork(networkName, schoolName);
		if(networksDB.containsKey(networkName))
			return false;
		networksDB.put(networkName, college);
		return true;
	}

	@Override
	//Will add high school network only if the network isn't in the database
	public boolean addHighSchoolNetwork(String networkName, String schoolName) {
		HighSchoolNetwork school = new HighSchoolNetwork(networkName, schoolName);
		if(networksDB.containsKey(networkName))
			return false;
		networksDB.put(networkName, school);
		return true;
	}

	@Override
	public boolean addPerson(String name, String gender,
			String relationshipStatus, String email, String photoURL) {
			Person person = new Person(name, email, gender, relationshipStatus, photoURL);
			if(this.getName(name) != null)
				return false;//Only if name is found in the database
			peopleDB.add(person);
			return true;
	}

	@Override
	//Will add regional network only if the network isn't in the database
	public boolean addRegionalNetwork(String networkName, String location) {
		RegionalNetwork region = new RegionalNetwork(networkName, location);
		if(networksDB.containsKey(networkName))
			return false;
		networksDB.put(networkName, region);
		return true;
	}

	@Override
	public ArrayList<String> findMatches(String gender,
			String relationshipStatus) {
		//Makes list and an iterator to traverse
		ArrayList<String> list = new ArrayList<String>();
		Iterator<Person> findMatch = peopleDB.iterator();
		while(findMatch.hasNext() == true) {
			//Stores person and name
			Person person = findMatch.next();
			String name = person.getName();
			if(gender != null  &&//Only if both parameters are passed
					relationshipStatus != null ) {
			if(person.getGender().equals(gender) ==true  &&
					person.getRelationshipStatus().equals(relationshipStatus)
					== true ) {//adds if person matches with the terms
				if(list.isEmpty() == true)//will be added first
					list.add(name);
				else {//for loop will help put this list in order
					for(int i =  0; i < list.size(); i++) {
						if(i == list.size()-1) {//if the counter is at the last person
							if(list.get(i).compareTo(name) > 0) {
								list.add(i, name);//add before the person
								break;
							} else {
								list.add(name);//add after
								break;//need break since person was added
							}
						}//it'll just add and shift everything to the right
						if(list.get(i).compareTo(name) > 0) {//current person's name is "greater" than the name
							list.add(i, name);
							break;//need the break to exit out
							} 
						}
					}
				}
			} else if(person.getGender().equals(gender) ==true  ||
						person.getRelationshipStatus().equals(relationshipStatus)
						== true ) {//Similar to above except only one parameter is used
					if(list.isEmpty() == true)
						list.add(name);
					else {
						for(int i =  0; i < list.size(); i++) {
							if(i == list.size()-1) {
								if(list.get(i).compareTo(name) > 0) {
									list.add(i, name);
									break;
								} else {
									list.add(name);
									break;
								}
							}
							if(list.get(i).compareTo(name) > 0) {
								list.add(i, name);
								break;
							} 
						}
				}
		}
			}
		return list;
		
	}

	@Override
	//Will add networks in sorted order in a list
	public ArrayList<String> getAllNetworks() {
		ArrayList<String> list = new ArrayList<String>();
		Iterator<String> networks = networksDB.keySet().iterator();
		while(networks.hasNext() == true) {
			String name = networks.next();
			/*Network list is made in the same way find matches 
			 * list except for parameters
			 */
			if(list.isEmpty() == true)
				list.add(name);
			else {
				for(int i =  0; i < list.size(); i++) {
					if(i == list.size()-1) {
						if(list.get(i).compareTo(name) > 0) {
							list.add(i, name);
							break;
						} else {
							list.add(name);
							break;
						}
					}
					if(list.get(i).compareTo(name) > 0) {
						list.add(i, name);
						break;
					} 
				}
			}
		}		
		return list;
	}

	@Override
	//Makes a sorted list of all persons in the database
	public ArrayList<String> getAllPersons() {
		ArrayList<String> list = new ArrayList<String>();
		Iterator<Person> people = peopleDB.iterator();
		while(people.hasNext() == true) {
			String name = people.next().getName();
			//Same process as getAllNetworks except with persons instead of networks
			if(list.isEmpty() == true)
				list.add(name);
			else {
				for(int i =  0; i < list.size(); i++) {
					if(i == list.size()-1) {
						if(list.get(i).compareTo(name) > 0) {
							list.add(i, name);
							break;
						} else {
							list.add(name);
							break;
						}
					}
					if(list.get(i).compareTo(name) > 0) {
						list.add(i, name);
						break;
					} 
				}
			}
		}		
		return list;
	}

	@Override
	//Gets a person email through an iterator
	public String getEmail(String personName) {
		Iterator<Person> people = peopleDB.iterator();
		while(people.hasNext() == true) {
			Person person = people.next();
			if(person.getName().equals(personName) == true) {
				return person.getEmail();
			}
				
		}
		return null;
	}

	@Override
	//Makes a sorted list of a persons frieds
	public ArrayList<String> getFriends(String personName) {
		ArrayList<String> list = new ArrayList<String>();
		Iterator<Person> people = peopleDB.iterator();
		while(people.hasNext() == true) {
			Person person = people.next();
			//Finds a person first through an iterator
			if(person.getName().equals(personName) == true) {
				 Set<Person> friends = person.getFriends();
				 //Adds all friends in order similar to getAllPersons
				 Iterator<Person> friendsDB = friends.iterator();
					while(friendsDB.hasNext() == true) {
						String name = friendsDB.next().getName();
						if(list.isEmpty() == true)
							list.add(name);
						else {
							for(int i =  0; i < list.size(); i++) {
								if(i == list.size()-1) {
									if(list.get(i).compareTo(name) > 0) {
										list.add(i, name);
										break;
									} else {
										list.add(name);
										break;
									}
								}
								if(list.get(i).compareTo(name) > 0) {
									list.add(i, name);
									break;
									}
								}	
							}
					}
			}
		}
		return list;
	}

	@Override
	//Makes a sorted list of online friends similar to getFriends except they must be online
	public ArrayList<String> getFriendsOnline(String personName) {
		ArrayList<String> list = new ArrayList<String>();
		Iterator<Person> people = peopleDB.iterator();
		while(people.hasNext() == true) {
			Person person = people.next();
			if(person.getName().equals(personName) == true) {
				 Set<Person> friends = person.getFriends();
				 Iterator<Person> friendsDB = friends.iterator();
					while(friendsDB.hasNext() == true) {
						Person friend = friendsDB.next();
						if(friend.getOnline() == true) {
							String name = friend.getName();
							if(list.isEmpty() == true)
								list.add(name);
							else {
								for(int i =  0; i < list.size(); i++) {
									if(i == list.size()-1) {
										if(list.get(i).compareTo(name) > 0) {
											list.add(i, name);
											break;
										} else {
											list.add(name);
											break;
										}
									}
									if(list.get(i).compareTo(name) > 0) {
										list.add(i, name);
										break;
										}
									}
								}
							}	
						}
			}
		}
		return list;
	}

	@Override
	//Get gender of a person through the personDB iterator
	public String getGender(String personName) {
		Iterator<Person> people = peopleDB.iterator();
		while(people.hasNext() == true) {
			Person person = people.next();
			if(person.getName().equals(personName) == true) {
				return person.getGender();
			}
				
		}
		return null;
	}

	@Override
	//Will get an info of a network by calling its toString
	public String getInfoNetwork(String networkName) {
		Networks network = networksDB.get(networkName);
		if(network == null)
			return null;
		else
			return network.toString();
	}

	@Override
	//Gets info of person by finding him/her and calling Person toString
	public String getInfoPerson(String personName) {
		Iterator<Person> people = peopleDB.iterator();
		while(people.hasNext() == true) {
			Person person = people.next();
			if(person.getName().equals(personName) == true) {
				return person.toString();
			}
				
		}
		return null;
	}

	@Override
	public String getName(String personName) {
		Iterator<Person> people = peopleDB.iterator();
		while(people.hasNext() == true) {
			Person person = people.next();
			if(person.getName().equals(personName) == true) {
				return person.getName();
			}
				
		}
		return null;
	}

	@Override
	//Returns a list of a person's network through Person getNetworks method
	public ArrayList<String> getNetworks(String personName) {
		ArrayList<String> list = new ArrayList<String>();
		Iterator<Person> people = peopleDB.iterator();
		while(people.hasNext() == true) {
			Person person = people.next();
			if(person.getName().equals(personName) == true) {
				
				return person.getNetworks();
			}
				
		}
		return list;
	}
	

	@Override
	//Get photo URL through iterator
	public String getPersonPhotoURL(String personName) {
		Iterator<Person> people = peopleDB.iterator();
		while(people.hasNext() == true) {
			Person person = people.next();
			if(person.getName().equals(personName) == true) {
				return person.getPhotoURL();
			}
				
		}
		return null;
	}

	@Override
	//Get relationship status through iterator
	public String getRelationshipStatus(String personName) {
		Iterator<Person> people = peopleDB.iterator();
		while(people.hasNext() == true) {
			Person person = people.next();
			if(person.getName().equals(personName) == true) {
				return person.getRelationshipStatus();
			}
				
		}
		return null;
	}

	@Override
	//Get status of a person through iterator
	public String getStatus(String personName) {
		Iterator<Person> people = peopleDB.iterator();
		while(people.hasNext() == true) {
			Person person = people.next();
			if(person.getName().equals(personName) == true) {
				return person.getStatus();
			}
				
		}
		return null;
	}

	@Override
	//Get person's wall posts by Person getWallPosts method
	public String getWallPostings(String personName) {
		String posts = "";
		Iterator<Person> people = peopleDB.iterator();
		while(people.hasNext() == true) {
			Person person = people.next();
			if(person.getName().equals(personName) == true) {
				posts+= person.getWallPosts();
				return posts;
			}
				
		}
		return posts;
	}

	/*The following joinNetworks methods will add a person to a network using
	 * the Persons network setter and passing the right parameters
	 * Returns false if person is not found*/
	@Override
	public boolean joinCollegeNetwork(String networkName, String personName,
			int year, String major) {
		Iterator<String> networks = networksDB.keySet().iterator();
		while(networks.hasNext() == true) {
			String netName = networks.next();
			if(netName.equals(networkName) ==true) {
				Iterator<Person> people = peopleDB.iterator();
				while(people.hasNext() == true) {
					Person person = people.next();
					if(person.getName().equals(personName) == true) {
						person.setCollege(networksDB.get(netName), year, major);
						return true;
					}	
				}
			}	
		}
		return false;
	}

	@Override
	public boolean joinHighSchoolNetwork(String networkName, String personName,
			int year, boolean wentToCollege) {
		Iterator<String> networks = networksDB.keySet().iterator();
		while(networks.hasNext() == true) {
			String netName = networks.next();
			if(netName.equals(networkName) == true) {
				Iterator<Person> people = peopleDB.iterator();
				while(people.hasNext() == true) {
					Person person = people.next();
					if(person.getName().equals(personName) == true) {
						person.setHighSchool(networksDB.get(netName), year, wentToCollege);
						return true;
					}	
				}
			}	
		}
		return false;
	}

	@Override
	public boolean joinRegionalNetwork(String networkName, String personName) {
		Iterator<String> networks = networksDB.keySet().iterator();
		while(networks.hasNext() == true) {
			String netName = networks.next();
			if(netName.equals(networkName) == true) {
				Iterator<Person> people = peopleDB.iterator();
				while(people.hasNext() == true) {
					Person person = people.next();
					if(person.getName().equals(personName) == true) {
						person.setRegion(networksDB.get(netName));
						return true;
					}	
				}
			}	
		}
		return false;
	}

	@Override
	//Adds a posts to a person's wall using Person addWallPost()
	public boolean postToWall(String personWriting, String writingToPerson,
			String message) {
		Iterator<Person> people = peopleDB.iterator();
		while(people.hasNext() == true) {
			Person person = people.next();
			if(person.getName().equals(writingToPerson) == true) {
				person.addWallPost(personWriting, message);
				return true;
			}
				
		}
		return false;
	}

	@Override
	/*Will return true if both persons are found and made
	 * sure each persons aren't friends with each other
	 */
	public boolean refuseFriendInvite(String refusingPersonName,
			String refusedPersonName) {
		Iterator<Person> list = peopleDB.iterator();
		Iterator<Person> findFriend = peopleDB.iterator();
		while(list.hasNext() == true) {
			Person person = list.next();
			String name = person.getName();
			if(refusingPersonName.equals(name) ==true ) {
				while(findFriend.hasNext()) {
					Person friend = findFriend.next();
					String friendName = friend.getName();
					if(refusedPersonName.equals(friendName) == true) {
						person.removeFriend(friend);
						friend.removeFriend(person);
						return true;
						}
					}
			}
		}
		return false;
	}

	@Override
	/*Will remove a friend on a person's list through 
	 * Person removeFriend(). Returns false if they are not friends
	 */
	public boolean removeFriend(String personName, String friendName) {
		Iterator<Person> list = peopleDB.iterator();
		Iterator<Person> findFriend = peopleDB.iterator();
		while(list.hasNext() == true) {
			Person person = list.next();
			String name = person.getName();
			if(personName.equals(name) ==true ) {
				while(findFriend.hasNext()) {
					Person friend = findFriend.next();
					String friName = friend.getName();
					if(friendName.equals(friName) == true) {
						if(person.getFriends().contains(friend) == false)
							return false;
						person.removeFriend(friend);
						return true;
						}
					}
			}
		}
		return false;
	}

	@Override
	/*Remove person from database, from networks, and 
	 * friends list by iterator
	 */
	public boolean removePerson(String name) {
		Iterator<Person> people = peopleDB.iterator();
		while(people.hasNext() == true) {
			Person person = people.next();
			if(person.getName().equals(name) == true) {
				peopleDB.remove(person);
				ArrayList<String> network = person.getNetworks();
				for(int i =0; i < network.size(); i++) {
					networksDB.get(network.get(i)).remove(person);
				}
				people = peopleDB.iterator();
				while(people.hasNext() ==true) {
					this.removeFriend(people.next().getName(), name);
				}
				return true;
			}
				
		}
		return false;
	}

	@Override
	/* "Sends a friend invite"  meaning return true if both
	 * persons are found, otherwise false
	 */
	public boolean sendFriendInvite(String invitingPersonName,
			String invitedPersonName) {
		Iterator<Person> list = peopleDB.iterator();
		Iterator<Person> findFriend = peopleDB.iterator();
		while(list.hasNext() == true) {
			Person person = list.next();
			String name = person.getName();
			if(invitingPersonName.equals(name) ==true ) {
				while(findFriend.hasNext()) {
					Person friend = findFriend.next();
					String friendName = friend.getName();
					if(invitedPersonName.equals(friendName) == true) {
						return true;
					}
					}
			}
		}
		return false;
	}
	/*The following update methods will each use an iterator to find person
	 * and update the field through the respective Person setter method()
	 */
	@Override
	public boolean updateOnlineStatus(String personName, boolean newStatus) {
		Iterator<Person> people = peopleDB.iterator();
		while(people.hasNext() == true) {
			Person person = people.next();
			if(person.getName().equals(personName) == true) {
				person.setOnline(newStatus);
				return true;
			}
				
		}
		return false;
	}

	@Override
	public boolean updateRelationshipStatus(String personName,
			String relationshipStatus) {
		Iterator<Person> people = peopleDB.iterator();
		while(people.hasNext() == true) {
			Person person = people.next();
			if(person.getName().equals(personName) == true) {
				person.setRelationshipStatus(relationshipStatus);
				return true;
			}
				
		}
		return false;
	}

	@Override
	public boolean updateStatus(String personName, String newStatus) {
		Iterator<Person> people = peopleDB.iterator();
		while(people.hasNext() == true) {
			Person person = people.next();
			if(person.getName().equals(personName) == true) {
				person.setStatus(newStatus);
				return true;
			}	
		}
		return false;
	}

}
