package com.lockedme.service;

import java.util.List;

import com.lockedme.exception.FileLockException;
import com.lockedme.model.FileLock;

public interface LockedMeService {
	
	public FileLock createFileLock(FileLock filelock) throws FileLockException;
	public void deleteFileLock(int id) throws FileLockException;
	public FileLock getFile(int id)throws FileLockException;
	public List<String> showMyFiles()throws FileLockException;
	public void exit() throws FileLockException;

}
