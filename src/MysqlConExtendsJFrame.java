import java.sql.*;

import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.swing.*;

public class MysqlConExtendsJFrame{
    JLabel JL_book_title,JL_book_author,JL_pub_year,JL_book_id;
    JTextField JT_book_title,JT_book_author,JT_pub_year,JT_book_id;
    JButton btn_insert,btn_update,btn_delete;
    public class MysqlCon () {
        super("INSERT UPDATE DELETE");
        JL_book_id = new JLabel("Book ID:");
        JL_book_title = new JLabel("Book Title:");
        JL_book_author = new JLabel("Book Author:");
        JL_pub_year = new JLabel("Publish Year:");
        JL_book_id.setBounds(20, 20, 100, 20);
        JL_book_title.setBounds(20, 50, 100, 20);
        JL_book_author.setBounds(20, 80, 100, 20);
        JL_pub_year.setBounds(20, 110, 100, 20);

        JT_book_id = new JTextField(20);
        JT_book_title = new JTextField(20);
        JT_book_author = new JTextField(20);
        JT_pub_year = new JTextField(20);
        JT_book_id.setBounds(130,20,150,20);
        JT_book_title.setBounds(130, 50, 150, 20);
        JT_book_author.setBounds(130, 80, 150, 20);
        JT_pub_year.setBounds(130, 110, 150, 20);

        btn_insert = new JButton("Insert");
        btn_update = new JButton("Update");
        btn_delete = new JButton("Delete");
        btn_insert.setBounds(300, 50, 80, 20);
        btn_update.setBounds(300, 80, 80, 20);
        btn_delete.setBounds(300, 110, 80, 20);


        setLayout(null);
        add(JL_book_id);
        add(JL_book_title);
        add(JL_book_author);
        add(JL_pub_year);

        add(JT_book_id);
        add(JT_book_title);
        add(JT_book_author);
        add(JT_pub_year);

        add(btn_insert);
        add(btn_update);
        add(btn_delete);



        //button insert
        btn_insert.addActionListener(new  ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try{
                    theQuery("insert into books (book_title,author,pub_year) values('"+JT_book_title.getText()+"','"+JT_book_author.getText()+"',"+JT_pub_year.getText()+")");
                }
                catch(Exception ex){}
            }
        });

        //button update
        btn_update.addActionListener(new  ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try{

                    theQuery("update books set book_title = '"+JT_book_title.getText()+"',author = '"+JT_book_author.getText()+"', pub_year = "+JT_pub_year.getText()+" where book_id = "+JT_book_id.getText());
                }
                catch(Exception ex){}
            }
        });

        //button delete
        btn_delete.addActionListener(new  ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try{

                    theQuery("delete from books where id = "+JT_book_id.getText());
                }
                catch(Exception ex){}
            }
        });


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        setSize(500,200);

    }



    //function to execute the insert update delete query
    public void theQuery(String query){
        Connection con = null;
        Statement st = null;
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/booksdb","root","Workspace@123");
            st = con.createStatement();
            st.executeUpdate(query);
            JOptionPane.showMessageDialog(null,"Query Executed");
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex.getMessage());
        }
    }


    public static void main(String[] args){

        new  MysqlCon();
    }
}