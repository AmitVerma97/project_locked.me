package com.lockedme.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.lockedme.exception.FileLockException;
import com.lockedme.model.FileLock;

public class LockerDAOImpl implements LockerDAO {
	
	private static Map<Integer, FileLock> fileLockMap = new HashMap<>();
	private static int c;
	@Override
	public FileLock createFileLock(FileLock fileLock) throws FileLockException {
		fileLock.setId(++c);
		fileLockMap.put(fileLock.getId(),fileLock);
		return fileLock;
	}

	@Override
	public void deleteFileLock(int id) throws FileLockException {
		
		if (fileLockMap.containsKey(id)) {
			fileLockMap.remove(id);
		}
		else {
			throw new FileLockException("Entered Id" + id + " doesnt exist");
		}
		
	}

	@Override
	public FileLock getFile(int id) throws FileLockException {
		
		if (fileLockMap.containsKey(id)) {
			return fileLockMap.get(id);
		}
		else {
			throw new FileLockException("Entered Id" + id + " doesnt exist");
		}
	}

	@Override
	public List<String> showMyFiles() throws FileLockException {
		if (fileLockMap.size()==0) {
			throw new FileLockException("Your file locker is empty....:-(");
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

}
