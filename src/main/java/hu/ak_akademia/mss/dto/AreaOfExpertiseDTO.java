package hu.ak_akademia.mss.dto;

public class AreaOfExpertiseDTO {

    private int areaOfExpertiseId;
    private String name;
    private String description;

    public AreaOfExpertiseDTO() {
    }

    public int getAreaOfExpertiseId() {
        return areaOfExpertiseId;
    }

    public void setAreaOfExpertiseId(int areaOfExpertiseId) {
        this.areaOfExpertiseId = areaOfExpertiseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "AreaOfExpertiseDTO{" +
                "areaOfExpertiseId=" + areaOfExpertiseId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }


}
