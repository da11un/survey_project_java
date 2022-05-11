package project_survey;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		SurveyDao sd = new SurveyDao();
		OtherDao od = new OtherDao();

		int num = 0;
		int selnum = 0;
		String other = "";
		String input = "";
		String list = "";
		boolean flag = false;
		loop: while (!(flag)) {
			try {
				System.out.println();
				System.out.println("★좋아하는 영화 장르 설문조사★");
				System.out.println("1. 설문 참여하기 \n2. 설문 현황보기\n3. 항목 이름 변경\n4. 초기화\n(종료: -1)");
				System.out.print("선택: ");
				num = scan.nextInt();
			} catch (InputMismatchException e) {
				scan = new Scanner(System.in);
				num = 0;
				System.out.println("다시 입력하세요.(정수만 입력 가능)");
				System.out.println();
				continue loop;
			}

			switch (num) {
			case 1:
				loop1: while (true) {
					try {
						System.out.println("=============");
						System.out.println("◀ 뒤로가기: -1");
						List<SurveyVo> ls = sd.selectAll();
						for (SurveyVo tmp : ls) {
							System.out.println(tmp.getNumber() + ". " + tmp.getList());
						}
						List<OtherVo> ls2 = od.selectOther();
						for (OtherVo tmp : ls2) {
							System.out.println(tmp);
						}
						System.out.print("선택: ");
						selnum = scan.nextInt();
						scan.nextLine(); // 엔터 처리
						if (selnum == -1) {
							continue loop;
						}
					} catch (InputMismatchException e) {
						scan = new Scanner(System.in);
						System.out.println("다시 입력하세요.(정수만 입력 가능)");
						System.out.println();
						continue loop1;
					}
					List<SurveyVo> ch = sd.selectAll();
					boolean check = false;
					loop2: for (SurveyVo sv : ch) {
						if (selnum != sv.getNumber()) {
							check = false;
						} else {
							check = true;
							break loop2;
						}
						if (selnum == 0) {
							check = true;
						}
					}
					if (!check) {
						System.out.println("해당 번호가 없습니다.");
						System.out.println();
						continue loop1;
					} else {
						List<SurveyVo> ls = sd.selectAll();
						if (selnum == 0) {
							while (true) {
								boolean check2 = false;
								System.out.print("입력: ");
								other = scan.nextLine();
								for (SurveyVo tmp : ls) {
									if (other.equals(tmp.getList())) {
										check2 = true;
									}
								}
								if(check2) {
									System.out.println("\"" + other + "\"" + "이(가) 이미 존재합니다.");
									continue;

								}else{
									od.insertSurvey(other);
									od.counter(other);
									break ;
								}
							}
						}
						sd.counter(selnum);
						System.out.println("설문 완료!");
						System.out.println();
						break;

					}
				}
				break;

			case 2:
				System.out.println("=============");
				List<SurveyVo> ls3 = sd.selectAll();
				for (SurveyVo tmp : ls3) {
					System.out.println(tmp);
				}
				System.out.println();
				System.out.println("※총 투표 수: " + sd.sumVote());
				System.out.println();
				break;

			case 3:
				ArrayList<SurveyVo> al = sd.allList();
				scan.nextLine();
				while (true) {
					System.out.print("변경할 항목 이름: ");
					list = scan.nextLine();
					boolean check = false;
					for (SurveyVo tmp : al) {
						if ((list.equals(tmp.getList())))
							check = true;
					}
					if (check) {
						System.out.print("변경 내용: ");
						input = scan.nextLine();
						sd.modifyList(input, list);
						System.out.println("변경되었습니다!");
						System.out.println();
						break;
					} else {
						System.out.println("해당 항목이 없음!\n다시 입력하세요.");
						continue;
					}
				}
				break;

			case 4:
				loop5: while (true) {
					System.out.println("정말 초기화 하시겠습니까? Y/N");
					boolean check = false;
					String select = scan.next();
					if (select.equals("y") || select.equals("n")) {
						System.out.println("대문자로 입력해주세요.");
						System.out.println();
						continue loop5;
					}
					if (select.equals("Y")) {
						check = true;
					} else if (select.equals("N")) {
						check = false;
					} else {
						System.out.println("올바르게 입력해주세요!");
						System.out.println();
						continue loop5;
					}
					if (check) {
						sd.init();
						System.out.println("초기화 완료!");
						System.out.println();
						break;
					} else {
						System.out.println("초기화를 취소합니다..");
						System.out.println();
						continue loop;
					}
				}
				break;

			case -1:
				flag = true;
				break;

			default:
				System.out.println("해당 번호가 없습니다.");
				System.out.println();
				break;
			}

		}

		System.out.println("설문조사가 종료되었습니다.");
	}
}
