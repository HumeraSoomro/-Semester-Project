package oopProject;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Restaurant {
	private String name;
    protected ArrayList<String> menuItems;
    protected ArrayList<Double> menuPrices;
	private String name2;
	private String phoneNumber;
	private float rating;
	private String comments;
	public void setName(String name) {
		this.name=name;
	}
    public String getname()
	{
		return getName();
	}
    public Restaurant(String name) {
        this.setName(name);
        this.menuItems = new ArrayList<>();
        this.menuPrices = new ArrayList<>();
    }
    public double calculateBill(String item) {
        double total = 0;
        String lowerCaseItem = item.toLowerCase();
        for (int i = 0; i < menuItems.size(); i++) {
            if (menuItems.get(i).toLowerCase().equals(lowerCaseItem)) {
                total += menuPrices.get(i);
                break;
            }
        }
        return total;
    }
    public void viewMenu() {};
    public void placeOrder(String item, Scanner scanner) {};
    
    public void provideFeedback(Scanner scanner) {};
    public void addToMenu(String item, double price) {
        menuItems.add(item);
        menuPrices.add(price);
    }
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PizzaHut pizzaHut = new PizzaHut();
        Ridan ridan = new Ridan();
        TheSicilian theSicilian = new TheSicilian();
        System.out.println("\n\t\t\t************************************************");
           System.out.println("\t\t\t\tWelcome to our restaurant service!");
           System.out.println("\t\t\t************************************************");
           while (true) {
               System.out.println("\n\t\t\tPlease select a restaurant:");
               System.out.println("\t\t\t1. Pizza Hut");
               System.out.println("\t\t\t2. Ridan");
               System.out.println("\t\t\t3. The Sicilian");
               System.out.println("\t\t\t4. Exit");
               System.out.print("\n\t\t\tChoose an option from above (1-4): ");
               int restaurantChoice = scanner.nextInt();
               switch (restaurantChoice) {
                   case 1:
                       processRestaurant(pizzaHut, scanner);
                       break;
                   case 2:
                       processRestaurant(ridan, scanner);
                       break;
                   case 3:
                       processRestaurant(theSicilian, scanner);
                       break;
                   case 4:System.out.println("\n\t\t\t\tThank you for visiting our restaurant service!");
                   scanner.close();
                   System.exit(0);
                	   
                   default:
                       System.out.println("\t\t\tInvalid choice! Please try again.");
               }
           }
       }

       private static void processRestaurant(Restaurant restaurant, Scanner scanner) {
           while (true) {
               System.out.println("\n\t\t\t************************************************");
               System.out.println("\t\t\t\tWelcome to " + restaurant.getname() + "!");
               System.out.println("\t\t\t************************************************");
               System.out.println("\n\t\t\t1. View Menu");
               System.out.println("\t\t\t2. Place Order");
               System.out.println("\t\t\t3. Give Feedback");
               System.out.println("\t\t\t4. Back to Main Page");
               System.out.print("\n\t\t\tChoose an option from above (1-4): ");
               int choice = scanner.nextInt();
               switch (choice) {
                   case 1:
                       restaurant.viewMenu();
                       break;
                   case 2:
                       scanner.nextLine();
                       System.out.println("\n\t\t\tEnter the item you want to order:");
                       String item = scanner.nextLine();
                       restaurant.placeOrder(item, scanner);
                       break;
                   case 3:
                       scanner.nextLine();
                       restaurant.provideFeedback(scanner);
                       break;
                   case 4:
                       return;
                   default:
                       System.out.println("\t\t\tInvalid choice! Please try again.");
               }
           }
       }
	public String getName2() {
		return name2;
	}
	public void setName2(String name2) {
		this.name2 = name2;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public float getRating() {
		return rating;
	}
	public void setRating(float f) {
		this.rating = f;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getName() {
		return name;
	}
}
class PizzaHut extends Restaurant {
    public PizzaHut() {
        super("Pizza Hut");
        System.out.print("\t\t\t");
        addToMenu("Pepperoni Pizza", 10);
        addToMenu("Margherita Pizza", 8);
        addToMenu("Garlic Bread", 5);
    }
    public void viewMenu() {
        System.out.println("\t\t\tMenu of " + getname() + ":");
        for (int i = 0; i < menuItems.size(); i++) {
            System.out.println("\t\t\t"+menuItems.get(i) + ": $" + menuPrices.get(i));
        }
    }
    public void placeOrder(String item, Scanner scanner) {
        String lowerCaseItem = item.toLowerCase();
        int index = -1;
        for (int i = 0; i < menuItems.size(); i++) {
            if (menuItems.get(i).toLowerCase().equals(lowerCaseItem)) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            System.out.println("\t\t\tYou are placing an order for " + menuItems.get(index) + " at " + getName() + ".Enter the quantity.");
           
            int quantity = scanner.nextInt();
            if (quantity <= 0) {
                System.out.println("\t\t\tInvalid quantity. Order cancelled.");
                return;
            }
            double total = calculateBill(item) * quantity;
            System.out.println("\t\t\tTotal bill for " + quantity + " " + menuItems.get(index) + "(s): $" + total);
            System.out.println("\t\t\tConfirm order? (y/n)");
            while (true) {
                String confirmation = scanner.nextLine().trim(); 
                if (!confirmation.isEmpty()) { 
                    char choice = confirmation.charAt(0); 
                    if (choice == 'y' || choice == 'Y') {
                        System.out.println("\t\t\tPlacing order for " + menuItems.get(index) + " at " + getName() + ". Enjoy your meal!");
                        break;
                    } else if (choice == 'n' || choice == 'N') {
                        System.out.println("\t\t\tOrder cancelled.");
                        break;
                    } 
                } else {
                    System.out.println(" \t\t\tPlease enter 'y' for yes or 'n' for no.");
                }
            }
        } else {
            System.out.println("\t\t\tSorry, " + item + " is not available on the menu of " + getName() + ".");
        }
    }
    public void provideFeedback(Scanner scanner) {
        System.out.print("\t\t\tEnter your name: ");
        setName2(scanner.nextLine());
        System.out.print("\t\t\tEnter your phone number: ");
        setPhoneNumber(scanner.nextLine());
        System.out.print("\t\t\tEnter your rating (1-5): ");
        setRating(getValidRating(scanner));
        scanner.nextLine();
        System.out.print("\t\t\tEnter your comments: ");
        setComments(scanner.nextLine());
        System.out.println("\t\t\tThank you for your feedback!");
        
        try (FileOutputStream outputStream=new FileOutputStream("feedback.txt", true)) {
      String feedback="Name: "+getName2()+", Phone: "+getPhoneNumber()+", Rating: "+getRating()+", Comments: "+getComments()+"\n";
            outputStream.write(feedback.getBytes());
            System.out.println("\t\t\tFeedback stored successfully!");
          } catch (IOException e) {
            System.out.println("\t\t\tError storing feedback: " + e.getMessage());
          }
        }
    
    
    private float getValidRating(Scanner scanner) {
        float rating;
        while (true) {
            while (!scanner.hasNextInt()) {
                System.out.println("\t\t\tInvalid input. Please enter a number.");
                scanner.next();
            }
            rating = scanner.nextInt();
            if (rating >= 1 && rating <= 5) {
                break;
            }
            System.out.println("\t\t\tRating must be between 1 and 5. Please try again.");
        }
        return rating;
    }
    
}
class Ridan extends Restaurant {
	
    public Ridan() {
        super("Ridan");
        addToMenu("Hummus", 6);
        addToMenu("Falafel Wrap", 8);
        addToMenu("Chicken Madbee", 7);
       
    }  
    public void viewMenu() {
        System.out.println("\t\t\tMenu of " + getname() + ":");
        for (int i = 0; i < menuItems.size(); i++) {
            System.out.println("\t\t\t"+menuItems.get(i) + ": $" + menuPrices.get(i));
        }
    }
    public void placeOrder(String item, Scanner scanner) {
        String lowerCaseItem = item.toLowerCase();
        int index = -1;
        for (int i = 0; i < menuItems.size(); i++) {
            if (menuItems.get(i).toLowerCase().equals(lowerCaseItem)) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            System.out.println("\t\t\tYou are placing an order for " + menuItems.get(index) + " at " + getName() + ".Enter the quantity.");
           
            int quantity = scanner.nextInt();
            if (quantity <= 0) {
                System.out.println("\t\t\tInvalid quantity. Order cancelled.");
                return;
            }
            double total = calculateBill(item) * quantity;
            System.out.println("\t\t\tTotal bill for " + quantity + " " + menuItems.get(index) + "(s): $" + total);
            System.out.println("\t\t\tConfirm order? (y/n)");
            while (true) {
                String confirmation = scanner.nextLine().trim(); 
                if (!confirmation.isEmpty()) { 
                    char choice = confirmation.charAt(0); 
                    if (choice == 'y' || choice == 'Y') {
                        System.out.println("\t\t\tPlacing order for " + menuItems.get(index) + " at " + getName() + ". Enjoy your meal!");
                        break;
                    } else if (choice == 'n' || choice == 'N') {
                        System.out.println("\t\t\tOrder cancelled.");
                        break;
                    } 
                } else {
                    System.out.println(" \t\t\tPlease enter 'y' for yes or 'n' for no.");
                }
            }
        } else {
            System.out.println("\t\t\tSorry, " + item + " is not available on the menu of " + getName() + ".");
        }
    }
    public void provideFeedback(Scanner scanner) {
        System.out.print("\t\t\tEnter your name: ");
        setName2(scanner.nextLine());
        System.out.print("\t\t\tEnter your phone number: ");
        setPhoneNumber(scanner.nextLine());
        System.out.print("\t\t\tEnter your rating (1-5): ");
        setRating(getValidRating(scanner));
        scanner.nextLine();
        System.out.print("\t\t\tEnter your comments: ");
        setComments(scanner.nextLine());
        System.out.println("\t\t\tThank you for your feedback!");
        
        try (FileOutputStream outputStream=new FileOutputStream("feedback.txt", true)) {
      String feedback="Name: "+getName2()+", Phone: "+getPhoneNumber()+", Rating: "+getRating()+", Comments: "+getComments()+"\n";
            outputStream.write(feedback.getBytes());
            System.out.println("\t\t\tFeedback stored successfully!");
          } catch (IOException e) {
            System.out.println("\t\t\tError storing feedback: " + e.getMessage());
          }
        }
    
    
    private float getValidRating(Scanner scanner) {
        float rating;
        while (true) {
            while (!scanner.hasNextInt()) {
                System.out.println("\t\t\tInvalid input. Please enter a number.");
                scanner.next();
            }
            rating = scanner.nextInt();
            if (rating >= 1 && rating <= 5) {
                break;
            }
            System.out.println("\t\t\tRating must be between 1 and 5. Please try again.");
        }
        return rating;
    }
    
}
class TheSicilian extends Restaurant {
    public TheSicilian() {
        super("The Sicilian");
        addToMenu("Spaghetti Carbonara", 12);
        addToMenu("Lasagna", 15);
        addToMenu("Russian Salad", 6);
    }
    public void viewMenu() {
        System.out.println("\t\t\tMenu of " + getname() + ":");
        for (int i = 0; i < menuItems.size(); i++) {
            System.out.println("\t\t\t"+menuItems.get(i) + ": $" + menuPrices.get(i));
        }
    }
    public void placeOrder(String item, Scanner scanner) {
        String lowerCaseItem = item.toLowerCase();
        int index = -1;
        for (int i = 0; i < menuItems.size(); i++) {
            if (menuItems.get(i).toLowerCase().equals(lowerCaseItem)) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            System.out.println("\t\t\tYou are placing an order for " + menuItems.get(index) + " at " + getName() + ".Enter the quantity.");
           
            int quantity = scanner.nextInt();
            if (quantity <= 0) {
                System.out.println("\t\t\tInvalid quantity. Order cancelled.");
                return;
            }
            double total = calculateBill(item) * quantity;
            System.out.println("\t\t\tTotal bill for " + quantity + " " + menuItems.get(index) + "(s): $" + total);
            System.out.println("\t\t\tConfirm order? (y/n)");
            while (true) {
                String confirmation = scanner.nextLine().trim(); 
                if (!confirmation.isEmpty()) { 
                    char choice = confirmation.charAt(0); 
                    if (choice == 'y' || choice == 'Y') {
                        System.out.println("\t\t\tPlacing order for " + menuItems.get(index) + " at " + getName() + ". Enjoy your meal!");
                        break;
                    } else if (choice == 'n' || choice == 'N') {
                        System.out.println("\t\t\tOrder cancelled.");
                        break;
                    } 
                } else {
                    System.out.println(" \t\t\tPlease enter 'y' for yes or 'n' for no.");
                }
            }
        } else {
            System.out.println("\t\t\tSorry, " + item + " is not available on the menu of " + getName() + ".");
        }
    }
    public void provideFeedback(Scanner scanner) {
        System.out.print("\t\t\tEnter your name: ");
        setName2(scanner.nextLine());
        System.out.print("\t\t\tEnter your phone number: ");
        setPhoneNumber(scanner.nextLine());
        System.out.print("\t\t\tEnter your rating (1-5): ");
        setRating(getValidRating(scanner));
        scanner.nextLine();
        System.out.print("\t\t\tEnter your comments: ");
        setComments(scanner.nextLine());
        System.out.println("\t\t\tThank you for your feedback!");
        
        try (FileOutputStream outputStream=new FileOutputStream("feedback.txt", true)) {
      String feedback="Name: "+getName2()+", Phone: "+getPhoneNumber()+", Rating: "+getRating()+", Comments: "+getComments()+"\n";
            outputStream.write(feedback.getBytes());
            System.out.println("\t\t\tFeedback stored successfully!");
          } catch (IOException e) {
            System.out.println("\t\t\tError storing feedback: " + e.getMessage());
          }
        }
    
    
    private float getValidRating(Scanner scanner) {
        float rating;
        while (true) {
            while (!scanner.hasNextInt()) {
                System.out.println("\t\t\tInvalid input. Please enter a number.");
                scanner.next();
            }
            rating = scanner.nextInt();
            if (rating >= 1 && rating <= 5) {
                break;
            }
            System.out.println("\t\t\tRating must be between 1 and 5. Please try again.");
        }
        return rating;
    }
    
}



	
