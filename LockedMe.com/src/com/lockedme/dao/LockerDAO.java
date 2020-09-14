package com.lockedme.dao;

import com.lockedme.model.FileLock;

import java.util.List;

import com.lockedme.exception.FileLockException;

public interface LockerDAO {
	
	public FileLock createFileLock(FileLock filelock) throws FileLockException;
	public void deleteFileLock(int id) throws FileLockException;
	public FileLock getFile(int id)throws FileLockException;
	public List<String> showMyFiles()throws FileLockException;

}
