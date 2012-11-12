package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
/*Person class has everything a person has and does on facebook.
 * 
 */
public class Person {
	//Instance variables, what a person has
    private String name;
    private String email;
    private String gender;
    private String relationshipStatus;
    private String photoURL;
    private Set<Person> friends;
    private Set<Application> apps;
    private String status;
    private WallApplication wall;
    private Set<Networks> networks;
    private boolean online;
    
    //Constructor of a Person
    public Person(String name, String email, String gender, String relationStat,
    		String photo){
    	this.name = name;
    	this.email = email;
    	this.gender = gender;
    	relationshipStatus = relationStat;
    	photoURL = photo;
    	friends = new HashSet<Person>();
    	status = "";
    	wall = new WallApplication();
    	apps = new HashSet<Application>();
    	apps.add(wall);
    	online = false;
    	networks = new HashSet<Networks>();
    	
    }

    //Setters and Getters for name
    public void setName(String name){
    	this.name = name;
    }

    public String getName(){
        return name;
    }
    //Setters and Getters for email
    public void setEmail(String email){
    	this.email = email;
    }

    public String getEmail(){
        return email;
    }
    //Returns gender
    public String getGender(){
        return gender;
    }
    //Setter and getter for relationship status
    public void setRelationshipStatus(String stat){
    	relationshipStatus = stat;
    }

    public String getRelationshipStatus(){
        return relationshipStatus;
    }
    //Setter and getter for photo URL
    public void setPhotoURL(String photo){
    	photoURL = photo;
    }

    public String getPhotoURL(){
        return photoURL;
    }
    //Returns friends
    public Set<Person> getFriends(){
        return friends;
    }
    //Add friend
    public void addFriend(Person friend){
    	friends.add(friend);
    }
    //Removes a particular friend
    public void removeFriend(Person friend){
    	if(friends.contains(friend) == true)
    	friends.remove(friend);
    }
    //Sets the status
    public void setStatus(String status) {
		this.status = status;
	}
    //return status
	public String getStatus(){
		return status;
	}
	//Gets wall posts by iterator
	public String getWallPosts(){
		String posts = "";
		Iterator<String> writings = wall.getPosts().iterator();
		while(writings.hasNext() == true) {
			String post = writings.next();
			if(writings.hasNext() == false)
				posts+= post;
			else
				posts+= post + "\n";
		}
        return posts;
    }
	//Adds wall post through the wall's addPost method
    public void addWallPost(String name, String post){
    	wall.addPost(name, post);
    }
    //Sets(adds) regional network to the Person's networks
    public void setRegion(Networks network){
    	network.addToGroup(this);//Person is added to network
    	networks.add(network);
    	
    }
    //Sets(adds) HS network to the Person's networks
    public void setHighSchool(Networks network, int year, boolean wentToCollege){
    	HighSchoolNetwork school = (HighSchoolNetwork) network;
    	school.addToYearCollege(this, year, wentToCollege);//Adds person
    	networks.add(network);
    }
    //Sets(adds) college network to the Person's networks
    public void setCollege(Networks network, int year, String major){
    	CollegeNetwork school = (CollegeNetwork) network;
    	school.addToCollege(this, year, major);
    	networks.add(network);
    }
    //Sets online status
    public void setOnline(boolean status){
    	online = status;
    }
    //returns online status
    public boolean getOnline(){
        return online;
    }
    //Overrides the toString() of Person, for use of getPersonInfo in FacebookDB
    public String toString() {
    	//Makes string of basic info of a person
		String result = "Name: " + name +"\n" + "Gender: " + gender +"\n" +
		"Status: " + relationshipStatus +"\n" + "Email: " + email +"\n" +
		"Photo URL: " + photoURL +"\n";
		if(online == true)
			result+= "Online: yes\n";
		else
			result+= "Online: no\n";
		//Uses iterator of an ArrayList of friends coming from a support method from Network
		result += "Friends:\n";
		Iterator<String> people = networks.iterator().next().getOrderedList(friends).iterator();
		while(people.hasNext() == true) {
			result += people.next() + "\n";//Adds to the friend info
		}
		//Uses iterator of Person getNetworksList()
		result+= "Networks:\n";
		Iterator<String> network = getNetworksList(networks).iterator();
		while(network.hasNext() == true) {
			result += network.next() + "\n";//Adds all of person's network
		}
		//Uses iterator of Person getApps() to develop a list of his apps
		result+= "Applications:\n";
		Iterator<String> application = getApps(apps).iterator();
		while(application.hasNext() == true) {
			result += application.next() + "\n";
		}
		result+= "Wall Postings:\n" + this.getWallPosts();//Adds his wall posts
		
		return result ;
    	
    }
    //The following are support methods
    
    //Develops a sorted list of Person's networks
    public ArrayList<String> getNetworks() {
    	ArrayList<String> list = new ArrayList<String>();
    	Iterator<Networks> network = networks.iterator();
		while(network.hasNext() == true) {
			list.add(network.next().getName());
		}
		return list;
    }
    //Develops an ordered list from a Set of Networks
    public ArrayList<String> getNetworksList(Set<Networks> net) {
    	ArrayList<String> list = new ArrayList<String>();
    	Iterator<Networks> network = net.iterator();
		while(network.hasNext() == true) {
			String name = network.next().getName();
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
    //Develops an ordered list of Person's Apps
    public ArrayList<String> getApps(Set<Application> app) {
    	ArrayList<String> list = new ArrayList<String>();
    	Iterator<Application> network = app.iterator();
		while(network.hasNext() == true) {
			String name = network.next().getName();
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

}
