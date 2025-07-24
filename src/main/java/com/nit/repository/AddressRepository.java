package com.nit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nit.entity.Address;
public interface AddressRepository extends JpaRepository<Address, Long>{

}
