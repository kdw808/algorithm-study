
$count = 0
$width = 0
$mid = 0
	

def check_finish(tail)

	# $count = $count + 1

	for y in 0..1
		for x in 0..$mid
			if tail[y][x] != tail[y][$width-x]
				$count = $count + 1
				return
			end
		end
	end
end

def remove_right_block(y,x,tail)

	if x + 1 >  $width
		return false
	end

	 tail[y][x] = nil
	 tail[y][x+1] = nil
	
end

def remove_down_block(y,x,tail)

	if y + 1 > 1 or x >  $width
		return false
	end

	tail[y][x] = nil
	tail[y+1][x] = nil
	
end

def fill_right_block(y,x,  tail)

	if x + 1 > $width
		return false
	end

	 tail[y][x] = true
	 tail[y][x+1] = true

	if y == 1 and x+1 == $width
		check_finish(tail)
	end
	
	if y == 0
		fill_block(y+1,x,tail)
	else
		fill_block(y-1,x+2,tail)
	end

end

def fill_down_block(y,x,tail)

	if y + 1 > 1 or x > $width
		return false
	end
	
	 tail[y][x] = false
	 tail[y+1][x] = false

	if y+1 == 1 and x == $width
		check_finish(tail)
	end

	if y == 0 and x < $width
		fill_block(y,x+1,tail)
	end
end

def fill_block(y,x, tail)

	fill_right_block(y,x,tail)

	remove_right_block(y,x,tail)


	fill_down_block(y,x,tail)

	remove_down_block(y,x,tail)
	
end

n = gets

tail = [Array.new(n.to_i, nil),Array.new(n.to_i, nil) ]

$width = tail[0].length - 1
$mid = $width / 2

fill_block(0,0, tail)
puts $count