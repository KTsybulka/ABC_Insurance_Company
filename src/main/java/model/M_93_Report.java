package model;

public class M_93_Report {

	
    private M_93_Users user;
    private M_93_Product product;
    private M_93_InsuranceClaim claim;

    // Constructors, getters, and setters
    public M_93_Report () {
        this.user = new M_93_Users();
        this.product = new M_93_Product();
        this.claim = new M_93_InsuranceClaim();
    }

	public M_93_Users getUser() {
		return user;
	}

	public void setUser(M_93_Users user) {
		this.user = user;
	}

	public M_93_Product getProduct() {
		return product;
	}

	public void setProduct(M_93_Product product) {
		this.product = product;
	}

	public M_93_InsuranceClaim getClaim() {
		return claim;
	}

	public void setClaim(M_93_InsuranceClaim claim) {
		this.claim = claim;
	}

	@Override
	public String toString() {
		return "M_93_Report [user=" + user + ", product=" + product + ", claim=" + claim + "]";
	}	
}
