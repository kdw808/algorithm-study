
def bitmask_to_list(b):
    ret = []
    i = 0
    while b:
        if b & 1:
            ret.append(i)
        i += 1
        b >>= 1
    return ret

def combination_in_bitmask(int_list, combination_count):
    if combination_count > len(int_list):
        yield(list_to_bitmask(int_list))
    else:
        for i in range(len(int_list)):
            if combination_count == 1:
                yield 1 << i
            else:
                for next in combination_in_bitmask(int_list[i+1:], combination_count - 1):
                    yield (1 << int_list[i]) | next
    
def solve(semester, prerequisite, min_course_count, max_course_per_semester, remaining_course):
    if remaining_course == 0:
        return 0
    if len(semester) == 0:
        return 'IMPOSSIBLE'

    available = [i for i in bitmask_to_list(semester[0] & remaining_course) if (prerequisite[i] & remaining_course) == 0]
    
    ans = 'IMPOSSIBLE'  # string > int in Python
    for this_semester_course in combination_in_bitmask(available, max_course_per_semester):
        ans = min(solve(semester[1:], prerequisite, min_course_count, max_course_per_semester, remaining_course & ~this_semester_course), ans)
    if ans != 'IMPOSSIBLE':
        ans += 1
    return ans

def read_int_list():
    return map(int, raw_input().split())

def list_to_bitmask(int_list):
    ret = 0
    for i in int_list:
        ret |= (1 << i)
    return ret

def read_n_line_input(n):
    ret = []
    for i in range(n):
        ret.append(list_to_bitmask(read_int_list()[1:]))
    return ret

for c in range(input()):
    course_count, required_course_count, semester_count, max_course_per_semester = read_int_list()
    prerequisite = read_n_line_input(course_count)
    semester = read_n_line_input(semester_count)
    print solve(semester, prerequisite, required_course_count, max_course_per_semester, (1 << course_count) - 1)

'''

2
4 4 4 4
0
1 0
3 0 1 3
0
4 0 1 2 3 
4 0 1 2 3
3 0 1 3
4 0 1 2 3
4 2 2 4
1 1
0
1 3
1 2
3 0 2 3
3 1 2 3

3
IMPOSSIBLE'''