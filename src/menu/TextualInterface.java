package menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.function.Predicate;

import domain.Car;
import domain.Moto;
import domain.Vehicle;
import domain.VehicleCategory;
import domain.VehicleKind;

public class TextualInterface {

	private Controller control;

	public TextualInterface(Controller control) {
		this.control = control;
	}

	public void mainMenu() {
		System.out.println("\n===== GESTION DE FLOTTE =====");
		System.out.println("1. Afficher tous les véhicules");
		System.out.println("2. Ajouter un véhicule");
		System.out.println("3. Mettre à jour un véhicule");
		System.out.println("4. Supprimer un véhicule");
		System.out.println("5. Afficher les véhicules critiques");
		System.out.println("0. Quitter\n");

		try {
			int choice = Integer.parseInt(inputOutput("Votre choix :"));

			switch (choice) {
			case 0:
				System.out.println("Au revoir !");
				System.exit(0);
				break;

			case 1:
				displayFleet(control.read());
				break;

			case 2:
				Vehicle newVehicle = promptVehicle();
				System.out.println(control.create(newVehicle));
				break;

			case 3:
				String idUpdate = inputOutput("ID du véhicule à modifier :");
				Vehicle updatedVehicle = promptVehicle(Integer.parseInt(idUpdate));
				System.out.println(Arrays.toString(control.update(idUpdate, updatedVehicle)));
				break;

			case 4:
				String idDelete = inputOutput("ID du véhicule à supprimer :");
				System.out.println(Arrays.toString(control.delete(idDelete)));
				break;

			case 5:
				displayFleet(control.readCritical());
				break;

			default:
				System.out.println("Choix invalide.");
			}
		} catch (NumberFormatException e) {
			System.out.println("Entrée invalide : veuillez entrer un nombre.");
		}
	}

	private String inputOutput(String message) {
		System.out.print(message + " ");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			return br.readLine();
		} catch (IOException e) {
			System.out.println("Erreur de lecture.");
			return "";
		}
	}

	private Vehicle promptVehicle() {
		int id = Integer.parseInt(inputOutput("ID :"));
		VehicleCategory category = null;
		while (category == null) {
		    String input = inputOutput("Entrez la motorisation (THERM / ELEC) : ");
		    category = parseEnum(input, VehicleCategory.class, c -> c == VehicleCategory.THERM || c == VehicleCategory.ELEC, "Motorisation invalide. Utilisez THERM ou ELEC.");
		}
		
		VehicleKind kind = null;
		while (kind == null) {
		    String input = inputOutput("Entrez le type de véhicule (CAR / MOTO) : ");
		    kind = parseEnum(input, VehicleKind.class, k -> k == VehicleKind.CAR || k == VehicleKind.MOTO, "Type de véhicule invalide. Utilisez CAR ou MOTO.");
		}
		
		String model = inputOutput("Modèle :");
		int km = -1;
		while(km < 0) {
			try {
				km = Integer.parseInt(inputOutput("Kilométrage :"));
				if (km < 0) System.out.println("Le kilométrage doit être positif");
			} catch (NumberFormatException e) {
				System.out.println("Entrez un nombre valide pour le kilométrage");
			}
		}
		
		if (kind == VehicleKind.CAR) {
			return new Car(id, category, model, km);
		} else {
			return new Moto(id, category, model, km);
		}
	}
	
	private Vehicle promptVehicle(int id) {
		VehicleCategory category = null;
		while (category == null) {
		    String input = inputOutput("Entrez la motorisation (THERM / ELEC) : ");
		    category = parseEnum(input, VehicleCategory.class, c -> c == VehicleCategory.THERM || c == VehicleCategory.ELEC, "Motorisation invalide. Utilisez THERM ou ELEC.");
		}
		
		VehicleKind kind = null;
		while (kind == null) {
		    String input = inputOutput("Entrez le type de véhicule (CAR / MOTO) : ");
		    kind = parseEnum(input, VehicleKind.class, k -> k == VehicleKind.CAR || k == VehicleKind.MOTO, "Type de véhicule invalide. Utilisez CAR ou MOTO.");
		}
		
		String model = inputOutput("Modèle :");
		int km = -1;
		while(km < 0) {
			try {
				km = Integer.parseInt(inputOutput("Kilométrage :"));
				if (km < 0) System.out.println("Le kilométrage doit être positif");
			} catch (NumberFormatException e) {
				System.out.println("Entrez un nombre valide pour le kilométrage");
			}
		}
		
		if (kind == VehicleKind.CAR) {
			return new Car(id, category, model, km);
		} else {
			return new Moto(id, category, model, km);
		}
	}

	private void displayFleet(String[] rawFleet) {
		if (rawFleet == null || rawFleet.length == 0) {
			System.out.println("Aucune donnée.");
			return;
		}

		String[][] fleet = Arrays.stream(rawFleet)
				.map(line -> line.trim().split("\\s+"))
				.toArray(String[][]::new);

		System.out.printf(
		        "%-4s\t%-10s\t%-12s\t%-12s\t%-8s\t%-8s%n",
		        "ID", "CATÉGORIE", "TYPE", "MODÈLE", "KM", "CRITIQUE"
		    );


		for (int i = 0; i < fleet.length; i++) {
			String[] v = Arrays.copyOf(fleet[i], 7);
			for (int j = 0; j < 7; j++) {
				if (v[j] == null) v[j] = "";
			}

			System.out.printf(
					"%-4s\t%-10s\t%-12s\t%-12s\t%-8s\t%-8s\t%n",
					v[0], v[1], v[2], v[3], v[4], v[5]
			);
		}
	}
	
	private <T extends Enum<T>> T parseEnum(String input, Class<T> enumClass, Predicate<T> validator, String errorMessage) {
	    try {
	        T value = Enum.valueOf(enumClass, input.toUpperCase());
	        if (!validator.test(value)) {
	            System.out.println(errorMessage);
	            return null;
	        }
	        return value;
	    } catch (IllegalArgumentException e) {
	        System.out.println(errorMessage);
	        return null;
	    }
	}
}
