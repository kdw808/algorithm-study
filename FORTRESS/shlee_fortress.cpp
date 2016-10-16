#include <stdbool.h>
#include <iostream>
#include <cstdlib>
#include <cmath>
#include <vector>
#include <algorithm>

using namespace std;

#define N_MAX 100
#define R_MAX 20000

struct wall_t
{
	float x;
	float y;
	float r;

	int height;

	vector<wall_t> childs;

	void add(wall_t rhs) {
		for (vector<wall_t>::size_type i = 0, n = childs.size(); i < n; i++) {
			if (childs[i].contain(rhs)) {
				childs[i].add(rhs);
				return;
			} else if (rhs.contain(childs[i])) {
				wall_t t = childs[i];
				childs.erase(childs.begin() + i);
				rhs.childs.push_back(t);
				childs.push_back(rhs);
				return;
			}
		}

		childs.push_back(rhs);
	}

	bool contain(const wall_t& rhs) {
		return (r > rhs.r) && distance(rhs) <= abs(r - rhs.r);
	}

	float distance(const wall_t& rhs)
	{
		return sqrt((x - rhs.x)*(x - rhs.x) + (y - rhs.y)*(y - rhs.y));
	}
};


int C;
int N;
int solution = 0;

int build_height(wall_t& wall)
{
	int height = 0;
	for (auto it = wall.childs.begin(); it != wall.childs.end(); ++it) {
		height = max(height, build_height(*it));
	}

	wall.height = height + 1;
	return wall.height;
}

void find_solution(wall_t& wall)
{
	if (wall.childs.size() >= 2) {
		int max1 = 0;
		int max2 = 0;

		for (auto it = wall.childs.begin(); it != wall.childs.end(); ++it) {
			if (it->height > max1) {
				max2 = max1;
				max1 = it->height;
			}
			else if (it->height > max2) {
				max2 = it->height;
			}
		}

		int candidate = max1 + max2;
		if (solution < candidate) {
			solution = candidate;
		}
	}


	for (auto it = wall.childs.begin() ; it != wall.childs.end(); ++it) {
		find_solution(*it);
	}
}

int main()
{
	int C;
	cin >> C;

	for (int c = 0; c < C; c++) {
		wall_t root;
		root.x = root.y = 0;
		root.r = R_MAX;

		int N;
		cin >> N;
		for (int n = 0; n < N; n++) {
			wall_t wall;
			cin >> wall.x >> wall.y >> wall.r;
			root.add(wall);
		}

		build_height(root.childs[0]);

		solution = 0;
		find_solution(root);
		if (root.childs[0].height - 1 > solution)
			solution = root.childs[0].height - 1;

		cout << solution << endl;
	}

	return 0;
}