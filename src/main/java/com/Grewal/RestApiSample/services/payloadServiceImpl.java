package com.Grewal.RestApiSample.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Grewal.RestApiSample.dao.payloadDao;
import com.Grewal.RestApiSample.entity.payload;
import com.Grewal.RestApiSample.exception.LoadNotFoundException;
@Service
public class payloadServiceImpl implements payloadService {
	@Autowired
	payloadDao myPayloadDao;

	@Override
	public String createPayload(payload myPayload) {
		myPayloadDao.save(myPayload);
		return "Load details added successfully";
	}

	@Override
	public ResponseEntity<String> updatePayload(Long loadId,payload myPayload) {
		if(myPayloadDao.findById(loadId).isEmpty())
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).
					body("Requested resource with loadId: "+loadId+" doesnot exist");
		}
		else {
		myPayload.setId(loadId);
		myPayloadDao.save(myPayload);
		return ResponseEntity.status(HttpStatus.OK).body("Requested load has been updated");
		}
		}

	@Override
	public ResponseEntity<String> deletePayload(Long payloadId) {
		if(myPayloadDao.findById(payloadId).isEmpty())
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).
					body("Requested resource with loadId: "+payloadId+" doesnot exist");
		}
		else {
		myPayloadDao.deleteById(payloadId);
		return ResponseEntity.status(HttpStatus.OK).body("Requested load has been deleted");
		}
	}

	@Override
	public payload getPayload(long payloadId) {
		  return myPayloadDao.findById(payloadId)
	                .orElseThrow(() -> new LoadNotFoundException("Load not found"));
	}

	@Override
	public List<payload> getPayloads(String shipperId) {
		return myPayloadDao.findByShipperId(shipperId);
	}

}
