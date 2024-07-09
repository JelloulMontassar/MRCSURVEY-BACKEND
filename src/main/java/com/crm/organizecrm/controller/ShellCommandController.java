package com.crm.organizecrm.controller;

import com.crm.organizecrm.service.ShellCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shell")
public class ShellCommandController {

    @Autowired
    private ShellCommandService shellCommandService;

    @PostMapping("/execute")
    public String executeShellCommand(@RequestParam String command) {
        return shellCommandService.executeCommand(command);
    }
}
