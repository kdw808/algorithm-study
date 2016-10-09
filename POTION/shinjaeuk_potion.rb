# 주어진 약의 비율의 최소 비율로 찾는다.
# 최소 비율을 찾으려면 제일 작은 수로 나누고, 나워지지 않으면 그 수의 최대 공약수 부터 나눠질 때까지 찾는다.
# 약의 최소 비율이 냄비 속의 비율보다 큰 수가 되도록 비율을 늘려본다 ( 1배, 2배, 3배...)
# 냄비 속의 비율 보다 크면 약의 비율과 냄비의 비율을 차이를 구한다.

def can_divide(potions, n)

	potions.each do |i|

		if i % n != 0
			return false
		end
	end

	return true
end

def get_min_rate(potions, min_potion)

	if min_potion == 1

		return potions

	end

	can_divide = can_divide(potions, min_potion)

	if can_divide == false

		max_divisor = max_divisor(min_potion)
		get_min_rate(potions, max_divisor)

	elsif can_divide == true

		return potions.map! { |x| x / min_potion }

	end
end

def max_divisor(n)

	for x in 1..(n-1)

		if n % (n-x) == 0

			return (n-x)
		end
	end
end

def compare(arr1, arr2)

	arr1.each_with_index do |val, index|

		if val < arr2[index]

			return true
		end
	end

	return false
end

def diff(arr1, arr2)

	diff = []

	arr1.each_with_index do |val, index|

		d = val - arr2[index]
		diff.push(d)

	end

	return diff
end

def get_more_potion(minimum_rate, pot_rate)

	multi = 1

	begin
		new_rate = minimum_rate.map { |x| x * multi }

		multi += 1

	end while compare(new_rate, pot_rate)

	return diff(new_rate, pot_rate)

end

round = gets.to_i

arr_result = []

for i in 1..round
	
	number = gets

	potion_rate = gets.split(" ").collect{|i| i.to_i}
	pot_rate = gets.split(" ").collect{|i| i.to_i}

	min_potion = potion_rate.min
	min_potion_rate = get_min_rate(potion_rate, min_potion)

	p = get_more_potion(min_potion_rate, pot_rate)

	arr_result.push(p)
end

arr_result.each do |arr|
	arr.each do |val|
		print val, " "
	end
	puts ""
end