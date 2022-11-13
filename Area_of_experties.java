public class Area_of_experties {
    private int areaOfExpertisesId;
    private String expertiesName;
    private int treatmentLength;
    private int administrationLength;
    private int centerId;
    private int roomId;
    private int prise;

    public Area_of_experties() {
    }

    public Area_of_experties(int areaOfExpertisesId, String expertiesName, int treatmentLength, int administrationLength, int centerId, int roomId, int prise) {
        this.areaOfExpertisesId = areaOfExpertisesId;
        this.expertiesName = expertiesName;
        this.treatmentLength = treatmentLength;
        this.administrationLength = administrationLength;
        this.centerId = centerId;
        this.roomId = roomId;
        this.prise = prise;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Area_of_experties)) return false;
        if (!super.equals(object)) return false;

        Area_of_experties that = (Area_of_experties) object;

        if (areaOfExpertisesId != that.areaOfExpertisesId) return false;
        if (treatmentLength != that.treatmentLength) return false;
        if (administrationLength != that.administrationLength) return false;
        if (centerId != that.centerId) return false;
        if (roomId != that.roomId) return false;
        if (prise != that.prise) return false;
        if (!expertiesName.equals(that.expertiesName)) return false;

        return true;
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + areaOfExpertisesId;
        result = 31 * result + expertiesName.hashCode();
        result = 31 * result + treatmentLength;
        result = 31 * result + administrationLength;
        result = 31 * result + centerId;
        result = 31 * result + roomId;
        result = 31 * result + prise;
        return result;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Area_of_experties{" +
                "areaOfExpertisesId=" + areaOfExpertisesId +
                ", expertiesName='" + expertiesName + '\'' +
                ", treatmentLength=" + treatmentLength +
                ", administrationLength=" + administrationLength +
                ", centerId=" + centerId +
                ", roomId=" + roomId +
                ", prise=" + prise +
                '}';
    }
}
