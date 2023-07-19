package com.manage.job.controller;

import com.manage.job.service.DailyJobService;
import jakarta.mail.MessagingException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("/job")
public class DailyJobController {

    private final DailyJobService dailyJobService;

    public DailyJobController(DailyJobService dailyJobService) {
        this.dailyJobService = dailyJobService;
    }

    @Scheduled(cron = "*/30 * * * * *")
    public void notifyDailyJob() throws MessagingException, UnsupportedEncodingException {
        dailyJobService.sendMailNotifyDailyTask();
    }
}
