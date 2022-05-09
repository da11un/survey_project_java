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
			System.out.println("★좋아하는 영화 장르 설문조사★");
			System.out.println("1. 설문 참여하기 \n2. 설문 현황보기\n(종료 : -1)");
			System.out.print("선택: ");
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
				System.out.print("선택: ");
				selnum = scan.nextInt();
				sd.counter(selnum);
				if (selnum == 0) {
					System.out.print("입력: ");
					other = scan.next();
					od.insertSurvey(other);
					od.counter(other);
				}
				System.out.println("설문 완료!");
				System.out.println();
				break;

			case 2:
				List<SurveyVo> ls3 = sd.selectAll();
				for (SurveyVo tmp : ls3) {
					System.out.println(tmp);
				}
				System.out.println();
				System.out.println("※총 투표 수: " + sd.sumVote());
				System.out.println();
			}

		}
		System.out.println("설문조사가 종료되었습니다.");
	}

}
