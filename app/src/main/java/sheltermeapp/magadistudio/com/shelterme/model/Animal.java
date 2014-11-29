package sheltermeapp.magadistudio.com.shelterme.model;

public class Animal {
	private String animalId;
	private String name;
	private String sex;
	private String primaryBreed;
	private String secondaryBreed;
	private String spayNeutered;
	private int ageMonths;
	private String age;
	private String photoUrl;
	private boolean specialNeeds;
	private boolean noDogs;
	private boolean noCats;
	private boolean noKids;
	
	
	
	public Animal(){
		
	}
	
	public Animal(String animalId, String name, String sex, String primaryBreed, String secondaryBreed, String spayNeutered, int ageMonths,
			String photoUrl, boolean specialNeeds, boolean noDogs, boolean noCats, boolean noKids){
		
		 this.animalId = animalId;
		 this.name = name;
		 this.sex = sex;
		 this.primaryBreed = primaryBreed;
		 this.secondaryBreed = secondaryBreed;
		 this.spayNeutered = spayNeutered;
		 this.ageMonths = ageMonths;
		 this.photoUrl = photoUrl;
		 this.specialNeeds = specialNeeds;
		 this.noDogs = noDogs;
		 this.noCats = noCats;
		 this.noKids = noKids;
		
	}

	

	public String getAnimalId() {
		return animalId;
	}

	public void setAnimalId(String animalId) {
		this.animalId = animalId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPrimaryBreed() {
		return primaryBreed;
	}

	public void setPrimaryBreed(String primaryBreed) {
		this.primaryBreed = primaryBreed;
	}

	public String getSecondaryBreed() {
		return secondaryBreed;
	}

	public void setSecondaryBreed(String secondaryBreed) {
		this.secondaryBreed = secondaryBreed;
	}





	public int getAgeMonths() {
		return ageMonths;
	}

	public void setAgeMonths(int ageMonths) {
		this.ageMonths = ageMonths;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public boolean isSpecialNeeds() {
		return specialNeeds;
	}

	public void setSpecialNeeds(boolean specialNeeds) {
		this.specialNeeds = specialNeeds;
	}

	public boolean isNoDogs() {
		return noDogs;
	}

	public void setNoDogs(boolean noDogs) {
		this.noDogs = noDogs;
	}

	public boolean isNoCats() {
		return noCats;
	}

	public void setNoCats(boolean noCats) {
		this.noCats = noCats;
	}

	public boolean isNoKids() {
		return noKids;
	}

	public void setNoKids(boolean noKids) {
		this.noKids = noKids;
	}

    public String getSpayNeutered() {
        return spayNeutered;
    }

    public void setSpayNeutered(String spayNeutered) {
        this.spayNeutered = spayNeutered;
    }
}
