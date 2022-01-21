package com.ihc.ehr.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ihc.ehr.db.DBOperations;
import com.ihc.ehr.model.HL7FileGenerationResult;
import com.ihc.ehr.model.SearchCriteria;
import com.ihc.ehr.model.ServiceResponse;
import com.ihc.ehr.util.PHS_SyndromicSurveillanceOperationsMU3;
import com.ihc.ehr.util.EnumUtil.ServiceResponseStatus;

@Repository
public class PHSDAOImpl implements PHSDAO {

	@Autowired
	DBOperations db;
	
	@Autowired
	PatientDAOImpl patientDAOImpl;
	
	//@Autowired
	//private PHS_SyndromicSurveillanceOperationsMU3 phsSyndromicSurveillanceOperationsMU3;
	
	@Override
	public ServiceResponse<HL7FileGenerationResult> GenerateSyndromicSurveillanceMessage(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		PHS_SyndromicSurveillanceOperationsMU3 phsSyndromicSurveillanceOperationsMU3=new PHS_SyndromicSurveillanceOperationsMU3(db, patientDAOImpl);
		
		ServiceResponse<HL7FileGenerationResult> resp = new ServiceResponse<HL7FileGenerationResult>();
		
		List<HL7FileGenerationResult> lstResult= phsSyndromicSurveillanceOperationsMU3.GenerateSyndromicSurveillanceMessage(searchCriteria);
		
		if (lstResult != null && lstResult.size() > 0) {

			if (lstResult.get(0).getType().equals("SUCCESSFULL")) {

				resp.setStatus(ServiceResponseStatus.SUCCESS);
				resp.setResponse(lstResult.get(0).getMessage());
				resp.setResult(lstResult.get(0).getId().toString());

			} else {
				resp.setStatus(ServiceResponseStatus.ERROR);
				resp.setResponse("An Error Occured while generating File. Details are in response_list");
				resp.setResponse_list(lstResult);
				resp.setResult(lstResult.get(0).getId().toString());
			}

		}
		
		return resp;
	}	

}
