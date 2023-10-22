package findr.fole.controller;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import findr.fole.dto.ContractDTO;
import findr.fole.dto.StudentDTO;
import findr.fole.service.ContractService;
import findr.fole.service.StudentService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1/pdfContract")
public class PDFController {

    private final StudentService studentService;
    private final ContractService contractService;
    private final ResourceLoader resourceLoader;
    public PDFController(StudentService studentService, ContractService contractService, ResourceLoader resourceLoader) {
        this.studentService = studentService;
        this.contractService = contractService;
        this.resourceLoader = resourceLoader;
    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

    @GetMapping("{contractId}")
    public pdfObject generateContract (@PathVariable("contractId") Integer contractId) throws DocumentException, IOException {
        ContractDTO contractDTO = contractService.find(contractId);
        StudentDTO student = contractDTO.getStudents();
//        FileOutputStream fos = new FileOutputStream(filePath);
        ByteArrayInputStream byteArrayInputStream = null;
        Document document = new Document();
        try(ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            PdfWriter writer = PdfWriter.getInstance(document, out);
            document.open();
            //Add Image
            Resource resource = resourceLoader.getResource("classpath:" + "fole.jpg");
            Image image1 = Image.getInstance(resource.getContentAsByteArray());
            //Scale to new height and new width of image
            image1.scaleAbsolute(100, 100);
            //Add to document
            document.add(image1);

            Font f = new Font();
            f.setStyle(Font.BOLD);
            f.setSize(15);

            Paragraph title = new Paragraph("Kontrate Qiraje", f);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            Paragraph preface = new Paragraph();

            Paragraph paragraph1 = new Paragraph();
            paragraph1.add("Ne Tirane, sot me date "+java.time.LocalDate.now()+" u lidh kjo kontrate qiraje midis paleve te meposhtme: ");
            document.add(paragraph1);

            addEmptyLine(preface, 2);

            Paragraph paragraph2 = new Paragraph();
            paragraph2.add("Qiradhenesi BOLV-OIL sha, e regjistruar ne Regjistrin e Shoqerive Tregtare me Nipt Ne.K32538408H, me seli ne adresen: Gize, Patos, Fier, e perfaqesuar nga Z. Agron Bulku, qe me poshte ne kete kontrate do te qujet \"Qiramarres\". \n");
            document.add(paragraph2);

            addEmptyLine(preface, 2);

            Paragraph paragraph3 = new Paragraph();
            paragraph3.add("Qiramarresi "+student.getFirstName() +" "+ student.getLastName() + " lindur ne Tirane banues/e ne Tirane, madhor/e me zotesi te plote juridike per te vepruar, me nr.Personal K12121221E qe me posthte ne kete kontrate do te quhet \"Qiramarresi\". \n\n");
            document.add(paragraph3);

            addEmptyLine(preface, 1);

            Paragraph paragraph4 = new Paragraph();
            paragraph4.add("Qiradhenesi \n BOLV-OIL sh.a \n Z Agron Bulku \n");
            document.add(paragraph4);

            addEmptyLine(preface, 1);


            Paragraph paragraph5 = new Paragraph();
            paragraph5.add("\nQiramarresi \n  Kevin Hoxhalli \n ___________ \n");
            document.add(paragraph5);


            document.close();
            writer.close();
            byteArrayInputStream = new ByteArrayInputStream(out.toByteArray());

        } catch (Exception e)
        {
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename="+student.getFirstName() +" "+ student.getLastName() +".pdf");
        return new pdfObject(new InputStreamResource(byteArrayInputStream).getContentAsByteArray());

    }

    public record pdfObject(byte[] stream){};
}