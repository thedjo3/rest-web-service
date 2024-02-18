package com.restful.restwebservice.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    @GetMapping("/filtering")
    public MappingJacksonValue filtering() {
        SomeBean someBean = new SomeBean("value1", "value2", "value3");

        // ~~~ Dynamic filtering defined in the REST API. ~~~
        // Creating MappingJacksonValue from our Bean.
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBean);

        // Defining the simple property filter.
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field3");

        // Adding filter to the FilterProvider with JsonFilter->id and property filter->filter.
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);

        // Setting the filters to the MappingJacksonValue.
        mappingJacksonValue.setFilters(filters);

        return mappingJacksonValue;
    }

    @GetMapping("/filtering-list")
    public MappingJacksonValue filteringList() {

        List<SomeBean> myBeanList = Arrays.asList(new SomeBean("value1", "value2", "value3"), new SomeBean("value4", "value5", "value6"));

        // Creating MappingJacksonValue from our Bean.
        MappingJacksonValue mappingJacksonValues = new MappingJacksonValue(myBeanList);

        // Defining the simple property filter.
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2", "field3");

        // Adding filter to the FilterProvider with JsonFilter->id and property filter->filter.
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);

        // Setting the filters to the MappingJacksonValue.
        mappingJacksonValues.setFilters(filters);

        return mappingJacksonValues;
    }
}
