package model;

import java.sql.Date;

public class M_93_InsuranceClaim {

	private int claim_id, reg_product_id, userid;	
	private Date claim_date;
	private String description, claim_status;
	


	public M_93_InsuranceClaim(int claim_id, int reg_product_id, int userid, Date claim_date, String description,
			String claim_status) {
		super();
		this.claim_id = claim_id;
		this.reg_product_id = reg_product_id;
		this.userid = userid;
		this.claim_date = claim_date;
		this.description = description;
		this.claim_status = claim_status;
	}	
	
	public M_93_InsuranceClaim() {
		this.reg_product_id = 0;
		this.userid = 0;
		this.claim_date = null;
		this.description = "";
		this.claim_status = "";
	}

	public int getClaim_id() {
		return claim_id;
	}

	public void setClaim_id(int claim_id) {
		this.claim_id = claim_id;
	}

	public int getReg_product_id() {
		return reg_product_id;
	}

	public void setReg_product_id(int reg_product_id) {
		this.reg_product_id = reg_product_id;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public Date getClaim_date() {
		return claim_date;
	}

	public void setClaim_date(Date claim_date) {
		this.claim_date = claim_date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}



	public String getClaim_status() {
		return claim_status;
	}



	public void setClaim_status(String claim_status) {
		this.claim_status = claim_status;
	}



	@Override
	public String toString() {
		return "M_93_InsuranceClaim [claim_id=" + claim_id + ", reg_product_id=" + reg_product_id + ", userid=" + userid
				+ ", claim_date=" + claim_date + ", description=" + description + ", claim_status=" + claim_status
				+ "]";
	}
	
}
