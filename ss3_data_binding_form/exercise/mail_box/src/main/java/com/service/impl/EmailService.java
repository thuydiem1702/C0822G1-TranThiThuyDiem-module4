package com.service.impl;

import com.model.EmailSetting;
import com.service.IEmailService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmailService implements IEmailService {
    private static List<String> languageList = new ArrayList<>();
    private static List<Integer> pageSizeList = new ArrayList<>();
    private static List<EmailSetting> emailSettingList = new ArrayList<>();

    static {
        languageList.add("English");
        languageList.add("Vietnamese");
        languageList.add("Japanese");
        languageList.add("Chinese");

        pageSizeList.add(5);
        pageSizeList.add(10);
        pageSizeList.add(15);
        pageSizeList.add(25);
        pageSizeList.add(50);
        pageSizeList.add(100);

        emailSettingList.add(new EmailSetting("Vietnamese", 5, false, "Loki"));
        emailSettingList.add(new EmailSetting("English", 10, true, "Odin"));

    }


    @Override
    public List<String> getListLanguage() {
        return languageList;
    }

    @Override
    public List<Integer> getListPageSize() {
        return pageSizeList;
    }

    @Override
    public void AddNewEmailSetting(EmailSetting emailSetting) {
        emailSettingList.add(emailSetting);
    }

    @Override
    public List<EmailSetting> getListEmailSetting() {
        return emailSettingList;
    }

    @Override
    public List<String> getListSignature() {
        List<String> signatureList = new ArrayList<>();

        for (EmailSetting x : emailSettingList) {
            signatureList.add(x.getSignature());
        }

        return signatureList;
    }

    @Override
    public EmailSetting getEmailSettingBasedOnSignature(String signature) {

        for (EmailSetting x : emailSettingList) {
            if (signature.equals(x.getSignature())) {
                return x;
            }
        }

        return null;
    }

    @Override
    public void updateEmailSetting(EmailSetting emailSetting) {
        for (EmailSetting x : emailSettingList) {
            if (x.getSignature().equals(emailSetting.getSignature())) {
                x.setLanguage(emailSetting.getLanguage());
                x.setPageSize(emailSetting.getPageSize());
                x.setSpamsFilter(emailSetting.isSpamsFilter());
            }
        }
    }

    @Override
    public void deleteEmailSetting(EmailSetting emailSetting) {
        for (EmailSetting x : emailSettingList) {
            if (x.getSignature().equals(emailSetting.getSignature())) {
                emailSettingList.remove(x);
            }
        }
    }
}