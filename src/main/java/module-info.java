module com.mycompany.mylibrarysys {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;
    requires jbcrypt;

    opens com.mycompany.mylibrarysys to javafx.fxml;
    exports com.mycompany.mylibrarysys;
    exports com.mycompany.mylibrarysys.data;
    exports com.mycompany.mylibrarysys.model;
}
