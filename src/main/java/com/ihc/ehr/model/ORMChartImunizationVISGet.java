package com.ihc.ehr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ORMChartImunizationVISGet {

	@Id
	private Long chart_imm_vis_id;
	private String vis_name;
	private String vis_encoded_text;
	private String vis_gdti_code;
	private String vis_info;
	private String vis_date_published;
	private String vis_date_presented;
	private String coding_system;


	public Long getChart_imm_vis_id() {
		return chart_imm_vis_id;
	}

	public void setChart_imm_vis_id(Long chart_imm_vis_id) {
		this.chart_imm_vis_id = chart_imm_vis_id;
	}

	public String getVis_name() {
		return vis_name;
	}

	public void setVis_name(String vis_name) {
		this.vis_name = vis_name;
	}

	public String getVis_encoded_text() {
		return vis_encoded_text;
	}

	public void setVis_encoded_text(String vis_encoded_text) {
		this.vis_encoded_text = vis_encoded_text;
	}

	public String getVis_gdti_code() {
		return vis_gdti_code;
	}

	public void setVis_gdti_code(String vis_gdti_code) {
		this.vis_gdti_code = vis_gdti_code;
	}

	public String getVis_info() {
		return vis_info;
	}

	public void setVis_info(String vis_info) {
		this.vis_info = vis_info;
	}

	public String getVis_date_published() {
		return vis_date_published;
	}

	public void setVis_date_published(String vis_date_published) {
		this.vis_date_published = vis_date_published;
	}

	public String getVis_date_presented() {
		return vis_date_presented;
	}

	public void setVis_date_presented(String vis_date_presented) {
		this.vis_date_presented = vis_date_presented;
	}

	public String getCoding_system() {
		return coding_system;
	}

	public void setCoding_system(String coding_system) {
		this.coding_system = coding_system;
	}

}
