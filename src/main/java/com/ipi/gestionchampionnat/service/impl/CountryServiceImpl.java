package com.ipi.gestionchampionnat.service.impl;

import com.ipi.gestionchampionnat.dao.CountryDao;
import com.ipi.gestionchampionnat.pojos.Country;
import com.ipi.gestionchampionnat.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {
    @Autowired
    private CountryDao countryDao;

    @Override
    public Country ajouterCountry(Country country) {
        return countryDao.save(country);
    }

    @Override
    public Country recupererCountry(Long id) {
        return countryDao.findById(id).orElse(null);
    }

    @Override
    public List<Country> recupererCountries() {
        return countryDao.findAll();
    }

    @Override
    public Country modifierCountry(Country country) {
        return countryDao.save(country);
    }

    @Override
    public void supprimerCountry(Long id) {
        countryDao.deleteById(id);
    }

    @Override
    public Country recupererCountryByName(String name) {
        return countryDao.findByName(name);
    }
}
