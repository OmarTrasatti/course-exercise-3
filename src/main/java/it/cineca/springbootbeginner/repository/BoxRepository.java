package it.cineca.springbootbeginner.repository;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import it.cineca.springbootbeginner.models.Box;

public interface BoxRepository extends JpaRepository<Box, Long> {

	public List<Box> findByName(String name, PageRequest pageReq);
}