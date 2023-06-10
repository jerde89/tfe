package com.tfe.fournil.controller;




import lombok.extern.slf4j.Slf4j;
        import org.springframework.stereotype.Controller;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The type legal information controller.
 */
@Slf4j
@Controller
@RequestMapping("/legalInformation")
public class LegalInformationController {
    /**
     * Legal information appel string.
     *
     * @return the string
     */
    @GetMapping("")
    public String LegalInformationAppel() {
        //Nom de la JSP

        return "legalInformation";
    }
}