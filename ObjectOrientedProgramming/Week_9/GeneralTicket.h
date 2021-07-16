#pragma once
#include "Ticket.h"
class GeneralTicket :
    public Ticket
{
private:
    bool payByCredit;

public:
    GeneralTicket() = delete;
    GeneralTicket(double _price, bool _payByCredit);
    ~GeneralTicket();
    double getPrice() const {
        return this->price;
    }
    bool getPayByCredit() const {
        return this->payByCredit;
    }
    void show() const;
};

