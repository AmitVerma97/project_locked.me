package com.lockedme.dao;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.lockedme.exception.FileLockException;
import com.lockedme.model.FileLock;

public class LockerDAOImpl implements LockerDAO {
	
	private static Map<Integer, FileLock> fileLockMap = new HashMap<>();
	private static int c=1000;
	@Override
	public FileLock createFileLock(FileLock fileLock) throws FileLockException {
		String fname=fileLock.getName();
		File file = new File(fname);
		boolean success;  
		try   
		{  
		success = file.createNewFile(); 
		if(success)     
		{  
			fileLock.setId(++c);
			fileLockMap.put(fileLock.getId(),fileLock);
		System.out.println("file created "+file.getCanonicalPath()); 
		System.out.println("file with name - "+fileLock.getName()+", and id= "+fileLock.getId()+" added in your Locker successfully....:-)" );
		}  
		else  
		{  
		System.out.println("File already exist at location: "+file.getCanonicalPath());  
		}  
		}   
		catch (IOException e)   
		{  
		e.printStackTrace();   
		}
		
		return fileLock;
	}

	@Override
	public void deleteFileLock(int id) throws FileLockException {
		
		if (fileLockMap.containsKey(id)) {
			
			String fname=fileLockMap.get(id).getName();
			File f= new File(fname);          
			if(f.delete())                     
			{  
			System.out.println("file named "+f.getName() + " deleted");
			System.out.println("file with id = "+id+" deleted successfully");
			}  
			else  
			{  
			System.out.println("failed");  
			}  
			 
			  
			fileLockMap.remove(id);
		}
		else {
			throw new FileLockException("Entered File Id doesnot exist...Please enter correct id..");
		}
		
	}

	@Override
	public FileLock getFile(int id) throws FileLockException {
		
		if (fileLockMap.containsKey(id)) {
			return fileLockMap.get(id);
		}
		else {
			throw new FileLockException("Entered Id doesnot exist");
		}
	}

	@Override
	public List<String> showMyFiles() throws FileLockException {
		if (fileLockMap.size()==0) {
			throw new FileLockException("Your file locker is empty.......PLease add some files first..");
		}
		else {
		List<FileLock> fileList = fileLockMap.values().stream().collect(Collectors.toList());
		List<String> nameList = new ArrayList<String>();
		for (FileLock f:fileList) {
			nameList.add(f.getName());
		}
		return nameList.stream().sorted().collect(Collectors.toList());
		}
	}

	@Override
	public void exit() throws FileLockException {
		if (fileLockMap.size()==0) {
		}
		else {
			int i=1000+fileLockMap.size();
			while(i>1000) {
				try  
				{         
				File f= new File(fileLockMap.get(i).getName());    
				f.delete();
				i--;
				//System.out.println("success deletion");
				}  
				catch(Exception e)  
				{  
				e.printStackTrace();  
				}  
			}
		}
		
	}

}
