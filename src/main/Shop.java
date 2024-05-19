package main;
import model.Product;
import model.Sale;
import model.Client;
import model.Employee;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;

public class Shop {
	public double cash = 100.00;
//	private Product[] inventory;	
	private ArrayList<Product> inventory;
	private int numberProducts;
//	private Sale[] sales;
	private ArrayList<Sale> sales;
	
	final static double TAX_RATE = 1.04;

	public Shop() {
		cash = 0.0;

//		inventory = new Product[10];
//      this.sales = new Sale[10]; 
		inventory = new ArrayList<Product>();
		sales = new ArrayList<Sale>();
	}
	
	

	public static void main(String[] args) {
		Shop shop = new Shop();

		shop.loadInventory();

		Scanner scanner = new Scanner(System.in);
		int opcion = 0;
		boolean exit = false;
	
		shop.initSession();

		do {
			shop.printMenu();
			opcion = scanner.nextInt();

			switch (opcion) {
			case 1:
				shop.showCash();
				break;

			case 2:
				shop.addProduct();
				break;

			case 3:
				shop.addStock();
				break;

			case 4:
				shop.setExpired();
				break;

			case 5:
				shop.showInventory();
				break;

			case 6:
				shop.sale();	
				break;

			case 7:
				shop.showSales();
				break;
				
			case 8:
				shop.totalSales();
				break;
			
			case 9:
				shop.deleteProduct();
				break;

			case 10:
				exit = true;
				break;
			}

		} while (!exit);

	}

	/**
	 * load initial inventory to shop
	 */
	public void loadInventory() {
//		addProduct(new Product("Manzana", 10.00, true, 10));
//		addProduct(new Product("Pera", 20.00, true, 20));
//		addProduct(new Product("Hamburguesa", 30.00, true, 30));
//		addProduct(new Product("Fresa", 5.00, true, 20));
		
//		inventory.add(new Product("Manzana", 10.00, true, 10));
//		inventory.add(new Product("Pera", 20.00, true, 20));
//		inventory.add(new Product("Hamburguesa", 30.00, true, 30));
//		inventory.add(new Product("Fresa", 5.00, true, 20));	
		
		try {
			File defaultProducts = new File("C:\\Users\\santi\\eclipse-workspace\\UF3P1_ShopV2\\files\\inputInventory.txt");
		    if (defaultProducts.canRead()) {
			    	FileReader newFichero = new FileReader(defaultProducts);
			    	BufferedReader newFichero2 = new BufferedReader(newFichero);
		    	String linea = newFichero2.readLine();
		    	while (linea != null) {
					String[] parts = linea.split(";");
					
					if (parts.length == 3) {
						String productName = parts[0].split(":")[1].trim();
						Double wholesalerPrice = Double.parseDouble( parts[1].split(":")[1].trim());
						int stock = Integer.parseInt(parts[2].split(":")[1].trim());

						inventory.add(new Product(productName, wholesalerPrice, true, stock));
						
					} else {
						System.out.println("Invalid format");
					}
					
					linea = newFichero2.readLine();
				}
		    }
		} catch (Exception e) {
			System.out.println("Ha habido algun problema con el fichero");
		}
		
	}

	/**
	 * show current total cash
	 */
	private void showCash() {
		System.out.println("Dinero actual: " + cash);
	}

	/**
	 * add a new product to inventory getting data from console
	 */
	public void addProduct() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Nombre: ");
		String name = scanner.nextLine();
		System.out.print("Precio mayorista: ");
		double wholesalerPrice = scanner.nextDouble();
		System.out.print("Stock: ");
		int stock = scanner.nextInt();

		addProduct(new Product(name, wholesalerPrice, true, stock));
		

	}

	/**
	 * add stock for a specific product
	 */
	public void addStock() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Seleccione un nombre de producto: ");
		String name = scanner.next();
		Product product = findProduct(name);

		if (product != null) {
			// ask for stock
			System.out.print("Seleccione la cantidad a añadir: ");
			int stock = scanner.nextInt();
			// update stock product
			product.setStock(product.getStock() + stock);
			System.out.println("El stock del producto " + name + " ha sido actualizado a " + product.getStock());

		} else {
			System.out.println("No se ha encontrado el producto con nombre " + name);
		}
	}

	/**
	 * set a product as expired
	 */
	private void setExpired() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Seleccione un nombre de producto: ");
		String name = scanner.next();

		Product product = findProduct(name);

		if (product != null) {
			
			product.expire();
			
			System.out.println("El precio del producto " + name + " ha sido actualizado a " + product.getPublicPrice());
		}
	}

	/**
	 * show all inventory
	 */
	public void showInventory() {
		System.out.println("Contenido actual de la tienda:");
				
		for (int i = 0; i < inventory.size(); i++) {
			if (inventory.get(i) != null) {
				System.out.println(inventory.get(i));
			}
		}
		
		
	}

	/**
	 * make a sale of products to a client
	 */
	public void sale() {
				
		// ask for client name
		Scanner sc = new Scanner(System.in);
		System.out.println("Realizar venta, escribir nombre cliente");
		String clientName = sc.nextLine();
		Client client = new Client(clientName, 456, 50.00);

		

		// sale product until input name is not 0
		double totalAmount = 0.0;
		String name = "";
//		Product[] saleProducts = new Product[10];
		ArrayList<Product> saleProducts = new ArrayList<>();
		
		while (!name.equals("0")) {
			System.out.println("Introduce el nombre del producto, escribir 0 para terminar:");
			name = sc.nextLine();

			if (name.equals("0")) {
				break;
			}
			Product product = findProduct(name);
			boolean productAvailable = false;

			if (product != null && product.isAvailable()) {
				productAvailable = true;
				totalAmount += product.getPublicPrice();	
				product.setStock(product.getStock() - 1);
				if (product.getStock() == 0) {
					product.setAvailable(false);
				}
				
//				saleProducts[index] = product;
				saleProducts.add(product);
				
				System.out.println("Producto añadido con éxito");
			}

			if (!productAvailable) {
				System.out.println("Producto no encontrado o sin stock");
			}
		}

		// show cost total
		totalAmount = totalAmount * TAX_RATE;
		cash += totalAmount;
		System.out.println("Venta realizada con éxito, total: " + totalAmount);
	
//		Implementamos el metodo pay de la clase client
		
		boolean paymentStatus = client.pay(totalAmount);
		
		if (paymentStatus) {
			System.out.println("Pago realizado de forma correcta.");
		} else {
//			Con el metodo "Math.abs" pasamos el numero negativo a positivo para que nos muestre la cantidada a deber
			System.out.println("Cantidad a deber: " + Math.abs(client.getBalance()));
		}
		
//		Mostramos el saldo restante
		System.out.println("Saldo restante: " + client.getBalance());
		
		
//		sales[indexSale] = new Sale(client, saleProducts, totalAmount);
		
		LocalDateTime nowDate = LocalDateTime.now();
	    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	    String dateTime = nowDate.format(dateFormat);
	    System.out.println("Fecha y hora de la venta: " + dateTime);
		
		sales.add(new Sale(client, saleProducts, totalAmount, dateTime));

	}

	/**
	 * show all sales
	 */
	private void showSales() {
		boolean foundSales = false;
		Scanner sc = new Scanner(System.in);
		System.out.println("- Pulse 1 para ver las ventas"
				+ "\n- Pulse 2 para escribir todas las ventas en un fichero"
				+ "\n- Pulse 0 para salir");
		int selector = sc.nextInt();
		
			switch (selector) {
			case 1:
				System.out.println("Lista de ventas:");
//				for (Sale sale : sales) {
//					if (sale != null) {
//						System.out.println(sale);
//						foundSales = true;
//					} 
//				}
//				
				for (int i = 0; i < sales.size(); i++) {
					if (sales.get(i) != null) {
						System.out.println(sales.get(i));
						foundSales = true;

					}
				}
				
				if (!foundSales) {
					System.out.println("No se han encontrado ventas");
					break;
				}
				break;
			
			case 2: 
				 try {
					 if (sales.size() <= 0) {
						System.out.println("No se han encontrado ventas");
						break;
					 } else {
						
					
				      FileWriter myWriter = new FileWriter("C:\\Users\\santi\\eclipse-workspace\\UF3P1_ShopV2\\files\\sales_yyyy-mm-dd.txt");
				      for (int i = 0; i < sales.size(); i++) {
							if (sales.get(i) != null) {
								String sale = sales.get(i).toString(); // Convert to String if necessary
							    myWriter.write(sale);
							}
						}
				      myWriter.close();
				      System.out.println("Se han añadido las ventas correctamente en el fichero");
					}
					 } catch (IOException e) {
				      System.out.println("An error occurred.");
				      e.printStackTrace();
				    }
				
				break;
				
			case 0:
				break;
			}
		
	}
	
	private void totalSales() {
		boolean foundSales = false;
		double totalAmount = 0.0;

		
		for (Sale sale : sales) {
			if (sale != null) {
				totalAmount += sale.getAmount();
				foundSales = true;
			} 
		}
		
		if (!foundSales) {
			System.out.println("No se han encontrado ventas");

		}
		
		System.out.println("Total ventas: " + totalAmount);
	}
	
	private void deleteProduct() {
		
		Scanner sc = new Scanner(System.in);

		System.out.println("Introduce el nombre del producto, escribir 0 para terminar:");
		String name = sc.nextLine();

		Product product = findProduct(name);

		
		if (product != null) {
			int index = inventory.indexOf(product);
			inventory.remove(index);
			System.out.println("Producto eliminado correctamente");
		} else {
			System.out.println("El producto no existe en el Inventario");
		}
	}
	
//	Creamos el menu de login
	public void initSession() {
		
		Scanner inputKeyboard = new Scanner(System.in);
		boolean exit = false;
		
		do {
		
			System.out.println("================");
			System.out.println("INICIO DE SESIÓN");
			System.out.println("================");
			
			System.out.print("Introduzca numero de empleado:");
			int numEmployee = inputKeyboard.nextInt();
			System.out.println("Introduzca contraseña");
			String passwordEmployee = inputKeyboard.next();
			
			Employee employee = new Employee(1, numEmployee, passwordEmployee);
			boolean login = employee.login(numEmployee, passwordEmployee);
			
			if (login) {
				exit = true;
				System.out.println("Credenciales correctas");
			} else {
				System.out.println("Las credenciales no són correctas");
				exit = false;
			}
			
		} while (!exit);
			
		

	}
	
	
	public void printMenu() {
		System.out.println("\n");
		System.out.println("===========================");
		System.out.println("Menu principal miTienda.com");
		System.out.println("===========================");
		System.out.println("1) Contar caja");
		System.out.println("2) Añadir producto");
		System.out.println("3) Añadir stock");
		System.out.println("4) Marcar producto proxima caducidad");
		System.out.println("5) Ver inventario");
		System.out.println("6) Venta");
		System.out.println("7) Ver ventas");
		System.out.println("8) Ver total ventas");
		System.out.println("9) Eliminar producto");
		System.out.println("10) Salir programa");
		System.out.print("Seleccione una opción: ");
	}
	

	/**
	 * add a product to inventory
	 * 
	 * @param product
	 */
	public void addProduct(Product product) {
//		inventory[numberProducts] = product;
		inventory.add(product);
		numberProducts++;
	}

	/**
	 * find product by name
	 * 
	 * @param product name
	 */
	public Product findProduct(String name) {
//		for (int i = 0; i < inventory.length; i++) {
//			if (inventory[i] != null && inventory[i].getName().equalsIgnoreCase(name)) {
//				return inventory[i];
//			}
//		}
		
		for (int i = 0; i < inventory.size(); i++) {
			if (inventory.get(i) != null && inventory.get(i).getName().equalsIgnoreCase(name)) {
				return inventory.get(i);
			}
		}
		return null;
		

	}



	public double getCash() {
		return cash;
	}



	public void setCash(double cash) {
		this.cash = cash;
	}



	public ArrayList<Product> getInventory() {
		return inventory;
	}



	public void setInventory(ArrayList<Product> inventory) {
		this.inventory = inventory;
	}



	public int getNumberProducts() {
		return numberProducts;
	}



	public void setNumberProducts(int numberProducts) {
		this.numberProducts = numberProducts;
	}



	public ArrayList<Sale> getSales() {
		return sales;
	}



	public void setSales(ArrayList<Sale> sales) {
		this.sales = sales;
	}
	
	
}