package freemarker;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.validator.internal.util.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/")
public class RController  {

    @Autowired
    private Freemarker service;
    @Autowired
    private Encryption encryption;
    @Autowired
    private FileStorageService fileStorageService;


    public RController(Freemarker service2, Encryption encryption2) {
        service = service2;
        encryption = encryption2;
    }
    
    /**
     * Given cols columns and input choose the correct ftl file
     * and Columns?.java produce a table 1-5 columns in a PDF.
     * CSV indicates EXCEL format CSV input this is used in the 
     * table headingsCSV and fileCSV and should have the same cols
     * 
     * @param cols
     * @param input
     * @return
     */
    @PostMapping(path="TabularToPDF/columns/{cols}", produces="application/json", consumes="application/json")
    public ResponseEntity<ReturnBean> postFile(@PathVariable("cols") Integer cols, @RequestBody InputBean input) {
        String result=null;
        try {
            result = service.convert(input.getTitle(),
                    input.getDatetime(),
                    input.getPrintedby(),
                    input.getHeadingsCSV(),
                    input.getFileCSV(),
                    cols);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        ReturnBean rb = new ReturnBean();
        rb.setFileB64(result);
        Base64.Decoder b64d = Base64.getDecoder();
        rb.setSha1(encryption.sha1(b64d.decode(result)));
        return new ResponseEntity<ReturnBean>(rb, HttpStatus.OK);
    }
    /**
     * Given the input which takes inputFTL and replacementStringsCSV
     * pass to freemarker then pass to the PDF generator returning
     * Base64 file and sha1.
     * CSV indicates EXCEL format CSV input this is used in the 
     * replacementStringsCSV and should have columns = 2
     * 
     * @param input
     * @return
     */
    @PostMapping(path="GeneralToPDF", produces="application/json", consumes="application/json")
    public ResponseEntity<ReturnBean> postFile(@RequestBody InputBeanGeneral input) {
        String result=null;
        try {
            result = service.convert(input.getInputFTL(),
                    input.getReplacementStringsCSV());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        ReturnBean rb = new ReturnBean();
        rb.setFileB64(result);
        Base64.Decoder b64d = Base64.getDecoder();
        rb.setSha1(encryption.sha1(b64d.decode(result)));
        return new ResponseEntity<ReturnBean>(rb, HttpStatus.OK);
    }
    /**
     * Given the input which takes bodyFTL, headerHTML, footerHTML
     * and arrayOfItems pass to freemarker then pass to the PDF
     * generator returning Base64 file and sha1.
     * CSV indicates EXCEL format CSV input this is used in the 
     * replacementStringsCSV and should have columns = 2, key, value
     * @param input
     * @return
     */
    @PostMapping(path="GeneralToPDF2", produces="application/json", consumes="application/json")
    public ResponseEntity<ReturnBean> postFile(@RequestBody InputBeanGeneral2 input) {
        String result=null;
        try {
            result = service.convert(input);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        ReturnBean rb = new ReturnBean();
        rb.setFileB64(result);
        Base64.Decoder b64d = Base64.getDecoder();
        rb.setSha1(encryption.sha1(b64d.decode(result)));
        return new ResponseEntity<ReturnBean>(rb, HttpStatus.OK);
    }
    /**
     * Saves the template to file
     * @param input
     * @return
     */
    @PostMapping(path="Init", produces="application/json", consumes="application/json")
    public ResponseEntity<Boolean> initFile(@RequestBody InputBeanInit input) {
        Boolean result=null;
        try {
            result = service.init(input.getInputFTL(),
                    input.getFilename());
        } catch (Exception e) {
            result = false;
            throw new RuntimeException(e);
        }
        return new ResponseEntity<Boolean>(result, HttpStatus.OK);
    }
    /**
     * Converts an array of Partha1InputBean to PDF via columnsPartha1Template.ftl
     * @param input
     * @return
     */
    @PostMapping(path="Partha1ToPDF", produces="application/json", consumes="application/json")
    public ResponseEntity<ReturnBean> partha1File(@RequestBody Partha1InputBean[] input) {
        String result=null;
        try {
            result = service.convert2(input);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        ReturnBean rb = new ReturnBean();
        rb.setFileB64(result);
        Base64.Decoder b64d = Base64.getDecoder();
        rb.setSha1(encryption.sha1(b64d.decode(result)));
        return new ResponseEntity<ReturnBean>(rb, HttpStatus.OK);
    }
    /**
     * Converts a HTML string to PDF
     * @param input
     * @return
     */
    @PostMapping(path="ToPDF", produces="application/json", consumes="application/json")
    public ResponseEntity<ReturnBean> toPDF(@RequestBody InputHTMLString input) {
        String result=null;
        try {
            result = service.convert2(input);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        ReturnBean rb = new ReturnBean();
        rb.setFileB64(result);
        Base64.Decoder b64d = Base64.getDecoder();
        rb.setSha1(encryption.sha1(b64d.decode(result)));
        return new ResponseEntity<ReturnBean>(rb, HttpStatus.OK);
    }
    /**
     * Compares the given sha1 with one generated from the file.
     * @param input
     * @return
     */
    @PostMapping(path="isTamperedWith", produces="application/json", consumes="application/json")
    public ResponseEntity<Boolean> isTamperedWith(@RequestBody TamperedBean input) {
        Base64.Decoder b64d = Base64.getDecoder();
        return new ResponseEntity<Boolean>(!input.getSha1().equals(
                encryption.sha1(
                        b64d.decode(input.getFileB64()))), HttpStatus.OK);
    }
    @GetMapping("/downloadFile")
    public ResponseEntity<Resource> downloadFile(HttpServletRequest request) throws Exception {
        File file= new File("test.pdf");
        // ...(file is initialised)...
        byte[] fileContent = Files.readAllBytes(file.toPath());
        Base64.Decoder b64d = Base64.getDecoder();
        String hexString = encryption.byteArrayToHexString(b64d.decode(encryption.sha1(fileContent)));
        try (FileOutputStream fos = new FileOutputStream("result"+hexString+".pdf")) {
            fos.write(fileContent);
         }

        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource("result"+hexString+".pdf");
        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

}