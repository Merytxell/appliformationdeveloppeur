package fr.fms.entities;

public class Courses {

	public String Name;
	public String Description;
	public String Remote;
	public String Duration;
	public int UnitaryPrice;



	public Courses(String name, String description, String remote, String duration, int unitaryPrice) {
		super();
		Name = name;
		Description = description;
		Remote = remote;
		Duration = duration;
		UnitaryPrice = unitaryPrice;

	}


	@Override
	public String toString() {
		return "Courses [Name=" + Name + ", Description=" + Description + ", Remote=" + Remote + ", Duration="
				+ Duration + ", UnitaryPrice=" + UnitaryPrice + "]";
	}



	public String getName() {
		return Name;
	}



	public void setName(String name) {
		Name = name;
	}



	public String getDescription() {
		return Description;
	}



	public void setDescription(String description) {
		Description = description;
	}



	public String getRemote() {
		return Remote;
	}



	public void setRemote(String remote) {
		Remote = remote;
	}



	public String getDuration() {
		return Duration;
	}



	public void setDuration(String duration) {
		Duration = duration;
	}



	public int getUnitaryPrice() {
		return UnitaryPrice;
	}



	public void setUnitaryPrice(int unitaryPrice) {
		UnitaryPrice = unitaryPrice;
	}

}
