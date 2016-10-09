#ifdef BFS_SOLUTION
#include <cstdio>
#include <unordered_set>
#include <vector>
#include <algorithm>
using namespace std;

#define CLOCK_COUNT     16

#define PUSH(n, s) ((n ^ s) ^ ((n & s) << 1))
const int switch_list[] = {0x00000015, 0x00444040, 0x50100100, 0x00005501, 0x01115000, 0x50000011, 0x50000040, 0x50004500, 0x00000554, 0x04040540};

int read_initial_state()
{
	int ret = 0;
	for (int i = 0; i < CLOCK_COUNT; i++)
	{
		int hour;
		scanf("%d", &hour);
		if (hour == 9)
			ret |= 1 << (i * 2);
		else if (hour == 6)
			ret |= 2 << (i * 2);
		else if (hour == 3)
			ret |= 3 << (i * 2);
	}
	return ret;
}

void read_tc(vector<int>& problem)
{
	int count;
	scanf("%d", &count);
	while (count--)
	{
		problem.push_back(read_initial_state());
	}
}

int check_new_state(const vector<int>& problem, vector<int>& answer, int new_state, int round)
{
	int updated = 0;
	for (int j = 0; j < problem.size(); j++)
	{
		if (problem[j] == new_state)
		{
			answer[j] = round;
			updated = 1;
		}
	}
	return updated;
}

int is_finished(const vector<int>& answer)
{
	return (find(answer.begin(), answer.end(), -1) == answer.end());
}
	
void print_answer(const vector<int>& answer)
{
	for (int ans : answer)
	{
		printf("%d\n", ans);
	}
}

int main()
{
	int round;
	unordered_set<int> known;
	vector<int> doing, todo;
	vector<int> problem, answer;
	
	read_tc(problem);
	answer.resize(problem.size(), -1);

	round = 0;
	known.insert(0);
	todo.push_back(0);

	while (!todo.empty())
	{
		doing.swap(todo);
		todo.clear();
		round++;
		
		for (int state : doing)
		{
			for (int sw : switch_list)
			{
				int next_state = PUSH(state, sw);
				if (known.find(next_state) == known.end())
				{
					if (check_new_state(problem, answer, next_state, round) && is_finished(answer))
					{
						print_answer(answer);
						return 0;
					}
					known.insert(next_state);
					todo.push_back(next_state);
				}
			}
		}
	}
	print_answer(answer);
	return 0;
}


#else
#include <cstdio>

const int switch_list[10][5] = {
	{0, 1, 2, -1, -1}, {3, 7, 9, 11, -1}, {4, 10, 14, 15, -1}, {0, 4, 5, 6, 7}, {6, 7, 8, 10, 12},
	{0, 2, 14, 15, -1}, {3, 14, 15, -1, -1}, {4, 5, 7, 14, 15}, {1, 2, 3, 4, 5}, {3, 4, 5, 9, 13}};

int clock[16];

void push(int sw, int count)
{
	for (int c : switch_list[sw])
	{
		if (c == -1)
			break;
		clock[c] += 3 * count;
		if (clock[c] > 12)
			clock[c] -= 12;
	}
}

#define SYNC(s, c) \
	do {\
		count = (12 - clock[c]) / 3;\
		total_count += count;\
		push(s, count);\
	} while (0)
#define CHECK(c) \
	if (clock[c] != 12)\
		return -1;\
	else (void) 0

int solve(void)
{
	int total_count = 0;
	int count;

	SYNC(4, 8); // switch, clock
	CHECK(12);
	SYNC(1, 11);
	SYNC(9, 13);
	CHECK(9);
	SYNC(3, 6);
	SYNC(2, 10);
	SYNC(7, 7);
	SYNC(8, 4);
	CHECK(5);
	SYNC(0, 1);
	SYNC(6, 3);
	SYNC(5, 0);
	CHECK(2);
	CHECK(14);
	CHECK(15);
	return total_count;
}

int main(void)
{
	int count;
	scanf("%d", &count);
	while (count--)
	{
		for (int i = 0; i < 16; i++)
			scanf("%d", clock+i);
		printf("%d\n", solve());
	}
}

/*
스위치 -> 시계
0	0, 1, 2
1	3, 7, 9, 11
2	4, 10, 14, 15
3	0, 4, 5, 6, 7
4	6, 7, 8, 10, 12
5	0, 2, 14, 15
6	3, 14, 15
7	4, 5, 7, 14, 15
8	1, 2, 3, 4, 5
9	3, 4, 5, 9, 13

시계 -> 스위치
0   0 3 5 
1   0 8 
2   0 5 8 
3   1 6 8 9 
4   2 3 7 8 9 
5   3 7 8 9 
6   3 4 
7   1 3 4 7 
8   4 
9   1 9 
10  2 4 
11  1 
12  4 
13  9 
14  2 5 6 7 
15  2 5 6 7  

switch 4 -> clock 8
clock 12 안맞으면 실패
s1 -> c11
s9 -> c13
check c9

시계 -> 스위치
0   0 3 5 
1   0 8 
2   0 5 8 
3   6 8 
4   2 3 7 8 
5   3 7 8 
6   3  
7   3 7 
10  2  
14  2 5 6 7 
15  2 5 6 7  

s3 -> c6
s2 -> c10

시계 -> 스위치
0   0 5 
1   0 8 
2   0 5 8 
3   6 8 
4   7 8 
5   7 8 
7   7 
14  5 6 7 
15  5 6 7  

s7 -> c7

시계 -> 스위치
0   0 5 
1   0 8 
2   0 5 8 
3   6 8 
4   8 
5   8 
14  5 6  
15  5 6  

s8 -> c4
check c5

시계 -> 스위치
0   0 5 
1   0 
2   0 5 
3   6 
14  5 6  
15  5 6  

s0 -> c1
s6 -> c3

시계 -> 스위치
0   5 
2   5 
14  5  
15  5  

s5 -> c0
check c2, c14, c15
*/

#endif
