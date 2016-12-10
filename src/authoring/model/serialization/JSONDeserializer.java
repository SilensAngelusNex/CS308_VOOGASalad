package authoring.model.serialization;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Scanner;

import authoring.model.TowerData;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

public class JSONDeserializer {

	private Gson gson;
	private Scanner scanner;
	private String fileText;
	//matthewfaw: after some revisions to scanner initialization, this string is no longer necessary
	// this should make our code more flexible
	//String fileLoc = "src/SerializedFiles/";
	
	public Object Deserialize(String json, Class cls){
		
		gson = new Gson();
		return (gson.fromJson(json, cls));
		
	}

	public Object deserializeFromFile(String filepath, Class cls) throws Exception{
		
		try {
			//The following lines look up a file relative to the project path
			File file = new File(filepath);
			scanner = new Scanner(file);
			fileText = scanner.useDelimiter("\\A").next();
		} catch (FileNotFoundException e) {
			throw new Exception("File was unable to be deserialized correctly.");
		} finally{
			scanner.close();
		}
		
		gson = new Gson();
		
		return (gson.fromJson(fileText, cls));
		
	}
	@Deprecated
	private URL getUrl(String aFilepath)
	{
		//a filepath should not have . to represent folder paths for the getResource method
//		aFilepath = aFilepath.replace('.', '/');
		System.out.println(aFilepath);
		System.out.println(getClass().getClassLoader().getResource(aFilepath));
		return getClass().getClassLoader().getResource(aFilepath);
	}
}
