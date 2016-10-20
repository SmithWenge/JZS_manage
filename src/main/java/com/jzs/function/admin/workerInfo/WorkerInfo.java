package com.jzs.function.admin.workerInfo;

import com.jzs.arc.Entry;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class WorkerInfo extends Entry{
    private int userId;
    private String userName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date attendanceDate;
    private int userGender;
    private String userTelOne;
    private int userPost;
    private String attTime;
    private int count;
    private String attendanceTime;

    public String getAttendanceTime() {
        return attendanceTime;
    }

    public void setAttendanceTime(String attendanceTime) {
        this.attendanceTime = attendanceTime;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getAttTime() {
        return attTime;
    }

    public void setAttTime(String attTime) {
        this.attTime = attTime;
    }

    public int getUserPost() {
        return userPost;
    }

    public void setUserPost(int userPost) {
        this.userPost = userPost;
    }

    public int getUserGender() {
        return userGender;
    }

    public void setUserGender(int userGender) {
        this.userGender = userGender;
    }

    public String getUserTelOne() {
        return userTelOne;
    }

    public void setUserTelOne(String userTelOne) {
        this.userTelOne = userTelOne;
    }

    public Date getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(Date attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
