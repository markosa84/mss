package hu.ak_akademia.mss.service.validators.editProfil;

import hu.ak_akademia.mss.service.CompareValidator;
import hu.ak_akademia.mss.service.EditProfilService;
import hu.ak_akademia.mss.service.validators.ConfirmationPasswordValidator;
import hu.ak_akademia.mss.service.validators.MSSUserValidatorFactory;

import java.util.HashMap;
import java.util.Map;

public class ClientCompareValidatorEditProfil implements CompareValidator<String> {
    private final EditProfilService editProfilService;

    private final Map<String, String> validatorErrorList;

    public ClientCompareValidatorEditProfil(EditProfilService editProfilService){
        this.editProfilService = editProfilService;
        this.validatorErrorList = new HashMap<>();
    }

    public ClientCompareValidatorEditProfil(EditProfilService editProfilService, Map<String, String> validatorErrorList ){
        this.editProfilService = editProfilService;
        this.validatorErrorList = validatorErrorList;
    }

    @Override
    public void validate(String password, String passWordAgain) {
        var instance = MSSUserValidatorFactory.getInstance();
        instance.collectValidationError(new ConfirmationPasswordValidator(), password, passWordAgain, validatorErrorList);
    }
}
