package ru.schukina.springcourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.schukina.springcourse.dao.PersonDAO;
import ru.schukina.springcourse.models.Person;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PersonDAO personDAO;
    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping() //метод показывает всех людей
    public String index(Model model) {
        model.addAttribute("people", personDAO.index());
        return "people/index";
    }

    @GetMapping("/{id}") //метод показывает 1го человека по id
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.show(id));
        return "people/show";
    }
    @GetMapping("/new")
    public String newPerson(Model model){
        model.addAttribute("person", new Person()); //под ключом "person" передали новый пустой объект (шаблон)
        return "people/new"; //возвращаем название thymeleaf шаблона, где у нас лежит newPerson
    }
    @PostMapping                                                        //под ключом "person" будет лежать объект
    public String createNewPerson(@ModelAttribute("person") @Valid Person person,
                                  BindingResult bindingResult){ //с данными, полученными из post-запроса
        if(bindingResult.hasErrors())
            return "people/new";

          personDAO.save(person);   //с помощью ДАО передаем полученный объект в модель
          return "redirect:/people";
    }

    @GetMapping("/{id}/edit") //редактирование человека
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", personDAO.show(id));
        return "people/edit";
    }

    @PatchMapping("/{id}") //обновление отредактированного человека в БД
    public String update(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult,
                         @PathVariable("id") int id){

        if(bindingResult.hasErrors())
            return "people/edit";

        personDAO.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}") //принимаем delete запрос и по id удаляем человека из БД
    public String delete(@PathVariable("id") int id) {
        personDAO.delete(id);
        return "redirect:/people";
    }

}
