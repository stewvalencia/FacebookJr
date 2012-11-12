package model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
/*HighSchoolNetwork is a Networks class where it stores
 * every person who went to this school.
 */
public class HighSchoolNetwork extends Networks {
	//Instance variables
	private String school;//Name of school
    private Map<Integer,Set<Person>> yearGrad;//Keeps all graduated classes
    //To store who went to college
    private Set<Person> wentToCollege;
    private Set<Person> notAttendingCollege;
    //Constructor
    public HighSchoolNetwork(String name, String school){
    	super(name);
    	this.school = school;
    	yearGrad = new HashMap<Integer,Set<Person>>();
    	wentToCollege = new HashSet<Person>();
    	notAttendingCollege = new HashSet<Person>();
    	
    }
    //Returns name of school
    public String getSchool() {
    	return school;
    }
    //Returns the grad year of a Person
    public int getYear(Person person){
    	int year = 0;
    	//Use an iterator to find the Person needed
    	Iterator<Integer> classes = yearGrad.keySet().iterator();
    	while(classes.hasNext() == true) {
    		int currYear = classes.next();
    		Iterator<Person> guys = yearGrad.get(currYear).iterator();
    		while(guys.hasNext() == true) {
    			Person guy = guys.next();
    			if(person.getName().compareTo(guy.getName()) == 0)
    				return currYear;//returns the person's grad year
    		}
    	}
        return year;
    }
    //Checks if a person is attending a college by use of iterator
    public boolean wentCollege(Person person){
    	Iterator<Person> guys = wentToCollege.iterator();
		while(guys.hasNext() == true) {
			Person guy = guys.next();
			if(person.getName().compareTo(guy.getName()) == 0)
				return true;
		}
        return false;
    }
    //Adds person to group, grad year class, and college/not bound group
    public void addToYearCollege(Person person, int year, boolean college){
    	this.addToGroup(person);
    	Set<Person> grad = yearGrad.get(year);
    	if(grad == null)
    		grad = new HashSet<Person>();
    	grad.add(person);
    	yearGrad.put(year, grad);
    	if(college == true)
    		wentToCollege.add(person);
    	else
    		notAttendingCollege.add(person);
    		
    	
    }

	@Override
	//Adds person to group
	public void addToGroup(Person person) {
		getGroup().add(person);
		
	}

	@Override
	//For use to get info on the network (FacebookJrDB)
	public String toString() {
		String result = "";
		//Will return name, school name, each grad class, and each college bound group
		result += "Network Name: "+ this.getName() + "\nMembers: \n";
		Iterator<String> people = this.getOrderedList(getGroup()).iterator();
		while(people.hasNext() == true) {
			result += people.next() + "\n";
		}
		result += "SchoolName: "+ school + "\n";
		Iterator<Integer> years = yearGrad.keySet().iterator();
		while(years.hasNext() == true) {
			int year = years.next();
			result += "Year: " + year + "\n";
			Iterator<String> person = this.getOrderedList(yearGrad.get(year)).iterator();
			while(person.hasNext() == true) {
				result += person.next() + "\n";
			}
		}
		result += "AttendedCollege\n";
		Iterator<String> grad = this.getOrderedList(wentToCollege).iterator();
		while(grad.hasNext() == true) {
			result += grad.next() + "\n";
		}
		
		result += "NotAttendedCollege\n";
		Iterator<String> notGrad = this.getOrderedList(notAttendingCollege).iterator();
		while(notGrad.hasNext() == true) {
			String name = notGrad.next();
			if(notGrad.hasNext() == false)
				result += name;
			else
				result += name + "\n";
		}
		
		return result;
	}

	@Override
	//Removes person from group, grad year class, and college bound group
	public void remove(Person person) {
		getGroup().remove(person);
		int year = getYear(person);
		yearGrad.get(year).remove(person);
		if(wentCollege(person) == true)
			wentToCollege.remove(person);
		else
			notAttendingCollege.remove(person);
	}

}
