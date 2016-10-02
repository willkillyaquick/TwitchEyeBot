package fileUtil;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.*;

public class FileJSONPraser {
	JSONObject jsonFile;
	public FileJSONPraser(String file) {
		setJsonFile(file);
	}

	public JSONObject getJsonFile() {
		return jsonFile;
	}

	public void setJsonFile(String file) {
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(file));
			String jsonTxt = readAll(br);
			jsonFile = new JSONObject(jsonTxt);
		} catch (FileNotFoundException e) {
			System.out.println("JSON File missing");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			System.out.println("ID does not exist");
			e.printStackTrace();
		}
		
	}
	private String readAll(BufferedReader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		// TODO Auto-generated method stub
		return sb.toString();
	}
	
	
}
