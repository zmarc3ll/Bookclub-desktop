package com.example.bookclubdesktop;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Statisztika {
  private static List<Member> members;


  public static void run() {
    try {
      readMembersFromDb();
      System.out.printf("Kitiltott tagok száma: %d\n", countBanedMembers());
      System.out.printf("%s a tagok között 18 évnél fiatalabb személy. \n", isYoungerThan18Present()? "Van" : "Nincs");
      Member oldest = getOldest();
      System.out.printf("Legidősebb tag: %s (%s)\n", oldest.getName(), oldest.getBirth_date());
      printMemberCount();
    } catch (SQLException e) {
      System.out.println("Nem sikerult kapcsolodni az alkalmazáshoz, az alkalmazás leáll!");
      System.out.println(e.getMessage());
    }
  }

  private static void printMemberCount() {
    Map<String, Integer> genderCount =  new HashMap<>();

  }

  private static Member getOldest() {
    Member oldest = members.get(0);
    for (int i = 1; i < members.size(); i++) {
      if (oldest.getBirth_date().isAfter(members.get(i).getBirth_date())) {
        oldest = members.get(i);
      }
    }
    return  oldest;
  }

  private static boolean isYoungerThan18Present() {
    int index = 0;
    while (index < members.size() &&
      (Period.between(members.get(index).getBirth_date(), LocalDate.now()).getYears()<18) ) {
      index++;
    }
    return index < members.size();
  }

  private static int countBanedMembers() {
    int count = 0;
    for(Member member: members) {
      if (member.isBanned()) {
        count++;
      }
    }
    return  count;
  }

  private static void readMembersFromDb() throws SQLException {
    DBHelper db = new DBHelper();
    members = db.readMembers();
  }
}
