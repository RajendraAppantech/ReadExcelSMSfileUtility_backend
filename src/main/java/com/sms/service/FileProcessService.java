package com.sms.service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.sms.entity.BulkSmsUploadActivity;
import com.sms.repositories.Repositories.BulkSmsUploadRepository;
import com.sms.repositories.Repositories.TemplateMasterRepository;

@Service
public class FileProcessService {

	@Autowired
	private BulkSmsUploadRepository bulkSmsUploadRepository;

	private static final Logger logger = LoggerFactory.getLogger(FileProcessService.class);

	public void processTransaction(BulkSmsUploadActivity master) {
		logger.info("Starting bulk SMS processing for file: {}", master.getFilePath());

		String filePath = master.getFilePath();

		try {
			if (filePath.endsWith(".csv")) {
				processCsvFile(master);
			} else if (filePath.endsWith(".xlsx")) {
				processExcelFile(master);
			} else {
				logger.warn("Unsupported file format: {}", filePath);
				master.setStatus("FAILED");
				master.setUpdatedDate(new Date());
				bulkSmsUploadRepository.save(master);
			}
		} catch (Exception e) {
			logger.error("Error during file processing", e);
			master.setStatus("FAILED");
			master.setUpdatedDate(new Date());
			bulkSmsUploadRepository.save(master);
		}
	}

	private void processCsvFile(BulkSmsUploadActivity master) throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader(master.getFilePath()))) {
			String line;
			boolean isFirstLine = true;

			while ((line = br.readLine()) != null) {
				if (isFirstLine) {
					isFirstLine = false;
					continue;
				}
				

				logger.debug("Reading line: {}", line);
				String[] values = line.split("\\|", -1);

				if (values.length < 3) {
					logger.warn("Skipping invalid row (expected at least 3 fields): {}", line);
					continue;
				}

				String mobileNo = values[1].trim();
				String body = values[2].trim();

				sendSmsWithMasterData(master, mobileNo, body);
			}

			logger.info("CSV file processed successfully.");
			master.setStatus("COMPLETED");
			master.setUpdatedDate(new Date());
			bulkSmsUploadRepository.save(master);
		}
	}

	private void processExcelFile(BulkSmsUploadActivity master) throws IOException {
		try (FileInputStream fis = new FileInputStream(master.getFilePath());
				Workbook workbook = new XSSFWorkbook(fis)) {

			Sheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			boolean isFirstRow = true;

			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				if (isFirstRow) {
					isFirstRow = false;
					continue;
				}

				String mobileNo = getCellValue(row.getCell(1));
				String body = getCellValue(row.getCell(2));

				if (mobileNo.isEmpty() || body.isEmpty()) {
					logger.warn("Skipping invalid row in Excel: rowNum={}", row.getRowNum());
					continue;
				}

				sendSmsWithMasterData(master, mobileNo, body);
			}

			logger.info("Excel file processed successfully.");
			master.setStatus("COMPLETED");
			master.setUpdatedDate(new Date());
			bulkSmsUploadRepository.save(master);
		}
	}

	private void sendSmsWithMasterData(BulkSmsUploadActivity master, String mobileNo, String body) {
		String sendorId = master.getSendorId();
		String entityId = master.getEntityId();
		String templateId = master.getTemplateId();
		String key = master.getCompanyId();
		String fileName = master.getFileName();
		String fileId = extractNumericPrefix(fileName);

		logger.info(
				"Sending SMS - senderId: {}, entityId: {}, templateId: {}, key: {}, mobileNo: {}, body: {}, fileId: {}",
				sendorId, entityId, templateId, key, mobileNo, body, fileId);

		sendSms(sendorId, entityId, templateId, key, mobileNo, body, fileId);
	}

	private String extractNumericPrefix(String fileName) {
		if (fileName == null || fileName.isEmpty())
			return "";

		int underscoreIndex = fileName.indexOf('_');
		if (underscoreIndex == -1)
			return "";

		return fileName.substring(0, underscoreIndex);
	}

/*	private void sendSms(String sendorId, String entityId, String templateId, String key, String mobileNo, String body,	String fileId) {
		try {
			logger.info("Sending SMS to {}", mobileNo);

			UriComponentsBuilder builder = UriComponentsBuilder

					.fromHttpUrl("https://sms-apidoc.appantech.com/api/sms")
					.queryParam("key", key)
					.queryParam("to", mobileNo)
					.queryParam("from", sendorId)
					.queryParam("templateid", templateId)
					.queryParam("entityid", entityId)
					.queryParam("body", body)
					.queryParam("fileId", fileId);

			String url = builder.toUriString().replace("%20", " ");

			RestTemplate restTemplate = new RestTemplate();
			String response = restTemplate.getForObject(url, String.class);

			logger.info("SMS sent to {} | Response: {}", mobileNo, response);

		} catch (Exception e) {
			logger.error("Failed to send SMS to {}", mobileNo, e);
		}
	}*/
	
	private void sendSms(String senderId, String entityId, String templateId, String key, String mobileNo, String body, String fileId) {
	    try {
	        logger.info("Sending SMS to {}", mobileNo);

	        UriComponentsBuilder builder = UriComponentsBuilder
	                .fromHttpUrl("https://sms-apidoc.appantech.com/api/sms")
	                .queryParam("key", key)
	                .queryParam("to", mobileNo)
	                .queryParam("from", senderId)
	                .queryParam("templateid", templateId)
	                .queryParam("entityid", entityId)
	                .queryParam("body", body)
	                .queryParam("fileId", fileId);

	        String url = builder.toUriString().replace("%20", " "); // Optional: handle body spacing

	        RestTemplate restTemplate = new RestTemplate();
	        String response = restTemplate.getForObject(url, String.class);

	        logger.info("SMS sent to {} | Response: {}", mobileNo, response);

	    } catch (Exception e) {
	        logger.error("Failed to send SMS to {}", mobileNo, e);
	    }
	}


	private String getCellValue(Cell cell) {
		if (cell == null)
			return "";
		return switch (cell.getCellType()) {
		case STRING -> cell.getStringCellValue().trim();
		case NUMERIC -> String.valueOf((long) cell.getNumericCellValue());
		case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
		default -> "";
		};
	}
}
