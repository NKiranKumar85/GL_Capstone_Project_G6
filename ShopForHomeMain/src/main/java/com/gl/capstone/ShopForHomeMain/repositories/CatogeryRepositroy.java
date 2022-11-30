package com.gl.capstone.ShopForHomeMain.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gl.capstone.ShopForHomeMain.model.Catogery;

public interface CatogeryRepositroy extends JpaRepository<Catogery, Long>{
	
	@Query("select c from Catogery c where c.catogeryName =?1")
	public Optional<Catogery> findByCatogeryname(String name);
}
