public class Medical_prescription {
    private int medicalPrescriptionsId;
    private String prescriptionName;
    private String description;

    public Medical_prescription() {
    }

    public Medical_prescription(int medicalPrescriptionsId, String prescriptionName, String description) {
        this.medicalPrescriptionsId = medicalPrescriptionsId;
        this.prescriptionName = prescriptionName;
        this.description = description;
    }

    public int getMedicalPrescriptionsId() {
        return medicalPrescriptionsId;
    }

    public void setMedicalPrescriptionsId(int medicalPrescriptionsId) {
        this.medicalPrescriptionsId = medicalPrescriptionsId;
    }

    public String getPrescriptionName() {
        return prescriptionName;
    }

    public void setPrescriptionName(String prescriptionName) {
        this.prescriptionName = prescriptionName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Medical_prescription)) return false;
        if (!super.equals(object)) return false;

        Medical_prescription that = (Medical_prescription) object;

        if (getMedicalPrescriptionsId() != that.getMedicalPrescriptionsId()) return false;
        if (!getPrescriptionName().equals(that.getPrescriptionName())) return false;
        if (!getDescription().equals(that.getDescription())) return false;

        return true;
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getMedicalPrescriptionsId();
        result = 31 * result + getPrescriptionName().hashCode();
        result = 31 * result + getDescription().hashCode();
        return result;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Medical_prescription{" +
                "medicalPrescriptionsId=" + medicalPrescriptionsId +
                ", prescriptionName='" + prescriptionName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
