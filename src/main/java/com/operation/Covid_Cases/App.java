package com.operation.Covid_Cases;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class App {

	private static String getDataofCovidcasesbycountry(String country) throws IOException {
		
		System.out.println("----------------Welcome we will fetch live data of covid details--------------------");
		StringBuilder br = new StringBuilder();
		br.append("<html>" + "<body style=text-align:center;color:blue;font-size:20px;>");
		br.append("Country : " + country.toUpperCase() + "<br>");
		String url = "https://www.worldometers.info/coronavirus/country/" + country + "/";
		System.out.println("URL FROM WHERE WE FETCH COVID DATA COUNTRY WISE : " + url);

		Document docs = Jsoup.connect(url).get();

		System.out.println(docs.title());

		// We will select the id's(html id) of above url where imp data is stored.
		if (!"404 Not Found".equals(docs.title())) {
			
			Elements elements = docs.select("div[id=maincounter-wrap]");
			System.out.println("All covid details related to " + country);
			elements.forEach((e) -> {

				String text = e.select("div[id=maincounter-wrap] > h1").text();
				String strins = e.select("div.maincounter-number>span").text();
				br.append(text).append(" ").append(strins).append("<br>");
				
			});
			
			System.out.println("URL CALL Ended !! Thankyou returning the data to you");
			br.append("</body></html>");
			
		} else {
			
			br.append("Sorry!!! No Data Found related to this country " + country + "...");
			br.append("</body></html>");
			
		}
		
		return br.toString();

	}

	public static void main(String[] args) throws IOException {
		/*
		 * System.out.println("Hello World!");
		 * 
		 * Scanner sc = new Scanner(System.in);
		 * System.out.println("Enter a country : ");
		 * 
		 * String country = sc.nextLine();
		 * 
		 * System.out.println("Country name which you want to fetch details : " +
		 * country);
		 * 
		 * System.out.println(" The Data you have been looking for"
		 * +getDataofCovidcasesbycountry(country));
		 */
		
		System.out.println("CODE FOR GUI");
		JFrame root = new JFrame("Covid Details of Countries");

		root.setSize(500, 500);

		Font f = new Font("Poppins", Font.PLAIN, 10);

		JTextField field = new JTextField();
		JLabel data = new JLabel();

		field.setFont(f);

		data.setFont(f);
		data.setBackground(Color.white);
		
		data.setHorizontalAlignment(SwingConstants.CENTER);
		field.setHorizontalAlignment(SwingConstants.CENTER);

		JButton button = new JButton("GET");

		button.addActionListener(event -> {
			// click to fetch data
			try {

				String maindata = field.getText();
				System.out.println("Country to get details of covid details :" + maindata);

				String result = getDataofCovidcasesbycountry(maindata);

				data.setText(result);

			} catch (IOException e) {

				e.printStackTrace();
			}
		});

		button.setFont(f);
		button.setBackground(Color.white);
		root.setLayout(new BorderLayout());

		root.add(field, BorderLayout.NORTH);
		root.add(data, BorderLayout.CENTER);
		root.add(button, BorderLayout.SOUTH);
		root.setVisible(true);

	}
}