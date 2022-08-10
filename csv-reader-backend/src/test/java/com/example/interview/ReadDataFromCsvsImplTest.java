package com.example.interview;

import com.example.interview.services.ReadDataFromCsvsImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ReadDataFromCsvsImplTest {

    @Spy
    @InjectMocks
    ReadDataFromCsvsImpl readDataFromCsvs;

    @Test
    public void loadResourceFileWhenPresent()
    {

    }

    @Test
    public void loadResourceFileWhenFileIsNotPresent()
    {

    }

    @Test
   public void readDataFromCSVContainingNameAndLinkWhenFileIsEmpty()
    {

    }

    @Test
    public void readDataFromCSVContainingNameAndLinkWhenFileIsNotEmpty()
    {

    }

}
