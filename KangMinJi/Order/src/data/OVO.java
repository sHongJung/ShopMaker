package data;

import java.sql.Date;
import java.sql.Timestamp;

public class OVO {

	private int oNum;
	private String oName;
	private String oId;
	private String oPhone;
	private String oAdd;
	private String oProduct;
	private int oQuan;
	private Timestamp oDate;
	private int oPrice;

	public OVO(){
		
	}
	public OVO(int oNum, String oName, String oId, String oPhone, String oAdd, String oProduct, int oQuan) {
		this.oNum = oNum;
		this.oName = oName;
		this.oId = oId;
		this.oPhone = oPhone;
		this.oAdd = oAdd;
		this.oProduct = oProduct;
		this.oQuan = oQuan;
	}

	public int getoNum() {
		return oNum;
	}

	public void setoNum(int oNum) {
		this.oNum = oNum;
	}

	public String getoName() {
		return oName;
	}

	public void setoName(String oName) {
		this.oName = oName;
	}

	public String getoId() {
		return oId;
	}

	public void setoId(String i) {
		this.oId = i;
	}

	public String getoPhone() {
		return oPhone;
	}

	public void setoPhone(String oPhone) {
		this.oPhone = oPhone;
	}

	public String getoAdd() {
		return oAdd;
	}

	public void setoAdd(String oAdd) {
		this.oAdd = oAdd;
	}

	public String getoProduct() {
		return oProduct;
	}

	public void setoProduct(String oProduct) {
		this.oProduct = oProduct;
	}

	public int getoQuan() {
		return oQuan;
	}

	public void setoQuan(int oQuan) {
		this.oQuan = oQuan;
	}
	
	public Timestamp getoDate() {
		// TODO Auto-generated method stub
		return oDate;
	}
	public void setoDate(Timestamp oDate) {
		this.oDate = oDate;
	}
	public void setoPrice(int oPrice){
		this.oPrice = oPrice;
	}
	public int getoPrice(){
		return oPrice;
	}
}

