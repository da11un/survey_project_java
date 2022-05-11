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
				System.out.println("�������ϴ� ��ȭ �帣 ���������");
				System.out.println("1. ���� �����ϱ� \n2. ���� ��Ȳ����\n3. �׸� �̸� ����\n4. �ʱ�ȭ\n(����: -1)");
				System.out.print("����: ");
				num = scan.nextInt();
			} catch (InputMismatchException e) {
				scan = new Scanner(System.in);
				num = 0;
				System.out.println("�ٽ� �Է��ϼ���.(������ �Է� ����)");
				System.out.println();
				continue loop;
			}

			switch (num) {
			case 1:
				loop1: while (true) {
					try {
						System.out.println("=============");
						System.out.println("�� �ڷΰ���: -1");
						List<SurveyVo> ls = sd.selectAll();
						for (SurveyVo tmp : ls) {
							System.out.println(tmp.getNumber() + ". " + tmp.getList());
						}
						List<OtherVo> ls2 = od.selectOther();
						for (OtherVo tmp : ls2) {
							System.out.println(tmp);
						}
						System.out.print("����: ");
						selnum = scan.nextInt();
						scan.nextLine(); // ���� ó��
						if (selnum == -1) {
							continue loop;
						}
					} catch (InputMismatchException e) {
						scan = new Scanner(System.in);
						System.out.println("�ٽ� �Է��ϼ���.(������ �Է� ����)");
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
						System.out.println("�ش� ��ȣ�� �����ϴ�.");
						System.out.println();
						continue loop1;
					} else {
						List<SurveyVo> ls = sd.selectAll();
						if (selnum == 0) {
							while (true) {
								boolean check2 = false;
								System.out.print("�Է�: ");
								other = scan.nextLine();
								for (SurveyVo tmp : ls) {
									if (other.equals(tmp.getList())) {
										check2 = true;
									}
								}
								if(check2) {
									System.out.println("\"" + other + "\"" + "��(��) �̹� �����մϴ�.");
									continue;

								}else{
									od.insertSurvey(other);
									od.counter(other);
									break ;
								}
							}
						}
						sd.counter(selnum);
						System.out.println("���� �Ϸ�!");
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
				System.out.println("���� ��ǥ ��: " + sd.sumVote());
				System.out.println();
				break;

			case 3:
				ArrayList<SurveyVo> al = sd.allList();
				scan.nextLine();
				while (true) {
					System.out.print("������ �׸� �̸�: ");
					list = scan.nextLine();
					boolean check = false;
					for (SurveyVo tmp : al) {
						if ((list.equals(tmp.getList())))
							check = true;
					}
					if (check) {
						System.out.print("���� ����: ");
						input = scan.nextLine();
						sd.modifyList(input, list);
						System.out.println("����Ǿ����ϴ�!");
						System.out.println();
						break;
					} else {
						System.out.println("�ش� �׸��� ����!\n�ٽ� �Է��ϼ���.");
						continue;
					}
				}
				break;

			case 4:
				loop5: while (true) {
					System.out.println("���� �ʱ�ȭ �Ͻðڽ��ϱ�? Y/N");
					boolean check = false;
					String select = scan.next();
					if (select.equals("y") || select.equals("n")) {
						System.out.println("�빮�ڷ� �Է����ּ���.");
						System.out.println();
						continue loop5;
					}
					if (select.equals("Y")) {
						check = true;
					} else if (select.equals("N")) {
						check = false;
					} else {
						System.out.println("�ùٸ��� �Է����ּ���!");
						System.out.println();
						continue loop5;
					}
					if (check) {
						sd.init();
						System.out.println("�ʱ�ȭ �Ϸ�!");
						System.out.println();
						break;
					} else {
						System.out.println("�ʱ�ȭ�� ����մϴ�..");
						System.out.println();
						continue loop;
					}
				}
				break;

			case -1:
				flag = true;
				break;

			default:
				System.out.println("�ش� ��ȣ�� �����ϴ�.");
				System.out.println();
				break;
			}

		}

		System.out.println("�������簡 ����Ǿ����ϴ�.");
	}
}
