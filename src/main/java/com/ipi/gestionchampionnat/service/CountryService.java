package com.ipi.gestionchampionnat.service;

import com.ipi.gestionchampionnat.pojos.Country;

import java.util.List;

public interface CountryService {
    Country ajouterCountry(Country country);
    Country recupererCountry(Long id);
    List<Country> recupererCountries();
    Country modifierCountry(Country country);
    void supprimerCountry(Long id);
    Country recupererCountryByName(String name);
}