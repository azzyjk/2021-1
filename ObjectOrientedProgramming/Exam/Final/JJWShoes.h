#pragma once
#include "JJWItems.h"
class JJWShoes :
    public JJWItems
{
private:
    string sex;
    string type;
    int size;
public:
    JJWShoes() = delete;
    JJWShoes(string _registNumber, string _category, int _num, int _price, string _sex, string _type, int _size);
    int getPrice() {
        return this->price;
    }
    void show() const;
    string getType() {
        return this->type;
    }
    string getCategory() {
        return this->category;
    }
    friend ostream& operator<<(ostream& out, const JJWShoes& _shoes);
};


