public class Email_subject {
    private int subjectId;
    private String subjectName;
    private String subjectDescription;

    public Email_subject() {
    }

    public Email_subject(int subjectId, String subjectName, String subjectDescription) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.subjectDescription = subjectDescription;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectDescription() {
        return subjectDescription;
    }

    public void setSubjectDescription(String subjectDescription) {
        this.subjectDescription = subjectDescription;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Email_subject)) return false;
        if (!super.equals(object)) return false;

        Email_subject that = (Email_subject) object;

        if (getSubjectId() != that.getSubjectId()) return false;
        if (!getSubjectName().equals(that.getSubjectName())) return false;
        if (!getSubjectDescription().equals(that.getSubjectDescription())) return false;

        return true;
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getSubjectId();
        result = 31 * result + getSubjectName().hashCode();
        result = 31 * result + getSubjectDescription().hashCode();
        return result;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Email_subject{" +
                "subjectId=" + subjectId +
                ", subjectName='" + subjectName + '\'' +
                ", subjectDescription='" + subjectDescription + '\'' +
                '}';
    }
}
