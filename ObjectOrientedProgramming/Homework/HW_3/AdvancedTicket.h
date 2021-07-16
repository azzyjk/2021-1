#pragma once
#include "Ticket.h"
class AdvancedTicket :
    public Ticket
{
private:
    int advanceDays;
public:
    AdvancedTicket() = delete;
    AdvancedTicket(double _price, int _advanceDays);
    ~AdvancedTicket();
    double getPrice() const {
        return this->price;
    }
    int getAdvanceDays() const {
        return this->advanceDays;
    }
    void setPrice(const double& _price);
    void show() const;
};

