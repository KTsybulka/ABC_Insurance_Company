package model;

import java.util.Date;

public class M_93_RegisteredProduct {
	private int reg_product_id, userid, productid;	
	private String serial_no;
	private Date purchase_date;
	
	public M_93_RegisteredProduct(int reg_product_id, int userid, int productid, String serial_no, Date purchase_date) {
		super();
		this.reg_product_id = reg_product_id;
		this.userid = userid;
		this.productid = productid;
		this.serial_no = serial_no;
		this.purchase_date = purchase_date;
	}

	public M_93_RegisteredProduct(int userid, int productid, String serial_no, Date purchase_date) {
		super();
		this.userid = userid;
		this.productid = productid;
		this.serial_no = serial_no;
		this.purchase_date = purchase_date;
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

	public int getProductid() {
		return productid;
	}

	public void setProductid(int productid) {
		this.productid = productid;
	}

	public String getSerial_no() {
		return serial_no;
	}

	public void setSerial_no(String serial_no) {
		this.serial_no = serial_no;
	}

	public Date getPurchase_date() {
		return purchase_date;
	}

	public void setPurchase_date(Date purchase_date) {
		this.purchase_date = purchase_date;
	}

	@Override
	public String toString() {
		return "M_93_RegisteredProduct [reg_product_id=" + reg_product_id + ", userid=" + userid + ", productid="
				+ productid + ", serial_no=" + serial_no + ", purchase_date=" + purchase_date + "]";
	}
	

  
    
    
}

