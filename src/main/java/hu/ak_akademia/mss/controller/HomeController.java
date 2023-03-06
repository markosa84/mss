package hu.ak_akademia.mss.controller;

import hu.ak_akademia.mss.model.user.Client;
import hu.ak_akademia.mss.model.user.MssUser;
import hu.ak_akademia.mss.service.LoginService;
import hu.ak_akademia.mss.service.PasswordEncryption;
import hu.ak_akademia.mss.service.RegistrationService;
import hu.ak_akademia.mss.service.exceptions.IncorrectEnteredDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Map;

@Controller
@RequestMapping("/")
public class HomeController {

    private LoginService loginService;
    private RegistrationService registrationService;

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    public void createTableGender() throws SQLException {
        // indulás előtt létrehozzuk a Gender táblát majd fel is töltjük adatokkal
        ScriptUtils.executeSqlScript(dataSource.getConnection(), new ClassPathResource("gender_schema.sql"));
        ScriptUtils.executeSqlScript(dataSource.getConnection(), new ClassPathResource("language.sql"));
        //                                                                          
        ScriptUtils.executeSqlScript(dataSource.getConnection(), new ClassPathResource("doctor.sql"));
        ScriptUtils.executeSqlScript(dataSource.getConnection(), new ClassPathResource("client.sql"));
        ScriptUtils.executeSqlScript(dataSource.getConnection(), new ClassPathResource("admin.sql"));
        ScriptUtils.executeSqlScript(dataSource.getConnection(), new ClassPathResource("assistant.sql"));
    }

    @Autowired
    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    @Autowired
    public void setRegistrationService(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping
    public String index() {
        // na ide kéne nekem létrehozni a felhasználokat vagy mégse ?

        return "/index";
    }

    @ExceptionHandler(value = RuntimeException.class)
    public String error(RuntimeException e, Model model) {
        model.addAttribute("exception", e.getMessage());
        return "error";
    }

//    ************************************************************************************************************

    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    @PostMapping("/login")
    public String loginProcess(Model model, @RequestParam String email, @RequestParam String password) {
        var currentPassword = new PasswordEncryption(password).encryptWithMD5();
        try {
            MssUser user = registrationService.authentication(email, currentPassword);
        } catch (IncorrectEnteredDataException e) {
            model.addAttribute(e.getMessage(), e.getErrorMessage());
            return "login";
        }
        return "redirect:/";
    }

//    **************************************************************************************************************

    @GetMapping("/register")
    public String registration(Client client, Model model) {
        // TODO: join the client object to the RegistrationService;
        return "registration";
    }

    @PostMapping("/register/client")
    public String registrationForm(Client client, Model model) {
        Map<String, String> errorList = registrationService.testMSSUserData(client);
        if (errorList.isEmpty()) {
            registrationService.save(client);
            return "index";
        }
        model.addAllAttributes(errorList);
        return "registration";
    }
}
