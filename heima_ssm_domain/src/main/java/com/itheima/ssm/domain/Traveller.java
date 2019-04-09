package com.itheima.ssm.domain;

import java.io.Serializable;

/**
 * @Author: Jason
 * @Date: 2019/4/2 18:53
 * @Description:
 */

public class Traveller implements Serializable {
    private String id;
    private String name;
    private String sex;
    private String phoneNum;
    private Integer credentialsType;//证件类型 0身份证 1护照 2军官证
    private String credentialsTypeStr;
    private String credentialsNum;
    private Integer travellerType;//旅客类型(人群) 0 成人 1 儿童
    private String travellerTypeStr;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Integer getCredentialsType() {
        return credentialsType;
    }

    public void setCredentialsType(Integer credentialsType) {
        //证件类型 0身份证 1护照 2军官证
        this.credentialsType = credentialsType;
        if (credentialsType!=null){
            if (credentialsType==0){
                this.setCredentialsTypeStr("身份证");
            }else if (credentialsType==1){
                this.setCredentialsTypeStr("护照");
            }else if (credentialsType==2){
                this.setCredentialsTypeStr("军官证");
            }else {
                this.setCredentialsTypeStr("未填写");
            }
        }
    }

    public String getCredentialsTypeStr() {
        return credentialsTypeStr;
    }

    public void setCredentialsTypeStr(String credentialsTypeStr) {
        this.credentialsTypeStr = credentialsTypeStr;
    }

    public String getCredentialsNum() {
        return credentialsNum;
    }

    public void setCredentialsNum(String credentialsNum) {
        this.credentialsNum = credentialsNum;
    }

    public Integer getTravellerType() {
        return travellerType;
    }

    public void setTravellerType(Integer travellerType) {
        this.travellerType = travellerType;
        if(travellerType!=null){
            if (travellerType==0){
                this.setTravellerTypeStr("成人");
            }else if (travellerType==1){
                this.setTravellerTypeStr("儿童");
            }
        }
    }

    public String getTravellerTypeStr() {
        return travellerTypeStr;
    }

    public void setTravellerTypeStr(String travellerTypeStr) {
        this.travellerTypeStr = travellerTypeStr;
    }
}
