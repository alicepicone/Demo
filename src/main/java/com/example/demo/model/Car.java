package com.example.demo.model;

import com.example.demo.service.Vehicle;

public class Car implements Vehicle
{
    @Override
    public String getVehicleType() {
        return "I am a vehicle!";
    }
}
