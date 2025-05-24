package com.sms.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sms.entity.BulkSmsUploadActivity;
import com.sms.entity.CustomerDetails;
import com.sms.entity.TemplateMaster;

@Repository
public class Repositories {

	public interface BulkSmsUploadRepository extends JpaRepository<BulkSmsUploadActivity, Long> {

		@Query("SELECT MAX(b.id) FROM BulkSmsUploadActivity b")
		Long findMaxId();

		List<BulkSmsUploadActivity> findByStatus(String status);
	}

	public interface CustomerDetailsRepository extends JpaRepository<CustomerDetails, String> {

	}

	public interface TemplateMasterRepository extends JpaRepository<TemplateMaster, String> {
	}

}
