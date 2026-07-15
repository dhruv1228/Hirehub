package com.hirehub.common.email;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/test")
@RequiredArgsConstructor
public class TestEmailController {

    private final EmailService emailService;

    @GetMapping("/email")
    public String sendTestEmail() {

        emailService.sendEmail(
                "dhruv.rishi.papa@gmail.com",
                "HireHub Test",
                "Congratulations! Email integration is working."
        );

        return "Email Sent";
    }
}