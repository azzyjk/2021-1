#pragma once
#include "JJWItems.h"
class JJWVehicle :
    public JJWItems
{
private:
    string maker;
    string model;
    int capacity;
public:
    JJWVehicle() = delete;
    JJWVehicle(string _registNumber, string _category, int _num, int _price, string _maker, string _model, int _capacity);
    int getPrice() {
        return this->price;
    }
    void show() const;
    string getType(){
        return this->model;
    }
    string getCategory() {
        return this->category;
    }
    
};

