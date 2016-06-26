#include <iostream>
#include <algorithm>

using namespace std;

#define FENCE_MAX 20000

struct _fence
{
	int x;
	int h;
};

_fence fence[FENCE_MAX];
_fence fence_ordered_by_h[FENCE_MAX];

int N;

int main()
{
	int C;
	cin >> C;

	for (int c = 0; c < C; c++) {

		// read problem
		cin >> N;
		for (int i = 0; i < N; i++) {
			fence[i].x = i;
			cin >> fence[i].h;
		}

		copy(&fence[0], &fence[N], &fence_ordered_by_h[0]);
		sort(&fence_ordered_by_h[0], &fence_ordered_by_h[N], [](const _fence& lhs, const _fence& rhs) {
			return lhs.h < rhs.h;
		});
 
		
		int area_max = 0;
		for (int i = 0; i < N; i++) {
			_fence f = fence_ordered_by_h[i];

			int left;
			for (left = f.x-1; left >= 0; left--) {
				if (fence[left].h < f.h) {
					break;
				}
			}
			
			int right;
			for (right = f.x+1; right < N; right++) {
				if (fence[right].h < f.h) {
					break;
				}
			}

			int area = (right - left-1)*f.h;
			if (area_max < area)
				area_max = area;
		}

		cout << area_max << endl;
	}

	return 0;
}