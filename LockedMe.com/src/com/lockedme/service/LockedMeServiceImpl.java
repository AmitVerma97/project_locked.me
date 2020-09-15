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
		
		return dao.createFileLock(filelock);
	}

	@Override
	public void deleteFileLock(int id) throws FileLockException {
		dao.deleteFileLock(id);
		
	}

	@Override
	public FileLock getFile(int id) throws FileLockException {
	
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

}
