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
				System.out.println("�������ϴ� ��ȭ �帣 ���������");
				System.out.println("1. ���� �����ϱ� \n2. ���� ��Ȳ����\n3. �׸� �̸� ����\n4. �ʱ�ȭ\n(���� : -1)");
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
					} catch (InputMismatchException e) {
						scan = new Scanner(System.in);
						System.out.println("������ �Է� �����մϴ�!");
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
						if (selnum == 0) {
							System.out.print("�Է�: ");
							other = scan.nextLine();
							od.insertSurvey(other);
							od.counter(other);
						} else {
							sd.counter(selnum);
						}
					}
					System.out.println("���� �Ϸ�!");
					System.out.println();
					break;
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
				loop3: while (true) {
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
						continue loop3;
					}
				}
				break;

			case 4:
				sd.init();
				System.out.println("�ʱ�ȭ �Ϸ�!");
				System.out.println();
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
