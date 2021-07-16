#include "JJWVehicle.h"

JJWVehicle::JJWVehicle(string _registNumber, string _category, int _num, int _price, string _maker, string _model, int _capacity)
	:JJWItems(_registNumber, _category, _num, _price), maker(_maker), model(_model), capacity(_capacity)
{
}

void JJWVehicle::show() const
{
    JJWItems::show();
    cout << "--------------------------" << endl;
    cout << "제조회사 : " << this->maker << endl;
    cout << "차량모델 : " << this->model << endl;
    cout << "배기용량(cc) : " << this->capacity << endl;
    cout << "--------------------------" << endl;
    cout << "구매 참여자" << endl;
    cout << "--------------------------" << endl;
    for (auto i : this->buyer) {
        cout << i << endl;
    }
    cout << "--------------------------" << endl;
}
