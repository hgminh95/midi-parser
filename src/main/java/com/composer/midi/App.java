package com.composer.midi;

import com.composer.midi.event.ControlEvent;
import com.composer.midi.event.TrackEvent;

/**
 * Created by hgminh95 on 11/01/16
 */
public class App {

    private static String inputFilePath;
    private static String outputFilePath = "a.out";
    private static String command = "parse";
    private static String outputType = "none";

    public static void main(String[] args) throws Exception {

        parseArgs(args);

        switch (command) {
            case "help":
                printHelp();
                break;
            case "parse":
                parseMidiFile();
                break;
        }
    }

    private static void parseArgs(String[] args) {
        if (args.length < 1)
            printError("Missing input file");

        inputFilePath = args[0];

        for (int i = 1; i < args.length; i++) {
            System.out.println(args[i]);

            switch (args[i]) {
                case "-o":
                case "--output":
                    if (i == args.length - 1) {
                        printError("Missing output file path");
                    }

                    outputFilePath = args[i + 1];
                    break;

                case "-t":
                case "--type":
                    if (i == args.length - 1) {
                        printError("Missing type parameter");
                    }

                    outputType = args[i + 1];
                    break;

                case "-h":
                case "--help":

                    command = "help";
                    break;
            }
        }
    }

    private static void parseMidiFile() {
        MidiFile file = null;

        System.out.print("Opening file...");
        try {
            file = MidiFile.fromFile(inputFilePath);
            System.out.println("Done");
        } catch (Exception e) {
            printError("Cannot open file \"" + inputFilePath + "\"");
        }

        System.out.println("Reading data...");
        System.out.println("Number of track: " + file.size());

        MidiTrack track = file.getTrack(0);
        System.out.println("Number of event: " + track.size());

        int cnt = 0;
        for (int i = 0; i < track.size(); i++) {
            TrackEvent event = track.getEvent(i);

            if (event.getType() == TrackEvent.CONTROL_EVENT) {
                ControlEvent controlEvent = (ControlEvent) event;

                if (controlEvent.getCommand() == ControlEvent.NOTE_ON ||
                        controlEvent.getCommand() == ControlEvent.NOTE_OFF) {
                    cnt++;
                    System.out.println(track.getEvent(i));
                }
            }
        }

        System.out.println("Number of note: " + cnt / 2);

        switch (outputType) {
            case "case1":

                break;

            case "case2":

                break;

            default:
                printError("Invalid output type");
        }
    }

    private static void printHelp() {
        System.out.println("Usage:\n" +
                "java -jar midi-parser.jar <input file> [<options>]\n" +
                "\n" +
                "-o <output file>       Path to the output file\n" +
                "-t <type>              Output type. Can be 1, 2 or 3\n"
        );
    }

    private static void printError(String error) {
        System.out.println("Error: " + error + ".");
        System.out.println("Run with -h option to see usage details.");
        System.exit(-1);
    }
}
