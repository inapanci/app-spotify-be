package app.spotify.spotifybe.importer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import app.spotify.spotifybe.model.Account;

public class FileReader {

	public FileReader() {
		super();
	}

	public List<Account> txtFileToAccount(InputStream is) throws IOException {
		List<Account> accounts = new ArrayList<>();
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		String line;
		while ((line = reader.readLine()) != null) {
			String[] columns = line.split(Pattern.quote("|"));
			Account a = new Account();
			if (columns.length >= 1) {
				a.setCredentials(columns[0]);
			}
			if (columns.length >= 2) {
				String[] subscription = columns[1].split(Pattern.quote(":"));
				a.setSubscriptionType(subscription[1]);
			}
			if (columns.length >= 3) {
				String[] country = columns[2].split(Pattern.quote(":"));
				a.setCountry(country[1]);
			}
			if (columns.length >= 4) {
				String[] invites = columns[3].split(Pattern.quote(":"));
				a.setInvites(invites[1]);
			}
			if (columns.length >= 5) {
				String[] address = columns[4].split(Pattern.quote(":"));
				a.setAddress(address[1]);
			}
			if (columns.length >= 6) {
				String[] inviteToken = columns[5].split(Pattern.quote(":"));
				a.setInviteToken(inviteToken[1]);
			}
			if (columns.length >= 7) {
				String[] expire = columns[6].split(Pattern.quote(":"));
				a.setExpire(java.sql.Date.valueOf(expire[1]));
			}
			if (columns.length >= 8) {
				String[] extra = columns[7].split(Pattern.quote(":"));
				a.setExtra(extra[1]);
			}
//			for (String s : columns) {
//				System.out.println(s);
//			}

			accounts.add(a);
		}

		return accounts;
	}

}
