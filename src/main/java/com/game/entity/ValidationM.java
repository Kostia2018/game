package com.game.entity;

import java.util.Date;
import java.util.GregorianCalendar;

public class ValidationM {

    public static boolean idValidation(String id) {


        long idL = 0;

        try {
            idL = Long.parseLong(id);
        } catch (Exception e) {
            return false;
        }
        if (idL > 0) {
            return true;
        }
        return false;
    }


    public static boolean isCreatePlayer(Player player) {
        if (player.getName() == null || player.getName().length() > 12) {
            return false;
        }
        if (player.getTitle() == null || player.getTitle().length() > 30) {
            return false;
        }

        if (player.getRace() == null || player.getProfession() == null) {
            return false;
        }

        if (player.getExperience() == null || player.getExperience() > 10000000||player.getExperience()<0) {
            return false;
        }

        if(player.getBirthday()==null){
            return false;
        }

        if (player.getBirthday().getTime() < 0) {
            return false;
        }

        return true;
    }
    public static boolean validBirtDay(Date date) {
        if(date == null) {
            return true;
       //попытаться разобраться почему так
        }

        Long bDay = date.getTime();
        Long begin = new GregorianCalendar(2000,0,0).getTimeInMillis();
        Long end = new GregorianCalendar(3000,0,0).getTimeInMillis();

        if(bDay < 0 || (bDay < begin || bDay > end)) {
            return false;
        }


        return true;
    }
    public static boolean cheExp(Integer e) {
        if(e == null) {
            return  true;
        }
        if(e < 0 || e > 10_000_000 ) {
            return false;
        }
        return true;
    }
}
