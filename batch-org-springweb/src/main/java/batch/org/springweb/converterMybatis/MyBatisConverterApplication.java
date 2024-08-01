package batch.org.springweb.converterMybatis;



import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyBatisConverterApplication {
    public void convertFiles(String inputDir, String outputDir, String namespace) throws IOException {
        File dir = new File(inputDir);
        if (!dir.exists() || !dir.isDirectory()) {
            throw new IllegalArgumentException("Invalid input directory: " + inputDir);
        }

        File[] files = dir.listFiles((d, name) -> name.endsWith(".pstmt"));
        if (files == null) {
            throw new IOException("Failed to list files in the directory: " + inputDir);
        }

        for (File file : files) {
            String sqlQuery = new String(Files.readAllBytes(file.toPath()));
            String id = file.getName().replace(".sql", "");
            String xmlContent = convertToMyBatisXml(sqlQuery, namespace, id);
            String outputFilePath = Paths.get(outputDir, id + ".xml").toString();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
                writer.write(xmlContent);
            }
        }
    }

    private String convertToMyBatisXml(String sqlQuery, String namespace, String id) {
        StringBuilder xmlBuilder = new StringBuilder();
        xmlBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        xmlBuilder.append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\n");
        xmlBuilder.append("<mapper namespace=\"").append(namespace).append("\">\n");
        xmlBuilder.append("    <select id=\"").append(id).append("\" resultType=\"map\">\n");

        // Replace pstmt placeholders (?) in the SQL query with MyBatis placeholders
        Pattern pattern = Pattern.compile("\\?");
        Matcher matcher = pattern.matcher(sqlQuery);
        int paramIndex = 1;
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "#{" + "param" + paramIndex + "}");
            paramIndex++;
        }
        matcher.appendTail(sb);

        xmlBuilder.append("        ").append(sb.toString()).append("\n");
        xmlBuilder.append("    </select>\n");
        xmlBuilder.append("</mapper>");
        return xmlBuilder.toString();
    }
}
