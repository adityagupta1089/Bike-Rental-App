package com.csl456.bikerentalapp.filter;

import com.csl456.bikerentalapp.core.*;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RolesAllowed {

    UserRole[] value();

}
