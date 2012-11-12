package model;
/*Application is an app a person has on FacebookJr
 * 
 */
public class Application {
    private String name;//Name of app
    private String author;//Name of author
    private String url;//Url address
    //Constructor
    public Application(String name, String author, String url){
    	this.name = name;
    	this.author = author;
    	this.url = url;
    	
    }
    //Returns name
	public String getName() {
		return name;
	}

}
