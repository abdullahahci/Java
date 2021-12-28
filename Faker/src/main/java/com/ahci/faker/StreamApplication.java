package com.ahci.faker;

import com.ahci.faker.modal.Address;
import com.ahci.faker.modal.Book;
import com.ahci.faker.modal.Commerce;
import com.ahci.faker.modal.Person;
import com.github.javafaker.Faker;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@SpringBootApplication
public class StreamApplication {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static void main(String[] args) {
        SpringApplication.run(StreamApplication.class, args);

        Faker faker = new Faker();
        List<Book> bookList = new ArrayList<>();
        com.github.javafaker.Book bookFaker = faker.book();
        for (int i = 0; i < 100; i++) {
            Book book = Book.builder()
                    .author(bookFaker.author())
                    .genre(bookFaker.genre())
                    .price(faker.commerce().price(25, 100))
                    .publisher(bookFaker.publisher())
                    .title(bookFaker.title())
                    .build();
            bookList.add(book);
        }

        System.out.println("*** Books with author name start with A ***");
        Predicate<Book> authorFilter = b -> b.getAuthor().startsWith("A");
        bookList.stream().filter(authorFilter).forEach(System.out::println);


        System.out.println();
        System.out.println("*** Books with title contains \"The\" and price below 50 ***");
        Predicate<Book> priceFilter = b -> Double.parseDouble(b.getPrice())<50;
        bookList.stream()
                .filter(b->b.getTitle().contains("The"))
                .filter(priceFilter)
                .forEach(System.out::println);

        List<Commerce> commerceList = new ArrayList<>();
        com.github.javafaker.Commerce commerceFaker = faker.commerce();
        for (int i = 0; i < 100; i++) {
            Commerce commerce = Commerce.builder()
                        .price(commerceFaker.price(25, 100))
                    .color(commerceFaker.color())
                    .department(commerceFaker.department())
                    .material(commerceFaker.material())
                    .productName(commerceFaker.productName())
                    .promotionCode(commerceFaker.promotionCode(3))
                    .build();
            commerceList.add(commerce);
        }

        // red or Copper commerce objects
        commerceList.stream()
                .filter(c->c.getColor().equals("red") || c.getMaterial().equals("Copper"))
                .forEach(System.out::println);

        // total wallet price sum
        Double redWalletSum = commerceList.stream()
                .filter(c->c.getProductName().contains("Wallet"))
                .map(c->Double.parseDouble(c.getPrice()))
                .reduce(0.00, Double::sum);
        System.out.println(redWalletSum);

        List<Person> personList = new ArrayList<>();
        com.github.javafaker.Address addrFaker= faker.address();

        for (int i = 0; i < 1000; i++) {
            Address addr = Address.builder()
                    .city(addrFaker.city())
                    .country(addrFaker.country())
                    .postalCode(addrFaker.zipCode())
                    .fulladress(addrFaker.fullAddress())
                    .number(addrFaker.buildingNumber())
                    .build();
            Person p = Person.builder()
                    .firstName(addrFaker.firstName())
                    .lastName(addrFaker.lastName())
                    .address(addr)
                    .build();
            personList.add(p);
        }

        // person count that lives in America
        System.out.println(
            personList.stream()
                .filter(p->"United States of America".equals(p.getAddress().getCountry()))
                .count()
        );

        // print people size of the countries
        Map<String, List<Person>> personByCountry = personList.stream()
                .collect(Collectors.groupingBy(Person::getCountry));
        personByCountry.forEach((key, value) -> System.out.println(StringUtils.joinWith(":", key, value.size())));
        System.out.println();
        System.out.println("Lord of the Rings");
        for (int i = 0; i < 10; i++) {
            System.out.println(
                    ANSI_RED + faker.lordOfTheRings().character() + ANSI_RESET +
                        " lives in " +
                    ANSI_BLUE + faker.lordOfTheRings().location()+ ANSI_RESET );
        }

        System.out.println();
        System.out.println("Pokemon Locations");
        for (int i = 0; i < 10; i++) {
            System.out.println(
                    ANSI_RED + faker.pokemon().name() + ANSI_RESET +
                    " can be find at " +
                    ANSI_BLUE + faker.pokemon().location() + ANSI_RESET );
        }

        System.out.println();
        System.out.println("Yoda says");
        for (int i = 0; i < 10; i++) {
            System.out.println(faker.yoda().quote());
        }

        System.out.println();
        System.out.println("Witcher sayings");
        for (int i = 0; i < 10; i++) {
            System.out.println(ANSI_RED + faker.witcher().character() + ANSI_RESET
                    + " says "
                    + ANSI_BLUE + faker.witcher().quote() + ANSI_RESET);
        }

        System.out.println();
        System.out.println("Superhero Powers");
        for (int i = 0; i < 10; i++) {
            System.out.println(
                ANSI_RED + faker.superhero().name() + ANSI_RESET+ " has the power of "
                + ANSI_BLUE + faker.superhero().power() + ANSI_RESET);
        }
    }

}
