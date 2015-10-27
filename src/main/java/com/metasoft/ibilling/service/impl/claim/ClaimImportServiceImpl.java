/**
 * 
 */
package com.metasoft.ibilling.service.impl.claim;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.metasoft.ibilling.bean.UploadedFile;
import com.metasoft.ibilling.controller.vo.ImportError;
import com.metasoft.ibilling.dao.UserInsuranceDao;
import com.metasoft.ibilling.dao.claim.ClaimDao;
import com.metasoft.ibilling.dao.security.UserDao;
import com.metasoft.ibilling.dao.standard.InsuranceDao;
import com.metasoft.ibilling.model.JobStatus;
import com.metasoft.ibilling.model.SecUser;
import com.metasoft.ibilling.model.TblClaimRecovery;
import com.metasoft.ibilling.model.TblUserInsurance;
import com.metasoft.ibilling.service.claim.ClaimImportService;
import com.metasoft.ibilling.service.impl.ModelBasedServiceImpl;
import com.metasoft.ibilling.util.DateToolsUtil;

@Service("claimImportService")
public class ClaimImportServiceImpl extends ModelBasedServiceImpl<ClaimDao, TblClaimRecovery, Integer> implements ClaimImportService {

	private ClaimDao claimDao;

	@Autowired
	private InsuranceDao insuranceDao;

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserInsuranceDao userInsuranceDao;

	/**
	 * 
	 * @param entityClass
	 */
	@Autowired
	public ClaimImportServiceImpl(ClaimDao dao) {
		super(dao);
		this.claimDao = dao;
	}

	@Override
	@Transactional
	public List<ImportError> saveFromFile(UploadedFile uploadedFile,SecUser user) throws Exception {
		List<ImportError> errorClaimNumbers = new ArrayList<ImportError>();
		XSSFWorkbook workbook = null;
		try {
			InputStream input = new ByteArrayInputStream(uploadedFile.bytes);
			Date today = new Date();

			// Using XSSF for xlsx format, for xls use HSSF
			workbook = new XSSFWorkbook(input);

			Sheet sheet = workbook.getSheetAt(0);

			Iterator<Row> rowIterator = sheet.iterator();
			Row row = rowIterator.next();
			// iterating over each row
			int i = 1;
			int rowAt = 1;
			while (rowIterator.hasNext()) {
				i = 1;
				TblClaimRecovery claim = new TblClaimRecovery();
				
				row = rowIterator.next();
				try{
	//				ลำดับ	เลขเคลม	เลขกรมธรรม์	รถประกัน	คู่กรณี	วันที่เกิดเหตุ	วันหมดอายุความ	คู่กรณี	 จำนวนเงิน			
					claim.setClaimNumber(StringUtils.trimToNull(row.getCell(i++).getStringCellValue()));
					claim.setPolicyNo(StringUtils.trimToNull(row.getCell(i++).getStringCellValue()));
					claim.setLicenseNumber(StringUtils.trimToNull(row.getCell(i++).getStringCellValue()));
					claim.setPartyLicenseNumber(StringUtils.trimToNull(row.getCell(i++).getStringCellValue()));
					claim.setAccidentDate(row.getCell(i++).getDateCellValue());
					claim.setMaturityDate(row.getCell(i++).getDateCellValue());
				} catch (Exception e) {
					ImportError importError = new ImportError();
					importError.setReason("ข้อมูลแถวที่ " + rowAt + " คอลัมน์ที่ " + i + " ไม่ถูกต้อง");
					importError.setClaimNumber(StringUtils.isEmpty(claim.getClaimNumber())?"ไม่พบหมายเลขเคลม":claim.getClaimNumber());
					errorClaimNumbers.add(importError);
					continue;
				}
				
				String partyInsuranceName = StringUtils.trimToNull(row.getCell(i++).getStringCellValue());
				if(partyInsuranceName != null){
					claim.setPartyInsurance(insuranceDao.findByName(partyInsuranceName));
				}

				claim.setClaimInsuranceAmount((float) row.getCell(i++).getNumericCellValue());
			
				if(claim.getClaimNumber() != null && claim.getPartyInsurance() != null) {
					if(!claimDao.checkDupClaimNumber(claim.getClaimNumber())){
						claim.setCreateDate(today);
						claim.setCreateBy(user);
						
						claim.setJobDate(today);
						claim.setJobStatus(JobStatus.RECEIVED);
						
						List<TblUserInsurance> tblUserInsurances = userInsuranceDao.searchByInsuranceId(claim.getPartyInsurance().getId());		
						if(tblUserInsurances != null && !tblUserInsurances.isEmpty()){
							claim.setAgent(userDao.findById(tblUserInsurances.get(0).getId().getUserId()));
						}

						claimDao.save(claim);
						
						claim.setJobNo(DateToolsUtil.convertToString(today, DateToolsUtil.DATE_PATTERN_VIEW_YYYYMMDD, DateToolsUtil.LOCALE_TH)
								+ claim.getId());
						
						claimDao.save(claim);
					}else{
						ImportError importError = new ImportError();
						importError.setReason("หมายเลขเคลมซ้ำ");
						importError.setClaimNumber(claim.getClaimNumber());
						errorClaimNumbers.add(importError);
					}
				}else if(claim.getClaimNumber() != null && claim.getPartyInsurance() == null) {
					ImportError importError = new ImportError();
					importError.setReason("ไม่พบบริษัทประกัน");
					importError.setClaimNumber(claim.getClaimNumber());
					errorClaimNumbers.add(importError);
				}
				
				rowAt++;
			}

			input.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			if(workbook != null){
				try {
					workbook.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return errorClaimNumbers;
	}

}
