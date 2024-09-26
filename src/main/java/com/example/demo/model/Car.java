package com.example.demo.model;

import com.example.demo.service.Vehicle;

public class Car implements Vehicle
{
    @Override
    public void getVehicleType() {
        System.out.println("I am a car!");
    }
}
