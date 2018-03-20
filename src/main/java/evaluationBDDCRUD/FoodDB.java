package evaluationBDDCRUD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class FoodDB {
	// add one food with properties to table "t_food"
	public static void addFood(Connection conn, Food food) throws SQLException {
		String insertCmd = "INSERT INTO T_food (namefood, fk_typefood_id, energeticvalue, proteinlevel, glucidlevel, lipidlevel) "
				+ "VALUES (?, ?, ?, ?, ?, ?);";
		PreparedStatement insertCmdStatement = conn.prepareStatement(insertCmd);
		insertCmdStatement.setString(1, food.getNameFood());
		insertCmdStatement.setLong(2, food.getTypeFoodId());
		insertCmdStatement.setFloat(3, food.getEnergeticValue());
		insertCmdStatement.setFloat(4, food.getProteinLevel());
		insertCmdStatement.setFloat(5, food.getGlucidLevel());
		insertCmdStatement.setFloat(6, food.getLipidLevel());
		insertCmdStatement.execute();
		System.out.println("Votre aliment a bien été ajouté.");
	}

	// delete one food to table t_food
	public static void deleteFood(Connection conn) throws SQLException {
		System.out.println("Veuillez saisir le nom de l'aliment à supprimer.");
		Scanner input = new Scanner(System.in);
		String nameFood = input.nextLine();
		String deleteCmd = "DELETE FROM T_food WHERE namefood= ?;";
		PreparedStatement deleteCmdStatement = conn.prepareStatement(deleteCmd);
		deleteCmdStatement.setString(1, nameFood);
		try {
			int res = deleteCmdStatement.executeUpdate();
			if ((res == 0) || (res == 1)) {
				System.out.println(res + " ligne mise à jour.");
			} else {
				System.out.println(res + " lignes mises à jour.");
			}
		} catch (SQLException e) {
			System.out.println("Impossible d'executer votre demande, vérifier votre connexion.");
		}
	}

	// print one or several foods with search by name or id of typeFood
	public static void printFood(Connection conn) throws SQLException {
		System.out.println("Quel est votre critère de recherche:");
		System.out.println("taper 1 pour rechercher par nom d'aliment.");
		System.out.println("taper 2 pour rechercher par type.");
		String indicatorFood = "";
		String selectCmd = null;
		ResultSet resultSelect = null;
		PreparedStatement selectCmdStatement = null;
		ArrayList<Food> displaySelect = new ArrayList<Food>();
		Scanner sc = new Scanner(System.in);
		String choice = sc.nextLine();
		switch (choice) {
		case "1":
			indicatorFood = "namefood";
			System.out.println("Veuillez saisir le nom de l'aliment recherché.");
			String filterName = sc.nextLine();
			selectCmd = "SELECT * FROM T_food WHERE " + indicatorFood + "= ?;";
			selectCmdStatement = conn.prepareStatement(selectCmd);
			selectCmdStatement.setString(1, filterName);

			break;

		case "2":
			indicatorFood = "fk_typefood_id";
			System.out.println("Veuillez saisir le code type d'aliment recherché.");
			Long filterId = sc.nextLong();
			selectCmd = "SELECT * FROM T_food WHERE " + indicatorFood + "= ?;";
			selectCmdStatement = conn.prepareStatement(selectCmd);
			selectCmdStatement.setLong(1, filterId);
			break;

		default:
			System.out.println("Ce choix n est pas proposé!");
			break;
		}
		resultSelect = selectCmdStatement.executeQuery();
		if (resultSelect != null) {
			while (resultSelect.next()) {
				displaySelect.add(new Food(resultSelect.getLong("id_food"), resultSelect.getString("nameFood"),
						resultSelect.getLong("fk_typefood_id"), resultSelect.getFloat("energeticvalue"),
						resultSelect.getFloat("proteinlevel"), resultSelect.getFloat("glucidlevel"),
						resultSelect.getFloat("lipidlevel")));
			}
			if (displaySelect.size() != 0) {
				System.out.println(displaySelect);
			} else {
				System.out.println("Désolé, votre recherche n'a pas de résultat.");
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection conn = null;
		// connection à la base avec les données URl, user et mdp passés en arguments du
		// main
		try {
			conn = DriverManager.getConnection(args[0], args[1], args[2]);
		} catch (SQLException e) {
			System.out.println("Erreur de connexion");
			e.printStackTrace();
		}

		Scanner sc = new Scanner(System.in);
		System.out.println("Bienvenue dans Keskejemange!");
		String menuChoice = "";
		do {
			System.out.println("Menu");
			System.out.println("1.Ajouter un aliment dans la base de donnée.");
			System.out.println("2.Supprimer un aliment de la base de donnée.");
			System.out.println("3.Rechercher un ou des aliment(s).");
			System.out.println("0.Quitter l'application.");
			System.out.println("Saisissez le numéro de votre choix");
			try {
				menuChoice = sc.nextLine();
				switch (menuChoice) {
				case "1":
					Food foodInput = new Food();
					foodInput = foodInput.defineFood();
					addFood(conn, foodInput);
					break;

				case "2":
					deleteFood(conn);
					break;

				case "3":
					printFood(conn);
					break;

				case "0":
					System.out.println("Aurevoir!");
					break;

				default:
					System.out.println("Choix non valide, merci de saisir 0, 1, 2 ou 3!");
				}
			} catch (InputMismatchException e) {
				System.out.println("Saisie incohérente. Recommencez");

			} catch (SQLException e) {
				System.out.println("une erreur s est produite! Recommencez");
				e.printStackTrace();
			}
		} while (!menuChoice.equals("0"));

		sc.close();

	}
}
