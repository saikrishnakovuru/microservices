package com.in28minutes.microservices.currencyexchangeservice.reposetory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.in28minutes.microservices.currencyexchangeservice.Entity.CurrencyExchange;

public interface CurrenyExchangeReposetory extends JpaRepository<CurrencyExchange, Long> {

	public CurrencyExchange findByFromAndTo(String from, String to);
}
