package com.sms.scheduler;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.sms.entity.BulkSmsUploadActivity;
import com.sms.repositories.Repositories.BulkSmsUploadRepository;
import com.sms.service.FileProcessService;

import jakarta.transaction.Transactional;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class FetchFileUtility {

	private static final Logger logger = LoggerFactory.getLogger(FetchFileUtility.class);

	@Autowired
	private BulkSmsUploadRepository bulkSmsUploadRepository;

	@Autowired
	private FileProcessService fileProcessService;

	@Transactional
	@Scheduled(initialDelayString = "${scheduler.bulk.initialDelay}", fixedRateString = "${scheduler.bulk.fixedRate}")
	protected void processMerchantOnboarding() {
		logger.info("\n\n========== BULK SMS DISPATCH JOB STARTED TEST ==========");

		try {
			List<BulkSmsUploadActivity> pendingFile = bulkSmsUploadRepository.findByStatus("PENDING");
			if (pendingFile.isEmpty()) {
				logger.info("No pending bulk SMS files found for processing.");
				return;
			}

			logger.info("Found {} file(s) with status 'PENDING'. Processing started...", pendingFile.size());
			pendingFile.forEach(fileProcessService::processTransaction);
			logger.info("Processing completed for all pending files.");

		} catch (Exception e) {
			logger.error("Error occurred while processing bulk SMS files: ", e);
		}

		logger.info("========== BULK SMS DISPATCH JOB ENDED ==========\n");
	}
}
