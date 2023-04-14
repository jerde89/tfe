package com.tfe.fournil.controller;

public class ChangePasswordDTO {
    private String oldPassword;
    private String newPassword;
    private String newConfirmPassword;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewConfirmPassword() {
        return newConfirmPassword;
    }

    public void setNewConfirmPassword(String newConfirmPassword) {
        this.newConfirmPassword = newConfirmPassword;
    }

    @Override
    public String toString() {
        return "ChangePasswordDTO{" +
                "oldPassword='" + oldPassword + '\'' +
                ", newPassword='" + newPassword + '\'' +
                ", newConfirmPassword='" + newConfirmPassword + '\'' +
                '}';
    }
}
