package com.ihc.ehr.util;

import java.util.ArrayList;
import java.util.List;

import com.ihc.ehr.model.ORM_277_ResponseSave;
import com.ihc.ehr.util.DateTimeUtil.DateFormatEnum;

public class EdiParser {

	public List<ORM_277_ResponseSave> parse277Response(String responseString) {

		String[] ResponseSegments = responseString.split("~");

		String hlCode = "";

		List<ORM_277_ResponseSave> lstClaimResponse = new ArrayList<ORM_277_ResponseSave>();

		ORM_277_ResponseSave orm = new ORM_277_ResponseSave();

		for (String segment : ResponseSegments) {

			String[] subSegments = segment.split("\\*");

			String segmentTag = subSegments[0];

			switch (segmentTag) {
			/*
			 * case "ST":
			 * 
			 * if (subSegments.length > 2) { orm.setTransaction_set_no(subSegments[2]); }
			 * 
			 * break;
			 */

			case "BHT":

				if (subSegments.length > 3) {
					orm.setResponse_ref_id(subSegments[3]);
				}
				if (subSegments.length > 5) {
					// CCYYMMDD+HHMM
					String responseDateTimeCreated = DateTimeUtil.formatDateString((subSegments[4] + subSegments[5]),
							DateFormatEnum.yyyyMMddHHmm, DateFormatEnum.yyyy_MM_dd_HH_mm);

					orm.setResponse_date_created(responseDateTimeCreated);
				}
				break;

			case "HL":

				if (hlCode.equalsIgnoreCase("PT")) {

					if (GeneralOperations.isNotNullorEmpty(orm.getClaim_id())) {
						lstClaimResponse.add(orm);

						// copy segment info to new ORM
						orm = new ORM_277_ResponseSave(orm);
					}
				}

				if (subSegments.length > 3) {
					hlCode = subSegments[3];
				}

				break;

			case "NM1":

				// Information Source
				if (hlCode.equalsIgnoreCase("20")) {

					if (subSegments.length > 1) {
						orm.setInfo_source_entity_code(subSegments[1]);
					}
					if (subSegments.length > 3) {
						orm.setInfo_source_name(subSegments[3]);
					}
					if (subSegments.length > 8) {
						orm.setInfo_source_identifer_code_qualifier(subSegments[8]);
					}
					if (subSegments.length > 9) {
						orm.setInfo_source_identifer_code(subSegments[9]);
					}
				}

				/*
				// Information RECEIVER|Submitter
				if (hlCode.equalsIgnoreCase("21")) {
					if (subSegments.length > 1) {

						if (subSegments[1].equalsIgnoreCase("41")) {

							if (subSegments.length > 3) {
								orm.setSubmitter_name(subSegments[3]);
							}
							if (subSegments.length > 8) {
								orm.setSubmitter_identifer_code_qualifier(subSegments[8]);
							}
							if (subSegments.length > 9) {
								orm.setSubmitter_identifer_code(subSegments[9]);
							}
						}
					}

				}
				*/

				/*
				// Billing Provider
				if (hlCode.equalsIgnoreCase("19")) {

					if (subSegments[1].equalsIgnoreCase("85")) {
						if (subSegments.length > 3) {
							orm.setBilling_provider_name(subSegments[3]);
						}
						if (subSegments.length > 8) {
							orm.setBilling_provider_identifer_code_qualifier(subSegments[8]);
						}
						if (subSegments.length > 9) {
							orm.setBilling_provider_identifer_code(subSegments[9]);
						}
					}
				}
				*/

				// PATIENT LEVEL
				if (hlCode.equalsIgnoreCase("PT")) {
					if (subSegments.length > 1) {

						if (subSegments[1].equalsIgnoreCase("QC")) {

							if (subSegments.length > 3) {
								orm.setPatient_last_name(subSegments[3]);
							}
							if (subSegments.length > 4) {
								orm.setPatient_first_name(subSegments[4]);
							}
							if (subSegments.length > 9) {
								if (subSegments[8].equalsIgnoreCase("MI")) {
									orm.setPatient_policy_number(subSegments[9]);
								}
							}
						}
					}
				}

				break;

			case "TRN":
				
				// PATIENT LEVEL
				if (hlCode.equalsIgnoreCase("PT")) {
					
					
					if (GeneralOperations.isNotNullorEmpty(orm.getClaim_id())) {
						lstClaimResponse.add(orm);

						// copy segment info to new ORM
						orm = new ORM_277_ResponseSave(orm);
					}
					
					if (subSegments.length > 2) {
						orm.setClaim_id(subSegments[2]);
					}
				}
				
				break;
			case "STC":
				// PATIENT LEVEL
				if (hlCode.equalsIgnoreCase("PT")) {
					
					
					if(GeneralOperations.isNullorEmpty(orm.getStatus_description())) {
						
						if (subSegments.length > 1) {
							String[] segments = subSegments[1].split(":");
							orm.setStatus_category_code(segments[0]);
							if (segments.length > 1) {
								
								orm.setStatus_code(segments[1]);
							}
						}
						if (subSegments.length > 2) {
							String statusEffectiveDate = DateTimeUtil.formatDateString(subSegments[2],
									DateFormatEnum.yyyyMMdd, DateFormatEnum.yyyy_MM_dd);
							orm.setStatus_effective_date(statusEffectiveDate);
						}

						//if (subSegments.length > 3) {
						//	orm.setAction_code(subSegments[3]);
						//}
						if (subSegments.length > 4) {
							orm.setAmount_billed(subSegments[4]);
						}
						if (subSegments.length > 12) {
							orm.setStatus_description(subSegments[12].trim());
						}
					}
					else { // append status description						
						orm.setStatus_description(orm.getStatus_description() +", "+  subSegments[12]);
					}					

				}
				break;

			case "REF":

				// PATIENT LEVEL
				if (hlCode.equalsIgnoreCase("PT")) {
					// ICN
					if (subSegments.length > 1 && subSegments[1].equalsIgnoreCase("1K")) {
						if (subSegments.length > 2) {
							orm.setIcn(subSegments[2]);
						}

					}
				}
				break;

			case "SE":
				lstClaimResponse.add(orm);

				// Create New ORM without copying any segment info as segment ends here
				orm = new ORM_277_ResponseSave();
				break;

			default:
				break;
			}

		}

		return lstClaimResponse;
	}
}
