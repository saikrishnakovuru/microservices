package com.in28minutes.microservices.currencyexchangeservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutes.microservices.currencyexchangeservice.Entity.CurrencyExchange;
import com.in28minutes.microservices.currencyexchangeservice.reposetory.CurrenyExchangeReposetory;

@RestController
public class CurrencyExchangeController {
	@Autowired
	private Environment environment;
	@Autowired
	private CurrenyExchangeReposetory currenyExchangeReposetory;
	
	private Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange retriveExchangeValue(@PathVariable String from, @PathVariable String to) {
//		CurrencyExchange currencyExchange = new CurrencyExchange(1000L, from, to, BigDecimal.valueOf(80));
		logger.info("retrieveExchangeValue called with {} to {}",from,to);
		CurrencyExchange currencyExchange = currenyExchangeReposetory.findByFromAndTo(from, to);
		if (currencyExchange == null)
			throw new RuntimeException("unable to finddata from" + from + "to" + to);

		String port = environment.getProperty("local.server.port");
		currencyExchange.setEnvironment(port);
		return currencyExchange;
	}
}
