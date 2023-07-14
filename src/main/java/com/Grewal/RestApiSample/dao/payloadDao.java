package com.Grewal.RestApiSample.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Grewal.RestApiSample.entity.payload;

public interface payloadDao extends JpaRepository<payload, Long> {
	  List<payload> findByShipperId(String shipperId);
}
