package com.trilokee.patientApi.restController;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//import org.apache.commons.math3.stat.descriptive.summary.Product;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.trilokee.patientApi.model.Product;


@RestController

@CrossOrigin
public class ImportExcelController {

	@RequestMapping(value = "/exceltojson", method = RequestMethod.POST)
    public ResponseEntity<List<Product>> importExcelFile(@RequestParam("file") MultipartFile files) throws IOException {
        HttpStatus status = HttpStatus.OK;
        List<Product> productList = new ArrayList<>();

        try {
			try (XSSFWorkbook workbook = new XSSFWorkbook(files.getInputStream())) {
				XSSFSheet worksheet = workbook.getSheetAt(0);

				for (int index = 0; index < worksheet.getPhysicalNumberOfRows(); index++) {
				    if (index > 0) {
				        Product product = new Product();

				        XSSFRow row = worksheet.getRow(index);
				        Integer id = (int) row.getCell(0).getNumericCellValue();

				        product.setId(id.toString());
				        product.setProductName(row.getCell(1).getStringCellValue());
				        product.setPrice(row.getCell(2).getNumericCellValue());
				        product.setCategory(row.getCell(3).getStringCellValue());

				        productList.add(product);
				    }
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return new ResponseEntity<>(productList, status);
    }
}

