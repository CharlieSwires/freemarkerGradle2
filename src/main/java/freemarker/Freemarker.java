package freemarker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;

@Service
public class Freemarker {

    static boolean doneOnce = false;
    @SuppressWarnings("unchecked")
    public synchronized String convert(String title,
            String datetime,
            String printedby,
            String csvHeadings,
            String csvList,
            int columns) throws Exception {
        // 1. Configure FreeMarker
        //
        // You should do this ONLY ONCE, when your application starts,
        // then reuse the same Configuration object elsewhere.

        Configuration cfg = new Configuration();
        // Where do we load the templates from:
        cfg.setDirectoryForTemplateLoading(new File("/usr/local/tomcat/webapps/freemarker/WEB-INF"));

        // Some other recommended settings:
        cfg.setIncompatibleImprovements(new Version(2, 3, 20));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setLocale(Locale.US);
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        // 2. Proccess template(s)
        //
        // You will do this for several times in typical applications.

        // 2.1. Prepare the template input:

        Map<String, Object> input = new HashMap<String, Object>();

        input.put("title", title);
        input.put("printedby", printedby);
        input.put("datetime", datetime);

        List systems = new ArrayList();

        Reader in = new StringReader(csvHeadings);
        Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);
        for (CSVRecord record : records) {
            Object c = null;
            switch(columns) {

            case 1:
                c = new Columns1();
                ((Columns1) c).setCol0(record.get(0));
                break;
            case 2:
                c = new Columns2();
                ((Columns2) c).setCol0(record.get(0));
                ((Columns2) c).setCol1(record.get(1));
                break;
            case 3:
                c = new Columns3();
                ((Columns3) c).setCol0(record.get(0));
                ((Columns3) c).setCol1(record.get(1));
                ((Columns3) c).setCol2(record.get(2));
                break;
            case 4:
                c = new Columns4();
                ((Columns4) c).setCol0(record.get(0));
                ((Columns4) c).setCol1(record.get(1));
                ((Columns4) c).setCol2(record.get(2));
                ((Columns4) c).setCol3(record.get(3));
                break;
            case 5:
                c = new Columns5();
                ((Columns5) c).setCol0(record.get(0));
                ((Columns5) c).setCol1(record.get(1));
                ((Columns5) c).setCol2(record.get(2));
                ((Columns5) c).setCol3(record.get(3));
                ((Columns5) c).setCol4(record.get(4));
                break;
            default:
                throw new IllegalArgumentException();
            }
            systems.add(c);
        }

        input.put("headings", systems);
        systems = new ArrayList();

        in = new StringReader(csvList);
        records = CSVFormat.EXCEL.parse(in);
        for (CSVRecord record : records) {
            Object c = null;
            switch(columns) {

            case 1:
                c = new Columns1();
                ((Columns1) c).setCol0(record.get(0));
                break;
            case 2:
                c = new Columns2();
                ((Columns2) c).setCol0(record.get(0));
                ((Columns2) c).setCol1(record.get(1));
                break;
            case 3:
                c = new Columns3();
                ((Columns3) c).setCol0(record.get(0));
                ((Columns3) c).setCol1(record.get(1));
                ((Columns3) c).setCol2(record.get(2));
                break;
            case 4:
                c = new Columns4();
                ((Columns4) c).setCol0(record.get(0));
                ((Columns4) c).setCol1(record.get(1));
                ((Columns4) c).setCol2(record.get(2));
                ((Columns4) c).setCol3(record.get(3));
                break;
            case 5:
                c = new Columns5();
                ((Columns5) c).setCol0(record.get(0));
                ((Columns5) c).setCol1(record.get(1));
                ((Columns5) c).setCol2(record.get(2));
                ((Columns5) c).setCol3(record.get(3));
                ((Columns5) c).setCol4(record.get(4));
                break;
            default:
                throw new IllegalArgumentException();
            }
            systems.add(c);
        }

        input.put("systems", systems);

        // 2.2. Get the template

        Template template = null;
        switch(columns) {

        case 1:
            template = cfg.getTemplate("columns1.ftl");
            break;
        case 2:
            template = cfg.getTemplate("columns2.ftl");
            break;
        case 3:
            template = cfg.getTemplate("columns3.ftl");
            break;
        case 4:
            template = cfg.getTemplate("columns4.ftl");
            break;
        case 5:
            template = cfg.getTemplate("columns5.ftl");
            break;
        default:
            throw new IllegalArgumentException();
        }

        // 2.3. Generate the output

        // Write output to the console
        Writer consoleWriter = new OutputStreamWriter(System.out);
        template.process(input, consoleWriter);
        consoleWriter.flush();

        // For the sake of example, also write output into a file:
        Writer fileWriter = new FileWriter(new File("/usr/local/tomcat/output.html"));
        try {
            template.process(input, fileWriter);
        } finally {
            fileWriter.close();
        }

        return publishPDF();
    }

    public synchronized Boolean init(String inputHTML, String filename) throws Exception {

        FileWriter fw = new FileWriter(new File("/usr/local/tomcat/"+filename));
        fw.write(inputHTML);
        fw.close();
        return true;
    } 
    public synchronized String convert(String inputHTML, String replacementStrings) throws Exception {

        FileWriter fw = new FileWriter(new File("/usr/local/tomcat/input.ftl"));
        fw.write(inputHTML);
        fw.close();

        // 1. Configure FreeMarker
        //
        // You should do this ONLY ONCE, when your application starts,
        // then reuse the same Configuration object elsewhere.

        Configuration cfg = new Configuration();
        // Where do we load the templates from:
        cfg.setDirectoryForTemplateLoading(new File("/usr/local/tomcat"));
        // Some other recommended settings:
        cfg.setIncompatibleImprovements(new Version(2, 3, 20));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setLocale(Locale.US);
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        Map<String, Object> input = new HashMap<String, Object>();

        Reader in = new StringReader(replacementStrings);
        Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);
        for (CSVRecord record : records) {
            input.put(record.get(0), record.get(1)); 
        }

        Template template = cfg.getTemplate("input.ftl");

        // Write output to the console
        Writer consoleWriter = new OutputStreamWriter(System.out);
        template.process(input, consoleWriter);
        consoleWriter.flush();
        
        // For the sake of example, also write output into a file:
        Writer fileWriter = new FileWriter(new File("/usr/local/tomcat/output.html"));
        try {
            template.process(input, fileWriter);
        } finally {
            fileWriter.close();
        }

        return publishPDF();
    }

    public synchronized String convert2(Partha1InputBean[] input2) throws Exception {
        // 1. Configure FreeMarker
        //
        // You should do this ONLY ONCE, when your application starts,
        // then reuse the same Configuration object elsewhere.

        Configuration cfg = new Configuration();
        // Where do we load the templates from:
        cfg.setDirectoryForTemplateLoading(new File("/usr/local/tomcat"));

        // Some other recommended settings:
        cfg.setIncompatibleImprovements(new Version(2, 3, 20));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setLocale(Locale.US);
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        // 2. Proccess template(s)
        //
        // You will do this for several times in typical applications.

        // 2.1. Prepare the template input:

        Map<String, Object> input = new HashMap<String, Object>();
        // Write output to the console
        Writer consoleWriter = new OutputStreamWriter(System.out);

        List<ColumnsPartha1InputBean> systems = new ArrayList<ColumnsPartha1InputBean>();
        consoleWriter.write(Arrays.toString(input2));
        for(int i = 0; i < input2.length; i++) {
            systems.add(input2[i].convert());
        }
        input.put("systems", systems);
        consoleWriter.flush();


        // 2.2. Get the template

        Template template = null;
        template = cfg.getTemplate("columnsPartha1Template.ftl");

        // 2.3. Generate the output

        // Write output to the console
        consoleWriter = new OutputStreamWriter(System.out);
        template.process(input, consoleWriter);
        consoleWriter.flush();

        // For the sake of example, also write output into a file:
        Writer fileWriter = new FileWriter(new File("/usr/local/tomcat/output.html"));
        try {
            template.process(input, fileWriter);
        } finally {
            fileWriter.close();
        }

        return publishPDF();
    }
    public synchronized String convert2(InputHTMLString input) throws Exception {

        FileWriter fw = new FileWriter(new File("/usr/local/tomcat/output.html"));
        fw.write(input.getInputHTML());
        fw.close();

        Writer consoleWriter = new OutputStreamWriter(System.out);
        consoleWriter.write(input.getInputHTML());
        consoleWriter.flush();
       return publishPDF();
    }


    private synchronized String publishPDF() throws Exception {

        boolean isWindows = System.getProperty("os.name")
                .toLowerCase().startsWith("windows");
        ProcessBuilder builder = new ProcessBuilder();
        if (isWindows) {
            throw new RuntimeException("Not Supported");
            //            builder.command("cmd.exe", "/c", "del test.pdf");
            //            builder.command("cmd.exe", "/c", "node index.js");
        } else {
            builder.command("sh", "-c", "chmod a+xr index.js");
            builder.directory(new File("/usr/local/tomcat"));
            Process process = builder.start();

            if(!doneOnce) {
                builder.command("sh", "-c", "adduser user");
                builder.directory(new File("/usr/local/tomcat"));
                process = builder.start();
                OutputStream os = process.getOutputStream();
                os.write("\n1\n1\n\n".getBytes());
                doneOnce = true;
            }
        }

        builder = new ProcessBuilder();
        if (isWindows) {
            throw new RuntimeException("Not Supported");
            //            builder.command("cmd.exe", "/c", "del test.pdf");
            //            builder.command("cmd.exe", "/c", "node index.js");
        } else {
            builder.command("sh", "-c", "rm *.pdf");
            builder.directory(new File("/usr/local/tomcat"));
            Process process = builder.start();
            builder.command("sh", "-c", "su user");
            builder.directory(new File("/usr/local/tomcat"));
            process = builder.start();
            builder.command("sh", "-c", "node index.js");
            builder.directory(new File("/usr/local/tomcat"));
            process = builder.start();
        }
        int i = 0;
        byte[] fileContent = null;
        while (i++ < 30) {
            try {
                File file = new File("/usr/local/tomcat/test.pdf");
                fileContent = Files.readAllBytes(file.toPath());
            } catch (Exception e) {
                Thread.sleep(10000);
                fileContent = null;
            }
        }
        if (fileContent == null) throw new RuntimeException("Timed out");
        Base64.Encoder b64e = Base64.getEncoder();
        //        Base64.Decoder b64d = Base64.getDecoder();
        String result = b64e.encodeToString(fileContent);

        builder = new ProcessBuilder();
        builder.command("sh", "-c", "exit");
        builder.directory(new File("/usr/local/tomcat"));
        Process process = builder.start();
        //        try (FileOutputStream fos = new FileOutputStream("/usr/local/tomcat/result.pdf")) {
        //            fos.write(b64d.decode(result));
        //        }        
        return result;    
    }

    public synchronized String convert(InputBeanGeneral2 input) throws Exception {
  
        Writer consoleWriter = new OutputStreamWriter(System.out);
        consoleWriter.write("=====================>"+input.toString());
        consoleWriter.flush();
        // 1. Configure FreeMarker
        //
        // You should do this ONLY ONCE, when your application starts,
        // then reuse the same Configuration object elsewhere.

        Configuration cfg = new Configuration();
        // Where do we load the templates from:
        cfg.setDirectoryForTemplateLoading(new File("/usr/local/tomcat"));

        // Some other recommended settings:
        cfg.setIncompatibleImprovements(new Version(2, 3, 20));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setLocale(Locale.US);
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        FileWriter fw = new FileWriter(new File("/usr/local/tomcat/input.ftl"));
        fw.write(input.getBodyFTL());
        fw.close();

        // 2. Proccess template(s)
        //
        // You will do this for several times in typical applications.

        // 2.1. Prepare the template input:
        for(int i = 0; i< input.getArrayOfItems().length; i++)
        {
            Map<String, Object> input2 = new HashMap<String, Object>();
            List<Object> systems = new ArrayList<Object>();
             
            for(int j = 0; j < input.getArrayOfItems()[i].getFindingsText().length; j++ ) {
                InputBeanGeneral2.ArrayOfItems.FindingsText item = new InputBeanGeneral2.ArrayOfItems.FindingsText();
                item.setType(input.getArrayOfItems()[i].getFindingsText()[j].getType());
                item.setNote(input.getArrayOfItems()[i].getFindingsText()[j].getNote());
                systems.add((Object)item);
                
            }
            
            Reader in = new StringReader(input.getArrayOfItems()[i].getInputCSV());
            Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);
            for (CSVRecord record : records) {
                input2.put(record.get(0), record.get(1)); 
            }

            Template template = cfg.getTemplate("input.ftl");
            input2.put("systems", systems);

            // Write output to the console
            consoleWriter = new OutputStreamWriter(System.out);
            template.process(input2, consoleWriter);
            consoleWriter.flush();
            // For the sake of example, also write output into a file:
            Writer fileWriter = new FileWriter(new File("/usr/local/tomcat/output"+i+".html"));
            try {
                template.process(input2, fileWriter);
            } finally {
                fileWriter.close();
            }

        }
        StringBuilder sb = new StringBuilder();
        sb.append(input.getHeaderHTML());
        for(int i = 0; i< input.getArrayOfItems().length; i++) {
            String line;
            BufferedReader fileReader = new BufferedReader(new FileReader(new File("/usr/local/tomcat/output"+i+".html")));
            while((line = fileReader.readLine())!=null){
                sb.append(line+"\n");
            }
            fileReader.close();
        }
        sb.append(input.getFooterHTML());

        FileWriter fw1 = new FileWriter(new File("/usr/local/tomcat/output.html"));
        fw1.write(sb.toString());
        fw1.close();

        return publishPDF();
    }
}