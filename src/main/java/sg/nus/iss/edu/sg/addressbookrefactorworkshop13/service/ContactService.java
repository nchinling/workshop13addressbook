package sg.nus.iss.edu.sg.addressbookrefactorworkshop13.service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;

import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import sg.nus.iss.edu.sg.addressbookrefactorworkshop13.model.Contact;

@Component("contacts")
public class ContactService {
    

    public void saveContact(Contact ctc, Model model, ApplicationArguments appArgs,
    String defaultDataDir){
    String datafilename = ctc.getId();
    PrintWriter printWriter = null;

    try {
        FileWriter fileWriter =new FileWriter(getDataDir(appArgs, defaultDataDir)
            + "/" + datafilename);
        printWriter = new PrintWriter(fileWriter);
        printWriter.println(ctc.getName());
        printWriter.println(ctc.getEmail());
        printWriter.println(ctc.getPhoneNumber());
        printWriter.println(ctc.getDateOfBirth());
        printWriter.close();
    } catch (IOException e) {
        e.printStackTrace();
    }

    model.addAttribute("person", new Contact(ctc.getId(), 
            ctc.getName(), ctc.getEmail(), ctc.getPhoneNumber(), ctc.getDateOfBirth()));
    }

    private String getDataDir(ApplicationArguments appArgs, String defaultDataDir){
        String dataDirResult = "";
        List<String> optValues = null;
        String[] optValuesArr = null;
        Set<String> opsNames = appArgs.getOptionNames();
        String[] opsNamesArr = opsNames.toArray(new String[opsNames.size()]);
        if(opsNamesArr.length > 0){
            optValues = appArgs.getOptionValues(opsNamesArr[0]);
            optValuesArr = optValues.toArray(new String[optValues.size()]);
            dataDirResult = optValuesArr[0];
        }else{
            dataDirResult = defaultDataDir;
        }

        return dataDirResult;
    }
}
