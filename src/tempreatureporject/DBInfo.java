/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempreatureporject;

public interface DBInfo {

    String DB_NAME = "jdbc:mysql://localhost:3306/temperature_project";

    String ENCODING = "?useUnicode=yes&characterEncoding=UTF-8&verifyServerCertificate=false&useSSL=false";

    String DB_NAME_WITH_ENCODING = DB_NAME + ENCODING;

    String USER = "root";

    String PASSWORD = "";

}
