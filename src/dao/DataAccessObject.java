package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import domain.Vehicle;

public class DataAccessObject {
	private final String path;
	private final String executable;

	public DataAccessObject(String path, String executable) {
		super();
		this.path = path;
		this.executable = executable;
	}

	private String[] processCmd(String cmd) {
		String[] params = cmd.split(" ");
		params[0] = path + File.separator + params[0];
		String[] output = new String[0];

		try {
			Process process = new ProcessBuilder(params).start();

			InputStream is = process.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			output = br.lines().toArray(String[]::new);

			InputStream es = process.getErrorStream();
			BufferedReader err = new BufferedReader(new InputStreamReader(es));
			String[] errors = err.lines().toArray(String[]::new);

			int exitCode = process.waitFor();

			if (exitCode != 0) {
				System.out.println("Erreur depuis OCaml (exit " + exitCode + "):");
				Arrays.stream(errors).forEach(System.out::println);
				return null;
			}

			if (errors.length > 0) {
				System.out.println("Messages d'erreur depuis OCaml :");
				Arrays.stream(errors).forEach(System.out::println);
			}

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}

		return output;
	}

	public String[] read() {
		return run("-R");
	}

	public String[] readCritical() {
		return run("-R", "-critical");
	}
	
	public String[] create(Vehicle v) {
		return run("-C", String.valueOf(v.getId()), v.getCategory().toString(), v.getKind().toString(), v.getModel(), String.valueOf(v.getKm()));
	}
	
	public String[] update(String id, Vehicle v) {
		return run("-U", id, v.getCategory().toString(), v.getKind().toString(), v.getModel(), String.valueOf(v.getKm()));
	}
	
	public String[] delete(String id) {
		return run("-D", id);
	}
	
	private String[] run(String option, String ...args) {
		String command = executable + " " + option;
		for (String arg: args) {
			command += " "  + arg;
		}
		return processCmd(command);
	}

}
