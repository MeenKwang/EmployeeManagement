package com.manage.job.service.impl;

import com.manage.employeemanagementmodel.entity.CheckIn;
import com.manage.employeemanagementmodel.entity.Employee;
import com.manage.employeemanagementmodel.entity.Punishment;
import com.manage.employeemanagementmodel.entity.PunishmentType;
import com.manage.employeemanagementmodel.entity.enums.TypeTimeOff;
import com.manage.job.dto.AbsenceDto;
import com.manage.job.dto.CheckInDto;
import com.manage.job.repository.*;
import com.manage.job.service.PunishmentService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
@Service
public class PunishmentServiceImpl implements PunishmentService {

    private final EmployeeRepository employeeRepository;
    private final AbsenceRepository absenceRepository;
    private final CheckInRepository checkInRepository;
    private final PunishmentRepository punishmentRepository;
    private final PunishmentTypeRepository punishmentTypeRepository;

    public PunishmentServiceImpl(EmployeeRepository employeeRepository, AbsenceRepository absenceRepository, CheckInRepository checkInRepository, PunishmentRepository punishmentRepository, PunishmentTypeRepository punishmentTypeRepository) {
        this.employeeRepository = employeeRepository;
        this.absenceRepository = absenceRepository;
        this.checkInRepository = checkInRepository;
        this.punishmentRepository = punishmentRepository;
        this.punishmentTypeRepository = punishmentTypeRepository;
    }

    @Override
    @Async
    public void checkPunishmentPerDay(List<Employee> employees) {
        List<Integer> employeeIdList = employeeRepository.findAllEmployeeId();
        for(Integer id : employeeIdList) {
            List<AbsenceDto> absenceDtoList = absenceRepository.getAbsenceInCurrentDay(id, LocalDate.now());
            CheckInDto checkInDto = checkInRepository.getCheckInOfEmployeePerDay(id, LocalDate.now());
            LocalDateTime checkIn = checkInDto.getCheckInTime();
            LocalDateTime checkOut = checkInDto.getCheckOutTime();
            LocalTime startTime = LocalTime.of(8, 46);
            LocalTime endTime = LocalTime.of(17, 14);
            Punishment punishment = new Punishment();
            CheckIn checkInEntity = new CheckIn();
            checkInEntity.setId(checkInDto.getId());
            punishment.setCheckIn(checkInEntity);
            punishment.setDate(LocalDate.now());
            punishment.setComplain(null);
            punishment.setComplainReply(null);
            if(absenceDtoList.isEmpty()) {
                if(checkIn != null && checkOut != null) {
                    checkPunishmentOfFullyCheckinDay(checkIn, checkOut, startTime, punishment);
                }
                if(checkIn == null && checkOut == null) {
                    List<PunishmentType> lst = new ArrayList<>();
                    lst.add(punishmentTypeRepository.customFindByName(com.manage.employeemanagementmodel.entity.enums.PunishmentType.NOT_CHECK_IN_AND_OUT.toString()));
                    punishment.setPunishmentType(lst);
                    punishmentRepository.save(punishment);
                }
                if(checkOut == null && checkIn != null) {
                    Duration checkInDuration = Duration.between(checkIn.toLocalTime(), startTime);
                    checkPunishmentWhenNotCheckIn(punishment, checkInDuration);
                }
            } else {
                if(checkIn != null && checkOut != null) {
                    for(AbsenceDto absence : absenceDtoList) {
                        switch (absence.getTypeTimeOff()) {
                            case MORNING, COME_LATE:
                                startTime = startTime.plusMinutes((long) absence.getTimeOff().doubleValue() * 60);
                                break;
                            case AFTERNOON, WENT_SOON:
                                endTime = endTime.minusMinutes((long) absence.getTimeOff().doubleValue() * 60);
                                break;
                            case FULL_DAY:
                                return;
                        }
                    }
                    checkPunishmentOfFullyCheckinDay(checkIn, checkOut, startTime, punishment);
                }
                if(checkIn == null && checkOut == null) {
                    for(AbsenceDto absence : absenceDtoList) {
                        if(absence.getTypeTimeOff() == TypeTimeOff.FULL_DAY) return;
                    }
                    List<PunishmentType> lst = new ArrayList<>();
                    lst.add(punishmentTypeRepository.customFindByName(com.manage.employeemanagementmodel.entity.enums.PunishmentType.NOT_CHECK_IN_AND_OUT.toString()));
                    punishment.setPunishmentType(lst);
                    punishmentRepository.save(punishment);
                }
                if(checkOut == null && checkIn != null) {
                    Duration checkInDuration = Duration.between(checkIn.toLocalTime(), startTime);
                    for(AbsenceDto absence : absenceDtoList) {
                        switch (absence.getTypeTimeOff()) {
                            case MORNING, COME_LATE:
                                startTime = startTime.plusMinutes((long) absence.getTimeOff().doubleValue() * 60);
                                break;
                        }
                    }
                    checkPunishmentWhenNotCheckIn(punishment, checkInDuration);
                }
            }
        }
    }

    private void checkPunishmentWhenNotCheckIn(Punishment punishment, Duration checkInDuration) {
        List<PunishmentType> lst = new ArrayList<>();
        if(!checkInDuration.isNegative()) {
            lst.add(punishmentTypeRepository.customFindByName(com.manage.employeemanagementmodel.entity.enums.PunishmentType.CHECK_IN_LATE.toString()));
        } else {
            lst.add(punishmentTypeRepository.customFindByName(com.manage.employeemanagementmodel.entity.enums.PunishmentType.CHECK_IN_LATE_AND_NOT_CHECK_OUT.toString()));
        }
        punishment.setPunishmentType(lst);
        punishmentRepository.save(punishment);
    }

    private void checkPunishmentOfFullyCheckinDay(LocalDateTime checkIn, LocalDateTime checkOut, LocalTime startTime, Punishment punishment) {
        Duration duration = Duration.between(checkIn, checkOut);
        long hours = duration.toHours();
        Duration checkInDuration = Duration.between(checkIn.toLocalTime(), startTime);
        if(!checkInDuration.isNegative()) {
            List<PunishmentType> lst = new ArrayList<>();
            lst.add(punishmentTypeRepository.customFindByName(com.manage.employeemanagementmodel.entity.enums.PunishmentType.CHECK_IN_LATE.toString()));
            punishment.setPunishmentType(lst);
            punishmentRepository.save(punishment);
        }
    }

}
