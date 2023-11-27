package hu.ak_akademia.mss.dto;

import hu.ak_akademia.mss.model.user.MssUser;

public class DoctorForAreaOfExpertiseDto {
    private   int doctorId;
    private  String name;

    public DoctorForAreaOfExpertiseDto(MssUser docotor) {
        this.doctorId = docotor.getUserId();
        this.name = "Dr. " + docotor.getFirstName() + " " + docotor.getLastName();
    }
//lehetséges tartalom
    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
