package jsonTutorial;



import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonTutorial {
	
	public static void main(String[] args) throws JSONException {
		JSONObject obj = new JSONObject();
		obj.put("name", "Simon");
		obj.put("location", "Hoevelaken");
		
		JSONArray list = new JSONArray();
		list.put("Java");
		list.put("JSP");
		list.put("Servlets");
		
		obj.put("courses", list);
		
		try(FileWriter file = new FileWriter("myJSON.json"))
		{
			file.write(obj.toString());
			file.flush();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		System.out.print(obj);
	}

}
