def f(n)

	if n == 2
		return 2
	end

	if n == 1 
		return 1
	end

	if $result[n] != 0
		return $result[n]
	end

	return $result[n] = f(n-1) + f(n-2)
end

n = gets

n = n.to_i

$result = Array.new(n+1, 0)

count = f(n) % 1000000007

puts count
