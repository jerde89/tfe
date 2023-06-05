package com.tfe.fournil.controller;

/**
 * The type Change password dto.
 */
public class ChangePasswordDTO {
    private String oldPassword;
    private String newPassword;
    private String newConfirmPassword;

    /**
     * Gets old password.
     *
     * @return the old password
     */
    public String getOldPassword() {
        return oldPassword;
    }

    /**
     * Sets old password.
     *
     * @param oldPassword the old password
     */
    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    /**
     * Gets new password.
     *
     * @return the new password
     */
    public String getNewPassword() {
        return newPassword;
    }

    /**
     * Sets new password.
     *
     * @param newPassword the new password
     */
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    /**
     * Gets new confirm password.
     *
     * @return the new confirm password
     */
    public String getNewConfirmPassword() {
        return newConfirmPassword;
    }

    /**
     * Sets new confirm password.
     *
     * @param newConfirmPassword the new confirm password
     */
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
