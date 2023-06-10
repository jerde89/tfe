package com.tfe.fournil.service;

import com.tfe.fournil.controller.ChangePasswordDTO;
import com.tfe.fournil.entity.User;
import com.tfe.fournil.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

/**
 * The type User service.
 */
@Service
public class UserService {
    /**
     * The User repository.
     */
    @Autowired
    UserRepository userRepository;

    /**
     * The Mail service.
     */
    @Autowired
    MailService mailService;


    /**
     * Check if email is unique boolean.
     *
     * @param email the email
     * @return the boolean
     */
    public boolean checkIfEmailIsUnique(String email) {
        List<User> userList = userRepository.findByEmail(email);
        return CollectionUtils.isEmpty(userList);
    }


    /**
     * Change password.
     *
     * @param dto the dto
     * @throws Exception the exception
     */
    public void changePassword(ChangePasswordDTO dto) throws Exception {
        if (!dto.getNewPassword().equals(dto.getNewConfirmPassword())) {
            throw new Exception("Le nouveau mot de passe et le mot de passe confirmer sont différents");
        }
        Optional<User> currentUserOptional = getCurrentUser();
        if (!currentUserOptional.isPresent()) {
            throw new Exception("l'utilisateur n'a pu être trouvé");
        }
        User currentUser = currentUserOptional.get();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (passwordEncoder.matches(dto.getNewPassword(), currentUser.getPassword())) {
            throw new Exception("L'ancien mot de passe n'est pas correct");
        }
        currentUser.setPassword(encryptPassword(dto.getNewPassword()));
        userRepository.save(currentUser);
    }

    /**
     * Gets current user.
     *
     * @return the current user
     */
    public Optional<User> getCurrentUser() {
        //Avec l'objet principal, on recuprère via le security spring boot l'email, le mdp et le role
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return Optional.of(userRepository.findByUsername(username));
    }

    private String encryptPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);

    }

    /**
     * Reset password.
     *
     * @param email the email
     */
    public void resetPassword(String email)  {
        User user = userRepository.findByUsername(email);
//        if (user == null) {
////            throw new Exception("l'email n'existe pas");
//        }
        sendMailResetPassword(user, "reset password");
    }

    /**
     * Send mail reset password.
     *
     * @param user    the user
     * @param subject the subject
     */
    public void sendMailResetPassword(User user, String subject) {
        //création d'une variable newpassword qui va chercher le résultat du service GeneratePassword
        String newPassword = GeneratePassword.generatePassword(10);
//        mailService.sendEmail(user.getEmail(), "Fournil - mot de passe oublié", "Votre nouveau mot de passe est " + newPassword);
        mailService.sendEmail(user.getEmail(), "Fournil - Mot de passe oublié",
                "Cher utilisateur,\n" +
                        "\n" +
                        "Nous vous prions de trouver ci-dessous votre nouveau mot de passe pour votre compte. Par mesure de précaution et afin de garantir la protection de vos informations, nous vous recommandons vivement de modifier votre mot de passe dès que possible.\n" +
                        "\n" +
                         newPassword +
                        "\n" +
                        "\n" +
                        "Pour ce faire, veuillez accéder à la page de modification du mot de passe. Cela vous permettra de choisir un nouveau mot de passe solide et unique, renforçant ainsi la sécurité de votre compte.\n" +
                        "\n" +
                        "La sécurité de nos utilisateurs est une priorité absolue pour nous, et nous vous remercions de votre coopération dans cette démarche. Si vous avez des questions ou besoin d'une assistance supplémentaire, n'hésitez pas à nous contacter. Nous sommes là pour vous aider.\n" +
                        "\n" +
                        "Cordialement,\n" +
                        "L'équipe de support " );
        user.setPassword(new BCryptPasswordEncoder().encode(newPassword));
        userRepository.save(user);
    }

    /**
     * Check if email exist boolean.
     *
     * @param email the email
     * @return the boolean
     */
    public boolean checkIfEmailExist(String email) {
//        List<User> userList = userRepository.findByEmail(email);
//        return (long) userList.size() == 1;
        User user = userRepository.findByUsername(email);
        if (user !=null){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Map to user dto user dto.
     *
     * @param user the user
     * @return the user dto
     */
    public UserDTO mapToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName(user.getFirstname());
        userDTO.setLastName(user.getLastname());
        return userDTO;
    }

}
