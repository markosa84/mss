package hu.ak_akademia.mss.dto;

import hu.ak_akademia.mss.model.AreaOfExpertise;
import hu.ak_akademia.mss.repository.AreaOfExpertiseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AreaOfExpertiseDTO {

    private int areaOfExpertiseId;
    private String name;
    private String description;
    @Autowired
    private AreaOfExpertiseRepository areaOfExpertiseRepository;


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

    public List<AreaOfExpertiseDTO> getAreOfExpertiseDTO () {
        ArrayList<AreaOfExpertiseDTO> listOfAreaOfExpertise = new ArrayList<>();

        for (AreaOfExpertise areaOfExpertise : areaOfExpertiseRepository.findAll()) {
            var DTO = new AreaOfExpertiseDTO();
            DTO.setName(areaOfExpertise.getName());
            DTO.setAreaOfExpertiseId(areaOfExpertise.getAreaOfExpertiseId());
            DTO.setDescription(areaOfExpertise.getDescription());
            listOfAreaOfExpertise.add(DTO);
        }
        return listOfAreaOfExpertise;
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
