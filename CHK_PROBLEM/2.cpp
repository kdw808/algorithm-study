#include <bits/stdc++.h>
using namespace std;

struct Combination {
	string p;
	string q;
	string r;

	Combination(string p, string q, string r) : p(p), q(q), r(r) {}
	Combination(const Combination& problem, int mapping[26]) {
		p = replace(problem.p, mapping);
		q = replace(problem.q, mapping);
		r = replace(problem.r, mapping);
	}
	string get_equation() const {
		return p + "+" + q + "=" + r;
	}
	string replace(const string& orig, int mapping[26]) {
		string ret = orig;
		for (int i = 0; i < ret.size(); i++) {
			unsigned int index = ret[i] - 'A';
			if (index < 26)
				ret[i] = mapping[index] + '0';
		}
		return ret;
	}
};

struct DigitCombination {
	int p;
	int q;
	int r;
	bool need_carry;
	DigitCombination(int p, int q, int r, bool carry) : p(p), q(q), r(r), need_carry(carry) {}
};

struct Map {
	int map[26];
	bitset<10> used_num;

	Map() {
		memset(map, -1, sizeof(map));
	}
	void add_mapping(char c, int i) {
		int index = c - 'A';
		if (map[index] == -1) {
			map[index] = i;
			used_num.set(i);
		}
	}
	void remove_mapping(char c) {
		int index = c - 'A';
		if (map[index] != -1) {
			used_num.reset(map[index]);
			map[index] = -1;
		}
	}
	bool is_mapped(char c) {
		return (map[c-'A'] != -1);
	}
	int get(char c) {
		return map[c-'A'];
	}
};

/**
@class Solver
@brief 주어진 입력에 대해 p + q = r을 만족하는 모든 조합을 찾는다.
       p, q, r은 숫자와 알파벳 대문자의 조합이며, 
	   각 알파벳은 서로 다른 한자리 숫자로 대체되고, 
	   첫 자리의 알파벳이 0이 될 수는 없다.
       예를 들어, "XYZ", "XY", "6PP" 가 입력으로 들어온 경우 가능한 조합은
	   {"546+65=600", "576+57=633", "586+58=644"} 이 됨.
*/
class Solver {
public:
	Solver(const string& p, const string& q, const string& r) : problem(p, q, r) {
		int len = max(p.size(), q.size());
		solve(len, r.size() > len);
	}

	void print_solutions() {
		for (vector<Combination>::const_iterator it = solution.begin(), end = solution.end(); it != end; ++it)
			cout << it->get_equation() << endl;
	}

private:
	void solve(int pos, bool generate_carry) {
		char p = get_char(problem.p, pos);
		char q = get_char(problem.q, pos);
		char r = get_char(problem.r, pos);
		vector<DigitCombination> possible_case;
		find_combination(p, q, r, generate_carry, possible_case);

		for (vector<DigitCombination>::const_iterator it = possible_case.begin(); it != possible_case.end(); ++it) {
			if (pos == 0 && it->need_carry == true)
				continue;
			if (p != it->p)
				map.add_mapping(p, it->p);
			if (q != it->q)
				map.add_mapping(q, it->q);
			if (r != it->r)
				map.add_mapping(r, it->r);

			if (pos == 0)
				solution.push_back(Combination(problem, map.map));
			else
				solve(pos-1, it->need_carry);

			if (p != it->p)
				map.remove_mapping(p);
			if (q != it->q)
				map.remove_mapping(q);
			if (r != it->r)
				map.remove_mapping(r);
		}
	}

	char get_char(const string& s, int pos) {
		pos = s.size() - pos;
		if (pos < 0)
			return 0;
		char ch = s[pos];
		if (ch >= '0' && ch <= '9')
			return ch - '0';
		if (ch >= 'A' && ch <= 'Z' && map.is_mapped(ch))
			return map.get(ch);
		return ch;
	}

	void find_combination(int p, int q, int r, bool generate_carry, vector<DigitCombination>& list) {
		if (p >= 'A') {
			for (int i = 0; i < 10; i++) {
				if (map.used_num[i] == 0) {
					map.add_mapping(p, i);
					find_combination(i, q, r, generate_carry, list);
					map.remove_mapping(p);
				}
			}
		} else if (q >= 'A') {
			if (map.is_mapped(q)) {	// p == q
				find_combination(p, map.get(q), r, generate_carry, list);
			} else {
				for (int i = 0; i < 10; i++) {
					if (map.used_num[i] == 0) {
						map.add_mapping(q, i);
						find_combination(p, i, r, generate_carry, list);
						map.remove_mapping(q);
					}
				}
			}
		} else if (r >= 'A') {
			if (map.is_mapped(r)) {
				find_combination(p, q, map.get(r), generate_carry, list);
			} else {
				for (int i = 0; i < 10; i++) {
					if (map.used_num[i] == 0) {
						map.add_mapping(r, i);
						find_combination(p, q, i, generate_carry, list);
						map.remove_mapping(r);
					}
				}
			}
		} else {
			if (p + q == r + generate_carry * 10)
				list.push_back(DigitCombination(p, q, r, false));
			else if (p + q + 1 == r + generate_carry * 10)
				list.push_back(DigitCombination(p, q, r, true));
		}
	}

	Combination problem;
	vector<Combination> solution;
	Map map;
};

int main()
{
	Solver("XYZ", "XY", "6PP").print_solutions();
	Solver("AA", "BC", "100").print_solutions();	// 11+89, 22+78, 33+67, 44+56, 66+34, 77_23, 88+12
}
