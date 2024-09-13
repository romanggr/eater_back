package com.eater.eater.utils.email;

public class EmailService {
    public static void banAccount(String userEmail, String reasonOfDeletion, Long adminId) {
        System.out.println(userEmail + " " + reasonOfDeletion + " " + adminId);
    }
}
