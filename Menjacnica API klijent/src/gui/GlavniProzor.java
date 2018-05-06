package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import main.Valuta;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Map;

public class GlavniProzor extends JFrame {

	private JPanel contentPane;
	private JLabel lblIzValuteZemlje;
	private JLabel lblUValutuZemlje;
	private JComboBox comboBox;
	private JComboBox comboBox_1;
	private JLabel lblIznos;
	private JLabel lblIznos1;
	private JTextField txtIznosiz;
	private JTextField txtIznosu;
	private JButton btnKonvertuj;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GlavniProzor frame = new GlavniProzor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GlavniProzor() {
		setTitle("Menjacnica");
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				String url= "http://free.currencyconverterapi.com/api/v3/countries";
				try {
					String content = util.URLConnectionUtil.getContent(url);
					Gson gson = new GsonBuilder().create();				
					JsonObject contentJson = gson.fromJson(content, JsonObject.class);
					JsonObject resultsJson = contentJson.get("results").getAsJsonObject();
					
					LinkedList<Valuta>valute=new LinkedList<Valuta>();
					
					 for (Map.Entry<String,JsonElement> entry : resultsJson.entrySet()) {
						 Valuta v=gson.fromJson(entry.getValue().getAsJsonObject(), Valuta.class);
					     valute.add(v);
					 
					 comboBox.addItem(v.getName());
					 comboBox_1.addItem(v.getName());    
					 }
//					System.out.println(valute.getLast().getCurrencyName());
//					System.out.println(valute.size());		
//					System.out.println(resultsJson);
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				
				
				System.out.println("Prozor otvoren");
				
				
				
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblIzValuteZemlje());
		contentPane.add(getLblUValutuZemlje());
		contentPane.add(getComboBox());
		contentPane.add(getComboBox_1());
		contentPane.add(getLblIznos());
		contentPane.add(getLblIznos1());
		contentPane.add(getTxtIznosiz());
		contentPane.add(getTxtIznosu());
		contentPane.add(getBtnKonvertuj());
	}
	private JLabel getLblIzValuteZemlje() {
		if (lblIzValuteZemlje == null) {
			lblIzValuteZemlje = new JLabel("Iz valute zemlje:");
			lblIzValuteZemlje.setBounds(41, 62, 95, 14);
		}
		return lblIzValuteZemlje;
	}
	private JLabel getLblUValutuZemlje() {
		if (lblUValutuZemlje == null) {
			lblUValutuZemlje = new JLabel("U valutu zemlje:");
			lblUValutuZemlje.setBounds(266, 62, 106, 14);
		}
		return lblUValutuZemlje;
	}
	private JComboBox getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox();
			comboBox.setBounds(41, 87, 147, 20);
		}
		return comboBox;
	}
	private JComboBox getComboBox_1() {
		if (comboBox_1 == null) {
			comboBox_1 = new JComboBox();
			comboBox_1.setBounds(266, 87, 147, 20);
		}
		return comboBox_1;
	}
	private JLabel getLblIznos() {
		if (lblIznos == null) {
			lblIznos = new JLabel("Iznos");
			lblIznos.setBounds(41, 133, 46, 14);
		}
		return lblIznos;
	}
	private JLabel getLblIznos1() {
		if (lblIznos1 == null) {
			lblIznos1 = new JLabel("Iznos");
			lblIznos1.setBounds(266, 133, 46, 14);
		}
		return lblIznos1;
	}
	private JTextField getTxtIznosiz() {
		if (txtIznosiz == null) {
			txtIznosiz = new JTextField();
			txtIznosiz.setBounds(41, 158, 147, 20);
			txtIznosiz.setColumns(10);
		}
		return txtIznosiz;
	}
	private JTextField getTxtIznosu() {
		if (txtIznosu == null) {
			txtIznosu = new JTextField();
			txtIznosu.setBounds(263, 158, 150, 20);
			txtIznosu.setColumns(10);
		}
		return txtIznosu;
	}
	private JButton getBtnKonvertuj() {
		if (btnKonvertuj == null) {
			btnKonvertuj = new JButton("Konvertuj");
			btnKonvertuj.setBounds(167, 210, 89, 23);
		}
		return btnKonvertuj;
	}
}
