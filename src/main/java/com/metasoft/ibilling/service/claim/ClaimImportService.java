/**
 * 
 */
package com.metasoft.ibilling.service.claim;

import java.util.List;

import com.metasoft.ibilling.bean.UploadedFile;
import com.metasoft.ibilling.controller.vo.ImportError;
import com.metasoft.ibilling.dao.claim.ClaimDao;
import com.metasoft.ibilling.model.SecUser;
import com.metasoft.ibilling.model.TblClaimRecovery;
import com.metasoft.ibilling.service.ModelBasedService;

public interface ClaimImportService extends ModelBasedService<ClaimDao, TblClaimRecovery, Integer> {
	public List<ImportError> saveFromFile(UploadedFile uploadedFile,SecUser user)  throws Exception;
}
