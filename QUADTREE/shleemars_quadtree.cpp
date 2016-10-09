#include <iostream>
#include <string>
#include <sstream>

using namespace std;

string solve(stringstream& stream)
{
	char c;

	stream >> c;

	if (c != 'x') {
		return string(1, c);
	}
	else {
		string a = solve(stream);
		string b = solve(stream);
		string c = solve(stream);
		string d = solve(stream);

		return string(1, 'x') + c + d + a + b;
	}
}


int main()
{
	int C;
	cin >> C;

	
	for (int c = 0; c < C; c++) {
		string line;
		cin >> line;

		stringstream stream(line, ios_base::in);
		cout << solve(stream) << endl;
	}

	return 0;
}