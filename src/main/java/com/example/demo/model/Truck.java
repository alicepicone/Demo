package com.example.demo.model;

import com.example.demo.service.Vehicle;

public class Truck implements Vehicle
{
    @Override
    public void getVehicleType() {
        System.out.println("I am a Truck");
    }
}
