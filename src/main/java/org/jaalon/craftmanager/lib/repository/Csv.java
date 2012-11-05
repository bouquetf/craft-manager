package org.jaalon.craftmanager.lib.repository;

import org.jaalon.craftmanager.lib.ComponentBuilder;
import org.jaalon.craftmanager.lib.ProductionBuilder;

import java.io.*;
import java.util.logging.Logger;

public class Csv {
    Logger logger;
    Repository repository;

    public Csv () {
        this.logger = Logger.getLogger(this.getClass().toString());
        repository = Repository.getInstance();
    }

    public void importRepositoryFromCsv(String filePath) throws CsvFileException {
        CsvFileHandler csvFileHandler = new CsvFileHandler(filePath);
        parseCsvContent(csvFileHandler.getCsvContents());
        csvFileHandler.closeCsvFile();
    }

    private void parseCsvContent(BufferedReader csvContent) throws CsvFileException {
        readCsvLine(csvContent);
        String line;
        while ((line = readCsvLine(csvContent)) != null) {
            parseLine(line);
        }
    }

    private String readCsvLine(BufferedReader csvContent) throws CsvFileException {
        try {
            return csvContent.readLine();
        } catch (IOException e) {
            throw new CsvFileException(e);
        }
    }

    private void parseLine(String line) {
        String[] contents = line.split(";");
        if (contents[0].equals("Base")) {
            addABaseToRepository(contents);
        } else if (contents[0].equals("Production")) {
            addAProduction(contents);
        }
    }

    private void addABaseToRepository(String[] contents) {
        ComponentBuilder componentBuilder = new ComponentBuilder().setName(contents[1]);
        if (!contents[2].equals("")) componentBuilder.setVendorPrice(Integer.valueOf(contents[2]));
        if (!contents[3].equals("")) componentBuilder.setBlackLionPrice(Integer.valueOf(contents[3]));
        componentBuilder.done();
    }

    private void addAProduction(String[] contents) {
        ProductionBuilder productionBuilder = new ProductionBuilder().setName(contents[1]);
        if (!contents[2].equals("")) productionBuilder.setVendorPrice(Integer.valueOf(contents[2]));
        if (!contents[3].equals("")) productionBuilder.setBlackLionPrice(Integer.valueOf(contents[3]));
        for (int i = 4; i <=10; i+=2) {
            if (contents.length > i && !contents[i].equals(""))
                productionBuilder.addToRecipe(Integer.valueOf(contents[i]), contents[i+1]);
        }
        productionBuilder.done();
    }

    public class CsvFileException extends Exception {
        public CsvFileException(Exception e) {
            super(e);
        }
    }

    private class CsvFileHandler {
        private File csvFile;
        private FileReader fileReader;

        public CsvFileHandler(String filePath) {
            this.csvFile = new File(filePath);
        }

        public BufferedReader getCsvContents() throws CsvFileException {
            try {
                fileReader = new FileReader(csvFile);
            } catch (FileNotFoundException e) {
                throw new CsvFileException(e);
            }
            return new BufferedReader(fileReader);
        }

        public void closeCsvFile() throws CsvFileException {
            try {
                fileReader.close();
            } catch (IOException e) {
                throw new CsvFileException(e);
            }
        }
    }
}
