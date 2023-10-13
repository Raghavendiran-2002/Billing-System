import java.sql.*;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.Scanner;

// To Compile Code
// javac -classpath ../Zoho/mysql-connector-j-8.1.0/mysql-connector-j-8.1.0.jar MysqlCon.java
// java -classpath ../Zoho/mysql-connector-j-8.1.0/mysql-connector-j-8.1.0.jar MysqlCon.java

class Main {

  private static final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
  static int vendorID = 2;

  public static void main(String[] arg) {
    try {
      Class.forName(DRIVER_CLASS);
      Connection con = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/mysql",
        "root",
        "honda4104"
      );
      //here sonoo is database name, root is username and password
      Statement stmt = con.createStatement();

      Random rand = new Random();
      Scanner sc = new Scanner(System.in);

      while (true) {
        System.out.println(
          "1 : Create Vendor \n2 : Show Vendors\n3 : Add Bills to the Vendor\n4 : Show Bills\n"
        );

        int choice = sc.nextInt();
        switch (choice) {
          case 1:
            System.out.println("Vendor Name : ");
            String vendorName = sc.next();
            System.out.println("Vendor UPI : ");
            String UPI = sc.next();
            System.out.println("Vendor AccountDetails : ");
            String accountDetails = sc.next();
            String mysqlQuery =
              "insert into vendor values (" +
              vendorID +
              ", '" +
              vendorName +
              "','" +
              UPI +
              "','" +
              accountDetails +
              "');";
            PreparedStatement ps = con.prepareStatement(mysqlQuery);
            ps.execute();
            vendorID++;
            System.out.println("------------------Vendor Added---------------");
            break;
          case 2:
            System.out.println("***********************************");

            ResultSet rs = stmt.executeQuery("select * from vendor");
            while (rs.next()) System.out.println(
              "Vendor ID : " +
              rs.getString(1) +
              " VendorName : " +
              rs.getString(2)
            );
            System.out.println("***********************************");
            break;
          // case 3:
          //   System.out.println("Vendor ID : ");
          //   int vID = sc.nextInt();
          //   LocalDateTime billData = LocalDateTime.now();
          //   System.out.println("Amount : ");

          //   int amount = sc.nextInt();

          //   company.billsList.add(new Bills(vID, amount, amount, billData));
          //   break;
          // case 4:
          //   for (Bills i : company.billsList) {
          //     System.out.println(
          //       "Bill ID : " +
          //       rand.nextInt(10) +
          //       " Vendor ID : " +
          //       i.vendorID +
          //       " Amount : " +
          //       i.amount
          //     );
          //   }
          //   break;
          default:
            break;
        }
        // con.close();
      }
    } catch (Exception e) {
      System.out.println(e);
    }
  }
}

class Vendor {

  static int vendorCount = 1;
  int vendorID;
  String vendorName;
  String UPI;
  String accountDetails;

  Vendor(String vendorName, String UPI, String accountDetails) {
    vendorID = vendorCount;
    this.vendorName = vendorName;
    this.UPI = UPI;
    this.accountDetails = accountDetails;
    vendorCount++;
  }
}
