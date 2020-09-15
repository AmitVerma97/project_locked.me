package com.lockedme.service;

import java.util.List;

import com.lockedme.dao.LockerDAO;
import com.lockedme.dao.LockerDAOImpl;
import com.lockedme.exception.FileLockException;
import com.lockedme.model.FileLock;

public class LockedMeServiceImpl implements LockedMeService {
	
	private LockerDAO dao=new LockerDAOImpl();

	@Override
	public FileLock createFileLock(FileLock filelock) throws FileLockException {
		
		if(!isValidName(filelock.getName())) {
			throw new FileLockException("Entered name "+filelock.getName()+" is invalid");
		}
		return dao.createFileLock(filelock);
	}

	@Override
	public void deleteFileLock(int id) throws FileLockException {
		if(!isValidId(id)) {
			throw new FileLockException("ID -> "+id+" is invalid...Id must be an integer and greater than 1000");
		}
		dao.deleteFileLock(id);
		
	}

	@Override
	public FileLock getFile(int id) throws FileLockException {
		if(!isValidId(id)) {
			throw new FileLockException("ID -> "+id+" is invalid...Id must be an integer and greater than 1000");
		}
		return dao.getFile(id);
	}

	@Override
	public List<String> showMyFiles() throws FileLockException {
		
		return dao.showMyFiles();
	}

	@Override
	public void exit() throws FileLockException {
		
		dao.exit();
		
	}
	
	private boolean isValidName(String name) {
		boolean val=false;
		if(name.trim().matches("[a-zA-Z _.]{1,15}")) {
			val=true;
		}
		return val;
	}
	
	private boolean isValidId(int id) {
		boolean val=false;
		if(id > 1000) {
			val=true;
		}
		return val;
	}

}
