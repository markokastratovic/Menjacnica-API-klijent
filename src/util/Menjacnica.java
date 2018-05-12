package util;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import domen.Valuta;

public class Menjacnica {
	public static LinkedList<Valuta>valute;
	public static LinkedList<Valuta> vratiValute() throws IOException{
		
		String url= "http://free.currencyconverterapi.com/api/v3/countries";
		String content = util.URLConnectionUtil.getContent(url);
		Gson gson = new GsonBuilder().create();				
		JsonObject contentJson = gson.fromJson(content, JsonObject.class);
		JsonObject resultsJson = contentJson.get("results").getAsJsonObject();
		
		valute=new LinkedList<Valuta>();
		
		 for (Map.Entry<String,JsonElement> entry : resultsJson.entrySet()) {
			 Valuta v=gson.fromJson(entry.getValue().getAsJsonObject(), Valuta.class);
		     valute.add(v);
	}		
	return valute;
}
	
	public static double vratiKonverziju(int indeksIZ,int indeksU) {
		String skraceni1=valute.get(indeksIZ).getCurrencyId();
		String skraceni2=valute.get(indeksU).getCurrencyId();
		String url="http://free.currencyconverterapi.com/api/v3/convert?q="+skraceni1+"_"+skraceni2;
		String content;
		double kurs=0;
		try {
			content = util.URLConnectionUtil.getContent(url);
			Gson gson = new GsonBuilder().create();				
			JsonObject contentJson = gson.fromJson(content, JsonObject.class);
			JsonObject resultsJson = contentJson.get("results").getAsJsonObject();
			JsonObject queryJson = contentJson.get("query").getAsJsonObject();
			int count=queryJson.get("count").getAsInt();
			System.out.println(resultsJson);
//			System.out.println(queryJson);
//			System.out.println(count);
			if (count!=0) {
			JsonObject konverzijaJson=resultsJson.get(skraceni1+"_"+skraceni2).getAsJsonObject();					
				kurs=konverzijaJson.get("val").getAsDouble();
				System.out.println(kurs);			
		}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return kurs;
}
	
	public static void SacuvajKonverziju(int indeksIZ,int indeksU,double kurs) {
		String skraceni1=valute.get(indeksIZ).getCurrencyId();
		String skraceni2=valute.get(indeksU).getCurrencyId();
		Gson gson = new GsonBuilder().create();		
		Date datum = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss.SSSSSS");
		String datumS = format.format(datum);
		
		gson=new GsonBuilder().setPrettyPrinting().serializeNulls().create();
		JsonObject obj=new JsonObject();
		obj.addProperty("datumVreme",datumS);
		obj.addProperty("izValute", skraceni1);
		obj.addProperty("uValutu", skraceni2);
		obj.addProperty("kurs", kurs);
		
		JsonArray log=null;
		try {
			FileReader reader = new FileReader("data/log.json");
			log = gson.fromJson(reader, JsonArray.class);
			FileWriter writer = new FileWriter("data/log.json");
			if(log==null)log=new JsonArray();

			log.add(obj);
			writer.write(gson.toJson(log));
			writer.close();
		} catch (JsonSyntaxException | JsonIOException | IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
}