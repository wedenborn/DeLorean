package com.delorean.jeopardy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.url.WebURL;

public class Crawlings {


	static Random random = new Random();
	static final int MINIMUM_QUESTION_LENGTH = 40;
	static final int MAXIMUM_QUESTION_LENGTH = 130;
	static final int NUMBER_OF_HINTS = 3;
	static final int NUMBER_OF_ALTERNATIVES = 3;
	static final String[] namePrefixes = {"De", "Du", "Van der", "Von", "Den", "Van",
		"D'", "De la", "Le", "La", "Des", "A", "Da", "Del", "Della", "Di", "Li", "Lo", "Dos", "De La", "Las", "Af", "Av"};

	static HashMap<String,ArrayList<String>> statements = new HashMap<String,ArrayList<String>>(); //maps answer to array of statements
	static HashMap<String,ArrayList<String>> alternatives = new HashMap<String,ArrayList<String>>(); //maps answer to names to use as alternatives
	static JSONArray jsons = new JSONArray();

	public Crawlings() {
		//
	}

	public static void runner() throws JSONException {
		Arrays.sort(namePrefixes);
		for(int i = 0; i < namePrefixes.length; i++){
			namePrefixes[i] = " " + namePrefixes[i].toLowerCase() + " ";
		}

		Crawlings crawler = new Crawlings();
		try {
			String[] celebURL = {"http://history1900s.about.com/od/people/tp/famouspeople.htm","http://www.whoismorefamous.com/?fulllist=1"};
			Document doc = Jsoup.connect(celebURL[1]).get();
			Elements elements = doc.select("li");
			int count = 0;
			for (Element element : elements) {
				WebURL url = new WebURL();
				url.setURL("https://en.wikipedia.org/wiki/" + element.text().replace(" ",	"_"));
				crawler.visit(new Page(url));
				count++;
				if(count > 5) break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}


		for(String answer : statements.keySet()){
			ArrayList<String> personsStatements = statements.get(answer);
			while(personsStatements.size() < NUMBER_OF_HINTS + 1){//Inte tillräckligt med meningar hittades
				personsStatements.add("No more hints available.");
			}

			ArrayList<String> personsAlternatives = alternatives.get(answer); 
			while(personsAlternatives.size() < NUMBER_OF_ALTERNATIVES){//Inte tillräckligt med alternativ hittades
				personsAlternatives.add("Snurre Sprätt"); 
			}

			for (int questionNr = 0; questionNr < personsStatements.size(); questionNr++) {
				String question = personsStatements.get(questionNr);

				ArrayList<Integer> statementNr = new ArrayList<Integer>();
				statementNr.add(questionNr);

				String hints[] = new String[NUMBER_OF_HINTS];
				for (int j = 0; j < hints.length; j++) {
					int randomHint = random.nextInt(personsStatements.size());
					while (statementNr.contains(randomHint)) {//to avoid duplicates
						randomHint = random.nextInt(personsStatements.size());
					}
					statementNr.add(randomHint);
					hints[j] = personsStatements.get(randomHint);
				}

				String[] alts = new String[NUMBER_OF_ALTERNATIVES];
				ArrayList<Integer> altNr = new ArrayList<Integer>();

				for (int j = 0; j < alts.length; j++) {
					int randAlt = random.nextInt(personsAlternatives.size());
					while (altNr.contains(randAlt)) {//to avoid duplicates
						randAlt = random.nextInt(personsAlternatives.size());
					}
					altNr.add(randAlt);
					alts[j] = personsAlternatives.get(randAlt);
				}
				if(jsons.length()<3) {
					addJSON(question, alts, hints, answer);
					System.out.println("JSONS LENGTH: " + jsons.length());
				}
				else {
					return;
				}
			}
		}

		//	crawler.test();
	}

	public void test() throws JSONException{
		int rnd = random.nextInt(jsons.length());
		JSONObject j = jsons.optJSONObject(rnd);
		Scanner sc = new Scanner(System.in);
		int ind = 0;
		while(sc.hasNext()){
			String next = sc.next();
			if(next.startsWith("q")){
				j = jsons.optJSONObject(random.nextInt(jsons.length()));	
				ind=0;
				System.out.println(j.get("Question"));
			}
			if(next.startsWith("alt")){
				String[] alts = (String[]) j.get("Alternatives");
				int r = random.nextInt(4);
				for(int i = 0; i < 3; i++){
					if(i == r) System.out.println(j.get("Answer"));
					else System.out.println(alts[i]);
				}
				if(r == 3) System.out.println(j.get("Answer"));
				else System.out.println(alts[r]);
			}
			if(next.startsWith("h")){
				String[] hints = (String[]) j.get("Hints");
				System.out.println(hints[ind%3]);
				ind++;
			}
			if(next.startsWith("ans")){
				System.out.println(j.get("Answer"));
			}
		}
		sc.close();
	}

	/**
	 * This function is called when a page is fetched and ready to be processed
	 * by your program.
	 */

	public void visit(Page page) {
		String url = page.getWebURL().getURL();
		String fullName = url.substring(url.lastIndexOf("/")+1,url.length()).replace("_"," ");
		String lastName = fullName.substring(fullName.lastIndexOf(" ")+1, fullName.length());
		for(int prefix = 0; prefix < namePrefixes.length; prefix++){
			if(fullName.toLowerCase().contains(namePrefixes[prefix])){
				lastName = (namePrefixes[prefix] + lastName).trim(); 
				//fixar så att t.ex. Leonardo da Vincis efternamn blir da Vinci och inte Vinci.
			}
		}

		try {
			Document doc = Jsoup.connect(url).get();
			System.out.println("URL: " + url);
			System.out.println("Full Name: " + fullName);
			System.out.println("Last name: " + lastName);
			ArrayList<String> names = new ArrayList<String>(); //alternativen (andra namn som förekommer i artikeln)
			Elements paragraphs = doc.select("p");
			for(int i=0;i<2;i++) {
				//			for (Element element : paragraphs) {

				if(paragraphs.get(i).hasText()){
					String text = paragraphs.get(i).text();

					String nameRegex = "[A-Z][a-z]+ [A-Z][a-z]+"; //regex för att hämta namn för att ha som alternativ (exakt 2 ord atm (3 var oftast inte namn))
					Elements links = doc.select("a[href]");
					for(Element link : links){
						Matcher nameMatcher =  Pattern.compile(nameRegex).matcher(link.text());
						if(nameMatcher.matches()){
							if(!names.contains(nameMatcher.group())){
								names.add(nameMatcher.group());
							}
						}
					}


					String regex1 = "[A-Z][.] [A-Z]"; 
					Matcher matcher1 = Pattern.compile(regex1).matcher(text);
					StringBuffer buffer1 = new StringBuffer();
					while (matcher1.find()) {
						matcher1.appendReplacement(buffer1, matcher1.group().replace(".", "")); 
						//Tar bort punkterna så att t.ex. "John F. Kennedy" blir "John F Kennedy" (temporär lösning)
					}
					matcher1.appendTail(buffer1);
					String regex2 = "\\[[0-9]+\\]";
					Matcher matcher2 = Pattern.compile(regex2).matcher(buffer1);
					StringBuffer buffer2 = new StringBuffer();
					while (matcher2.find()) {
						matcher2.appendReplacement(buffer2, "");
						//Tar bort Wikipedias referenser
					}
					matcher2.appendTail(buffer2);
					text = buffer2.toString();

					String[] sentences = text.split("[.] "); //array där varje element är en mening i paragrafen
					for(int j = 0; j < sentences.length; j++){
						if(sentences[j].contains(lastName)){
							String sentence = sentences[j].trim();
							String replacement = "this person";
							if(sentence.startsWith(fullName) || sentence.startsWith(lastName)) replacement = "This person";
							if(sentence.contains(fullName.replace(".",""))){ //replace(".", "") bara temporärt
								sentence = sentence.replaceAll(fullName.replace(".", ""), replacement);
							}
							sentence = sentence.replaceAll(lastName, replacement);

							String regex3 = "[A-Z][a-z]+ " + replacement;
							String regex4 = "[T|t]he " + replacement;
							Matcher matcher3 = Pattern.compile(regex3).matcher(sentence);
							Matcher matcher4 = Pattern.compile(regex4).matcher(sentence);
							while (matcher3.find()) {
								sentence = matcher3.replaceAll(matcher3.group().replace(replacement, "****"));
							}
							while (matcher4.find()) {
								sentence = matcher4.replaceAll(matcher4.group().replace(replacement, "____"));
							}

							if(!sentence.endsWith(".")) sentence = sentence + ".";

							if(sentence.length() <= MAXIMUM_QUESTION_LENGTH && sentence.length() >= MINIMUM_QUESTION_LENGTH){
								ArrayList<String> currentStatements = new ArrayList<String>();
								if(statements.get(fullName) != null) currentStatements.addAll(statements.get(fullName));
								currentStatements.add(sentence);
								statements.put(fullName, currentStatements);
							}
						}
					}
				}
			}
			names.remove(fullName); //tar bort svaret som alternativ
			alternatives.put(fullName, names);
			System.out.println("Alternatives: " + alternatives.get(fullName)); //printar alla alternativ
		} catch (IOException e) {
			//e.printStackTrace();
		} 
		System.out.println("=============");
	}

	public JSONArray getJSON(){
		return jsons;
	}

	private static void addJSON(String question, String[] alternatives, String[] hints, String answer) throws JSONException{
		JSONObject json = new JSONObject();
		json.put("Question", question);
		json.put("Alternatives", alternatives);
		json.put("Hints", hints);
		json.put("Answer", answer);
		jsons.put(json);
	}
}


