package model;

import java.util.Iterator;
/*RegionalNetwork is a Networks class where it has a group of people
 * sharing a location.
 */
public class RegionalNetwork extends Networks {
	//Regional network has an extra instance variable
    private String area;//The area of the network
    //Constructor
    public RegionalNetwork(String name, String area){
    	super(name);
    	this.area = area;
    }
    //Returns area
    public String getArea(){
        return area;
    }
    //Just add person to the group
    public void addToGroup(Person person){
    	getGroup().add(person);
    }

	@Override
	//For use to getInfoNetwork in FacebookJrDB
	public String toString() {
		String result = "";
		
		result += "Network Name: "+ this.getName() + "\nMembers: \n";
		Iterator<String> people = this.getOrderedList(getGroup()).iterator();
		while(people.hasNext() == true) {
			result += people.next() + "\n";
		}
		result += "Location: " + area;
		
		return result;
	}

	@Override
	//Simply calls the remove method of the group
	public void remove(Person person) {
		getGroup().remove(person);
		
	}


}
