
package com.example.rest;

import java.util.List;

import javax.ws.rs.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeDAO edao;

    // Get all Employees
    @RequestMapping(method = RequestMethod.GET)
    public Employee[] getAll() {
        return edao.getAllEmployees().toArray(new Employee[0]);
    }

    // Get an Employee
    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    public ResponseEntity get(@PathVariable long id) {
        Employee match = null;
        ResponseEntity re = new ResponseEntity<>(match, HttpStatus.NOT_FOUND);
        match = edao.getEmployee(id);

        if (match != null) {
            re = new ResponseEntity<>(match, HttpStatus.OK);
        }
        return re;
    }

    // Get employees by lastName (Week 2)
    @RequestMapping(method = RequestMethod.GET, value = "/lastname/{name}")
    public ResponseEntity getByLastName(@PathVariable String name) {
        List<Employee> matchList = null;
        ResponseEntity re = new ResponseEntity<>(matchList, HttpStatus.NOT_FOUND);

        matchList = edao.getByLastName(name);

        if (matchList != null) {
            re = new ResponseEntity<>(matchList, HttpStatus.OK);
        }
        return re;
    }

    // Get employee by title (Week 2)
    @RequestMapping(method = RequestMethod.GET, value = "/title/{title}")
    public ResponseEntity getByTitle(@PathVariable String title) {
        List<Employee> matchList = null;
        ResponseEntity re = new ResponseEntity<>(matchList, HttpStatus.NOT_FOUND);

        matchList = edao.getByTitle(title);

        if (matchList != null) {
            re = new ResponseEntity<>(matchList, HttpStatus.OK);
        }
        return re;
    }

    // Get employee by dept (Week 2)
    @RequestMapping(method = RequestMethod.GET, value = "/department/{dept}")
    public ResponseEntity getByDept(@PathVariable String dept) {
        List<Employee> matchList = null;
        ResponseEntity re = new ResponseEntity<>(matchList, HttpStatus.NOT_FOUND);

        matchList = edao.getByDept(dept);

        if (matchList != null) {
            re = new ResponseEntity<>(matchList, HttpStatus.OK);
        }
        return re;
    }

    // Add an Employee
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity add(@RequestBody Employee employee) {
        ResponseEntity re = new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

        if (edao.add(employee)) {
            re = new ResponseEntity<>(null, HttpStatus.CREATED);
        }
        return re;
    }

    // Update an employee
    @RequestMapping(method = RequestMethod.PUT, value = "{id}")
    public ResponseEntity update(@PathVariable long id, @RequestBody Employee employee) {
        ResponseEntity re = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        if (edao.update(id, employee)) {
            re = new ResponseEntity<>(null, HttpStatus.OK);
        }

        return re;
    }

    // Delete a employee (Week 3)
    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    public ResponseEntity delete(@PathVariable long id) {
        ResponseEntity re = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        if (edao.delete(id)) {
            re = new ResponseEntity<>(null, HttpStatus.OK);
        }
        return re;
    }
}
