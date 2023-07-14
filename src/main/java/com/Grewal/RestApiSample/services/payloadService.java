package com.Grewal.RestApiSample.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.Grewal.RestApiSample.entity.payload;

public interface payloadService {
	public String createPayload(payload myPayload);
	public ResponseEntity<String> updatePayload(Long loadId,payload myPayload);
	public ResponseEntity<String> deletePayload(Long payloadId);
	public payload getPayload(long payloadId);
	public List<payload> getPayloads(String shipperId);
}
