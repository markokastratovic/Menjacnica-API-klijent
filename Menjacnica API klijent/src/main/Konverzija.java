package main;

public class Konverzija {
	private String datumVreme;
	private String izValute;
	private String uValutu;
	private Double kurs=null;
	public String getDatumVreme() {
		return datumVreme;
	}
	public void setDatumVreme(String datumVreme) {
		this.datumVreme = datumVreme;
	}
	public String getIzValute() {
		return izValute;
	}
	public void setIzValute(String izValute) {
		this.izValute = izValute;
	}
	public String getuValutu() {
		return uValutu;
	}
	public void setuValutu(String uValutu) {
		this.uValutu = uValutu;
	}
	public Double getKurs() {
		return kurs;
	}
	public void setKurs(Double kurs) {
		this.kurs = kurs;
	}
}
