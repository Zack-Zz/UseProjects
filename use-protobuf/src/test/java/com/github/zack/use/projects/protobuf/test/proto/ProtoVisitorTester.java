package com.github.zack.use.projects.protobuf.test.proto;

import com.github.zack.use.projects.protobuf.proto.Person;
import com.google.common.collect.Lists;
import com.google.protobuf.InvalidProtocolBufferException;
import org.junit.jupiter.api.*;

import java.util.logging.Logger;

/**
 * @author zack
 * @date 2021/12/14
 */
public class ProtoVisitorTester {
    static final Logger logger = Logger.getLogger(ProtoVisitorTester.class.getName());

    static Person person;

    @BeforeAll
    static void buildPerson() {
        person = Person.newBuilder()
                .setId(1)
                .setName("LiLei")
                .setEmail("lilei@gmail.com")
                .addAllPhones(Lists.newArrayList(
                        Person.PhoneNumber.newBuilder()
                                .setNumber("No1")
                                .setPhoneType(Person.PhoneType.HOME)
                                .build(),
                        Person.PhoneNumber.newBuilder()
                                .setNumber("No2")
                                .setPhoneType(Person.PhoneType.WORK)
                                .build()
                ))
                .build();
    }

    @Order(1)
    @DisplayName("test build person")
    @Test
    void buildPersonTest() {

        Assertions.assertNotNull(person);
        Assertions.assertTrue(person.isInitialized());

        logger.info(() -> String.format("Read person:\n%s", person.toString()));

    }

    @Order(2)
    @DisplayName("test serialize person")
    @Test
    void serializeTest() throws InvalidProtocolBufferException {

        byte[] bytes = person.toByteArray();

        Person parsePerson = Person.parseFrom(bytes);

        Assertions.assertNotNull(parsePerson);
        Assertions.assertTrue(parsePerson.isInitialized());

        logger.info(() -> String.format("Read parsed person:\n%s", parsePerson.toString()));
        logger.info(() -> String.format("Read parsed person,byteString:\n%s", parsePerson.toByteString()));

    }
}
