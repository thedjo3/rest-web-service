package com.restful.restwebservice.filtering;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//@JsonIgnoreProperties("field1") // Ignoring on class level.
//@JsonIgnoreProperties({"field1", "field2"}) // Ignoring on class level two fields.
public class SomeBean {
    private String field1;

    //@JsonIgnore // Static filtering - ignoring this field from response.
    private String field2;

    @JsonIgnore // Static filtering - ignoring this field from response.
    private String field3;


    public SomeBean(String field1, String field2, String field3) {
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
    }

    public String getField1() {
        return field1;
    }

    public String getField2() {
        return field2;
    }

    public String getField3() {
        return field3;
    }

    @Override
    public String toString() {
        return "SomeBean{" +
                "field1='" + field1 + '\'' +
                ", field2='" + field2 + '\'' +
                ", field3='" + field3 + '\'' +
                '}';
    }
}
