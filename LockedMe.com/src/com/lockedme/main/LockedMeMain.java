package com.lockedme.main;

import java.util.List;
import java.util.Scanner;

import com.lockedme.exception.FileLockException;
import com.lockedme.model.FileLock;
import com.lockedme.service.LockedMeService;
import com.lockedme.service.LockedMeServiceImpl;

public class LockedMeMain {

	public static void main(String[] args) {
		System.out.println("<Welcome to LockedMe.com>");
		System.out.println("*************************");
		System.out.println("developed by Amit Verma");
		System.out.println("\nLockedMe.com helps you manage ");
		System.out.println("your files efficiently and safely");
		System.out.println("\nPlease select appropriate option");
		System.out.println("from the menu below to get started");
		Scanner input = new Scanner(System.in);
		int choice=0;
		
		LockedMeService service = new LockedMeServiceImpl();

			while(choice!=3){
				System.out.println("\nLocker Menu");
				System.out.println("==============");
				System.out.println("1)Show all my files");
				System.out.println("2)Add/Delete/Search a file");
				System.out.println("3)Exit");
				
				try {
					choice=Integer.parseInt(input.nextLine());
				}
				catch(NumberFormatException e) {
					
				}
				switch (choice) {
				case 1:
					try {
						List<String> fList=service.showMyFiles();
						if(fList!=null && fList.size()>0) {
							System.out.println("Here are your "+fList.size()+" file/files sorted alphabetically");
							for(String f:fList) {
								System.out.println(f);
							}
							
						}
					}catch(FileLockException e){
						System.out.println(e.getMessage());
					}
					break;
				case 2 :
					int ch=0;
					//Scanner input2 = new Scanner(System.in);
					
					do {
						System.out.println("Please select from following operations");
						System.out.println("----------------------------------------");
						System.out.println("1) Add a file to my Locker");
						System.out.println("2) Delete a file from my Locker");
						System.out.println("3) Search a file in my Locker");
						System.out.println("4) Return to main menu");
						try {
							ch=Integer.parseInt(input.nextLine());
						}catch(NumberFormatException e) {
							
						}
						switch(ch) {
						case 1 :
							System.out.println("Please enter details below");
							FileLock f=new FileLock();
							System.out.println("Enter file name");
							f.setName(input.nextLine());
							
							try {
								f=service.createFileLock(f);
								System.out.println(f.getName()+" with id= "+f.getId()+" added in your Locker successfully....:-)" );
							}catch(FileLockException e1) {
								System.out.println(e1.getMessage());
							}
							break;
						case 2 : 
							System.out.println("Please enter id of the file to be deleted");
							int id=Integer.parseInt(input.nextLine());
							try {
								service.deleteFileLock(id);
								System.out.println("file with id = "+id+" deleted successfully");
								System.out.println("");
							}catch(FileLockException e2) {
								System.out.println(e2.getMessage());
							}
							
							break;
							
						case 3:
							System.out.println("Please enter file id of file you are looking for..");
							id =Integer.parseInt(input.nextLine());
							try {
								FileLock fl=service.getFile(id);
								System.out.println("Here is the file you are looking for ");
								System.out.println(fl.getName());
							}catch(FileLockException e3) {
								System.out.println(e3.getMessage());
							}
							break;
						case 4:
							break;
							
						default:
							System.out.println("Invalid choice...Please choose from 1,2,3 or 4");
							
						}
					} while(ch!=4);
					break;
	
				case 3: 
					System.out.println("Exitted.. Thanks for visiting LockerMe.com...See you again..");
					break;
					
				default : 
					System.out.println("Invalid choice...Please choose from 1,2 or 3");
					
				}

				
			}
			
			input.close();
	}
	

}
