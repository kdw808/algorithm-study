#include <iostream>
#include <cmath>
#include <stdlib.h>
#include <string.h>

using namespace std;

#define CLOCK_MAX 16
#define SWITCH_MAX 10
int COUNT_MAX = 4 * 4 * 4 * 4 * 4 * 4 * 4 * 4 * 4 * 4;

int _clock[CLOCK_MAX];


int sw_clock[SWITCH_MAX][CLOCK_MAX] = {
	{1,1,1,0, 0,0,0,0, 0,0,0,0, 0,0,0,0},
	{0,0,0,1, 0,0,0,1, 0,1,0,1, 0,0,0,0},
	{0,0,0,0, 1,0,0,0, 0,0,1,0, 0,0,1,1},
	{1,0,0,0, 1,1,1,1, 0,0,0,0, 0,0,0,0},
	{0,0,0,0, 0,0,1,1, 1,0,1,0, 1,0,0,0},
	{1,0,1,0, 0,0,0,0, 0,0,0,0, 0,0,1,1},
	{0,0,0,1, 0,0,0,0, 0,0,0,0, 0,0,1,1},
	{0,0,0,0, 1,1,0,1, 0,0,0,0, 0,0,1,1},
	{0,1,1,1, 1,1,0,0, 0,0,0,0, 0,0,0,0},
	{0,0,0,1, 1,1,0,0, 0,1,0,0, 0,1,0,0}
};

unsigned char sw[SWITCH_MAX];

inline bool is_solution()
{
	for (int i = 0; i < CLOCK_MAX; i++) {
		int c = _clock[i];
		for (int j = 0; j < SWITCH_MAX; j++) {
			c += sw[j] * sw_clock[j][i];
		}

		if (c % 4 != 0)
			return false;
	}

	return true;
}

int main()
{
	int C;
	cin >> C;

	for (int c = 0; c < C; c++) {
		for (int i = 0; i < CLOCK_MAX; i++) {
			cin >> _clock[i];
			_clock[i] = (_clock[i] / 3) % 4;
		}

		int count = COUNT_MAX + 1;

		for (sw[0] = 0; sw[0] < 4; sw[0]++)
		for (sw[1] = 0; sw[1] < 4; sw[1]++)
		for (sw[2] = 0; sw[2] < 4; sw[2]++)
		for (sw[3] = 0; sw[3] < 4; sw[3]++)
		for (sw[4] = 0; sw[4] < 4; sw[4]++)
		for (sw[5] = 0; sw[5] < 4; sw[5]++)
		for (sw[6] = 0; sw[6] < 4; sw[6]++)
		for (sw[7] = 0; sw[7] < 4; sw[7]++)
		for (sw[8] = 0; sw[8] < 4; sw[8]++)
		for (sw[9] = 0; sw[9] < 4; sw[9]++)
		{
			if (is_solution()) {
				int total = 0;
				for (int i = 0; i < SWITCH_MAX; i++)
					total += sw[i];

				if (total < count)
					count = total;
			}
		}

		if (count >= COUNT_MAX)
			cout << -1 << endl;
		else
			cout << count << endl;
	}
	return 0;
}

