package com.crm.organizecrm.service;

import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.InputStreamReader;

@Service
public class ShellCommandService {

    public String executeCommand(String command) {
        StringBuilder output = new StringBuilder();

        try {
            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.command("bash", "-c", command);

            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            int exitVal = process.waitFor();
            if (exitVal != 0) {
                return "Error executing command";
            }

        } catch (Exception e) {
            return "Exception: " + e.getMessage();
        }

        return output.toString();
    }
}
