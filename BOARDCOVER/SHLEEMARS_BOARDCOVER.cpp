// BOARDCOVER.cpp : 콘솔 응용 프로그램에 대한 진입점을 정의합니다.
//

#include "stdafx.h"
#include <iostream>
#include <stdlib.h>
#include <string.h>

using namespace std;

#define H_MAX 20
#define W_MAX 20

char board[H_MAX][W_MAX];

int H;
int W;
int filled;

int block[4][3][2] = {
	{
		{ 0,0 }, { 1,0 }, { 0,1 }
	},
	{
		{ 0,0 }, { 1,0 }, { 1,1 }
	},
	{
		{ 0,0 }, { 0,1 }, { 1,1 }
	},
	{
		{ 0,0 }, { 1,0 }, { 1, -1 }
	}
};

int fillable(int block_index, int h0, int w0)
{
	for (int i = 0; i < 3; i++) {
		int h = h0 + block[block_index][i][0];
		int w = w0 + block[block_index][i][1];
		if (h < 0 || w < 0 || h >= H || w >= W)
			return 0;
		if (board[h][w] == '#')
			return 0;
	}

	return 1;
}

void fill(int block_index, int h0, int w0, char c)
{
	for (int i = 0; i < 3; i++) {
		int h = h0 + block[block_index][i][0];
		int w = w0 + block[block_index][i][1];
		board[h][w] = c;
	}
}

void find_hole(int* h0, int* w0)
{
	for (int h = 0; h < H; h++) {
		for (int w = 0; w < W; w++) {
			if (board[h][w] == '.') {
				*h0 = h;
				*w0 = w;
				return;
			}
		}
	}
}

int solve()
{
	if (filled == W*H)
		return 1;

	int h;
	int w;
	h = w = 0;
	find_hole(&h, &w);

	int count = 0;
	for (int bi = 0; bi < sizeof(block)/sizeof(block[0]); bi++) {
		if (fillable(bi, h, w)) {
			fill(bi, h, w, '#');
			filled += 3;

			count += solve();

			fill(bi, h, w, '.');
			filled -= 3;
		}
	}
	return count;
}


int main()
{
	int C;
	cin >> C;

	for (int c = 0; c < C; c++) {
		cin >> H >> W;
		for (int h = 0; h < H; h++) 
			for (int w = 0; w < W; w++) 
				cin >> board[h][w];
			
		filled = 0;
		for (int h = 0; h < H; h++) {
			for (int w = 0; w < W; w++) {
				if (board[h][w] == '#')
					filled++;
			}
		}

		if (filled == W*H || (W*H - filled)% 3 != 0) {
			cout << 0 << endl;
			continue;
		}
		
		cout << solve() << endl;
	}
	return 0;
}

