package com.service;

import com.model.EmailSetting;

import java.util.List;

public interface IEmailService {

    List<String> getListLanguage();

    List<Integer> getListPageSize();

    void AddNewEmailSetting(EmailSetting emailSetting);

    List<EmailSetting> getListEmailSetting();

    List<String> getListSignature();

    EmailSetting getEmailSettingBasedOnSignature(String signature);

    void updateEmailSetting(EmailSetting emailSetting);

    void deleteEmailSetting(EmailSetting emailSetting);
}
