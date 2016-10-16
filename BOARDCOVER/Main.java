import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

	public enum BlockType {
		BLACK('#'),
		WHITE('.'),
		BLOCK('X');

		private char value;

		BlockType(char value) {
			this.value = value;
		}

		public char getValue() {
			return this.value;
		}
	}

	// 입력받은 보드판
	static List<BlockType[][]> boards = new ArrayList<>();
	/**
	 * ┐ , ┘, └, ┌
	 * 블럭의 상대경로
	 */
	static final int[][][] blockLocation = {{{0, 0}, {0, 1}, {1, 1}}, {{0, 0}, {1, 0}, {1, -1}}, {{0, 0}, {1, 0}, {1, 1}}, {{0, 0}, {0, 1}, {1, 0}}};

	public static void main(String[] args) {

		try (Scanner sc = new Scanner(System.in)) {
			// 첫번쨰줄
			String first = sc.nextLine();
			int repeatCount = Integer.parseInt(first);

			if (repeatCount > 30) {
				throw new InputMismatchException();
			}

			while (repeatCount-- > 0) {
				// 두번째줄
				String second = sc.nextLine();
				String[] splited = second.split(" ");
				int length = splited.length;

				if (length != 2) {
					throw new InputMismatchException();
				}

				int height = Integer.valueOf(splited[0]);
				int width = Integer.valueOf(splited[1]);
				if (height < 1 || height > 20 || width < 1 || width > 20) {
					throw new InputMismatchException();
				}

				// 세번째줄 - 보드판을 만든다.
				String[] boardLines = new String[height];
				for (int i = 0; i < height; i++) {
					boardLines[i] = sc.nextLine();
				}

				generateBoard(boardLines, width, height);
			}

			// 계산하자.
			int results[] = new int[boards.size()];

			// 입력받은 보드수만큼 loop
			for (int i = 0; i < boards.size(); i++) {
				BlockType[][] blockTypes = boards.get(i);

				if (!isThreeTimes(blockTypes)) {
					results[i] = 0;
					continue;
				}

				// 계산 후 결과저장
				results[i] = calculate(blockTypes, 0, 0, 0);

				// 출력
				printResults(results);
			}
		} catch (InputMismatchException | NumberFormatException e) {
			e.printStackTrace();
			System.err.println("first : 숫자만, 정수 30이하");
			System.err.println("second : 정수 두개(높이 폭), 공백으로 구분, 1보다크고 20보다 작아야함");
			System.err.println("third : H(높이)만큼 #(검정), .(흰) 바둑판을 만든다.(높이랑 폭이 맞아야한다, 1 <= 흰칸수 <= 50)");
		} finally {
			System.exit(-1);
		}
	}

	private static void printResults(int results[]) {
		for (int result : results) {
			System.out.println(result);
		}
	}

	// 흰색이 3이상 && 3의배수일경우 true
	private static boolean isThreeTimes(BlockType[][] blockTypes) {
		int height = blockTypes.length;
		int width = blockTypes[0].length;
		int whiteCount = 0;

		for (int h = 0; h < height; h++) {
			for (int w = 0; w < width; w++) {
				BlockType currentType = blockTypes[h][w];
				//하나라도 흰색이 있으면 리턴
				if (currentType == BlockType.WHITE) {
					whiteCount++;
				}
			}
		}

		if (whiteCount >= 3 && whiteCount % 3 == 0) {
			return true;
		} else {
			return false;
		}
	}

	// 보드판에 블록을 쌓는다.
	// resursive
	private static int calculate(BlockType[][] blockTypes, int offSetH, int offSetW, int result) {
		int height = blockTypes.length;
		int width = blockTypes[0].length;

		if (offSetW == width && offSetH <= height - 1) {
			//offSetW가 W랑 같으면 offSetH + 1, offSetW = 0
			offSetH = offSetH + 1;
			offSetW = 0;
		}

		//offSetW >= width, offSetH == height 일 경우(마지막 지점) 블럭이 채워졌는지 확인한다.
		if (offSetW >= width - 1 && offSetH >= height - 1) {
			//마지막 위치
			//블럭이 다 채워졌는지 확인하고 채워졌으면 result++

			int blockCount = 0;

			for (int h = 0; h < height; h++) {
				for (int w = 0; w < width; w++) {
					BlockType currentType = blockTypes[h][w];
					//하나라도 흰색이 있으면 리턴
					if (currentType == BlockType.WHITE) {
						break;
					} else if (currentType == BlockType.BLOCK) {
						blockCount++;
					}
				}
			}

			//블럭카운트가 3의 배수이고 블럭이 3개 이상일경우
			if (blockCount % 3 == 0 && blockCount >= 3) {
				result = result + 1;
			}
		} else {
			//위치가 끝이 아닐우우
			BlockType curr = blockTypes[offSetH][offSetW];

			if (curr == BlockType.BLACK || curr == BlockType.BLOCK) {
				result = calculate(blockTypes, offSetH, offSetW + 1, result);
			} else {
				// 블럭을 넣는다.
				// 블럭 종류별로 loop(4개)
				for (int i = 0; i < blockLocation.length; i++) {
					int oneBlock[][] = blockLocation[i];

					boolean canInsert = true;

					//하나의 블록 점 3번 LOOP
					for (int blockCnt = 0; i < oneBlock.length; blockCnt++) {
						int widthHeight[] = oneBlock[blockCnt];
						int blockWidth = widthHeight[0] + offSetW;
						int blockHeight = widthHeight[1] + offSetH;

						//상대위치 블록 폭 + 현재 폭 위치는 width보다 작아야한다.
						//상대위치 블록 높이 + 현재 높이 위치는 height보다 작아야한다.
						if (blockWidth < width && blockHeight < height) {
							if (blockTypes[blockHeight][blockWidth] != BlockType.WHITE) {
								//흰색이 아니면 false;
								canInsert = false;
								break;
							}

						} else {
							//3개블록중 하나라도 높이, 폭 범위를 벗어나면 빠져나간다.
							canInsert = false;
							break;
						}
					}

					if (canInsert) {
						//블록을 채운다.
						for (int blockCnt = 0; i < oneBlock.length; i++) {
							int widthHeight[] = oneBlock[blockCnt];
							int blockWidth = widthHeight[0] + offSetW;
							int blockHeight = widthHeight[1] + offSetH;
							blockTypes[blockHeight][blockWidth] = BlockType.BLOCK;
						}
					}

					result = calculate(blockTypes, offSetH, offSetW + 1, result);
				}
			}
		}

		return result;
	}

	// 보드판을 만든다.
	private static void generateBoard(String[] boardLines, int width, int height) throws InputMismatchException {
		BlockType[][] blockTypes = new BlockType[height][width];

		// 높이
		for (int h = 0; h < height; h++) {
			String currentLine = boardLines[h];

			char[] block = currentLine.toCharArray();

			// 두번째 줄 width과 실제 보드판의 넓이는 같아야함
			if (currentLine.length() != width) {
				throw new InputMismatchException();
			}

			for (int w = 0; w < width; w++) {
				// '# .'만 입력 받아야함
				if (!(block[w] == BlockType.BLACK.getValue() || block[w] == BlockType.WHITE.getValue())) {
					throw new InputMismatchException();
				}

				blockTypes[h][w] = block[w] == BlockType.BLACK.getValue() ? BlockType.BLACK : BlockType.WHITE;
			}
		}

		boards.add(blockTypes);
	}

	public static void printInputBoard(BlockType[][] blockTypes) {
		System.out.println("입력받은 보드판(로그)");
		for (int h = 0; h < blockTypes.length; h++) {
			for (int w = 0; w < blockTypes[h].length; w++) {
				System.out.print(blockTypes[h][w].getValue());
			}
			System.out.print("\n");
		}
	}


}
