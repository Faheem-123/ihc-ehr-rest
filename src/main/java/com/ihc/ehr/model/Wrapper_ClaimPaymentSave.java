package com.ihc.ehr.model;

import java.util.List;

public class Wrapper_ClaimPaymentSave {
	
	List<ORMClaimPaymentSave> lstClaimPaymentSave;
	List<ORMClaimPaymentAdjustmentSave> lstClaimPaymentAdjustmentSave;
	List<ORMKeyValue> lstKeyValue;
	ORMCashRegisterAdd cashRegisterAdd;
	
	public List<ORMClaimPaymentSave> getLstClaimPaymentSave() {
		return lstClaimPaymentSave;
	}
	public void setLstClaimPaymentSave(List<ORMClaimPaymentSave> lstClaimPaymentSave) {
		this.lstClaimPaymentSave = lstClaimPaymentSave;
	}
	public List<ORMClaimPaymentAdjustmentSave> getLstClaimPaymentAdjustmentSave() {
		return lstClaimPaymentAdjustmentSave;
	}
	public void setLstClaimPaymentAdjustmentSave(List<ORMClaimPaymentAdjustmentSave> lstClaimPaymentAdjustmentSave) {
		this.lstClaimPaymentAdjustmentSave = lstClaimPaymentAdjustmentSave;
	}
	public List<ORMKeyValue> getLstKeyValue() {
		return lstKeyValue;
	}
	public void setLstKeyValue(List<ORMKeyValue> lstKeyValue) {
		this.lstKeyValue = lstKeyValue;
	}
	public ORMCashRegisterAdd getCashRegisterAdd() {
		return cashRegisterAdd;
	}
	public void setCashRegisterAdd(ORMCashRegisterAdd cashRegisterAdd) {
		this.cashRegisterAdd = cashRegisterAdd;
	}	
}
