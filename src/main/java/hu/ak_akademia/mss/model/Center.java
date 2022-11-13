package hu.ak_akademia.mss.model;

import java.util.Objects;

public class Center {

    private int centerId;
    private String centerName;
    private int areaOfExpertiseId;
    private String address;
    private String phoneNumber;
    private String description;

    public int getCenterId() {
        return centerId;
    }

    public void setCenterId(int centerId) {
        this.centerId = centerId;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getAreaOfExpertiseId() {
        return areaOfExpertiseId;
    }

    public void setAreaOfExpertiseId(int areaOfExpertiseId) {
        this.areaOfExpertiseId = areaOfExpertiseId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Center() {
    }


    public Center(int centerId, String centerName, int areaOfExpertiseId, String address, String phoneNumber, String description) {
        this.centerId = centerId;
        this.centerName = centerName;
        this.areaOfExpertiseId = areaOfExpertiseId;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.description = description;
    }

    @Override
    public int hashCode() {
        return Objects.hash(centerId, centerName, areaOfExpertiseId, address, phoneNumber, description);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Center center = (Center) o;

        if (centerId != center.centerId) return false;
        if (areaOfExpertiseId != center.areaOfExpertiseId) return false;
        if (!Objects.equals(centerName, center.centerName)) return false;
        if (!Objects.equals(address, center.address)) return false;
        if (!Objects.equals(phoneNumber, center.phoneNumber)) return false;
        return Objects.equals(description, center.description);
    }

    @Override
    public String toString() {
        return "Center{" +
                "centerId=" + centerId +
                ", centerName='" + centerName + '\'' +
                ", areaOfExpertiseId=" + areaOfExpertiseId +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
