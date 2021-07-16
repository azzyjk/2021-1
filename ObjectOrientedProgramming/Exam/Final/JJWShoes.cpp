#include "JJWShoes.h"

JJWShoes::JJWShoes(string _registNumber, string _category, int _num, int _price, string _sex, string _type, int _size)
:JJWItems(_registNumber, _category, _num, _price), sex(_sex), type(_type), size(_size)
{
}

void JJWShoes::show() const
{
    JJWItems::show();
    cout << "--------------------------" << endl;
    cout << "성별유형 : " << this->sex << endl;
    cout << "신발종류 : " << this->type << endl;
    cout << "사이즈 : " << this->size << endl;
    cout << "--------------------------" << endl;
    cout << "구매 참여자" << endl;
    cout << "--------------------------" << endl;
    for (auto i : this->buyer) {
        cout << i << endl;
    }
    cout << "--------------------------" << endl;
}

ostream& operator<<(ostream& out, const JJWShoes& _shoes)
{
    _shoes.show();
    return out;
}
