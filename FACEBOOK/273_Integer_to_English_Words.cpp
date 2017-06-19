const char* small[] = {"One", "Two", "Three", "Four","Five","Six","Seven","Eight","Nine","Ten", "Eleven","Twelve","Thirteen","Fourteen","Fifteen","Sixteen","Seventeen","Eighteen","Nineteen"};
const char* big[] ={"Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
class Solution {
public:
    string numberToWords(int num) {
        if(num == 0) return "Zero";
        else return internal(num).substr(1);  // smart to remove leading " "
    }
    string internal(int num) {
        if (num >= 1000000000) {
            return internal(num/1000000000) + " Billion"+ internal(num%1000000000);
        } else if(num>=1000000) {
            return internal(num/1000000) + " Million" + internal(num%1000000);
        } else if(num>=1000) {
            return internal(num/1000) + " Thousand" + internal(num%1000);
        } else if(num>=100) {
            return internal(num/100) + " Hundred" + internal(num%100);
        } else if(num>=20) {
            return " " + std::string(big[num/10-2]) + internal(num%10);  // key
        } else if(num>=1) {
            return " " + std::string(small[num-1]);   //key
        } else {
            return "";
        }
    }


};
