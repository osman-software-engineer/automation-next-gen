package org.osmanacademy.utils;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ChromeDriverKiller {
    public static void main(String[] args) {
        try {
            // Command to list all processes with 'chromedriver' in the name
            String line;
            Process process = Runtime.getRuntime().exec("tasklist");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            List<String> processIds = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                if (line.contains("chromedriver.exe")) {
                    // Extracting the process ID from the line
                    String[] parts = line.split("\\s+");
                    processIds.add(parts[1]); // Assuming the PID is in the second column
                }
            }

            // Killing the processes
            for (String pid : processIds) {
                Runtime.getRuntime().exec("taskkill /F /PID " + pid);
                System.out.println("Killed chromedriver process with PID: " + pid);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

