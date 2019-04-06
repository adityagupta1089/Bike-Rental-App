package com.csl456.bikerentalapp;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class BikeRentalAppConfiguration extends Configuration {

    @Valid
    @NotNull
    @JsonProperty("database")
    private final DataSourceFactory database = new DataSourceFactory();

    public DataSourceFactory getDataSourceFactory() { return database; }
}
