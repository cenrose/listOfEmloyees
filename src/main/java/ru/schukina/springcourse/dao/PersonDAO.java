package ru.schukina.springcourse.dao;

import org.springframework.stereotype.Component;
import ru.schukina.springcourse.models.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;
    private List<Person> people;

    {
        people = new ArrayList<>();

        people.add(new Person(++PEOPLE_COUNT, "Tom", 24, "tom123mail.com"));
        people.add(new Person(++PEOPLE_COUNT, "Bob", 43, "bob123@mail.com"));
        people.add(new Person(++PEOPLE_COUNT, "Mike", 14, "mike123@mail.com"));
        people.add(new Person(++PEOPLE_COUNT, "Katy", 21, "katy123@mail.com"));
    }

    public List<Person> index() {
        return people;
    }

    public Person show(int id) {
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    public void save(Person person){
        person.setId(++PEOPLE_COUNT);
        people.add(person);

    }
    public void update(int id, Person updatedPerson){
        Person personToBeUpdated = show(id); //ищем в нашей БД человека (по id) которого нужно обновить

        personToBeUpdated.setName(updatedPerson.getName());//назначили новое имя personToBeUpdated то имя, кот пришло с формы редактирования (edit.html)
        personToBeUpdated.setAge(updatedPerson.getAge());
        personToBeUpdated.setEmail(updatedPerson.getEmail());
    }

    public void delete(int id){
        people.removeIf(p -> p.getId() == id);
        //прошлись по списку из людей, и если id совпадает с аргументом метода (true) то мы удаляем человека
    }

}
