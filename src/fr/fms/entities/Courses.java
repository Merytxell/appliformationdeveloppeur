package fr.fms.entities;

public class Courses {
	public int idCourse;
	public String Name;
	public String Description;
	public String Remote;
	public String Duration;
	public int UnitaryPrice;

	
	
	
	public Courses(int idCourse, String name, String description, String remote, String duration, int unitaryPrice) {
		super();
		this.idCourse = idCourse;
		Name = name;
		Description = description;
		Remote = remote;
		Duration = duration;
		UnitaryPrice = unitaryPrice;
	}




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
		return "Courses [idCourse=" + idCourse + ", Name=" + Name + ", Description=" + Description + ", Remote="
				+ Remote + ", Duration=" + Duration + ", UnitaryPrice=" + UnitaryPrice + "]";
	}


	public int getIdCourse() {
		return idCourse;
	}


	public void setIdCourse(int idCourse) {
		this.idCourse = idCourse;
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




	public int getQuantity() {
		// TODO Auto-generated method stub
		return 0;
	}




	public void setQuantity(int i) {
		// TODO Auto-generated method stub
		
	}

}
