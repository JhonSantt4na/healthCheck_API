package com.santt4na.health_check.controller;

import com.santt4na.health_check.controller.docs.AppoimentControllerDocs;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Appointment Endpoint")
@RestController
@RequestMapping("/appointment")
public class AppointmentController implements AppoimentControllerDocs {
}
