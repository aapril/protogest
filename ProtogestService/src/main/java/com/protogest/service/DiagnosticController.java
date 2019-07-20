package com.protogest.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DiagnosticController {
    @GetMapping("/diagnostics")
    public @ResponseBody
    ResponseEntity diagnostic() {
        return ResponseEntity.ok("Server Running.");
    }
}
