package app.spotify.spotifybe.importer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import app.spotify.spotifybe.model.Account;

public class FileReader {

	public List<Account> txtFileToAccount(InputStream is) throws IOException{
		List<Account> accounts = new ArrayList<>();
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		
		while(reader.readLine()!=null) {
			String line = reader.readLine();
			
		}
		
		
		return accounts;
	}
	
}
