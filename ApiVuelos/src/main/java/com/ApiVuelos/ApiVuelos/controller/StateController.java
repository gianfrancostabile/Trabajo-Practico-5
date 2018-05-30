package com.ApiVuelos.ApiVuelos.controller;

import com.ApiVuelos.ApiVuelos.service.CountryService;
import com.ApiVuelos.ApiVuelos.service.StateService;
import com.utn.tssi.tp5.Models.model.Country;
import com.utn.tssi.tp5.Models.model.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/state")
public class StateController {

    @Autowired
    private StateService stateService;

    @Autowired
    private CountryService countryService;

    @PostMapping(value = "/add")
    public void add(String name, String iataCode, long id_Country) {

        Country country = this.countryService.getById(id_Country);

        if(country != null) {
            State state = new State(name, iataCode, country);
            this.stateService.newObject(state);

        }   else {
        }
    }

    @PutMapping(value = "/update")
    public void update() {

    }

    @DeleteMapping(value = "/remove")
    public void remove() {

    }

    @GetMapping(value = "/")
    public void getAll() {

    }
}