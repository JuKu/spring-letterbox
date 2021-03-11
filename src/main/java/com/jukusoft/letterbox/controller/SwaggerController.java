package com.jukusoft.letterbox.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller for swagger documentation.
 *
 * @author Vincent Gerber
 */
@Controller
public class SwaggerController {

    /**
     * Redirect all requests for /swagger to the html page.
     *
     * @return Redirect String
     */
    @GetMapping("/swagger")
    public String redirectToSwaggerUI() {
        return "redirect:/swagger-ui.html";
    }

}
