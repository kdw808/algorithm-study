#if 0 // O(NH)?
#include <stdio.h>

int left[10001];
int max;
int last;
int i;

inline void check(int height)
{
    for (int j = last + 1; j <= height; j++)
        left[j] = i;
    for (int j = height + 1; j <= last; j++)
    {
        int size = j * (i - left[j]);
        if (size > max)
            max = size;
    }
    last = height;
}

int main()
{
    int C, N, height;
    for (scanf("%d", &C); C; C--)
    {
        max = 0;
        last = 0;
        scanf("%d", &N);
        for (i = 0; i < N; i++)
        {
            scanf("%d", &height);
            check(height);
        }
        check(0);
        printf("%d\n", max);
    }
}
#else O(N)?
#include <cstdio>
#include <stack>
using namespace std;

struct Board {
    int pos;
    int height;
    Board(int p, int h) : pos(p), height(h) {}
};

stack<Board> boardlist;
int max_size;

void add(int pos, int height)
{
    int left = pos;
    while (height < boardlist.top().height)
    {
        left = boardlist.top().pos;
        max_size = max(boardlist.top().height * (pos - left), max_size);
        boardlist.pop();
    }
    if (height > boardlist.top().height)
        boardlist.push(Board(left, height));
}

int main()
{
    int C, N, height;
    boardlist.push(Board(0, 0));
    for (scanf("%d", &C); C; C--)
    {
        max_size = 0;
        scanf("%d", &N);
        for (int i = 0; i < N; i++)
        {
            scanf("%d", &height);
            add(i, height);
        }
        add(N, 0);
        printf("%d\n", max_size);
    }
}
#endif
