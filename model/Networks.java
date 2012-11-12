package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
/*Networks will be an abstract class that layout what every network has
 * 
 */
public abstract class Networks {
	//Instance variables of every network
    private String name;
    private Set<Person> group;
    //Constructor
    public Networks(String name){
    	this.name = name;
    	this.group = new HashSet<Person>();
    }
    //Shared abstract method that all networks share
    public abstract void addToGroup(Person person);
    
    //Getters of instance variables
    public String getName(){
        return name;
    }

    public Set<Person> getGroup(){
        return group;
    }
    
    //toString() and remove are also methods that each network share
    public abstract String toString();
    
    public abstract void remove(Person person);
    
    //Support Method: makes a list of persons from a set of persons in order
    public ArrayList<String> getOrderedList(Set <Person> person) {
    	ArrayList<String> list = new ArrayList<String>();
		Iterator<Person> people = person.iterator();
		while(people.hasNext() == true) {
			String name = people.next().getName();
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
		} return list;
    }
}
