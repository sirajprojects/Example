package batch.org.springweb.converterMybatis;


import java.io.IOException;

public class MyBatisConverterService {

    public static void main(String[] args) {
        // Hardcoded paths and namespace
        String inputDir = "C:/Users/DELL/Documents/workspace-spring-tool-suite-4-4.23.1.RELEASE/relationproject/bin/java/com/siraj/app/main/data/access/Infox/resours";
        String outputDir = "C:/Users/DELL/Documents/workspace-spring-tool-suite-4-4.23.1.RELEASE/relationproject/bin/java/com/siraj/app/main/data/access/Infox/resours";
        String namespace = "com.example.mapper";

        MyBatisConverterApplication converterService = new MyBatisConverterApplication();
        try {
            converterService.convertFiles(inputDir, outputDir, namespace);
            System.out.println("Conversion completed successfully.");
        } catch (IOException e) {
            System.err.println("Error during conversion: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
