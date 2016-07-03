#include <iostream>
#include <cstdlib>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

#define N_MAX 50

string W;
string S;

bool is_match(int w, int s)
{
	for (;w < W.size() && s < S.size(); ) {
		if (W[w] != '?' && (W[w] != S[s]))
			break;

		w++; s++;
	}

	if (w == W.size()) 
		return s == S.size();
	
	if (W[w] != '*')
		return false;
	else {
		for (int i = 0; s + i <= S.size(); i++) {
			if (is_match(w + 1, s + i))
				return true;
		}
		return false;
	}
}

int main()
{
	int C;
	int N;
	vector<string> sol;

	cin >> C;
	for (int c = 0; c < C; c++) {
		sol.clear();
		cin >> W;
		cin >> N;
		for (int i = 0; i < N; i++) {
			cin >> S;
			if (is_match(0, 0))
				sol.push_back(S);
		}
		sort(sol.begin(), sol.end());
		for (string filename : sol) {
			cout << filename << endl;
		}
	}

	return 0;
}