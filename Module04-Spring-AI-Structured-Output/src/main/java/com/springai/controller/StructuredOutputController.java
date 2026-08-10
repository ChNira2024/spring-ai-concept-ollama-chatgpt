package com.springai.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springai.model.Employee;
import com.springai.model.Student;
import com.springai.service.OutputConverterService;
import com.springai.service.StructuredOutputService;
import com.springai.service.StructuredOutputService_POJO_Mapping;
import com.springai.service.StudentService;

@RestController
public class StructuredOutputController {

	private final StudentService studentService;
    private final StructuredOutputService service;
    private final StructuredOutputService_POJO_Mapping pojoMapping;
    private final OutputConverterService outputConverterService;

    public StructuredOutputController(StructuredOutputService service,StructuredOutputService_POJO_Mapping pojo_Mapping,
    		StudentService studentService,OutputConverterService outputConverterService) {
        this.service = service;
        this.pojoMapping = pojo_Mapping;
        this.studentService = studentService;
        this.outputConverterService = outputConverterService;
    }

    @GetMapping("/employee-json")
    public String employeeJson() {

        return service.generateEmployeeJson();

    }

    
    @GetMapping("/employee2-json")
    public String employeeJson2() {

        return service.generateEmployeeJson2();

    }
    
    //JSON Output
    @GetMapping("/product-json")
    public String productJson() {

        return service.generateProductJson();

    }

    //POJO Mapping
    @GetMapping("/employee")
    public Employee employee() {
        return pojoMapping.employee();

    }

    //Record Mapping
    @GetMapping("/student")
    public Student student() {
        Student student = studentService.student();

        System.out.println(student.name());

        System.out.println(student.course());

        System.out.println(student.age());
        return student;
    }
    
    //Output Converter
    @GetMapping("/employee-output")
    public Employee employee2() {

        return outputConverterService.generateEmployee();

    }
    
    //Generate Schema
    @GetMapping("/schema")
    public Employee employeeSchema() {
        return outputConverterService.generateSchema();

    }
}

//http://localhost:8080/employee-json

/*
Where is the schema?
We never wrote one! Exactly.
Spring AI automatically generates the schema from: Employee.class, when you use .entity(Employee.class)
*/

/*
Difference between Output Converter and Schema?
------------------------------------------------
Schema
-------
Guides the AI to produce the correct JSON structure	
Works before the model generates a response	

Output Converter
---------------
Converts the AI response into a Java object
Works after the model returns a response

*/