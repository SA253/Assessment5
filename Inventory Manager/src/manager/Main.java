package manager;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.List;
import manager.Main.NameException;

public class Main {
	ManagerDao mdao = new ManagerDao();
	ArrayList<Manager> managerList;
	
	public static void main(String args[]) {
		Main m = new Main();
		/*m.managerList = new ArrayList<>();
		
		Manager s1 = new Manager("1","biscuits","rich in carbs","20","2");
		m.managerList.add(s1);

		Manager s2 = new Manager("2","chacos","rich in fat","10","4");
		m.managerList.add(s2);

		Manager s3 = new Manager("3","lollipop","rich in sugar","30","3");
		m.managerList.add(s3);

		Manager s4 = new Manager("4","lays","rich in protiens","25","1");
		m.managerList.add(s4);

		Manager s5 = new Manager("5","chips","rich in carbohydrates","40","5");
		m.managerList.add(s5);*/
		
		
		
		int f=1;
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("MENU");
			System.out.println("1. Add ");
			System.out.println("2. View ");
			System.out.println("3. Update ");
			System.out.println("4. Delete");
			System.out.println("0. Exit");
			System.out.println("Enter a option:");
			int opt=Integer.parseInt(sc.nextLine());
			switch(opt) {
			case 1:
				m.add();
				break;
			case 2:
				m.view();
				break;
			case 3:
				
				break;
			case 4:
				
				
				break;
			case 0:
				f=0;
				break;
			}	
			
			
		}while(f==1);
			
			
		
	}//main method ends
	class NameException extends Exception {
		public NameException(String message) {
			super(message);
		}
	}
	
	public boolean validatename(String name) {
		Pattern p = Pattern.compile("^[a-zA-Z][a-zA-Z\\s]+$");
		try {
		if(p.matcher(name).matches()) {return false;}
		if (!(p.matcher(name).matches())) {
			throw new NameException("Name Should not contain numbers or special characters or be null");
		}
		else {return false;}
		}
		catch(NameException e){
			System.err.println(e.getMessage());
			return true;
		}
	}
	
	
	
	public void add() {
		Scanner sc = new Scanner(System.in);
		System.out.printf("%70s\n","ENTER DETAILS OF PRODUCT");
		
		
		
        System.out.println("Enter Product Name : ");
			String name=sc.nextLine();
			while(this.validatename(name)) {
				System.out.println("Please enter valid product name : ");
				name=sc.nextLine();
			}
			System.out.println("Enter Description : ");
			String desp = sc.nextLine();
			
			System.out.println("Enter Price : ");
			String price = sc.nextLine();
			
			System.out.println("Enter Quantity : ");
			String quant = sc.nextLine();
			
			
			Manager st = new Manager(name,desp,price,quant);
			this.managerList.add(st);
			mdao.create(st);
			
		}
		

		
	
	
	public void update() {
		int flag=1;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Item Code to Update");
		int i =Integer.parseInt(sc.nextLine());
		for(Manager s:managerList) {
			if(s.getId()==(i)) {
				System.out.println("Enter the new Product Name: ");
				String name = sc.nextLine();
				while(validatename(name)) {
					System.out.println("Please enter valid product name : ");
					name=sc.nextLine();
				}
				
				System.out.println("Enter the new description :");
				String d = sc.nextLine();
				
				System.out.println("Enter Price : ");
				String price =sc.nextLine();
				
				System.out.println("Enter the new Quantity :");
				String q = sc.nextLine();
				
				
				
				flag=0;
				s.setName(name);
				s.setDescription(d);
				s.setQuantity(q);
				s.setPrice(price);
				System.out.println("Product has been Updated Successfully");
				
				
			}
			
			
		}
		if(flag==1) {
			System.out.println("No item matches the entered Item Code");
		}
		
	}
	
	public void view() {
		
		List<Manager> man = mdao.findAll();
		System.out.println("******************* PRODUCT DETAILS *****************************\n");
		System.out.format("%10s %10s %10s %10s %10s\n", "CODE","NAME","DESCRIPTION","PRICE","QUANTITY");
		for (Manager n : man) {
			System.out.format("%10s %10s %10s %10s %10s",n.getId(),n.getName(),n.getDescription(),n.getPrice(),n.getQuantity());
			System.out.println();

	}
		System.out.println();
		System.out.println("**************************************************************");
	}

	
	public void Delete(int id) {
		Manager temp = new Manager();
		for (Manager s : this.managerList) {
			if (s.getId() == id) {
				temp = s;
			}
		}
		this.managerList.remove(temp);
		
	}
	
}
