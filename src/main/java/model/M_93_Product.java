package model;

public class M_93_Product {

	private int productid;	
	private String product_name, serial_no, product_model, product_description;
	
	public M_93_Product(int productid, String product_name, String serial_no, String product_model,
			String product_description) {		
		this.productid = productid;
		this.product_name = product_name;
		this.serial_no = serial_no;
		this.product_model = product_model;
		this.product_description = product_description;
	}

	public M_93_Product(String product_name, String serial_no, String product_model, String product_description) {
		super();
		this.product_name = product_name;
		this.serial_no = serial_no;
		this.product_model = product_model;
		this.product_description = product_description;
	}

	public M_93_Product() {
		this.productid = 0;
		this.product_name = "";
		this.serial_no = "";
		this.product_model = "";
		this.product_description = "";
	}

	public int getProductid() {
		return productid;
	}

	public void setProductid(int productid) {
		this.productid = productid;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getSerial_no() {
		return serial_no;
	}

	public void setSerial_no(String serial_no) {
		this.serial_no = serial_no;
	}

	public String getProduct_model() {
		return product_model;
	}

	public void setProduct_model(String product_model) {
		this.product_model = product_model;
	}

	public String getProduct_description() {
		return product_description;
	}

	public void setProduct_description(String product_description) {
		this.product_description = product_description;
	}

	@Override
	public String toString() {
		return "M_93_Product [productid=" + productid + ", product_name=" + product_name + ", serial_no=" + serial_no
				+ ", product_model=" + product_model + ", product_description=" + product_description + "]";
	}
	
	
	
	
}



