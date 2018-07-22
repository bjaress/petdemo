package com.bjaress.petdemo;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

/**
 * Demo app for searching through CSV
 *
 */
public class App {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        List<String> argList = Arrays.asList(args);

        if (argList.isEmpty()) {
            System.out.println("FAIL: You need to include an input filename.");
            System.exit(-1);
        }

        Map<String, String> query = argList.stream().skip(1)
            .map(arg -> Arrays.asList(arg.split("=")))
            .filter(parts -> parts.size() > 1)
            .collect(Collectors.toMap(
                        parts -> parts.get(0),
                        parts -> parts.get(1)));

        CSVReader reader = new CSVReader(new FileReader(argList.get(0)));

        CSVWriter writer = new CSVWriter(new OutputStreamWriter(System.out));

        String[] header = reader.readNext();
        writer.writeNext(header, true);

        while(true) {
            String[] line = reader.readNext();
            if (line == null) { break; }

            writer.writeNext(line, true);
        }
        writer.close();

    }
}
