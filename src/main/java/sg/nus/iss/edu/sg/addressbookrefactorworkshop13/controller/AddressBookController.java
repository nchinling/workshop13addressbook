package sg.nus.iss.edu.sg.addressbookrefactorworkshop13.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import sg.nus.iss.edu.sg.addressbookrefactorworkshop13.model.Contact;
import sg.nus.iss.edu.sg.addressbookrefactorworkshop13.service.ContactService;

@Controller
@RequestMapping(path="/form")
public class AddressBookController {

    @Autowired
    private ContactService ctcs;

    @Autowired
    private ApplicationArguments appArgs;

    @Value("${addressbookrefactorworkshop13.default.data.dir}")
    private String defaultDataDir;

    
    
    @GetMapping
    public String getForm(Model m){
        

        m.addAttribute("person", new Contact());
        return "addressbook";
    }


    @PostMapping
    public String savePerson(@ModelAttribute @Valid Contact person, BindingResult binding, Model m ){
        if (binding.hasErrors()){
            return "addressbook";
        }

        ctcs.saveContact(person, m, appArgs, defaultDataDir);
        return "stand";
    }

}
