package com.manage.employeemanagementmodel.entity.enums;

public enum AbsenceType {
    NORMAL_ABSENCE("Nghỉ thông thường"),
    WEDDING_DAY("Nghỉ cưới bản thân (3 ngày phép)"),
    WIFE_ABSENCE_1("Nghỉ vợ sinh thường (3 ngày phép)"),
    WIFE_ABSENCE_2("Nghỉ vợ sinh mổ (7 ngày phép)");

    private AbsenceType (String description) {}
}
