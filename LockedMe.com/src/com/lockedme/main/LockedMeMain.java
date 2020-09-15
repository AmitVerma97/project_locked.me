package com.lockedme.main;

import java.util.List;
import java.util.Scanner;

import com.lockedme.exception.FileLockException;
import com.lockedme.model.FileLock;
import com.lockedme.service.LockedMeService;
import com.lockedme.service.LockedMeServiceImpl;

public class LockedMeMain {

	public static void main(String[] args) {
		System.out.println("\n    <Welcome to LockedMe.com>");
		System.out.println("     *************************");
		System.out.println("    -developed by Amit Verma");
		System.out.println("    -a trainee at Simplilearn");
		System.out.println("           ________________");
		System.out.println("\n    LockedMe.com helps you manage ");
		System.out.println("   your files efficiently and safely");
		System.out.println("           ________________");
		System.out.println("\nPlease select appropriate option(1-3)");
		System.out.println("from the menu below to get started");
		System.out.println("\n**************************************");
		Scanner input = new Scanner(System.in);
		int choice=0;
		
		LockedMeService service = new LockedMeServiceImpl();

			do{
				System.out.println("\nLocker Menu");
				System.out.println("..........................");
				System.out.println("1) Show all my files");
				System.out.println("2) Add/Delete/Search a file");
				System.out.println("3) Exit LockedMe.com");
				System.out.println("...........................");
				
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
						System.out.println("Please select from following operations(1-4)");
						System.out.println("----------------------------------------");
						System.out.println("1) Add a file to my Locker");
						System.out.println("2) Delete a file from my Locker");
						System.out.println("3) Search a file in my Locker");
						System.out.println("4) Return to main menu");
						System.out.println("----------------------------------------");
						try {
							ch=Integer.parseInt(input.nextLine());
						}catch(NumberFormatException e) {
							
						}
						switch(ch) {
						case 1 :
							System.out.println("Please enter name of the file below");
							System.out.println("-its length should not exceed 40");
							System.out.println("-only alphabets,underscores,dots and spaces are allowed");
							FileLock f=new FileLock();
							System.out.println("\nEnter file name :");
							f.setName(input.nextLine());
							
							try {
								f=service.createFileLock(f);
							}catch(FileLockException e1) {
								System.out.println(e1.getMessage());
							}
							ch=0;
							break;
						case 2 : 
							System.out.println("Please enter id of the file to be deleted :");
							
							try {
								int id=Integer.parseInt(input.nextLine());
								service.deleteFileLock(id);
		
							}catch(Exception e2) {
								System.out.println("Entered File Id doesnot exist...Please enter correct id..");
								
							}
							ch=0;
							break;
							
						case 3:
							System.out.println("Please enter file id of file you are looking for : ");
							
							try {
								int id =Integer.parseInt(input.nextLine());
								FileLock fl=service.getFile(id);
								System.out.println("Here is the file you are looking for ");
								System.out.println(fl.getName());
							}catch(Exception e3) {
								System.out.println("Entered File Id doesnot exist...Please enter correct id..");
							
							}
							ch=0;
							break;
						case 4:
							break;
							
						default:
							System.out.println("Invalid choice...Please choose from 1,2,3 or 4");
							
						}
					} while(ch!=4);
					break;
	
				case 3: 
					try {
						service.exit();
					} catch (FileLockException e) {
						e.printStackTrace();
					}
					System.out.println("Exitted.. ");
					System.out.println("Thanks for visiting LockerMe.com...See you again..");
					break;
					
				default : 
					System.out.println("Invalid choice...Please choose from 1,2 or 3");
					
				}

				
			}while(choice!=3);
			
			input.close();
	}
	

}