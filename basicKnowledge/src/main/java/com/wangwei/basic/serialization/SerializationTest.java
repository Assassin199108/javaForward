package com.wangwei.basic.serialization;

import com.wangwei.basic.dto.AnimalDTO;

import java.io.*;

public class SerializationTest {

    public static void main(String[] args) throws IOException {
        AnimalDTO animalDTO = new AnimalDTO();
        ObjectOutputStream objectOutputStream =
                new ObjectOutputStream(new FileOutputStream("test.txt"));

        objectOutputStream.writeObject(animalDTO);

        objectOutputStream.close();
    }

}
