package hu.ak_akademia.mss.dto;

import hu.ak_akademia.mss.model.user.MssUser;

public class DoctorForAreaOfExpertiseDto {
    private   int docotr_Id ;
    private  String name;

    public DoctorForAreaOfExpertiseDto(MssUser docotor) {
        this.docotr_Id = docotor.getUserId();
        this.name = "Dr. " + docotor.getFirstName() + " " + docotor.getLastName();
    }
//lehetséges tartalom
    public int getDocotr_Id() {
        return docotr_Id;
    }

    public void setDocotr_Id(int docotr_Id) {
        this.docotr_Id = docotr_Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
