package org.osmanacademy.webbrowser.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ProcessKiller {
    private final String processName;
    private static final Logger logger = LoggerFactory.getLogger(ProcessKiller.class);

    // Constructor
    public ProcessKiller(String processName) {
        this.processName = processName;
    }

    public void killMatchingProcesses() {
        // Check for operating system
        String os = System.getProperty("os.name");
        if (!os.startsWith("Windows")) {
            logger.warn("This script is designed to run on Windows only");
            return;
        }

        try {
            killProcesses(getProcessIds(getRunningProcesses()));
        } catch (IOException e) {
            logger.error("IOException occurred", e);
            System.exit(1);
        } catch (Exception e) {
            logger.error("Unexpected exception occurred", e);
            System.exit(1);
        }
    }

    private String getRunningProcesses() throws IOException {
        String result;
        Process process = new ProcessBuilder("tasklist").start();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream())
        )) {
            result = reader.lines().collect(Collectors.joining("\n"));
        }
        return result;
    }

    private List<String> getProcessIds(String processes) {
        return Arrays.stream(processes.split("\n"))
                .filter(line -> line.contains(processName))
                .map(line -> line.split("\\s+")[1])
                .collect(Collectors.toList());
    }

    private void killProcesses(List<String> processIds) throws IOException {
        for (String pid : processIds) {
            new ProcessBuilder("taskkill", "/F", "/PID", pid).start();
            logger.info("Killed process {} with PID: {}", processName, pid);
        }
    }

    public static void main(String[] args) {
        ProcessKiller chromedriverKiller = new ProcessKiller("chromedriver.exe");
        chromedriverKiller.killMatchingProcesses();
    }
}