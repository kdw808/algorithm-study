# 풀이
# 
# 1. 문자열을 쿼드트리로 만든다. -> make_node()
#
# 2. 쿼드트리의 모든 1,3 노드와 2,4 노드를 위치를 변경하다. 뒤집는 효과 -> change_node()
#
# 3. 쿼드트리를 문자열로 만든다. -> make_string()

class Node

	attr_accessor :data, :one, :two, :three, :four, :parent

	def initialize(data)
		@data = data
	end
end

$result = []

def make_sring(node)

	if node == nil
		return
	end

	$result.push(node.data)

	make_sring(node.one)
	make_sring(node.two)
	make_sring(node.three)
	make_sring(node.four)

end

def change_node(node)

	if node == nil
		return
	end

	@tmp

	tmp = node.one
	node.one = node.three
	node.three = tmp

	tmp = node.two
	node.two = node.four
	node.four = tmp

	change_node(node.one)
	change_node(node.two)
	change_node(node.three)
	change_node(node.four)
end

def make_node(current_node)

	if $quad == ""
		return
	end

	if current_node.one == nil

			char = $quad.slice(0,1)

			node = current_node.one = Node.new(char)

			$quad.slice!(0,1)

			if char == "x"
				make_node(node)
			end
	end

	if current_node.two == nil

			char = $quad.slice(0,1)

			node = current_node.two = Node.new(char)

			$quad.slice!(0,1)

			if char == "x"
				make_node(node)
			end
	end

	if current_node.three == nil

			char = $quad.slice(0,1)

			node = current_node.three = Node.new(char)

			$quad.slice!(0,1)
			
			if char == "x"
				make_node(node)
			end
	end

	if current_node.four == nil

			char = $quad.slice(0,1)

			node = current_node.four = Node.new(char)

			$quad.slice!(0,1)

			if char == "x"
				make_node(node)
			end
	end
end


def make_result(string)

	if string.slice(0,1) != "x"

		puts string.slice(0,1)

	else

		root = Node.new(string.slice(0,1))

		$quad = string.slice!(1,string.length)

		make_node(root)

		change_node(root)

		make_sring(root)

		puts $result.join("")

	end
end

count = gets

str_list = []

for c in 0..count.to_i-1
	string = gets
	str_list.push(string)
end

str_list.each do |one_line|
	$result = []
	make_result(one_line)
end