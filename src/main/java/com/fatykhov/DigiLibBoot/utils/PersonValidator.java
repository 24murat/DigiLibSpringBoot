//package com.fatykhov.DigiLibBoot.utils;
//
////import com.fatykhov.digilib.dao.PersonDAO;
////import com.fatykhov.digilib.models.Person;
//import com.fatykhov.DigiLibBoot.models.Person;
//import com.fatykhov.DigiLibBoot.services.PeopleService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.validation.Errors;
//import org.springframework.validation.Validator;
//
//import java.time.LocalDate;
//
//@Component
//public class PersonValidator implements Validator {
//
//    private final PeopleService peopleService;
//
//    @Autowired
//    public PersonValidator(PeopleService peopleService) {
//        this.peopleService = peopleService;
//    }
//
//    @Override
//    public boolean supports(Class<?> clazz) {
//        return Person.class.equals(clazz);
//    }
//
//    @Override
//    public void validate(Object target, Errors errors) {
//        Person person = (Person) target;
//
//        if (peopleService.findOne(person.getPersonName()).isPresent()) {
//            errors.rejectValue("personName", "", "Человек с таким именем уже есть в базе");
//        }
//    }
//}
