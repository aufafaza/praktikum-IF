#include "MesinHitungNeroifa.hpp"
#include "MesinHitungException.hpp"

#include <stack>
#include <sstream>
#include <cctype>
using namespace std;

MesinHitungNeroifa::MesinHitungNeroifa() {}

int MesinHitungNeroifa::hitung(const string& ekspresi) const {
    stack<int> st;
    stringstream ss(ekspresi);
    string token;

    while (ss >> token) {
        // TODO:
        // Jika token adalah operator (+, -, *, /),
        // lakukan operasi terhadap dua elemen teratas stack.
        // Jika jumlah operand tidak mencukupi, lempar exception yang sesuai.

        if (token == "+" || token == "-" || token == "*" || token == "/") {
            // TODO: cek apakah stack memiliki minimal 2 operand
		if (st.size() < 2) throw InsufficientOperandException(); 	
            // TODO: ambil dua operand teratas dari stack
            int operand2;
            int operand1;
		operand2 = st.top();
		st.pop(); 
		operand1 = st.top();
		st.pop(); 
            // TODO: lakukan operasi sesuai token
            // Perhatikan bahwa pembagian dengan nol harus melempar exception
		if (token == "+") {
                st.push(operand1 + operand2);
            } else if (token == "-") {
                st.push(operand1 - operand2);
            } else if (token == "*") {
                st.push(operand1 * operand2);
            } else if (token == "/") {
                if (operand2 == 0) {
                    throw DivisionByZeroException();
                }
                st.push(operand1 / operand2);
            }
        } else {
            // TODO:
            // Anggap token sebagai kandidat bilangan bulat.
            // Cek apakah token valid:
            // - boleh berupa bilangan negatif
            // - selain itu semua karakter harus digit
            bool valid = true;
            int startIdx = 0;

            // TODO: tangani kasus bilangan negatif
		if (token.length() > 1 && token[0] == '-'){
			startIdx = 1;
		} else if (token.length() == 1 && !isdigit(token[0])){
			valid = false;
		} 

		for(size_t i = startIdx; i < token.length(); ++i){
			if (!isdigit(token[i])){
				valid = false; 
				break;
			}
		}

		if (!valid) { 
			throw InvalidTokenException(token);
		}

		st.push(stoi(token)); 
            // TODO: cek apakah semua karakter yang relevan adalah digit


            // TODO: ubah token menjadi integer lalu push ke stack
        }
    }

    // TODO:
    // Setelah semua token diproses, stack harus berisi tepat satu elemen.
    // Jika tidak, lempar exception yang sesuai.
	if (st.size() != 1){
		throw InvalidExpressionException();
	} 
    // TODO: kembalikan hasil akhir
    return st.top();
}
