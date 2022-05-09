package project_survey;

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
		while (num != -1) {
			System.out.println("�������ϴ� ��ȭ �帣 ���������");
			System.out.println("1. ���� �����ϱ� \n2. ���� ��Ȳ����\n(���� : -1)");
			System.out.print("����: ");
			num = scan.nextInt();
			switch (num) {
			case 1:
				List<SurveyVo> ls = sd.selectAll();
				for (SurveyVo tmp : ls) {
					System.out.println(tmp.getNumber() + ". " +tmp.getList());
				}
				List<OtherVo> ls2 = od.selectOther();
				for (OtherVo tmp : ls2) {
					System.out.println(tmp);
				}
				System.out.print("����: ");
				selnum = scan.nextInt();
				sd.counter(selnum);
				if (selnum == 0) {
					System.out.print("�Է�: ");
					other = scan.next();
					od.insertSurvey(other);
					od.counter(other);
				}
				System.out.println("���� �Ϸ�!");
				System.out.println();
				break;

			case 2:
				List<SurveyVo> ls3 = sd.selectAll();
				for (SurveyVo tmp : ls3) {
					System.out.println(tmp);
				}
				System.out.println();
				System.out.println("���� ��ǥ ��: " + sd.sumVote());
				System.out.println();
			}

		}
		System.out.println("�������簡 ����Ǿ����ϴ�.");
	}

}
