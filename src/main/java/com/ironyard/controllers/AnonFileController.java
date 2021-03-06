package com.ironyard.controllers;

import com.ironyard.PasswordStorage;
import com.ironyard.entities.AnonFile;
import com.ironyard.services.AnonFileRepository;
import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.SQLException;

/**
 * Created by illladell on 6/27/16.
 */

@Controller
public class AnonFileController {

    @Autowired
    AnonFileRepository files;

    @PostConstruct
    public void init() throws SQLException {
        Server.createWebServer().start();

    }

    @RequestMapping(path = "/upload", method = RequestMethod.POST)
    public String upload(MultipartFile file, boolean permanent, String comment, String password) throws Exception {
        File dir = new File("public/files");
        dir.mkdirs();


        if (files.countByIsPermanentFalse() > 3) {
            Iterable<AnonFile> returnFiles = files.findByIsPermanentFalseOrderByIdAsc();
            if (returnFiles.iterator().hasNext()) {
                AnonFile anon = returnFiles.iterator().next();
                files.delete(anon);
                File newFile = new File("public/files/" + anon.getRealFileName());
                newFile.delete();
            }
        }

        File uploadedFile = File.createTempFile("file", file.getOriginalFilename(), dir);
        FileOutputStream fos = new FileOutputStream(uploadedFile);

        fos.write(file.getBytes());

        AnonFile anonFile = new AnonFile(file.getOriginalFilename(), uploadedFile.getName(), permanent, comment, PasswordStorage.createHash(password));
        files.save(anonFile);

        return "redirect:/";
    }

    @RequestMapping(path = "/delete", method = RequestMethod.POST)
    public String delete(int id, String password) throws Exception {
        AnonFile anon = files.findOne(id);
        if (!PasswordStorage.verifyPassword(password, anon.getPassword())) {
            throw new Exception("Wrong password!");
        }
        else {
            files.delete(anon);
        }
        return "redirect:/";
    }
}
