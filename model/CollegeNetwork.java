package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
/*CollegeNetwork is a Networks class where it stores
 * every person who went to this school.
 */
public class CollegeNetwork extends Networks {
	private String college;//Name of college
    private Map<String,Set<Person>> major;//Stores every major
	private HashMap<Integer, Set<Person>> yearGrad;//stores every grad class
	//onstructor
    public CollegeNetwork(String name, String college){
    	super(name);
    	this.college = college;
    	yearGrad = new HashMap<Integer,Set<Person>>();
    	major = new HashMap<String,Set<Person>>();
    }
    //Getters...
    public String getCollege() {
    	return college;
    }
    //Similar to the same one in high school
    public int getYear(Person person){
    	int year = 0;
    	Iterator<Integer> classes = yearGrad.keySet().iterator();
    	while(classes.hasNext() == true) {
    		int currYear = classes.next();
    		Iterator<Person> guys = yearGrad.get(currYear).iterator();
    		while(guys.hasNext() == true) {
    			Person guy = guys.next();
    			if(person.getName().compareTo(guy.getName()) == 0)
    				return currYear;
    		}
    	}
        return year;
    }
    //Returns a person's major, uses an iterator
    public String getPersonsMajor(Person person){
    	String noMajor = null;
    	Iterator<String> majors = major.keySet().iterator();
    	while(majors.hasNext() == true) {
    		String degree = majors.next();
    		Iterator<Person> guys = major.get(degree).iterator();
    		while(guys.hasNext() == true) {
    			Person guy = guys.next();
    			if(person.getName().compareTo(guy.getName()) == 0)
    				return degree;
    		}
    	}
        return noMajor;
    }
    //Adds person to college, grad class, and major group.
    public void addToCollege(Person person, int year, String major){
    	this.addToGroup(person);
    	Set<Person> grad = yearGrad.get(year);
    	if(grad == null)
    		grad = new HashSet<Person>();
    	grad.add(person);
    	yearGrad.put(year, grad);
    	
    	Set<Person> majors = this.major.get(major);
    	if(majors == null)
    		majors = new HashSet<Person>();
    	majors.add(person);
    	this.major.put(major, majors);
    }
    //Adds person to group, used by above method
	public void addToGroup(Person person) {
		getGroup().add(person);
		
	}

	@Override
	//Makes a String with all the info of this network
	public String toString() {
		String result = "";
		//Adds name, school name, grad classes, and major groups
		result += "Network Name: "+ this.getName() + "\nMembers: \n";
		Iterator<String> people = this.getOrderedList(getGroup()).iterator();
		while(people.hasNext() == true) {
			result += people.next() + "\n";
		}
		result += "SchoolName: "+ college + "\n";
		Iterator<Integer> years = yearGrad.keySet().iterator();
		while(years.hasNext() == true) {
			int year = years.next();
			result += "Year: " + year + "\n";
			Iterator<String> person = this.getOrderedList(yearGrad.get(year)).iterator();
			while(person.hasNext() == true) {
				result += person.next() + "\n";
			}
		}
		result += "By Major\n";
		Iterator<String> majors = getOrderedString(major.keySet()).iterator();
		while(majors.hasNext() == true) {
			String majorName = majors.next();
			result += "Major: " + majorName + "\n";
			Iterator<String> person = this.getOrderedList(major.get(majorName)).iterator();
			while(person.hasNext() == true) {
				String name = person.next();
				if(person.hasNext() == false && majors.hasNext() == false)
					result += name;
				else
					result += name + "\n";
			}
		}
		
		return result;
	}

	@Override
	//Remove person from all groups
	public void remove(Person person) {
		getGroup().remove(person);
		int year = getYear(person);
		yearGrad.get(year).remove(person);
		String study = getPersonsMajor(person);
		major.get(study).remove(person);
		
	}
	//Support method to get a sorted list of Strings(such as a person's name)
	public ArrayList<String> getOrderedString(Set <String> person) {
    	ArrayList<String> list = new ArrayList<String>();
		Iterator<String> people = person.iterator();
		while(people.hasNext() == true) {
			String name = people.next();
			if(list.isEmpty() == true)
				list.add(name);//Will be first
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
