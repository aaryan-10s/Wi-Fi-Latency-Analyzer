# Wi-Fi-Latency-Analyzer

A Java Windows desktop application that measures network latency, packet loss, and jitter to help users understand the stability of their internet connection. 

![Wi-Fi Latency Analyzer Demo](assets/WifiLatencyAnalyzerGIF.gif)

### Test Results
![Wi-Fi Latency Analyzer Results](assets/WifiResults.png)

## Try It
A downloadable version of this application will be provided when the project is shipped.

Wi-Fi Latency Analyzer is currently designed for Windows computers with an internet connection.

## Quick Start

### Requirements

- Windows Computer
- Java 25 (LTS)
- Internet Connection

### Running the Application

1. Download the latest Windows installer from the GitHub Release.
2. Run the installer.
3. Launch Wi-Fi Latency Analyzer from Windows.
4. Select a target and configure your test.
5. Click **Start Test**.
6. To exit, click the red *X* on top right hand side corner

## Features

- Pings and measures latency to multiple targets
- Run multiple ping tests in a session
- User can configure number of tests and delay between tests
- User can choose between 5 available DNS servers
- User validation (to ensure user enters valid inputs)
- Calculates average, minimum, and maximum latency
- Calculates packet loss
- Calculates network jitter
- Displays live test results through a Java Swing Desktop UI

## How It Works

Wi-Fi Latency Analyzer uses the java class 'ProcessBuilder' to extract and use the Ping command from the computer's Operating System, specifically Windows OS. The program reads the command output and extracts the reported latency from each response. Each ping result is stored as a 'PingResult', and each 'PingResult' contains the Target, Latency, Success, and Time Stamp. Multiple results are stored in an 'ArrayList<PingResult>', so the program can actually read the outputs. After pinging, the Latency Analyzer calculates average, minimum, and maximum latency, packet loss, and jitter. The Java Swing Interface connects to the latency testing backend file 'src.java' which contains ProcessBuilder; the results are displayed through a simple graphical user interface (GUI).

## Technical Decisions

### ProcessBuilder/Windows Ping Command

Instead of attempting to implement network testing software from scratch, the project uses java's 'ProcessBuilder' to interact with the local Windows 'Ping' command. This feature allows the program to use an existing, reliable command while focusing on calculating and analyzing latency statistics.

### Storing Individual Ping Results

Each test performed is stored as a  'PingResult' object containing information about that test. Multiple ping results are stored in 'ArrayList<PingResult>', so the program can run and store multiple ping results and analyze statistics like average latency.

### Analyzing Jitter

Jitter is an important latency measurement that determines the stability of the connection. Jitter is calculated by finding the absolute difference between each consecutive pair of individual ping results and averaging them to find the session's average jitter (in milliseconds).

## AI Usage

ChatGPT was used as a debugger, teacher, and assistant throughout the development of this major project. When I encountered unfamiliar concepts for the first time, like ProcessBuilder or Java Swing, ChatGPT aided my progress by teaching me the fundamentals and assisting in my architectural development. For the GUI within 'LatencyAnalyzerWindow.java', AI was especially helpful in Swing's syntax and API.

## Developer's Note

VSCode's Hackatime extension tracked the time spent writing code, but many hours were poured into this project outside of VSCode primarily from planning architecture, calculations, brainstorming, and learning new concepts.

## Credits/Acknowledgements

- **Java Swing**: used to build the program's user interface and desktop application
- **Java 'ProcessBuilder'**: used operating system's built-in Ping commands to perform tests
- **ChatGPT**: used as a learning assistant, debugger, and teacher
