package com.immotef.corona.virus_reports;

import com.immotef.corona.users.PagedUsersResult;
import com.immotef.corona.users.UserController;
import com.immotef.corona.virus_reports.deeplinks.FirebaseDeeplinksService;
import com.immotef.corona.virus_reports.deeplinks.FirebaseDynamicLink;
import com.immotef.corona.virus_reports.deeplinks.VirusReportingCode;
import com.immotef.corona.virus_reports.deeplinks.VirusReportingCodeService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
public class VirusReportServiceTest {

    @Autowired
    VirusReportingCodeService virusReportingCodeService;

    @Autowired
    FirebaseDeeplinksService firebaseDeeplinksService;

    @Autowired
    private UserController userController;

    @Test
    void testGenerateCode() {
        VirusReportingCode virusReportingCode = virusReportingCodeService.generateCodeForDate("2019-06-14T15:50:36");
        assertTrue(virusReportingCode.getCode().isEmpty() == false);

    }

    @Test
    void testFirebaseDynamicLink() {
        FirebaseDynamicLink firebaseDynamicLink = firebaseDeeplinksService.prepareFirebaseDynamicLinkWithDate("2019-06-14T15:50:36");
        assertTrue(firebaseDynamicLink.shortLink.isEmpty() == false);
    }


    @Test
    void testEncoding() throws Exception {
        String page = "2";
        String filter = "filter";
        PagedUsersResult aaa = userController.userList(Optional.of(page),Optional.of(filter));
        int i = 0;
    }
}