package com.manage.job.service;

import jakarta.mail.MessagingException;

import java.io.UnsupportedEncodingException;

public interface DailyJobService {
    void sendMailNotifyDailyTask() throws MessagingException, UnsupportedEncodingException;
}
